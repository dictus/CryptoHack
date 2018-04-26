package com.crypto.CryptoHack.cr.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "cryptoEntityMangerFactory",
        transactionManagerRef = "crypotTransactionManger" ,basePackages = "com.crypto.CryptoHack.backjpa.repo")
public class DataSourceConfig {



    @Primary
    @Bean
    @ConfigurationProperties("cr.spring.datasource")
    public DataSourceProperties crDataSourceProperties(){
        return  new DataSourceProperties();
    }

    @Primary
    @Bean(name ="crDataSource")
    @ConfigurationProperties("cr.spring.datasource")
    public DataSource crDataSource(){
        return (HikariDataSource) crDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }



    @Bean(name = "crJdbcTemplete")
    public JdbcTemplate crJdbcTemplete(){return new JdbcTemplate(crDataSource());}


    @Bean(name ="cryptoEntityMangerFactory")
    public LocalContainerEntityManagerFactoryBean cryptoEntityMangerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("crDataSource") DataSource dataSource)
    {
            return builder.dataSource(dataSource).
                    packages("com.crypto.CryptoHack.backjpa.domain").
                    persistenceUnit("cr").build();
    }

    @Bean(name = "crypotTransactionManger")
    public PlatformTransactionManager crypotTransactionManger(
            @Qualifier ("cryptoEntityMangerFactory")
                    EntityManagerFactory crEntityManagerFactory){
        return new JpaTransactionManager(crEntityManagerFactory);
    }

}
