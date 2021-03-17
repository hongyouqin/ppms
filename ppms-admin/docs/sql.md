## h2常用配置说明
```
#h2配置
spring.jpa.show-sql = true #启用SQL语句的日志记录
spring.jpa.hibernate.ddl-auto = update  #设置ddl模式
##数据库连接设置
spring.datasource.url = jdbc:h2:mem:dbtest  #配置h2数据库的连接地址
spring.datasource.username = sa  #配置数据库用户名
spring.datasource.password = sa  #配置数据库密码
spring.datasource.driverClassName =org.h2.Driver  #配置JDBC Driver
##数据初始化设置
#进行该配置后，每次启动程序，程序都会运行resources/db/schema.sql文件，对数据库的结构进行操作。
spring.datasource.schema=classpath:db/schema.sql  
#进行该配置后，每次启动程序，程序都会运行resources/db/data.sql文件，对数据库的数据操作。
spring.datasource.data=classpath:db/data.sql  
##h2 web console设置
spring.datasource.platform=h2  #表明使用的数据库平台是h2
# 进行该配置后，h2 web consloe就可以在远程访问了。否则只能在本机访问。
spring.h2.console.settings.web-allow-others=true  
#进行该配置，你就可以通过YOUR_URL/h2访问h2 web consloe。YOUR_URL是你程序的访问URl。
spring.h2.console.path=/h2  
#进行该配置，程序开启时就会启动h2 web consloe。这是默认的。
spring.h2.console.enabled=true 
```