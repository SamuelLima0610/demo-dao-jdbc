package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{

	private Connection conn = null;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("INSERT INTO department\r\n" + 
					"(Name)\r\n" + 
					"VALUES\r\n" + 
					"(?);",PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getName());
			int rowsAffected = pst.executeUpdate();
			if(rowsAffected > 0) {
				ResultSet rst = null;
				rst = pst.getGeneratedKeys();
				if(rst.next()) {
					int id = rst.getInt(1);
					obj.setId(id);
				}
				rst.close();
			}
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatment(pst);
		}	
	}

	@Override
	public void update(Department obj) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("UPDATE department\r\n" + 
					"SET Name = ?\r\n" + 
					"WHERE Id = ?;");
			pst.setString(1, obj.getName());
			pst.setInt(2, obj.getId());
			pst.execute();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatment(pst);
		}
		
	}

	@Override
	public void deleteById(int id) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("DELETE FROM department\r\n" + 
					"WHERE Id = ?;");
			pst.setInt(1, id);
			pst.execute();
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatment(pst);
		}
	}

	@Override
	public Department findById(int id) {
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			pst = conn.prepareStatement("SELECT department.*\r\n" + 
					"FROM department\r\n" +  
					"WHERE department.Id = ?;");
			pst.setInt(1, id);
			rst = pst.executeQuery();
			if(rst.next()) {
				Department dep = instantiateInstanceDepartment(rst);
				return dep;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatment(pst);
			DB.closeResultSet(rst);
		}
	}

	@Override
	public List<Department> findAll() {
		Statement st = null;
		ResultSet rst = null;
		try {
			st = conn.createStatement();
			rst = st.executeQuery("SELECT * from department");
			List<Department> departments = new ArrayList<>();
			while(rst.next()) {
				Department dep = instantiateInstanceDepartment(rst);
				departments.add(dep);
			}
			return departments;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatment(st);
			DB.closeResultSet(rst);
		}
	}
	
	private Department instantiateInstanceDepartment(ResultSet rst) throws SQLException {
		return new Department(rst.getInt("Id"),rst.getString("Name"));
	}
}
