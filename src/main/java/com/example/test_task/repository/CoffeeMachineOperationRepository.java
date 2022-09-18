package com.example.test_task.repository;

import com.example.test_task.model.CoffeeMachineOperation;
import com.example.test_task.utils.CoffeeTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeMachineOperationRepository extends JpaRepository<CoffeeMachineOperation, Long> {

    Iterable<CoffeeMachineOperation> findCoffeeMachineOperationByType(CoffeeTypes type);
}
