package dao;

import exceptions.DepartmentException;
import exceptions.EmployeeException;
import models.EmpDeptDTO;

public interface EmpDeptDAO {
	
	public EmpDeptDTO getDeptHeadDetails(int deptId) throws DepartmentException,EmployeeException ;
	
}
