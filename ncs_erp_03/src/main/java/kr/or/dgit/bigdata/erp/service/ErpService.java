package kr.or.dgit.bigdata.erp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import kr.or.dgit.bigdata.erp.MybatisSessionFactory;
import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Employee;
import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.mappers.ErpMapper;

public class ErpService implements ErpMapper {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ErpService.class);

	private final static ErpService instance = new ErpService();

	public static ErpService getInstance() {
		return instance;
	}

	private ErpService() {
	}

	@Override
	public List<Employee> getEmpList() {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			return erp.getEmpList();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public List<Title> getTitleList() {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			return erp.getTitleList();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public List<Department> getDepartmentList() {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			return erp.getDepartmentList();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void insertEmp(Employee e) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			erp.insertEmp(e);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void updateEmp(Employee e) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			erp.updateEmp(e);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void deleteEmp(int eno) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			erp.deleteEmp(eno);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Employee selectEmp(int eno) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			return erp.selectEmp(eno);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void insertDept(Department d) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			erp.insertDept(d);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void updateDept(Department d) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			erp.updateDept(d);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Department selectDept(int dcode) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			return erp.selectDept(dcode);
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void insertTitle(Title t) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			erp.insertTitle(t);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void updateTitle(Title t) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			erp.updateTitle(t);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public Title selectTitle(int tcode) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			return erp.selectTitle(tcode);
		} finally {
			sqlSession.close();
		}
	}



	@Override
	public void deleteDept(int dcode) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			erp.deleteDept(dcode);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	@Override
	public void deleteTitle(int tcode) {
		SqlSession sqlSession = MybatisSessionFactory.openSession();
		try {
			ErpMapper erp = sqlSession.getMapper(ErpMapper.class);
			erp.deleteTitle(tcode);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

}
