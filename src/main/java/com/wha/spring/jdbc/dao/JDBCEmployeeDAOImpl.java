package com.wha.spring.jdbc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.wha.spring.jdbc.model.Employee;

public class JDBCEmployeeDAOImpl implements CrudDAO{
	
	
	private JdbcTemplate jdbcTemplate;
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	
	public void save(Employee employee) {
		String sql = "insert into Employee (id,name,role) values (?,?,?)  ";
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql,new Object[] {employee.getId(),employee.getName(),employee.getRole()});
		
	}



	public List<Employee> getAll() {
		String sql="select * from  Employee  ";
		jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String,Object>>   rows = jdbcTemplate.queryForList(sql);
		
		List<Employee> list = new ArrayList<Employee>();
		for(Map row:rows) {
			Employee employee = new Employee();
			employee.setId((Integer) row.get("id"));
			employee.setName( (String)    row.get("name"));
			employee.setRole( (String)    row.get("role"));
			list.add(employee);
		}
		
		
		return list;
	}
	
	
	public Employee getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
