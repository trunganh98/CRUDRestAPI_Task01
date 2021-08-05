package com.example.restapicurd.service;

import com.example.restapicurd.dao.EmployeeRepository;
import com.example.restapicurd.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}

	@Override
	@Transactional
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	@Transactional
	public Employee getEmployee(int theId) {
		return employeeRepository.getById(theId);
	}

	@Override
	@Transactional
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}






