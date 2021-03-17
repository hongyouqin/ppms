package com.qhy.ppmsadmin.repository;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class HibernateConf {

    @Profile("dev")
    @Bean
    public LocalSessionFactoryBean sessionFactoryDev() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSourceDev());
        sessionFactory.setPackagesToScan("com.qhy.ppmsadmin");
        return sessionFactory;
    }

    @Profile("dev")
    @Bean
    public DataSource dataSourceDev() {
        EmbeddedDatabaseBuilder edb = new EmbeddedDatabaseBuilder();
        edb.setType(EmbeddedDatabaseType.H2);
        EmbeddedDatabase embeddedDatabase = edb.build();
        return embeddedDatabase;
    }

}
