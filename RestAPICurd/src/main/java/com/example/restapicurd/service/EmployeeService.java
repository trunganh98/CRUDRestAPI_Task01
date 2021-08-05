package com.example.restapicurd.service;

import com.example.restapicurd.entity.Employee;

import java.util.List;

public interface EmployeeService {

	public List<Employee> getEmployees();
	
	public Employee getEmployee(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
}
