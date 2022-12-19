package dao;

import exceptions.DepartmentException;
import exceptions.EmployeeException;
import models.Department;

public interface AdminDAO {
public String addNewDepartment(Department dept) throws DepartmentException;
	
	public String transferEmpToOtherDept(int empId, int deptId) throws DepartmentException, EmployeeException;
	
	public String registerNewEmployee(Department dept) throws DepartmentException;
	

}
