package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SellerDao daoSeller = DaoFactory.createSellerDao();
		Seller seller = daoSeller.findById(3);
		System.out.println("-------------- Test findById--------------");
		System.out.println(seller);
		List<Seller> sellers = daoSeller.findByDepartment(new Department(2,null));
		System.out.println("\n-------------- Test findByDepartment--------------");
		for(Seller sel: sellers) {
			System.out.println(sel);
		}
		sellers = daoSeller.findAll();
		System.out.println("\n-------------- Test findAll--------------");
		for(Seller sel: sellers) {
			System.out.println(sel);
		}
	}

}
