package com.example.test_task.service;

import com.example.test_task.dto.CoffeeMachineOperationDto;
import com.example.test_task.model.CoffeeMachine;
import com.example.test_task.model.CoffeeMachineOperation;
import com.example.test_task.utils.CoffeeTypes;

public interface CoffeeMachineService {

    String turnOnCoffeeMachine(CoffeeMachine coffeeMachine);

    String turnOffCoffeeMachine(CoffeeMachine coffeeMachine);

    String createNewOperation(CoffeeMachineOperationDto coffeeMachineOperationDto, CoffeeMachine coffeeMachine);

    CoffeeMachineOperation getOperationById(Long id);

    Iterable<CoffeeMachineOperation> getOperationsByCoffeeType(CoffeeTypes type);

    Iterable<CoffeeMachineOperation> getAll();

    void deleteById(Long id);

    void deleteByCoffeeType(CoffeeTypes type);

    void deleteAll();
}
