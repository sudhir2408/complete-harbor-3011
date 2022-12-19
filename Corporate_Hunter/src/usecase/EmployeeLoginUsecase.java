package usecase;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.EmpDAO;
import dao.EmpDAOImpl;
import exceptions.EmployeeException;
import models.Employee;

public class EmployeeLoginUsecase {

	public static Employee empLogin(Scanner sc) {
		
		Employee emp = null;
		try {
			
			
			System.out.println("Enter your email ");
			String email = sc.next();
			
			System.out.println("Enter your  Password");
			String password = sc.next();
			
			EmpDAO dao = new EmpDAOImpl();
			
			try {
				emp = dao.getEmpfromDB(email, password);
				System.out.println();
				System.out.println("***********| "+emp.getEname()+" Logged In Successfully !! welcome in corporate_hunter |**********");
				System.out.println();
			} catch (EmployeeException e) {
				
				System.out.println();
				System.out.println("*********| Oops !! something went wrong!! Wrong Password!! Try Again... |*************");
				System.out.println();
			}
		}
		catch (InputMismatchException e) {
			System.out.println( e.getMessage() );
		}
		
		return emp;
	}
}
