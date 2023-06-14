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
package org.devlive.authx.test.repository.system;

import org.devlive.authx.storage.mysql.model.system.SystemSettingsModel;
import org.devlive.authx.storage.mysql.repository.system.SystemSettingsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p> SystemSettingsRepositoryTest </p>
 * <p> Description : SystemSettingsRepositoryTest </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-29 23:01 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Slf4j
@SpringBootTest(classes = {
        org.devlive.authx.core.AuthX.class
}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource(value = {
        "classpath:bootstack.properties",
        "classpath:bootstack-database.properties",
        "classpath:bootstack-api.properties"
})
public class SystemSettingsRepositoryTest {

    @Autowired
    private SystemSettingsRepository repository;

    private SystemSettingsModel model;

    @Before
    public void init() {
        model = new SystemSettingsModel();
        model.setName("BootStack默认配置");
        model.setCode("DC");
        model.setLabel("BootStack默认配置");
        model.setActive(Boolean.TRUE);
        model.setValue("{\"settings.default.icon\":\"zmdi zmdi-balance\",\"settings.default.role\":\"2\"}");
    }

    @Test
    public void testSave() {
        log.info(this.repository.save(model).toString());
    }

    @Test
    public void testFindByName() {
        log.info(this.repository.findByName("BootStack默认配置").toString());
    }

    @Test
    public void testFindActiveTrue() {
        log.info(this.repository.findByActiveTrue().toString());
    }

}
