/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bootstack.storage.mysql.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * <p> StorageForMySqlPrimaryConfig </p>
 * <p> Description : StorageForMySqlPrimaryConfig </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 12:59 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ConfigSupport.CONFIG_DATASOURCE_BASE_PACKAGE)
@Slf4j
@PropertySource(value = "bootstack-storage-mysql.properties")
public class StorageForMySqlPrimaryConfig {

    @Resource
    private Environment environment;

    @Bean
    @Primary
    public DataSource dataSource() {
        boolean embeddedDatabase = Boolean.valueOf(environment.getProperty(ConfigSupport.CONFIG_DATASOURCE_PREFIX + "embedded.enable"));
        log.info("start config datasource，use embedded datasource is {}", embeddedDatabase);
        if (embeddedDatabase) {
            EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
            return builder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("schema.sql", "data.sql")
                    .build();
        } else {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            String dbType = environment.getProperty(ConfigSupport.CONFIG_DATASOURCE_PREFIX + "type");
            log.info("datasource type is {}", dbType);
            Assert.notNull(dbType, "database type must not null");
            switch (dbType) {
                case "mysql":
                    String dbMySQLClass = environment.getProperty(ConfigSupport.CONFIG_DATASOURCE_PREFIX + "mysql.class");
                    dataSource.setDriverClassName(dbMySQLClass);
                    Assert.notNull(dbMySQLClass, "mysql driver class must not null");
                    String dbMySQLUrl = environment.getProperty(ConfigSupport.CONFIG_DATASOURCE_PREFIX + "mysql.url");
                    Assert.notNull(dbMySQLUrl, "mysql connection url must not null");
                    dataSource.setUrl(dbMySQLUrl);
                    String dbMySQLUser = environment.getProperty(ConfigSupport.CONFIG_DATASOURCE_PREFIX + "mysql.username");
                    Assert.notNull(dbMySQLUser, "mysql connection user name must not null");
                    dataSource.setUsername(dbMySQLUser);
                    String dbMySQLUserPassword = environment.getProperty(ConfigSupport.CONFIG_DATASOURCE_PREFIX + "mysql.password");
                    Assert.notNull(dbMySQLClass, "mysql connection user password must not null");
                    dataSource.setPassword(dbMySQLUserPassword);
            }
            log.info("datasource config complete");
            return dataSource;
        }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(ConfigSupport.CONFIG_DATASOURCE_MODEL);
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
