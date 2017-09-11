
### IoC

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IoC的全称是Inversion of Control，中文称为控制反转,Martin Flower又根据它创造了一个新词：Dependency Injection，中文称为依赖注入。这两个词讲的是一回事儿。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IoC的实质是如何管理对象，传统意义上我们使用new方式来创建对象，但在企业应用开发的过程中，大量的对象创建都在程序中维护很容易造成资源浪费，并且不利于程序的扩展。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实现IoC的三种方式：

* 1）利用接口或者继承，一般以接口较多。这种实现方式和我们平时提到的lazy load有异曲同工之妙。
* 2）构造函数注入。
* 3）属性注入。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">IoC是Spring框架的核心。</font>
