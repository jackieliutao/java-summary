### 一：Mybatis介绍
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mybatis是一个支持普通SQL查询，存储过程和高级映射的优秀持久层框架。MyBatis消除了几乎所有的JDBC代码和参数的手工设置以及对结果集的检索封装。MaBatis可以使用简单的XML或注解用于配置和原始映射。将接口和Java的POJO（Plain Old Java Objects，普通的Java对象）映射成数据库中的记录。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MyBatis的功能架构分为三层：
> - API接口层：提供给外部使用的接口API，开发人员通过这些本地API来操作数据库。接口层一接收到调用请求就会调用数据处理层来完成具体的数据处理。
> - 数据处理层：负责具体的SQL查找、SQL解析。SQL执行和执行结果映射处理等。他主要的目的是根据调用的请求完成一次数据库操作。
> - 基础支撑层：负责最基础的功能支撑，包括连接管理、事务管理。配置加载和缓存处理，这些都是共用的东西，将他们呢抽取出来作为最基础的组件。为上层的数据处理层提供最基础的支撑。

> ![image](../image/mybatis架构层次.jpg)

> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MyBatis整体流程图

> ![image](../image/mybatis流程图.jpg)

### 二：MyBatis快速入门
- #### 1、准备开发环境

> - A:创建测试项目，普通Java项目或者是JavaWeb项目均可，如下图所示：

> ![image](../image/创建测试项目.png)

> - B：添加相应的jar包

> ![image](../image/添加依赖jar包.png)

> - C：创建数据库和表，针对MYSQL数据哭。SQL脚本如下：
```
CREATE DATABASE;
USE mybatis;
CREATE TABLE users(ID INT PRIMARY KEY AUTO_INCREMENT,NAME VARCHAR(20),AGE INT)
INSERT INTO `mybatis`.`users` (`NAME`, `AGE`) VALUES ( '孤傲苍狼', 27);
INSERT INTO `mybatis`.`users` (`NAME`, `AGE`) VALUES ( '白虎神皇', 27);
```

> 将SQL脚本在mysql数据库中执行，完成创建数据库和表的操作，如下：

> ![image](../image/执行SQL脚本.png)

> 至此，前期的所有开发准备环境全部完成。

- #### 2、使用MyBatis查询表中的数据

> - A：添加Mybatis的配置文件conf.XML

> 在src目录下创建一个conf.xml文件，如下图所示：

> ![image](../image/添加conf配置文件.png)

> conf配置文件的内容如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC" />
            <!-- 配置数据库连接信息 -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver" />
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis" />
                <property name="username" value="root" />
                <property name="password" value="mysql" />
            </dataSource>
        </environment>
    </environments>
</configuration>
```

> - B：定义表对应的实体类，如下图所示：

> ![image](../image/创建user实体类.png)

> user实体类的内容如下：

```
package com.jackie;

public class User {
	private int id;
	private String name;
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
```

> - C：定义操作users表的sql映射文件userMapper.xml

> 创建一个com.jackie.mapping包，专门用于存放sql映射文件，在包中创建一个userMapper.xml文件，如下图所示：

> ![image](../image/创建userMapper文件.png)

> userMapper.xml文件的内容如下：

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 为这个mapper指定一个唯一的namespace，namespace的值习惯上设置成包名+sql映射文件名，这样就能够保证namespace的值是唯一的
例如namespace="me.gacl.mapping.userMapper"就是me.gacl.mapping(包名)+userMapper(userMapper.xml文件去除后缀)
 -->
<mapper namespace="com.jackie.mapping.userMapper">
    <!-- 在select标签中编写查询的SQL语句， 设置select标签的id属性为getUser，id属性值必须是唯一的，不能够重复
    使用parameterType属性指明查询时使用的参数类型，resultType属性指明查询返回的结果集类型
    resultType="me.gacl.domain.User"就表示将查询结果封装成一个User类的对象返回
    User类就是users表所对应的实体类
    -->
    <!--
        根据id查询得到一个user对象
     -->
    <select id="getUser" parameterType="int"
        resultType="me.gacl.domain.User">
        select * from users where id=#{id}
    </select>
</mapper>

```

> - D:在conf.xml文件中注册userMapper.xml文件

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC" />
            <!-- 配置数据库连接信息 -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver" />
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis" />
                <property name="username" value="root" />
                <property name="password" value="XDP" />
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <!-- 注册userMapper.xml文件，
        userMapper.xml位于me.gacl.mapping这个包下，所以resource写成me/gacl/mapping/userMapper.xml-->
        <mapper resource="me/gacl/mapping/userMapper.xml"/>
    </mappers>

</configuration>
```

> - E：编写测试代码，执行定义的select语句

> 创建一个Test1类，编写如下的测试代码：

```
package com.jackie.test;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jackie.domain.User;

public class Test1 {
	public static void main(String[] args) {
		//mybatis的配置文件
		String resource = "conf.xml";
		//使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		InputStream is = Test1.class.getClassLoader().getResourceAsStream(resource);
		//构建sqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		//使用mybatis提供的resources类加载mybatis的配置文件（他也加载关联的映射文件）
		//Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
		SqlSession session = sessionFactory.openSession();
		 /**
         * 映射sql的标识字符串，
         * com.jackie.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "com.jackie.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement, 1);
        System.out.println(user);
	}

}
```

> 执行效果如下：

> ![image](../image/执行效果.png)

### 三、使用Mybatis对表执行CRUD操作——基于XML的实现
- #### 1、定义映射xml文件
> userMapper.xml文件的内容如下：

```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 为这个mapper指定一个唯一的namespace，namespace的值习惯上设置成包名+sql映射文件名，这样就能够保证namespace的值是唯一的
例如namespace="me.gacl.mapping.userMapper"就是me.gacl.mapping(包名)+userMapper(userMapper.xml文件去除后缀)
 -->
<mapper namespace="me.gacl.mapping.userMapper">
    <!-- 在select标签中编写查询的SQL语句， 设置select标签的id属性为getUser，id属性值必须是唯一的，不能够重复
    使用parameterType属性指明查询时使用的参数类型，resultType属性指明查询返回的结果集类型
    resultType="me.gacl.domain.User"就表示将查询结果封装成一个User类的对象返回
    User类就是users表所对应的实体类
    -->
    <!--
        根据id查询得到一个user对象
     -->
    <select id="getUser" parameterType="int"
        resultType="me.gacl.domain.User">
        select * from users where id=#{id}
    </select>

    <!-- 创建用户(Create) -->
    <insert id="addUser" parameterType="me.gacl.domain.User">
        insert into users(name,age) values(#{name},#{age})
    </insert>

    <!-- 删除用户(Remove) -->
    <delete id="deleteUser" parameterType="int">
        delete from users where id=#{id}
    </delete>

    <!-- 修改用户(Update) -->
    <update id="updateUser" parameterType="me.gacl.domain.User">
        update users set name=#{name},age=#{age} where id=#{id}
    </update>

    <!-- 查询全部用户-->
    <select id="getAllUsers" resultType="me.gacl.domain.User">
        select * from users
    </select>

</mapper>
```

> 单元测试代码如下：

```
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
```

### 四：使用MyBatis对表执行CRUD操作——基于注解的实现

> - 1：定义sql映射的接口

> userMapperⅠ接口的代码如下：

```
package com.jackie.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jackie.domain.User;
/**
 * @author jackie
 * 定义sql映射的接口，使用注解指明方法要执行的SQL
 */
public interface UserMapperⅠ {

	//使用@Insert注解致命你个add方法要执行的SQL
	@Insert("insert into users(name,age) values(#{name},#{age})")
	public int add(User user);

	 //使用@Delete注解指明deleteById方法要执行的SQL
    @Delete("delete from users where id=#{id}")
    public int deleteById(int id);

    //使用@Update注解指明update方法要执行的SQL
    @Update("update users set name=#{name},age=#{age} where id=#{id}")
    public int update(User user);

    //使用@Select注解指明getById方法要执行的SQL
    @Select("select * from users where id=#{id}")
    public User getById(int id);

    //使用@Select注解指明getAll方法要执行的SQL
    @Select("select * from users")
    public List<User> getAll();

}
```

<font color="red">需要说明的是，我们不需要针对UserMapperI接口去编写具体的实现类代码，这个具体的实现类由MyBatis帮我们动态构建出来，我们只需要直接拿来使用即可。</font>

> - 2：在conf.xml文件中注册这个映射接口

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC" />
            <!-- 配置数据库连接信息 -->
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver" />
                <property name="url" value="jdbc:mysql://localhost:3306/mybatis" />
                <property name="username" value="root" />
                <property name="password" value="XDP" />
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <!-- 注册userMapper.xml文件，
        userMapper.xml位于me.gacl.mapping这个包下，所以resource写成me/gacl/mapping/userMapper.xml-->
        <mapper resource="me/gacl/mapping/userMapper.xml"/>
        <!-- 注册UserMapper映射接口-->
        <mapper class="me.gacl.mapping.UserMapperI"/>
    </mappers>

</configuration>
```


> 单元测试类代码如下：

```
package me.gacl.test;

import java.util.List;
import me.gacl.domain.User;
import me.gacl.mapping.UserMapperI;
import me.gacl.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class TestCRUDByAnnotationMapper {

    @Test
    public void testAdd(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
        User user = new User();
        user.setName("用户xdp");
        user.setAge(20);
        int add = mapper.add(user);
        //使用SqlSession执行完SQL之后需要关闭SqlSession
        sqlSession.close();
        System.out.println(add);
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
        User user = new User();
        user.setId(3);
        user.setName("孤傲苍狼_xdp");
        user.setAge(26);
        //执行修改操作
        int retResult = mapper.update(user);
        //使用SqlSession执行完SQL之后需要关闭SqlSession
        sqlSession.close();
        System.out.println(retResult);
    }

    @Test
    public void testDelete(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession(true);
        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
        //执行删除操作
        int retResult = mapper.deleteById(7);
        //使用SqlSession执行完SQL之后需要关闭SqlSession
        sqlSession.close();
        System.out.println(retResult);
    }

    @Test
    public void testGetUser(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
        //执行查询操作，将查询结果自动封装成User返回
        User user = mapper.getById(8);
        //使用SqlSession执行完SQL之后需要关闭SqlSession
        sqlSession.close();
        System.out.println(user);
    }

    @Test
    public void testGetAll(){
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        //得到UserMapperI接口的实现类对象，UserMapperI接口的实现类对象由sqlSession.getMapper(UserMapperI.class)动态构建出来
        UserMapperI mapper = sqlSession.getMapper(UserMapperI.class);
        //执行查询操作，将查询结果自动封装成List<User>返回
        List<User> lstUsers = mapper.getAll();
        //使用SqlSession执行完SQL之后需要关闭SqlSession
        sqlSession.close();
        System.out.println(lstUsers);
    }
}
```

> 用到的mabatis工具类代码如下：

```
package me.gacl.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

    /**
     * 获取SqlSessionFactory
     * @return SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        String resource = "conf.xml";
        InputStream is = MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        return factory;
    }

    /**
     * 获取SqlSession
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        return getSqlSessionFactory().openSession();
    }

    /**
     * 获取SqlSession
     * @param isAutoCommit
     *         true 表示创建的SqlSession对象在执行完SQL之后会自动提交事务
     *         false 表示创建的SqlSession对象在执行完SQL之后不会自动提交事务，这时就需要我们手动调用sqlSession.commit()提交事务
     * @return SqlSession
     */
    public static SqlSession getSqlSession(boolean isAutoCommit) {
        return getSqlSessionFactory().openSession(isAutoCommit);
    }
}
```
