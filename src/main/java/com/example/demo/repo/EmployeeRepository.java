package com.example.demo.repo;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Serializable> {
    
	@Query("select e.salary from EmployeeEntity e")
	List<Object> getSalaryColumn();
	
	/*
	 * @Query("select e.salary, e.name from EmployeeEntity e") List<Object>
	 * getSalaryEmployeeColumn();
	 */
	
	
	public Optional<EmployeeEntity> findByEmpId(Integer empId);
	
}
