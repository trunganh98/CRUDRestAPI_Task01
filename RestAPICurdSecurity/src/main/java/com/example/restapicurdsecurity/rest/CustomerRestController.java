package com.example.restapicurdsecurity.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.restapicurdsecurity.dao.EmployeeRepository;
import com.example.restapicurdsecurity.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    private EmployeeRepository repository;

    @Autowired
    public CustomerRestController(EmployeeRepository theCustomerRepository) {
        repository = theCustomerRepository;
    }

    @GetMapping("/emp")
    List<Employee> all() {
        return repository.findAll();
    }

    @PostMapping("/emp")
    Employee newEmployee(@RequestBody Employee newCustomer) {
        return repository.save(newCustomer);
    }

    @GetMapping("/emp/{id}")
    Employee one(@PathVariable Integer id) {
        return repository.findById(id).
                orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @DeleteMapping("/emp/{id}")
    void deleteEmployee(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PutMapping("/emp/{id}")
    Employee replaceCustomer(@RequestBody Employee newCustomer, @PathVariable Integer id) {

        return repository.findById(id)
                .map(customer -> {
                    customer.setFirstName(newCustomer.getFirstName());
                    customer.setLastName(newCustomer.getLastName());
                    customer.setEmail(newCustomer.getEmail());
                    return repository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return repository.save(newCustomer);
                });
    }
}
