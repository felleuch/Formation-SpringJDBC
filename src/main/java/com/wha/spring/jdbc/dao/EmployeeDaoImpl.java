package com.wha.spring.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.wha.spring.jdbc.model.Employee;

public class EmployeeDaoImpl implements CrudDAO{
	
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Employee employee) {
		String query="insert into Employee (id,name,role) values (?,?,?)  ";
		Connection con = null;
		
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, employee.getId());
			ps.setString(2,employee.getName());
			ps.setString(3,employee.getRole());
			
			int out = ps.executeUpdate();
			if(out!=0) {
				System.out.println("Employee saved wwith id = "+employee.getId());
			}else {
				System.out.println("Employee save failed with id = "+employee.getId());
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}					
	}

	public Employee getById(int id) {
		String query="select name,role from  Employee where id= ? ";
		Connection con = null;
		ResultSet rs = null;
		Employee emp=null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			
			
			rs = ps.executeQuery();
			if(rs.next()) {
				emp = new Employee();
				emp.setId(id);
				emp.setName(rs.getString("name"));
			}else {
				System.out.println("Employee save failed with id = "+id);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}		
		return emp;
	}

	public void update(Employee employee) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	public List<Employee> getAll() {

		String query="select id,name,role from  Employee  ";
		Connection con = null;
		ResultSet rs = null;
		 List<Employee> list = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);	
			rs = ps.executeQuery();
			list = new ArrayList<Employee>();
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setRole(rs.getString("role"));
				list.add(emp);
			}			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}		
		return list;
	
	}

}
