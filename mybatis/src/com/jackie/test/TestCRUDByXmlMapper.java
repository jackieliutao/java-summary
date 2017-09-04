package com.jackie.test;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.jackie.domain.User;
import com.jackie.util.TestUtils;

public class TestCRUDByXmlMapper {
	@Test
	public void testAdd(){
		User user = new User();
		user.setName("齐天大圣");
		user.setAge(500);
	        SqlSession sqlSession = null;
	        try {
	            sqlSession = TestUtils.getSqlSession();
	            int i = sqlSession.insert("com.jackie.mapping.userMapper.add", user);
	            System.out.println("本次操作影响"+i+"行数据");
	            sqlSession.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            sqlSession.rollback();
	        }
	        finally{
	            TestUtils.closeSqlSession();
	        }
	}
	
	@Test
	public void testUpdate(){
		SqlSession sqlSession = TestUtils.getSqlSession();
		/**
		 * 映射sql的标识字符串
		 * com.jackie.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值
		 * updateUser是update标签的id属性值，通过update标签的id属性值就可以最后哦啊到要执行的SQL
		 */
		String statement = "com.jackie.mapping.userMapper.updateUser";//映射sql的标识字符串
		User user = new User();
		user.setId(3);
		user.setName("美猴王");
		user.setAge(1000);
		//执行修改操作
		int retResult = sqlSession.update(statement,user);
		//使用sqlSession执行完SQL之后需要关闭sqlsession
		sqlSession.close();
		System.out.println(retResult);
	}
	@Test
	public void testDelete(){
		SqlSession sqlSession = TestUtils.getSqlSession();
		/**
		 * 映射sql的标识字符串
		 * com.jackie.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值
		 * updateUser是update标签的id属性值，通过update标签的id属性值就可以最后哦啊到要执行的SQL
		 */
		String statement = "com.jackie.mapping.userMapper.deleteUser";//映射sql的标识字符串
		//执行删除操作
		int retResult = sqlSession.update(statement,5);
		//使用sqlSession执行完SQL之后需要关闭sqlsession
		sqlSession.close();
		System.out.println(retResult);
	}
	@Test
	public void getAll(){
		SqlSession sqlSession = TestUtils.getSqlSession();
		/**
		 * 映射sql的标识字符串
		 * com.jackie.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值
		 * updateUser是update标签的id属性值，通过update标签的id属性值就可以最后哦啊到要执行的SQL
		 */
		String statement = "com.jackie.mapping.userMapper.findAll";//映射sql的标识字符串
		 //执行查询操作，将查询结果自动封装成List<User>返回
        List<User> lstUsers = sqlSession.selectList(statement);
        //使用SqlSession执行完SQL之后需要关闭SqlSession
        sqlSession.close();
        System.out.println(lstUsers);
	}
	@Test
	public void findById(){
		SqlSession sqlSession = null;
        try {
            sqlSession = TestUtils.getSqlSession();
            User user = sqlSession.selectOne("com.jackie.mapping.userMapper.findById", 3);
            System.out.println( user.getName() + ":"+user.getAge());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        }
        finally{
            TestUtils.closeSqlSession();
        }        
	}

}
