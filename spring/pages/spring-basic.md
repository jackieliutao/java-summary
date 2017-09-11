### 一：什么是Spring框架？
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;spring是J2EE应用程序框架，是轻量级的IoC和AOP的容器框架，主要是针对javaBean的生命周期进行管理的轻量级容器，可以单独使用，也可以和Struts，ibatis框架等组合使用。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Spring 框架是一个分层架构，由 7 个定义良好的模块组成。Spring 模块构建在核心容器之上，核心容器定义了创建、配置和管理 bean 的方式。

### 二：架构概述

![image](../images/pic-spring框架.gif)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;组成 Spring 框架的每个模块（或组件）都可以单独存在，或者与其他一个或多个模块联合实现。每个模块的功能如下：

* **<font color="red">核心容器：</font>**核心容器提供了spring框架的基本功能。核心容器的主要组件是BeanFactory，它是<a href="../../designPatterns/pages/factory-method.html">工厂模式</a>的是实现。BeanFactory使用控制反转（IOC）模式将应用程序的配置和依赖规范与实际应用程序代码分开。 这是最重要的，也是最基础的， Spring的基础。它的作用是配置和Java对象的生命周期管理。

* **<font color="red">Spring上下文：</font>**Spring上下文是一个配置文件，向Spring框架提供上下文信息。Spring上下文包括企业服务，例如JNDI、EJB、电子邮件、国际化、校验和调度功能。

* **<font color="red">Spring AOP：</font>**通过配置管理特性，Spring AOP模块直接将面向方面的编程功能集成到了Spring框架中。所以，可以很容易地使Spring框架管理的任何对象支持AOP。Spring AOP模块为基于Spring的应用程序中的对象提供了事务管理服务。通过使用Spring AOP，不用依赖EJB组件，就可以将声明性事务管理集成到应用程序中。

* **<font color="red">Spring DAO</font>**JDBC DAO抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理和不同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，并且极大地降低了需要编写的异常代码数量（如打开和关闭连接）。Spring DAO的面向JDBC的异常遵从通用的DAO异常层次结构。

* **<font color="red">Spring ORM：</font>**Spring框架插入了若干个ORM框架，从而提供了ORM的对象关注工具，其中包括JDO、Hibernate和iBatis SQL Map。所有这些都遵从Spring的通用事务和DAO异常层次结构。

* **<font color="red">Spring Web模块：</font>**Web上下文模块建立在应用程序上下文模块之上，为基于Web的应用程序提供了上下文。所以，Spring框架支持与Jakarta Struts的集成。Web模块还简化了处理多部分请求以及讲请求参数绑定到域对象的工作。

* **<font color="red">Spring MVC框架：</font>**MVC框架是一个全功能的构建Web应用程序的MVC实现。通用策略接口，MVC框架变成为高度可配置的，MVC容纳了大量视图技术，其中包括JSP、Velocity、iText和POI。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Spring框架的功能可以用在任何J2EE服务器中，大多数功能也适用于不受管理的环境。Spring的核心要点是：支持不绑定到特定J2EE服务的可重用业务和数据访问对象。毫无疑问，这样的对象可以在不同J2EE环境（Web或EJB）、独立应用程序、测试环境之间重用。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DAO, ORM, AOP, WEB: 该模块可用于将工具或框架集成到了Spring。
