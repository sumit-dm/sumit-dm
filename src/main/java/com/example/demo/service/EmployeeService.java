package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.model.Employee;

@Service
public interface EmployeeService {

	public boolean saveEmployeeDetails(Employee employee);
	
	
	public List<Employee> getAllEmpl();
	
    public Object findEmployeeById(Integer id);
    
    public List<EmployeeEntity> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy); 
    
    public Employee updateEmployee(Integer empId,Employee employee);
    
}
/* also refer java techie for streamer api for java 8 features 
 * List<Object> salaryName=employeeRepo.getSalaryEmployeeColumn();
 * //https://www.logicbig.com/tutorials/spring-framework/spring-data/limiting-
 * query-results.html //refer this for all these top 3 and all...
 */