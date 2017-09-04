### 一：mybatis配置文件详解
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MyBatis配置文件中大标签configuration下子标签包括如下：
> - properties
> - settings
> - typeAliases
> - typeHandlers
> - objectFactory
> - plugins
> - environments
> > - environment
> > > - transactionManager
> > > - dataSource
> - mappers

#### 1.properties属性
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;properties和java的properties的配置文件有关。配置properties的resource的路径，然后在properties标签下配置property的name和value，则可以替换properties文件中相应属性值。

> - A:在MyBatis配置文件中引用属性文件

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;MyBatis允许在mybatis-config.xml配置文件中加载*.properties属性文件，并使用属性文件的属性值，以提高应用的配置能力。例如在mybatis-config.xml文件所在目录创建config.properties，其内容如下：

```
mysql.driver = com.mysql.jdbc.Driver
mysql.url = jdbc:mysql://localhost:3306/test
mysql.username = root
mysql.password = lizhiwei
oracle.driver=oracle.jdbc.driver.OracleDriver
oracle.url=jdbc:oracle:thin:@127.0.0.1:1521:orcl
oracle.username=
oracle.password=
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 在mybatis-config.xml文件中的使用如下：
```
<!-- 属性替换 -->
<properties resource="mysql.properties">
<property name="jdbc.driverClassName" value="com.mysql.jdbc.Driver"/>
<property name="jdbc.url" value="jdbc:mysql://localhost:3306/test"/>
<property name="username" value="root"/>
<property name="password" value="lizhiwei"/>
</properties>

```
> - B：使用代码加载属性
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;属性也可以被传递到SqlSessionBuilder.build()方法中。例如：

```
SqlSessionFactory factory = sqlSessionFactoryBuilder.build(reader, props);
// ... or ...
SqlSessionFactory factory = sqlSessionFactoryBuilder.build(reader, environment, props);
```
<font color="red">使用这种方法可以使用加密的属性文件!在不想让用户打开config.properties文件就知道用户密码时，对用户密码加密特别有用。</font>

> C：属性的加载顺序

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果属性在不止一个地方进行了配置，那么MyBatis将按照下面的顺序来加载：
> > - 在properties元素体内指定的属性首先被读取。
> > - 然后根据properties元素中的resource属性读取类路径下属性文件或根据url属性指定的路径读取属性文件，并覆盖以读取的同名属性，
> > - 最后兑取最为方法参数传递属性，并覆盖以读取的同名属性。

<font color="red">因此，通过方法参数传递的属性具有最高优先级，resource/url属性中指定的配置文件次之，最低优先级的是properties属性中指定的属性。</font>

#### 2.settings设置
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;这是MyBatis修改操作运行过程细节的重要的步骤。下方这个表格描述了这些设置项、含义和默认值。

|设置项|描述|允许值|默认值|
|---------|---------|---------|---------|
|cacheEnabled|对在此配置文件的所有cache进行全局性开/关设置|true/false|true|
|lazyLoadingEnabled|全局性设置懒加载。如果设为‘false’，则所有相关联的都会被初始化加载。|true/false|true|
|aggressiveLazyLoading|当设置为true的时候，懒加载的对象可能被任何懒属性全部加载。否则，每个属性都按需加载。|true/false|true|
|multipleResultSetsEnabled|允许和不允许单条语句返回数据集（取决于驱动需求）|true/false|true|
|useColumnLabel|使用列标签代替列名称。不同的驱动器有不同的作法。|true/false|true|
|useGeneratedKeys|允许JDBC生成主键。需要驱动器支持。如果设为了true，这个设置将强制使用被生成的主键，有一些驱动器不兼容不过仍然可以执行。|true/false|false|
|autoMappingBehavior|指定MyBatis是否并且如何来自动映射数据表字段与对象的属性。PARTIAL将只自动映射简单的，没有嵌套的结果。FULL将自动映射所有复杂的结果。|NONE.PARTIAL.FULL|PARTIAL|
|defaultExecutorType|配置和设定执行器，SIMPLE执行器执行其语句。REUSE执行器可能重复使用preparedstatements语句，BATCH执行器可以重复执行语句和批量更新。|SIMPLE.REUSE.BATCH|SIMPLE|
|defaultStatementTimeout|设置一个时限，以决定让驱动器等待数据库回应的多长时间为超时|正整数|Not Set（Null）|
|safeRowBoundsEnabled|Allows using RowBounds on nested statements|true/false|false|
|mapUnderscoreToCamelCase|Enables automatic mapping from classic database column names A_COLUMN to camel case classic Java property names aColumn.|true/false|false|
|localCacheScope|MyBatis uses local cache to prevent circular references and speed up repeated nested queries. By default (SESSION) all queries executed during a session are cached. If localCacheScope=STATEMENT local session will be used just for statement execution, no data will be shared between two different calls to the same SqlSession.	|SESSION/STATEMENT|SESSION|
|jdbcTypeForNull|Specifies the JDBC type for null values when no specific JDBC type was provided for the parameter. Some drivers require specifying the column JDBC type but others work with generic values like NULL, VARCHAR or OTHER.	|JdbcType enumeration. Most common are: NULL, VARCHAR and OTHER	|OTHER|
|lazyLoadTriggerMethods|Specifies which Object's methods trigger a lazy load	|A method name list separated by commas	|equals,clone,hashCode,toString|
|defaultScriptingLanguage|Specifies the language used by default for dynamic SQL generation.|A type alias or fully qualified class name.|org.apache.ibatis.scripting.xmltags.XMLDynamicLanguageDriver|
|logPrefix|Specifies the prefix string that MyBatis will add to the logger names.|Any String|NOT SET|
|logImpl|Specifies which logging implementation MyBatis should use. If this setting is not present logging implementation will be autodiscovered.|SLF4J /LOG4J /LOG4J2 /JDK_LOGGING/ COMMONS_LOGGING /STDOUT_LOGGING / NO_LOGGING|NOT SET|
|proxyFactory|Specifies the proxy tool that MyBatis will use for creating lazy loading capable objects.|CGLIB / JAVASSIST|CGLIB|

> settins配置示例：
> settings配置在mybatis-config.xml文件中，一个配置完整的 settings 元素的示例如下：

```
<settings>
    <setting name="cacheEnabled" value="true" />
    <setting name="lazyLoadingEnabled" value="true" />
    <setting name="multipleResultSetsEnabled" value="true" />
    <setting name="useColumnLabel" value="true" />
    <setting name="useGeneratedKeys" value="false" />
    <setting name="autoMappingBehavior" value="PARTIAL" />
    <setting name="defaultExecutorType" value="SIMPLE" />
    <setting name="defaultStatementTimeout" value="25" />
    <setting name="defaultFetchSize" value="100" />
    <setting name="safeRowBoundsEnabled" value="false" />
    <setting name="mapUnderscoreToCamelCase" value="false" />
    <setting name="localCacheScope" value="SESSION" />
    <setting name="jdbcTypeForNull" value="OTHER" />
    <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString" />
</settings>
```
#### 3、typeAliases（类型别名）
> - A：MyBatis设置别名

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;给Java类型取一个别名，方便在核心配置、映射配置中来使用这个java类型。

> 类型别名是为Java类型设置一个短的名字。它只和XML配置有关，存在的意义仅在于用来减少类完全限定名的冗余。别名的设置可以通过配置文件或注解来定义。通过配置文件配置如下：(取自mybatis-config.xml文件)
```
<typeAliases>
	<typeAlias alias="Author" type="domain.blog.Author" />
	<typeAlias alias="Blog" type="domain.blog.Blog" />
	<typeAlias alias="Comment" type="domain.blog.Comment" />
	<typeAlias alias="Section" type="domain.blog.Section" />
</typeAliases>
```
> 当这样配置时，Blog可以用在任何使用domain.blog.Blog的地方。也可以指定一个包名，MyBatis会在包名下面搜索需要的Java Bean，比如:
```
<typeAliases>
	<package name="domain.blog" />
</typeAliases>
```
> 每一个在包domain.blog中的Java Bean，在没有注解的情况下，会使用Bean的首字母小写的非限定类名来作为它的别名。 比如domain.blog.Author的别名为author；若有注解，则别名为其注解值。看下面的例子：
```
@Alias("author")
public class Author {
    ...
}
```
> - B：MyBatis内置的别名定义说明

>  Mybatis已经为许多常见的 Java 类型内建了相应的类型别名。它们都是大小写不敏感的，需要注意的是由基本类型名称重复导致的特殊处理。参考如下表格：

|别名|映射的类型|别名|映射的类型|别名|映射的类型|别名|映射的类型|
|--|--|--|--|--|--|--|--|
|_byte|byte|_long|long|_short|short|_int|int|
|_integer|integer|_double|double|_float|float|_boolean|boolean|
|string|String|byte|Byte|long|Long|short|Short|
|int|Integer|integer|Integer|double|Double|float|Float|
|boolean|Boolean|date|Date|decimal|BigDecimal|bigdecimal|BigDecimal|
|object|Object|map|Map|hashmap|HashMap|list|List|
|arraylist|ArrayList|collection|Collection|iterator|Iterator|--|--|

#### 4、typeHandlers 类型处理器

> - A：typeHandlers介绍

> 无论是MyBatis在预处理语句（PreparedStatement）中设置一个参数时，还是从结果集中取出一个值时，都会用类型处理器将获取的值以合适的方式转换成Java类型。类型处理器就是把java类型转成数据库类型，把数据库类型转成java类型。

> 用途：
> - (1)获取数据库的值，以合适的方式转变为对应的java类型。
> - (2)将java类型，以合适的方式转化为数据库的保存类型。
> mybatis中默认的类型处理器：

|类型处理器|Java类型|JDBC类型|
|--|--|--|--|
|BooleanTypeHandler|java.lang.Boolean,boolean|任何兼容的布尔值|
|ByteTypeHandler|java.lang.Byte,byte|任何兼容的数字或字节类型|
|ShortTypeHandler|java.lang.Short,short|任何兼容的数字或短整型|
|IntegerTypeHandler|java.lang.Integer,int|任何兼容的数字和整型|
|LongTypeHandler|java.lang.Long,long|任何兼容的数字或长整型|
|FloatTypeHandler|java.lang.float,float|任何兼容的数字或单精度浮点型|
|DoubleTypeHandler|java.lang.Double,double|任何兼容的数字或双精度浮点型|
|BigDecimalTypeHandler|java.math.BigDecimal|任何兼容的数字或十进制小数类型|
|StringTypeHandler|java.lang.String|CHAR和VARCHAR类型|
|ClobTypeHandler|java.lang.String|CLOB和LONGVARCHAR类型|
|NStringTypeHandler|java.lang.String|NVARCHAR和CHAR类型|
|NClobTypeHandler|java.lang.String|NCLOB类型|
|ByteArrayTypeHandler|byte[]|任何兼容的字节流类型|
|BlobTypeHandler|byte[]|BLOB和LONGVARBINARY类型|
|DateTypeHandler|java.util.Date|TIMESTAMP类型|
|DateOnlyTypeHandler|java.util.Date|Date类型|
|TimeOnlyTypeHandler|java.util.Date|TIME类型|
|SqlTimestampTypeHandler|java.sql.Timestamp|TIMESTAMP 类型|
|SqlDateTypeHandler|java.sql.Date|DATE类型|
|SqlTimeTypeHandler|java.sql.Time|TIME 类型|
|ObjectTypeHandler|Any|其他或未指定类型|
|EnumTypeHandler|Enumeration Type|VARCHAR-任何兼容的字符串类型, 作为代码存储(而不是索引)|
|EnumOrdinalTypeHandler|Enumeration Type|Any compatible NUMERIC or DOUBLE, as the position is stored (not the code itself).|


> 可以重写类型处理器或创建你自己的类型处理器来处理不支持的或非标准的类型。 具体做法为：实现 org.apache.ibatis.type.TypeHandler 接口， 或继承一个很便利的类 org.apache.ibatis.type.BaseTypeHandler， 然后可以选择性地将它映射到一个 JDBC 类型。比如：

```
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ExampleTypeHandler extends BaseTypeHandler<String>
{
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException
    {
        ps.setString(i, parameter);
    }
    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException
    {
        return rs.getString(columnName);
    }
    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException
    {
        return rs.getString(columnIndex);
    }
    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        return cs.getString(columnIndex);
    }
}
```

> 在配置文件mybatis-config.xml中配置自定义的类型处理器如下：

```
<typeHandlers>
	<typeHandler handler="org.mybatis.example.ExampleTypeHandler" />
</typeHandlers>
```

<font color="red">使用这个的类型处理器将会覆盖已经存在的处理 Java 的 String 类型属性和 VARCHAR 参数及结果的类型处理器。 要注意 MyBatis 不会窥探数据库元信息来决定使用哪种类型，所以你必须在参数和结果映射中指明那是 VARCHAR 类型的字段， 以使其能够绑定到正确的类型处理器上。 这是因为：MyBatis 直到语句被执行才清楚数据类型。</font>

>  在设置类型处理器时也可以使用扫描包的方式，在配置文件mybatis-config.xml中如下配置：

```
<typeHandlers>
	<package name="org.mybatis.example" />
</typeHandlers>
```

<font color="red"> 注意：使用扫描包的方式注册类型处理器时，只能通过注解方式来指定 JDBC 的类型。</font>

> - B：设置类型处理器转换类型方法

> 通过类型处理器的泛型，MyBatis可以得知该类型处理器处理的Java类型，不过这种行为可以通过两种方法改变：
> * 在类型处理的配置元素（typeHandler element）上增加一个javaType属性（如：javaType=“String”）
> * 在类型处理器的类上（TypeHandler class）增加一个@MappedJdbcTypes注解来指定与其关联的Java类型列表。如果在 javaType 属性中也同时指定，则注解方式将被忽略。