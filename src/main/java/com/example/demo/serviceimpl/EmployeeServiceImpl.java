package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.exceptions.RecordNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public boolean saveEmployeeDetails(Employee employee) {

		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeEntity);
		EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
		return savedEmployeeEntity.getId() != null;
	}

	@Override
	public Object findEmployeeById(Integer id) {

		Optional<EmployeeEntity> optional = employeeRepository.findByEmpId(id);

		if (optional.isPresent()) {
			
			EmployeeEntity emplEntity = optional.get();
			Employee e = new Employee();
			BeanUtils.copyProperties(emplEntity, e);
			return e;
		} else {
			// throw new RecordNotFoundException("No record Found");
			return "Form Not Filled";
		}

	}

	@Override
	public List<Employee> getAllEmpl() {

		List<EmployeeEntity> allEntityList = employeeRepository.findAll();
		List<Employee> list = new ArrayList<>();
		allEntityList.forEach(oftheentitypresentinAllentitieslist -> {

			Employee e = new Employee();
			BeanUtils.copyProperties(oftheentitypresentinAllentitieslist, e);
			list.add(e);

		});

		return list;
	}

	@Override
	public List<EmployeeEntity> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy) {

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		Page<EmployeeEntity> pagedResult = employeeRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<EmployeeEntity>();
		}

	}

	@Override
	public Employee updateEmployee(Integer empId, Employee employee) {

		Optional<EmployeeEntity> optional = employeeRepository.findByEmpId(empId);

		if (optional.isPresent()) {
			EmployeeEntity emplEntity = optional.get();
			emplEntity.setEmpId(employee.getEmpId());
			emplEntity.setName(employee.getName());
			emplEntity.setSalary(employee.getSalary());

			EmployeeEntity updatedEmployeeEntity = employeeRepository.save(emplEntity);
			Employee updatedEmployeeModel = new Employee();
			BeanUtils.copyProperties(updatedEmployeeEntity, updatedEmployeeModel);
			return updatedEmployeeModel;
		}

		return null;
	}

}
