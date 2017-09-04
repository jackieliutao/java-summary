package com.jackie.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestUtils {
	 private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	    private static SqlSessionFactory sqlSessionFactory;
	    static{
	        try {
	            Reader reader = Resources.getResourceAsReader("conf.xml");
	            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	    }
	    private TestUtils() {}
	    public static SqlSession getSqlSession(){
	        SqlSession sqlSession = threadLocal.get();
	        if(sqlSession == null){
	            sqlSession = sqlSessionFactory.openSession();
	            threadLocal.set(sqlSession);
	        }
	        return sqlSession;
	    }
	     
	    public static void closeSqlSession(){
	        SqlSession sqlSession = threadLocal.get();
	        if(sqlSession != null){
	            sqlSession.close();
	            threadLocal.remove();
	        }
	    }
	     
	    public static void main(String[] args) {
	        Connection conn = TestUtils.getSqlSession().getConnection();
	        System.out.println(conn!=null ? "连接成功" : "连接失败");
	    }
	   

}
