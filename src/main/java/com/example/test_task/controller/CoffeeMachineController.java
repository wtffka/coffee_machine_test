package com.example.test_task.controller;

import com.example.test_task.dto.CoffeeMachineDto;
import com.example.test_task.dto.CoffeeMachineOperationDto;
import com.example.test_task.model.CoffeeMachine;
import com.example.test_task.service.impl.CoffeeMachineServiceImpl;
import com.example.test_task.utils.CoffeeTypes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CoffeeMachineController {

    private static CoffeeMachine coffeeMachine = new CoffeeMachine(false);

    private final CoffeeMachineServiceImpl coffeeMachineService;


    @Autowired
    public CoffeeMachineController(CoffeeMachineServiceImpl coffeeMachineService) {
        this.coffeeMachineService = coffeeMachineService;
    }

    @Operation(summary = "Find out coffee machine is turned on")
    @ApiResponse(responseCode = "200", description = "Coffee machine status shown")
    @GetMapping("/status")
    public String coffeeMachineStatus() {
        return coffeeMachine.isOn() ? "Coffee machine is on" : "Coffee machine is off" ;
    }

    @Operation(summary = "Turn on/off coffee machine if needed")
    @ApiResponse(responseCode = "200", description = "Coffee machine is turned on/off")
    @PostMapping("/launch")
    @ResponseStatus(HttpStatus.OK)
    public String switchCoffeeMachine(@Parameter(description = "Data to turn on/off coffee machine", required = true)
                                      @RequestBody CoffeeMachineDto coffeeMachineDto) {
        if (coffeeMachineDto.getIsOn().equalsIgnoreCase("true")) {
            return coffeeMachineService.turnOnCoffeeMachine(coffeeMachine);
        }
        return coffeeMachineService.turnOffCoffeeMachine(coffeeMachine);
    }

    @Operation(summary = "Get all coffee machine operations")
    @ApiResponse(responseCode = "200", description = "All coffee machine operations shown")
    @GetMapping("/operations")
    public String getAllOperations() {
        return coffeeMachineService.getAll().toString();
    }


    @Operation(summary = "Get coffee machine operation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coffee machine operation found"),
            @ApiResponse(responseCode = "404", description = "Coffee machine operation not found")
    })
    @GetMapping("/operations/{id}")
    public String getOperationById(@PathVariable Long id) {
        return coffeeMachineService.getOperationById(id).toString();
    }


    @Operation(summary = "Get coffee machine operations by coffee type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coffee machine operations with that type found"),
            @ApiResponse(responseCode = "404", description = "Coffee machine operations with that type not found")
    })
    @GetMapping("/operations/type/{type}")
    public String getOperationsByCoffeeType(@PathVariable String type) {
        return coffeeMachineService.getOperationsByCoffeeType(CoffeeTypes.valueOf(type.toUpperCase())).toString();
    }


    @Operation(summary = "Create new coffee machine operation")
    @ApiResponse(responseCode = "200", description = "New operation created if coffee machine is turned on")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public String createOperation (@RequestBody CoffeeMachineOperationDto coffeeMachineOperationDto) {
        return coffeeMachineService.createNewOperation(coffeeMachineOperationDto, coffeeMachine);
    }

    @Operation(summary = "Delete coffee machine operation by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Operation with that id not found")
    })
    @DeleteMapping("/operations/{id}")
    public void deleteOperationById(@PathVariable Long id) {
        coffeeMachineService.deleteById(id);
    }


    @Operation(summary = "Delete coffee machine operations by coffee type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operations deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Operations with that coffee type not found")
    })
    @DeleteMapping("/operations/type/{type}")
    public void deleteOperationByCoffeeType(@PathVariable String type) {
        coffeeMachineService.deleteByCoffeeType(CoffeeTypes.valueOf(type.toUpperCase()));
    }


    @Operation(summary = "Delete all coffee machine operations")
    @ApiResponse(responseCode = "200", description = "All operations were deleted")
    @DeleteMapping("/operations")
    public void deleteAllOperations() {
        coffeeMachineService.deleteAll();
    }
}
