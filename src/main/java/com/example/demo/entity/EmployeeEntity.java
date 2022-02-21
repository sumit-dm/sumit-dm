package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EmployeeDetails")
public class EmployeeEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TableID")
	private Integer id;
	
	@Column(name="EmployeeID")
	private Integer empId;
	
	@Column(name="EmployeeName")
	private String name;
	
	@Column(name="EmployeeSalary")
	private Double salary;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	
}
