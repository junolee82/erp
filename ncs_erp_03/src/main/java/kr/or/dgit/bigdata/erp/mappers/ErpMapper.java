package kr.or.dgit.bigdata.erp.mappers;

import java.util.List;

import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Employee;
import kr.or.dgit.bigdata.erp.dto.Title;

public interface ErpMapper {
	List<Employee> getEmpList();

	List<Title> getTitleList();

	List<Department> getDepartmentList();

	void insertEmp(Employee e);
	void updateEmp(Employee e);
	void deleteEmp(int eno);
	Employee selectEmp(int eno);
	
	void insertDept(Department d);
	void updateDept(Department d);
	Department selectDept(int dcode);
	void deleteDept(int dcode);
	
	void insertTitle(Title t);
	void updateTitle(Title t);
	Title selectTitle(int tcode);
	void deleteTitle(int tcode);
}
