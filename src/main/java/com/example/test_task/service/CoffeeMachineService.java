package com.example.test_task.service;

import com.example.test_task.dto.CoffeeMachineOperationDto;
import com.example.test_task.model.CoffeeMachine;
import com.example.test_task.model.CoffeeMachineOperation;

import java.util.List;

public interface CoffeeMachineService {

    String turnOnOffCoffeeMachine(CoffeeMachine coffeeMachine);

    String createNewOperation(CoffeeMachineOperationDto coffeeMachineOperationDto);

    CoffeeMachineOperation getOperationById(Long id);

    List<CoffeeMachineOperation> getOperationsByCoffeeType(String type);

    List<CoffeeMachineOperation> getAll();

    void deleteById(Long id);

    void deleteByCoffeeType(String type);

    void deleteAll();
}
