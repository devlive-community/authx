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
package com.bootstack.test.service.json;

import com.bootstack.core.BootStackBootstrap;
import com.bootstack.service.json.JsonService;
import com.bootstack.test.JsonSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p> JsonServiceTest </p>
 * <p> Description : JsonServiceTest </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-21 18:44 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Slf4j
@SpringBootTest(classes = {
        BootStackBootstrap.class
}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestPropertySource(value = {
        "classpath:bootstack.properties",
        "classpath:bootstack-database.properties",
        "classpath:bootstack-api.properties"
})
public class JsonServiceTest {

    @Autowired
    private JsonService jsonService;

    private String source;
    private String sourceEmpty;
    private String prettySource;
    private String prettySourceEmpty;

    @Before
    public void init() {
        source = JsonSupport.source;
        sourceEmpty = JsonSupport.sourceEmpty;
        prettySource = JsonSupport.prettySource;
        prettySourceEmpty = JsonSupport.prettySourceEmpty;
    }

    @Test
    public void testFormatPrettyEmpty() {
        log.info(jsonService.formatPretty(sourceEmpty).toString());
    }

    @Test
    public void testFormatPrettyNotEmpty() {
        log.info(jsonService.formatPretty(source).toString());
    }

    @Test
    public void testCompressionEmpty() {
        log.info(jsonService.compression(prettySourceEmpty).toString());
    }

    @Test
    public void testCompressionNotEmpty() {
        log.info(jsonService.compression(prettySource).toString());
    }

}
