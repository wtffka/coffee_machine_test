package com.example.test_task.repository;

import com.example.test_task.model.CoffeeMachineOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeMachineOperationRepository extends JpaRepository<CoffeeMachineOperation, Long> {

    List<CoffeeMachineOperation> findCoffeeMachineOperationByType(String type);
}
