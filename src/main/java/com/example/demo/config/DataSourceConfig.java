package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
	
	@Primary
    @Bean(name="datasource1")
//    @ConfigurationProperties(prefix = "database1.datasource")
    public DataSource dataSourceOne() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/db1");
		dataSource.setUsername("root");
		dataSource.setPassword("12345678");
		return dataSource;
    	
//    	DataSourceBuilder dataSource = DataSourceBuilder.create();
//    	dataSource.driverClassName("com.mysql.jdbc.Driver");
//    	dataSource.url("jdbc:mysql://localhost:3306/db1");
//    	dataSource.username("root");
//    	dataSource.password("12345678");
//    	return dataSource.build();
    	
    	
//    	return DataSourceBuilder.create().build();
    }

    @Bean(name="datasource2")
//    @ConfigurationProperties(prefix = "database2.datasource")
    public DataSource dataSourceTwo() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/db2");
		dataSource.setUsername("root");
		dataSource.setPassword("12345678");
        return dataSource;
    	
//    	DataSourceBuilder dataSource = DataSourceBuilder.create();
//    	dataSource.driverClassName("com.mysql.jdbc.Driver");
//    	dataSource.url("jdbc:mysql://localhost:3306/db2");
//    	dataSource.username("root");
//    	dataSource.password("12345678");
//    	return dataSource.build();
    	
//    	return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "spring")
    @Autowired
    public JdbcTemplate jdbcTemplate(@Qualifier("datasource1") DataSource dataSourceOne) {
        return new JdbcTemplate(dataSourceOne);
    }

    @Bean(name = "spring2")
    @Autowired
    public JdbcTemplate jdbcTemplate2(@Qualifier("datasource2") DataSource dataSourceTwo) {
        return new JdbcTemplate(dataSourceTwo);
    }
}
