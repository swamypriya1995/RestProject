package com.niit.RestController.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.niit.SpringProjectBackend.Model.*;
import com.niit.SpringProjectBackend.service.EmployeeService;

import java.util.*;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins="http://localhost:4200")																						
public class HomeRestController {
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping
	public ResponseEntity<List<Employee>> listEmployee()
	{
		List<Employee> emp=empService.listService();
		if(!emp.isEmpty())
		return new ResponseEntity<List<Employee>>(emp,HttpStatus.OK);
		else
		return new ResponseEntity<List<Employee>>(emp,HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping//("/{employeeid}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("employeeid") int empId) {
		
		if(empService.getEmployeeService(empId)!=null) {
			return new ResponseEntity<Employee>(empService.getEmployeeService(empId), HttpStatus.OK);
		}
		else 
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	
	
	
	@DeleteMapping("/{employeeid}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("employeeid") int employeeid) {
		
		if(empService.getEmployeeService(employeeid)!=null) {
			empService.deleteService(employeeid);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else 
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND); 
	}
	@PostMapping
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee)
	{
		if(empService.registerService(employee))
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping
	public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
		
		if(empService.getEmployeeService(employee.getEmployeeid())!=null) {
			empService.updateService(employee);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}


	}
	
	
}
