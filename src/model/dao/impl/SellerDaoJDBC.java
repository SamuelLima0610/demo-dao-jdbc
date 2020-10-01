package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
				Department dep = new Department(rst.getInt("DepartmentId"),rst.getString("DepName"));
				Seller seller = new Seller(
						rst.getInt("Id"),
						rst.getString("Name"),
						rst.getString("Email"),
						rst.getDate("BirthDate"),
						rst.getDouble("BaseSalary"),
						dep);
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

}
