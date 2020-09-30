package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Department dep = new Department(1,"Technology");
		Seller sel = new Seller(21,"Samuel","sam@mail.com",new Date(),3000.0,dep);
		System.out.println(dep);
		System.out.println(sel);
	}

}
