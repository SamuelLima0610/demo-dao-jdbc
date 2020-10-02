package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(int id) {
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			pst = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName\r\n" + 
					"FROM seller INNER JOIN department\r\n" + 
					"ON seller.DepartmentId = department.Id\r\n" + 
					"WHERE seller.Id = ?;");
			pst.setInt(1, id);
			rst = pst.executeQuery();
			if(rst.next()) {
				Department dep = instantiateInstanceDepartment(rst);
				Seller seller = instantiateInstanceSeller(rst,dep);
				return seller;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rst);
			DB.closeStatment(pst);
		}
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement pst = null;
		ResultSet rst = null;
		try {
			pst = conn.prepareStatement("SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE DepartmentId = ? " + 
					"ORDER BY Name;");
			pst.setInt(1, department.getId());
			rst = pst.executeQuery();
			List<Seller> sellers = new ArrayList<>();
			Map<Integer,Department> map = new HashMap<>();
			while(rst.next()) {
				Department dep = map.get(rst.getInt("DepartmentId"));
				if(dep == null) {
					dep = instantiateInstanceDepartment(rst);
					map.put(rst.getInt("DepartmentId"), dep);
				}
				Seller seller = instantiateInstanceSeller(rst,dep);
				sellers.add(seller);
			}
			return sellers;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeResultSet(rst);
			DB.closeStatment(pst);
		}
	}
	
	private Department instantiateInstanceDepartment(ResultSet rst) throws SQLException {
		return new Department(rst.getInt("DepartmentId"),rst.getString("DepName"));
	}
	
	private Seller instantiateInstanceSeller(ResultSet rst, Department department) throws SQLException{
		return new Seller(rst.getInt("Id"),
				rst.getString("Name"),
				rst.getString("Email"),
				rst.getDate("BirthDate"),
				rst.getDouble("BaseSalary"),
				department);
	}

}
