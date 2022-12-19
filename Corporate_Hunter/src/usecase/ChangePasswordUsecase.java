package usecase;

import java.util.Scanner;

import dao.EmpDAO;
import dao.EmpDAOImpl;
import exceptions.EmployeeException;
import models.Employee;

public class ChangePasswordUsecase {

	public static void changePassword(Scanner sc, Employee e) {
		
		System.out.println("Enter Old Password : ");
		String oldPass = sc.next();
		System.out.println("Enter New Password : ");
		String newPass = sc.next();
	 	
		
		EmpDAO dao = new EmpDAOImpl();
		
			try {
				String ans = dao.updateEmpPassword( newPass,oldPass,e);
				System.out.println();
				System.out.println("*****************| "+ans+" |*****************");
				System.out.println();
			
				if(ans.equals(" ****congratulation ! Password Updated Successfully !******")) {
					e.setPassword(newPass);
				}
				
				
			} catch (EmployeeException e1) {
//				e1.printStackTrace();
				System.out.println();
				System.out.println("*****something wrong **| Password NOT Updated! | **********");
				System.out.println();
				System.out.println();
				System.out.println("*********| "+e1.getMessage() +" |********************");
			
				
			}
		}
		
		
		
	}