## 目前问题:

1. h2内存数据库，每次启动时都去加载默认的数据库名jdbc:mem:testdb,spring.datasource.url所给的配置起不来作用

## 已知问题
1. org.hibernate.query.sqm.InterpretationException: Error interpreting query报错，网上说是因为用了6.0.0.Alpha6版本，回退到5.x.x版本就好了。其实不用那样，报这个错误，有一点可以排查下，就是数据库名是那个entity类的类名，不是@Table(name = "user_info")所对应的名称。
