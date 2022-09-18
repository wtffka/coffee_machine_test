package com.example.test_task.service.impl;

import com.example.test_task.dto.CoffeeMachineOperationDto;
import com.example.test_task.model.CoffeeMachine;
import com.example.test_task.model.CoffeeMachineOperation;
import com.example.test_task.repository.CoffeeMachineOperationRepository;
import com.example.test_task.service.CoffeeMachineService;
import com.example.test_task.utils.CoffeeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    private final CoffeeMachineOperationRepository coffeeMachineOperationRepository;

    @Autowired
    public CoffeeMachineServiceImpl (CoffeeMachineOperationRepository coffeeMachineOperationRepository) {
        this.coffeeMachineOperationRepository = coffeeMachineOperationRepository;
    }

    @Override
    public String turnOnCoffeeMachine(CoffeeMachine coffeeMachine) {
        if (!coffeeMachine.isOn()) {
            tuneAndSave(true, coffeeMachine);
            return "Coffee machine is on. You can brew coffee you want!";
        }
        return "Coffee machine is already on";
    }

    @Override
    public String turnOffCoffeeMachine(CoffeeMachine coffeeMachine) {
        if (coffeeMachine.isOn()) {
            tuneAndSave(false, coffeeMachine);
            return "Coffee machine is off. Turn on to brew coffee!";
        }
        return "Coffee machine is already off.";
    }

    private void tuneAndSave(boolean isOn, CoffeeMachine coffeeMachine) {
        CoffeeMachineOperation operation = new CoffeeMachineOperation();
        operation.setOn(isOn);
        coffeeMachine.setOn(isOn);
        coffeeMachineOperationRepository.save(operation);
    }

    @Override
    public String createNewOperation(CoffeeMachineOperationDto coffeeMachineOperationDto, CoffeeMachine coffeeMachine) {
        if (coffeeMachine.isOn()) return fromDto(coffeeMachineOperationDto).toString();
        return "Coffee machine is off. Turn on to brew coffee.";
    }

    @Override
    public CoffeeMachineOperation getOperationById(Long id) {
        return coffeeMachineOperationRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Iterable<CoffeeMachineOperation> getOperationsByCoffeeType(CoffeeTypes type) {
        return coffeeMachineOperationRepository.findCoffeeMachineOperationByType(type);
    }

    @Override
    public Iterable<CoffeeMachineOperation> getAll() {
        return coffeeMachineOperationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        coffeeMachineOperationRepository.deleteById(id);
    }

    @Override
    public void deleteByCoffeeType(CoffeeTypes type) {
        Iterable<CoffeeMachineOperation> operationsByCoffeeType = getOperationsByCoffeeType(type);
        coffeeMachineOperationRepository.deleteAllInBatch(operationsByCoffeeType);
    }

    @Override
    public void deleteAll() {
        coffeeMachineOperationRepository.deleteAll();
    }

    private CoffeeMachineOperation fromDto (CoffeeMachineOperationDto coffeeMachineOperationDto) {
        CoffeeMachineOperation operation = new CoffeeMachineOperation();
        operation.setType(CoffeeTypes.valueOf(coffeeMachineOperationDto.getType().toUpperCase()));
        operation.setLaunchedAt(LocalDateTime.now().withNano(0));
        return coffeeMachineOperationRepository.save(operation);
    }
}
