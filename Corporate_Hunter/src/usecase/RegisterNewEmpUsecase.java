package usecase;

import java.util.Scanner;

import dao.EmpDAO;
import dao.EmpDAOImpl;
import exceptions.EmployeeException;
import models.Employee;

public class RegisterNewEmpUsecase {

	public static void registerNewEmp(Scanner sc) {
		Employee emp = null;
		try {
			System.out.println("Enter Employee Name to register: ");
			sc.nextLine();
			String name = sc.nextLine();
			
			System.out.println("Enter Department ID of Employee to register:");
			int deptId = sc.nextInt();
			System.out.println("Enter Employee's Email to register: ");
			String email = sc.next();
			System.out.println("Set Employee's Temporary Initial Password to register: ");
			String pass = sc.next();
			System.out.println("Enter Employee's Salary to register : ");
			int sal = sc.nextInt();
			emp = new Employee( name,  deptId,  email,  pass, sal);
		} catch (Exception e) {
			System.out.println();
			System.out.println("***Invalid Input Type! please check it once ***.");
			System.out.println();
			registerNewEmp( sc);
		}
		
		
	
		
		EmpDAO dao = new EmpDAOImpl();
		
		try {
			String ans =  dao.registerEmployee(emp);
			System.out.println();
			System.out.println("***********| " + ans + " |**************");
			System.out.println();
		} catch (EmployeeException e) {
			System.out.println();
			System.out.println("***************| " + e.getMessage() + " |************");
			System.out.println();
			System.out.println("want to Try Again then enter  (y/n)");
			String a = sc.next();
			if(a.equals("y")) {
				registerNewEmp(sc);
			}
		}
	}
}
