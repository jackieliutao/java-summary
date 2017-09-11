
<p align="center"><font size="6">IoC</font></p>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IoC的全称是Inversion of Control，中文称为控制反转,Martin Flower又根据它创造了一个新词：Dependency Injection，中文称为依赖注入。这两个词讲的是一回事儿。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IoC的实质是如何管理对象，传统意义上我们使用new方式来创建对象，但在企业应用开发的过程中，大量的对象创建都在程序中维护很容易造成资源浪费，并且不利于程序的扩展。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：三种依赖注入：

* 1）利用接口或者继承，一般以接口较多。这种实现方式和我们平时提到的lazy load有异曲同工之妙。
* 2）构造函数注入。
* 3）属性注入。

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">IoC是Spring框架的核心。</font>

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">依赖注入和控制反转，目的是为了使类与类之间解耦合，提高系。统的可扩展性和可维护性</font><font color="yellow>我们可以从以下几个方面理解：</font>

* <font color="yellow>a、参与者都有谁？</font>
  
* <font color="yellow>b、依赖：谁依赖谁？为什么需要依赖？</font>
  
* <font color="yellow>c、注入：谁注入谁？又注入了什么呢？</font>
  
* <font color="yellow>d、控制反转：谁控制谁？控制什么？为什么叫反转呢？存在正转嘛？</font>
  
* <font color="yellow>e、控制反转和依赖注入是同意概念吗？我们需要弄明白上面的问题，这样对于控制反转和依赖注入的理解由大大的帮助。</font>
  

