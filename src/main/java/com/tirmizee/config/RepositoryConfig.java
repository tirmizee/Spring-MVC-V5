package com.tirmizee.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class RepositoryConfig {

	@Autowired
	private Environment env;
	
	@Bean(name = "dataSource")
	public DataSource dataSourceDev() {
		HikariConfig config = new HikariConfig();
		config.setUsername(env.getProperty("dataSource.dev.user"));
		config.setPassword(env.getProperty("dataSource.dev.password"));
		config.setJdbcUrl(env.getProperty("dataSource.dev.jdbcUrl"));
		config.setDriverClassName(env.getProperty("dataSource.dev.driverclassName"));
		config.setMinimumIdle(Integer.valueOf(env.getProperty("dataSource.dev.minIdle")));
		config.setMaximumPoolSize(Integer.valueOf(env.getProperty("dataSource.dev.MaximumPoolSize")));
		return new HikariDataSource(config);
	}
	 
	@Bean
	@Profile("prod")
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setUsername(env.getProperty("dataSource.prod.user"));
		config.setPassword(env.getProperty("dataSource.prod.password"));
		config.setJdbcUrl(env.getProperty("dataSource.prod.jdbcUrl"));
		config.setDriverClassName(env.getProperty("dataSource.prod.driverclassName"));
		config.setMinimumIdle(Integer.valueOf(env.getProperty("dataSource.prod.minIdle")));
		config.setMaximumPoolSize(Integer.valueOf(env.getProperty("dataSource.prod.MaximumPoolSize")));
		return new HikariDataSource(config);
	}
	
}
