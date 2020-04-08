package com.wha.spring.jdbc.main;

import java.util.List;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wha.spring.jdbc.dao.CrudDAO;
import com.wha.spring.jdbc.model.Employee;

public class SpringMain {
	
	
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//testDao(ctx);
		
		testJdbcDao(ctx);
		
		ctx.close();	
	
	}

	private static void testDao(ClassPathXmlApplicationContext ctx) {
		CrudDAO employeeDAO = ctx.getBean("employeeDAO",CrudDAO.class);
		
		Employee emp = new Employee();
		int rand = new Random().nextInt(1000);
		emp.setId(rand);
		emp.setName("Alain");
		emp.setRole("Director");
					
		// Create Employee
		employeeDAO.save(emp);		
		
		List<Employee> list =employeeDAO.getAll();
		for(Employee e:list) {
			System.out.println("Employee [id="+e.getId()+"]");
		}
	}
	
	
	
	private static void testJdbcDao(ClassPathXmlApplicationContext ctx) {
		CrudDAO employeeDAO = ctx.getBean("employeeJdbcDAO",CrudDAO.class);
		
		Employee emp = new Employee();
		int rand = new Random().nextInt(1000);
		emp.setId(rand);
		emp.setName("Jean");
		emp.setRole("Tech");
					
		// Create Employee
		employeeDAO.save(emp);		
		
		List<Employee> list =employeeDAO.getAll();
		for(Employee e:list) {
			System.out.println("Employee [id="+e.getId()+"]");
		}
	}
	
}
