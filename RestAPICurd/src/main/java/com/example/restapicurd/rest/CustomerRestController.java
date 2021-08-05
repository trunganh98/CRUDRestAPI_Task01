package com.example.restapicurd.rest;

import com.example.restapicurd.dao.EmployeeRepository;
import com.example.restapicurd.entity.Employee;
import com.example.restapicurd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/emp/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.getEmployee(employeeId);
                if (theEmployee == null) {
                    throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
                }
                return theEmployee;
    }

    @PostMapping("/emp")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        theEmployee.setId(0);
        employeeService.save(theEmployee);
        return theEmployee;
    }

    @PutMapping("/emp")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        employeeService.save(theEmployee);
        return theEmployee;
    }

    @DeleteMapping("/emp/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.getEmployee(employeeId);
        if (tempEmployee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }

}
