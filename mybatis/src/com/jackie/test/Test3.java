package com.jackie.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.jackie.domain.Classes;
import com.jackie.util.TestUtils;

public class Test3 {
	
	@Test
	public void testGetClass(){
		SqlSession sqlSession = TestUtils.getSqlSession();
		String statement = "com.jackie.mapping.classMapper.getClass";
		Classes clazz = sqlSession.selectOne(statement,1);
		sqlSession.close();
		System.out.println(clazz);
	}
	@Test
	public void testGetClass2(){
		SqlSession sqlSession = TestUtils.getSqlSession();
		String statement = "com.jackie.mapping.classMapper.getClass2";
		Classes clazz = sqlSession.selectOne(statement,1);
		sqlSession.close();
		System.out.println(clazz);
	}

}
