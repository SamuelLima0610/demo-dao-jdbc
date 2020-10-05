package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		// Seller
		SellerDao daoSeller = DaoFactory.createSellerDao();
		Seller seller = daoSeller.findById(3);
		System.out.println("-------------- Test Seller: findById--------------");
		System.out.println(seller);
		Department department = new Department(2,null);
		List<Seller> sellers = daoSeller.findByDepartment(department);
		System.out.println("\n-------------- Test Seller: findByDepartment--------------");
		for(Seller sel: sellers) {
			System.out.println(sel);
		}
		sellers = daoSeller.findAll();
		System.out.println("\n-------------- Test Seller: findAll--------------");
		for(Seller sel: sellers) {
			System.out.println(sel);
		}
		System.out.println("\n-------------- Teste Seller: Insert --------------");
		Seller newSeller = new Seller(null,"Cris","cris@mail.com",new Date(), 4000.0,department);
		daoSeller.insert(newSeller);
		System.out.println("Inserted new id = " + newSeller.getId());
		System.out.println("\n-------------- Teste Seller: Update --------------");
		seller = daoSeller.findById(1);
		seller.setName("Marta Wayne");
		daoSeller.update(seller);
		System.out.println("Update complete");
		System.out.println("\n-------------- Teste Seller: Delete --------------");
		daoSeller.deleteById(12);
		System.out.println("Delete complete");
	}

}
