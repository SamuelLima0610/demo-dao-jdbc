package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		DepartmentDao daoDepartment = DaoFactory.createDepartmentDao();
		System.out.println("\n-------------- Teste Department: Insert --------------");
		Department department = new Department(null,"Movies");
		daoDepartment.insert(department);
		System.out.println("Inserted. id = " + department.getId());
		System.out.println("\n-------------- Teste Department: Update --------------");
		department.setName("Games");
		department.setId(6);
		daoDepartment.update(department);
		System.out.println("Update complete");
		System.out.println("\n-------------- Teste Department: Delete --------------");
		daoDepartment.deleteById(7);
		System.out.println("Delete complete");
		System.out.println("\n-------------- Teste Department: FindById --------------");
		department = daoDepartment.findById(6);
		System.out.println(department);
		System.out.println("\n-------------- Teste Department: FindAll --------------");
		List<Department> departments = daoDepartment.findAll();
		for(Department dep : departments) {
			System.out.println(dep);
		}
	}

}
