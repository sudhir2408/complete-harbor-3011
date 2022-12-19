package main;

import java.util.Scanner;

import models.Employee;
import usecase.AddNewDeptUsecase;
import usecase.AdminLoginUsecase;
import usecase.ChangePasswordUsecase;
import usecase.CreateLeaveRequestUsecase;
import usecase.EmployeeDeptTransferUsecase;
import usecase.EmployeeLoginUsecase;
import usecase.RegisterNewEmpUsecase;
import usecase.RespondToLeaveRequestUsecase;
import usecase.UpdateDeptUsecase;
import usecase.UpdateProfileUsecase;
import usecase.ViewAllDeptUsecase;
import usecase.ViewLeaveRequestStatusUsecase;
import usecase.ViewProfileUsecase;

public class Main {

		static Scanner sc = new Scanner(System.in);

		static void adminOrEmployee(Scanner sc) {
			System.out.println();
			System.out.println("welcome in corporate_hunter Choose from the below Options to start the : ");
			System.out.println("" + "\n" + " 1. Login As Admin          " + "\n"
					+ " 2. Login As Employee " + "\n" + " 3. Quit " + "\n"
					+ "");
			choice(sc);
		}

		static void choice(Scanner sc) {

			int choice = 0;
			try {
				choice = Integer.parseInt(sc.next());
			} catch (Exception e) {

				System.out.println("Your Choice must be a number");
			}

			if (choice == 1) {
				System.out.println("Welcome Admin in corporate_hunter ! Please Login to your account");
				AdminLogin();
			} else if (choice == 2) {
				System.out.println("Welcome employee !");
				employeeLogin();
			} else if (choice == 3) {
				System.out.println();
				System.out.println("***************************|CORPORATE_HUNTER WELCOMES YOU !!!  |**********************************");
				System.out.println();
				System.exit(0);
			} else if (choice < 1 || choice > 3) {
				System.out.println("Please choose a number from the options below");
				adminOrEmployee(sc);
			} else {
				System.out.println("Input type should be number");
			}

		}

		static void AdminLogin() {

			Boolean result = AdminLoginUsecase.AdminLogin(sc);

			if (result)
				adminFeatures();
			else {
				adminOrEmployee(sc);
			}
		}

		static void adminFeatures() {
			System.out.println();
			System.out.println("Choose From below Options to go furture : ");
			System.out.println("" + "\n"
					+ " Welcome Admin in corporate_hunter " + "\n" + " 1. Add New Department "
					+ "\n" + " 2. View All Departments  " + "\n"
					+ "3. Update Department  " + "\n" + " 4. Register New Employee "
					+ "\n" + "5. Transfer Employee to other Department " + "\n"
					+ "6. Respond To Leaves " + "\n"
					+ "7. Logout " + "\n"
					+ "8. Quit corporate_hunter " + "\n"
					+ "");

			int choice = 0;

			try {
				choice = Integer.parseInt(sc.next());
			} catch (Exception e) {

				System.out.println("Your Choice must be a number only");
			}

			if (choice < 1 || choice > 8) {
				System.out.println("Please choose a number from below options to furture");
				adminFeatures();
			} else {

				adminChoice(choice);
			}
		}

		static void adminChoice(int choice) {

			switch (choice) {
			case 1: {
				AddNewDeptUsecase.addNewDept(sc);
				adminFeatures();
			}
				break;

			case 2: {
				ViewAllDeptUsecase.viewAllDept();
				adminFeatures();
			}
				break;

			case 3: {
				ViewAllDeptUsecase.viewAllDept();
				System.out.println("Enter the Department Id of the Department you want to be Update : ");
				int deptId = sc.nextInt();
				UpdateDeptUsecase.updateDepartment(deptId, sc);
				adminFeatures();
			}
				break;

			case 4: {
				RegisterNewEmpUsecase.registerNewEmp(sc);
				adminFeatures();
			}
				break;

			case 5: {
				EmployeeDeptTransferUsecase.transferDept(sc);
				adminFeatures();
			}
				break;

			case 6: {
				RespondToLeaveRequestUsecase.respondToLeaveRequest(sc);
				adminFeatures();
			}
				break;

			case 7: {
				adminOrEmployee(sc);
			}
				break;
			case 8: {
				System.out.println();
				System.out.println("********************| **Corporate_hunter welcomes you** |******************");
				System.out.println("====== =============| *************App Closed***********|==================");
				System.exit(0);
				sc.close();
			}
				break;

			}
		}

		static void employeeLogin() {
			Employee emp = EmployeeLoginUsecase.empLogin(sc);

			if (emp == null) {
				adminOrEmployee(sc);
			} else {
				
				employeeFeatures(emp);
			}

		}

		static void employeeFeatures(Employee emp) {
			System.out.println();
			System.out.println("Choose from the below Options to go furture: ");
			System.out.println("++"  + "\n" + " 1. View Profile  " + "\n"
					+ " 2. Update Profile " + "\n" + " 3. Change Password  " + "\n"
					+ " 4. Apply for Leave " + "\n" + "5. View Leave Request Status" + "\n"
					+ " 6. View Leave Request History " + "\n" + " 7. Logout  " + "\n"
					+ " 8. Quit Corporate_hunter app  " + "\n" + "++");

			int choice = 0;
			try {
				choice = Integer.parseInt(sc.next());
			} catch (Exception e) {

				System.out.println("Your Choice must be a number");
			}
			if (choice < 1 || choice > 8) {
				System.out.println("Please choose a number from below options (Only enter the number, eg: 8,2)");
				employeeFeatures(emp);
			} else
				empChoice(choice, emp);
		}

//				

		static void empChoice(int choice, Employee emp) {
			switch (choice) {
			case 1: {
				ViewProfileUsecase.showEmpProfile(emp);
				employeeFeatures(emp);
			}
				break;
			case 2: {
				UpdateProfileUsecase.updateProfile(sc, emp);
				employeeFeatures(emp);
			}
				break;
			case 3: {
				ChangePasswordUsecase.changePassword(sc, emp);
				employeeFeatures(emp);
			}
				break;

			case 4: {
				CreateLeaveRequestUsecase.applyForLeave(sc, emp);
				employeeFeatures(emp);
			}
				break;

			case 5: {
				ViewLeaveRequestStatusUsecase.viewLeaveRequestStatus(emp);
				employeeFeatures(emp);
			}
				break;

			case 6: {
				ViewLeaveRequestStatusUsecase.viewLeaveRequestHistory(emp);
				employeeFeatures(emp);
			}
				break;

			case 7: {
				adminOrEmployee(sc);
			}
				break;
			case 8: {
				System.out.println();
				System.out.println("***************************| Corporate_hunter |********************");
				System.out.println("========================== |    App Closed    |=====================");
				System.exit(0);
				sc.close();
			}
			}
		}

		public static void main(String[] args) {
			System.out.println();
			System.out.println("**************************| Corporate_hunter welcomes You|**********************");
			System.out.println();
			adminOrEmployee(sc);
			System.out.println();
			System.out.println("**************************| Corporate_hunter welcomes You|**********************");
			System.out.println();
			sc.close();

		}

	}


