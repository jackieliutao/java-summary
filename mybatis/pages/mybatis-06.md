### <p align="center">MyBatis缓存介绍</p>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正如大多数持久层框架一样，MyBatis同样提供了<font color="red">一级缓存</font>和<font color="red">二级缓存</font>的支持。

* 1、**一级缓存：**</font>基于PerpetualCache的HashMap本地缓存，其**<font color="yellow">存储作用域为Session</font>,**,当**<font color="yellow">Session flush或close</font>**之后，该**<font color="yellow">Session中的所有Cache就将清空</font>**。
* 2、**二级缓存:**二级缓存与一级缓存的机制相同，默认也是采用PerpetualCache，HashMap存储，不同在于其**<font color="yellow">存储作用域为Mapper（Namespace），并且可自定义存储源</font>**，如Ehcache。
* 3、对于缓存数据更新机制，当某一个作用域（一级缓存Session/二级缓存Namespaces）的进行了C/U/D操作后，默认作用域下所有select中的缓存将被clear。

### 一：查询缓存
#### 1、什么是查询缓存
> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mybatis提供查询缓存，用于减轻数据压力，提高数据库性能。

> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;mybatis提供一级缓存和二级缓存。

> ![image](../image/mybatis缓存机制.png)

* A：一级缓存

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一级缓存是SqlSession级别的缓存。在操作数据库时需要构造sqlSesion对象，在对象中有一个（内存区域）数据结构（HashMap）用于存储缓存数据。不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一级缓存的作用域是同一个SqlSession，在同一个sqlSession中两次执行相同的sql语句，第一次执行完毕会将数据库中查询的数据写到缓存（内存），第二次会从缓存中获取数据将不再从数据库查询，从而提高查询效率。当一个sqlSession结束后该sqlSession中的一级缓存也就不存在了。MyBatis默认开启一级缓存。

* B： 二级缓存

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;二级缓存是多个SqlSession共享的，其作用域是mapper的同一个namespace，不同desqlSession两次执行相同的namespace下的sql语句且向sql中传递参数也相同即最终执行相同的sql语句，第一次执行完毕会将数据库中查询的数据写到缓存（内存），第二次会从缓存中获取数据将不再从数据库查询，从而提高查询效率。MyBatis默认没有开启二级缓存，需要在setting全局参数中配置开启二级缓存。

----
#### <font color="red">一级缓存</font>
##### 1、一级缓存工作原理

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下面是根据id查询用户的一级缓存图解：

![image](../image/一级缓存图解.png)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font size="4">第一次发起查询用户id为1的用户信息，先去找缓存中是否有id为1的用户信息，如果没有，从数据库查询用户信息。得到用户信息，将用户信息缓存到一级缓存中。如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做目的为了让缓存中存储的是最新的信息，避免脏读。</font>
---
