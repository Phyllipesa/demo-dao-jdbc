package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.Scanner;

public class Program2 {
  public static void main(String[] args) {
    DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

    Scanner sc = new Scanner(System.in);
    System.out.println("=== TEST 1: department insert ===");
    Department newDepartment = new Department(null, "Food");
    departmentDao.insert(newDepartment);
    System.out.println("Inserted! new id = " + newDepartment.getId());

    System.out.println("\n=== TEST 2: department update ===");
    newDepartment.setId(6);
    newDepartment.setName("Beverage");
    departmentDao.update(newDepartment);
    System.out.println("Update completed!");

    System.out.println("\n=== TEST 3: department delete ===");
    System.out.print("Enter id for delete test: ");
    int id = sc.nextInt();
    departmentDao.deleteById(id);
    System.out.println("Delete completed!");

    System.out.println("\n=== TEST 4: department findById ===");
    System.out.print("Enter id to search: ");
    id = sc.nextInt();
    Department dep = departmentDao.findById(id);
    System.out.println(dep);
  }
}
