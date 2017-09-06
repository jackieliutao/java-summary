package com.jackie.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.jackie.domain.Classes;
import com.jackie.util.TestUtils;

public class TestOneToMany {
	@Test
	public void testGetClass3(){
		SqlSession sqlSession = TestUtils.getSqlSession();
		String statement = "com.jackie.mapping.classMapper.getClass3";
		Classes clazz = sqlSession.selectOne(statement,1);
		sqlSession.close();
		System.out.println(clazz);
	}
	@Test
	public void testGetClass4(){
		SqlSession sqlSession = TestUtils.getSqlSession();
		String statement = "com.jackie.mapping.classMapper.getClass4";
		Classes clazz = sqlSession.selectOne(statement,1);
		sqlSession.close();
		System.out.println(clazz);
	}

}
