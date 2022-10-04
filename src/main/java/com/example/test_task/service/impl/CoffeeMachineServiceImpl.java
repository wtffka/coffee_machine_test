package com.example.test_task.service.impl;

import com.example.test_task.dto.CoffeeMachineOperationDto;
import com.example.test_task.model.CoffeeMachine;
import com.example.test_task.model.CoffeeMachineOperation;
import com.example.test_task.repository.CoffeeMachineOperationRepository;
import com.example.test_task.service.CoffeeMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CoffeeMachineServiceImpl implements CoffeeMachineService {

    private final CoffeeMachineOperationRepository coffeeMachineOperationRepository;

    @Autowired
    public CoffeeMachineServiceImpl (CoffeeMachineOperationRepository coffeeMachineOperationRepository) {
        this.coffeeMachineOperationRepository = coffeeMachineOperationRepository;
    }

    @Override
    public String turnOnOffCoffeeMachine(CoffeeMachine coffeeMachine) {
        if (coffeeMachine.isOn()) {
            tuneAndSave(false, coffeeMachine);
            return "Coffee machine is off. Turn on to brew coffee!";
        }
        tuneAndSave(true, coffeeMachine);
        return "Coffee machine is on. You can brew coffee you want!";
    }

    private void tuneAndSave(boolean isOn, CoffeeMachine coffeeMachine) {
        CoffeeMachineOperation operation = new CoffeeMachineOperation();
        operation.setLaunchedAt(LocalDateTime.now().withNano(0));
        operation.setOn(isOn);
        coffeeMachine.setOn(isOn);
        coffeeMachineOperationRepository.save(operation);
    }

    @Override
    public String createNewOperation(CoffeeMachineOperationDto coffeeMachineOperationDto) {
        return fromDto(coffeeMachineOperationDto).toString();
    }

    @Override
    public CoffeeMachineOperation getOperationById(Long id) {
        return coffeeMachineOperationRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<CoffeeMachineOperation> getOperationsByCoffeeType(String type) {
        return coffeeMachineOperationRepository.findCoffeeMachineOperationByType(type);
    }

    @Override
    public List<CoffeeMachineOperation> getAll() {
        return coffeeMachineOperationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        coffeeMachineOperationRepository.deleteById(id);
    }

    @Override
    public void deleteByCoffeeType(String type) {
        List<CoffeeMachineOperation> operationsByCoffeeType = getOperationsByCoffeeType(type);
        coffeeMachineOperationRepository.deleteAllInBatch(operationsByCoffeeType);
    }

    @Override
    public void deleteAll() {
        coffeeMachineOperationRepository.deleteAll();
    }

    private CoffeeMachineOperation fromDto (CoffeeMachineOperationDto coffeeMachineOperationDto) {
        CoffeeMachineOperation operation = new CoffeeMachineOperation();
        operation.setOn(true);
        operation.setType(coffeeMachineOperationDto.getType().toUpperCase());
        operation.setLaunchedAt(LocalDateTime.now().withNano(0));
        return coffeeMachineOperationRepository.save(operation);
    }
}
