package kr.or.dgit.test;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import junit.framework.Assert;
import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Employee;
import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.service.ErpService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmpTest {
	private ErpService erp = ErpService.getInstance();

	@org.junit.Test
	public void testAInsert() {
		erp.insertEmp(new Employee(18002, "테스트", 150000, new Department("마케팅"), 0, new Date(), new Title("사장")));
		Assert.assertNotNull(erp.selectEmp(18002));
	}

	@org.junit.Test
	public void testBSelect() {
		Assert.assertNotNull(erp.selectEmp(18002));
	}

	@org.junit.Test
	public void testCUpdate() {
		erp.updateEmp(new Employee(18002, "테스트2", 150000, new Department("마케팅"), 0, new Date(), new Title("사장")));
		Assert.assertEquals(erp.selectEmp(18002).getEname(), "테스트2");
	}

	@org.junit.Test
	public void testDDelete() {
		erp.deleteEmp(18002);
		Assert.assertNull(erp.selectEmp(18002));
	}

	@org.junit.Test
	public void testESelect() {
		Assert.assertNotNull(erp.getEmpList());
	}
}
