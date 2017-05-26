package kr.or.dgit.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import kr.or.dgit.bigdata.erp.MybatisSessionFactory;

public class SessionTest {

	@Test
	public void testMyBatisSqlSessionFactory() {
		Assert.assertNotNull(MybatisSessionFactory.getSqlSessionFactory().openSession());
	}

}
