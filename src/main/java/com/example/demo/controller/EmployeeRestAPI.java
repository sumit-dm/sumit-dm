package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.model.Employee;
import com.example.demo.model.ResponseMessage;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/Employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeRestAPI {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeRepository employeeRepo;

	@PostMapping("/saveEmployeeDetails")
	public ResponseEntity<ResponseMessage<String>> addEmployeeDetails(@RequestBody Employee employee) {

		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		Integer empId = employee.getEmpId();

		Object result1 = employeeService.findEmployeeById(empId);

		if (result1.equals("Form Not Filled")) {
			boolean result = employeeService.saveEmployeeDetails(employee);
			if (result) {
				responseMessage.setData(employee.toString());
				responseMessage.setStatus(1);
				responseMessage.setMessage("success");
				return ResponseEntity.ok().body(responseMessage);
			} else {
				responseMessage.setData(employee.toString());
				responseMessage.setStatus(1);
				responseMessage.setMessage("error in saving employee object");
				return ResponseEntity.ok().body(responseMessage);
			}

		} else {

			responseMessage = new ResponseMessage<>();
			responseMessage.setData(employee.toString());
			responseMessage.setMessage("FormAlreadyFilled");
			responseMessage.setStatus(0);
			return ResponseEntity.ok().body(responseMessage);

		}

	}
	
	@GetMapping(value = "/getAllEmployees")
	public ResponseEntity<ResponseMessage<String>> getAllEmployees() {

		List<Employee> allEmpl = employeeService.getAllEmpl();
		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		responseMessage.setData(allEmpl.toString());
		responseMessage.setStatus(1);
		responseMessage.setMessage("success");

		return ResponseEntity.ok().body(responseMessage);
	}


	@GetMapping(value = "/getEmployeeById/{empId}")
	public ResponseEntity<ResponseMessage<Object>> getEmployeeById(@PathVariable("empId") Integer empId) {

		// Employee e = (Employee) employeeService.findEmployeeById(empId); ok no issue
		// with this
		Object result = employeeService.findEmployeeById(empId); // but this looks sensible, thats it.
		ResponseMessage<Object> responseMessage = new ResponseMessage<>();
		responseMessage.setData(result);
		if (result.equals("Form Not Filled")) {
			responseMessage.setMessage("No Employee Present With This EmpId");
		} else {
			responseMessage.setMessage("Form is Already Filled by Employee");
		}
		responseMessage.setStatus(1);
		return ResponseEntity.ok().body(responseMessage);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@GetMapping("/highestSalary")
	public ResponseEntity<ResponseMessage<String>> getHighestSalary() {

		List<Object> salaries = employeeRepo.getSalaryColumn();
		System.out.println(salaries);

		Object maxSalary = (Object) salaries.stream().max((i1, i2) -> ((Double) i1).compareTo((Double) i2)).get();

		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		responseMessage.setData(maxSalary.toString());
		responseMessage.setStatus(1);
		responseMessage.setMessage("success");

		return ResponseEntity.ok().body(responseMessage);

	}

	@GetMapping(value = "/getNoOfEmployeeSalaryGreaterthan/{salary}")
	public ResponseEntity<ResponseMessage<String>> getNoOfEmplWhoseSalaryGreaterThan(
			@PathVariable("salary") Double salary) {

		List<Object> salaries = employeeRepo.getSalaryColumn();

		Long noOfEmployees = salaries.stream().filter(s -> (Double) s > salary).count();

		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		responseMessage.setData(noOfEmployees.toString());
		responseMessage.setStatus(1);
		responseMessage.setMessage("success");

		return ResponseEntity.ok().body(responseMessage);

	}

	
	@GetMapping(value = "/getAllEmployeesByPagingSorting")
	public ResponseEntity<ResponseMessage<List<EmployeeEntity>>> getAllEmployeesPagingAndSorting(
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "3") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {

		List<EmployeeEntity> list = employeeService.getAllEmployees(pageNo, pageSize, sortBy);
		ResponseMessage<List<EmployeeEntity>> responseMessage = new ResponseMessage<>();
		responseMessage.setData(list);
		responseMessage.setStatus(1);
		responseMessage.setMessage("success");

		return ResponseEntity.ok().body(responseMessage);

	}

	
	@PostMapping(value = "/updateEmployee/{empId}")
	public ResponseEntity<ResponseMessage<String>> updateEmployee(@PathVariable("empId") Integer empId,
			@RequestBody Employee employee) {

		Object result1 = employeeService.findEmployeeById(empId);
		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		if (!result1.equals("Form Not Filled")) {
			Employee result = employeeService.updateEmployee(empId, employee);
			if (result != null) {
				responseMessage.setData(employee.toString());
				responseMessage.setStatus(1);
				responseMessage.setMessage("success");
				return ResponseEntity.ok().body(responseMessage);
			} else {
				responseMessage.setData(employee.toString());
				responseMessage.setStatus(1);
				responseMessage.setMessage("error in saving employee object");
				return ResponseEntity.ok().body(responseMessage);
			}

		} else {
			responseMessage.setData(employee.toString());
			responseMessage.setStatus(1);
			responseMessage.setMessage("No employee exist with this EmployeeId: " + employee.getEmpId());
			return ResponseEntity.ok().body(responseMessage);

		}
	}	
	
	
	
	
	
	
	
}
