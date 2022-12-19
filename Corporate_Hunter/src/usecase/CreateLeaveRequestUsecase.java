package usecase;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dao.LeaveDAO;
import dao.LeaveDAOImpl;
import exceptions.LeaveException;
import models.Employee;
import models.Leave;

public class CreateLeaveRequestUsecase {

	public static void applyForLeave(Scanner sc, Employee emp) {
		
		
		System.out.println("**Enter Leave start Date must be in dd-MM-yyyy pattern only ! please check before submitt**");
		String from= sc.next();
		System.out.println("**Enter Leave end Date must be in dd-MM-yyyy pattern only ! please check before submitt**");
		String till= sc.next();
		sc.nextLine();
		System.out.println("Enter the Reason of Leave (in <=120 characters)| ! please check before submitt");
		String reason= sc.nextLine();
		
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate startdate = null;
		LocalDate enddate = null;
		try {
			 startdate = LocalDate.parse(from, dtf);
			from = startdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}catch (Exception e){
			
			System.out.println();
			System.out.println("********* something wrong **| Please pass the leave Start date in proper format***|************");
			System.out.println();

		
		}
		try {
			 enddate = LocalDate.parse(till, dtf);
			 if(startdate.compareTo(enddate)<0) { //checking if start date comes before end date or not...
				 till = enddate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				 int noOfDays = Period.between(startdate, enddate).getDays();
				 
				 Leave leave = new Leave(emp.getEid(),emp.getEname(), emp.getDeptId(), from, till,reason);
					
					LeaveDAO dao = new LeaveDAOImpl();
					try {
						String ans = dao.addLeavetoDB(leave, noOfDays);
						System.out.println();
						System.out.println("**********| "+ans+" |***************");
						System.out.println();
						if(ans.equals("great !!***Leave Applied and remaining leaves updated Successfully!!******")) {
							emp.setLeavesLeft(emp.getLeavesLeft() - noOfDays);
						}
						
					} catch (LeaveException e) {
						
						System.out.println();
						System.out.println("*********| "+e.getMessage() +" |***************");
						System.out.println();
					}
			 }
			 else {
				    System.out.println();
					System.out.println("***************| Start Date cannot come after end Date! please check it once |**************");
					System.out.println();
				 
			 }
			
		}catch (Exception e){
		
			System.out.println();
			System.out.println("***************| Please pass the Leave End date in proper format. please check it once |************");
			System.out.println();
			}
		
		
		
		
		}
	}
