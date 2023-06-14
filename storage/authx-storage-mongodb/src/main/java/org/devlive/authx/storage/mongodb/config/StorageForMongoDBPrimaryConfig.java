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
package org.devlive.authx.storage.mongodb.config;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.Resource;

/**
 * <p> StorageForMongoDBPrimaryConfig </p>
 * <p> Description : StorageForMongoDBPrimaryConfig </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-10-21 14:52 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Configuration
@Slf4j
@EnableMongoRepositories(basePackages = {ConfigSupport.CONFIG_DATASOURCE_BASE_PACKAGE})
@ComponentScan(basePackages = {ConfigSupport.CONFIG_DATASOURCE_MODEL})
@PropertySource("classpath:bootstack-storage-mongodb.properties")
public class StorageForMongoDBPrimaryConfig extends AbstractMongoConfiguration {

    @Resource
    private Environment environment;

    @Override
    public MongoClient mongoClient() {
        MongoClient client = new MongoClient(new ServerAddress(
                environment.getProperty(ConfigSupport.CONFIG_DATASOURCE_PREFIX + "host"),
                Integer.valueOf(environment.getProperty(ConfigSupport.CONFIG_DATASOURCE_PREFIX + "port"))
        ));
        return client;
    }

    @Override
    protected String getDatabaseName() {
        return environment.getProperty(ConfigSupport.CONFIG_DATASOURCE_PREFIX + "database");
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), getDatabaseName());
    }

}
