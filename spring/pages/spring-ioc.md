
<p align="center"><font size="6">IoC</font></p>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IoC的全称是Inversion of Control，中文称为控制反转,Martin Flower又根据它创造了一个新词：Dependency Injection，中文称为依赖注入。这两个词讲的是一回事儿。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IoC的实质是如何管理对象，传统意义上我们使用new方式来创建对象，但在企业应用开发的过程中，大量的对象创建都在程序中维护很容易造成资源浪费，并且不利于程序的扩展。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">IoC是Spring框架的核心。</font>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">依赖注入和控制反转，目的是为了使类与类之间解耦合，提高系。统的可扩展性和可维护性。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="yellow">我们可以从以下几个方面理解：</font>

* <font color="yellow">a、参与者都有谁？</font>
  
* <font color="yellow">b、依赖：谁依赖谁？为什么需要依赖？</font>
  
* <font color="yellow">c、注入：谁注入谁？又注入了什么呢？</font>
  
* <font color="yellow">d、控制反转：谁控制谁？控制什么？为什么叫反转呢？存在正转嘛？</font>
  
* <font color="yellow">e、控制反转和依赖注入是同一概念吗？
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们需要弄明白上面的问题，这样对于控制反转和依赖注入的理解由大大的帮助。</font>
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">第一个
问题：参与者都有谁？</font>
  
* **<font color="yellow">1）对象</font>**
* **<font color="yellow">2）IOC/DI容器</font>**
* **<font color="yellow">3）某个的对象的外部资源</font>**
  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">第二个
问题：依赖，谁依赖谁？为什么需要依赖？</font>

* **<font color="yellow">对象依赖IOC/DI容器。</font>**
  
* **<font color="yellow">对象需要IOC/DI容器来提供对象需要的外部资源。</font>**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">第三个
问题：注入，谁注入谁？又注入了什么呢？</font>

* **<font color="yellow">IOC/DI容器注入对象。**</font>

* **<font color="yellow">注入了对象所需要的资源。</font>**


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">第四个
问题：控制反转，谁控制谁？控制什么？为什么叫反转？有正转吗？</font>

* **<font color="yellow">IOC/DI容器控制对象，主要是控制对象实例的创建。</font>**

* **<font color="yellow">反转是相对于正向而言的,那么什么算是正向的呢？考虑一下常规情况下的应用程序，如果要在A里面使用C，你会怎么做呢？当然是直接去创建C的对象，也就是说，是在A类中主动去获取所需要的外部资源C，这种情况被称为正向的。那么什么是反向呢？就是A类不再主动去获取C，而是被动等待，等待IoC/DI的容器获取一个C的实例，然后反向的注入到A类中。</font>**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">第五个
问题：控制反转和依赖注入是同一概念吗？ </font>

* **<font color="yellow">依赖注入和控制反转是对同一件事情的不同描述，从某个方面讲，就是它们描述的角度不同。依赖注入是从应用程序的角度在描述，可以把依赖注入描述完整点：应用程序依赖容器创建并注入它所需要的外部资源；而控制反转是从容器的角度在描述，描述完整点：容器控制应用程序，由容器反向的向应用程序注入应用程序所需要的外部资源。</font>**

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实现依赖注入的三种方式：

* 1）利用接口或者继承，一般以接口较多。这种实现方式和我们平时提到的lazy load有异曲同工之妙。
* 2）构造函数注入。
* 3）属性注入。
