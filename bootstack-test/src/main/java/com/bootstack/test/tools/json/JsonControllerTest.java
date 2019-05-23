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
package com.bootstack.test.tools.json;

import com.bootstack.core.BootStackBootstrap;
import com.bootstack.test.JsonSupport;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> JsonControllerTest </p>
 * <p> Description : JsonControllerTest </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-21 19:07 </p>
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
public class JsonControllerTest {

    // 通过MockMvcBuilders.webAppContextSetup(this.context).build();初始化一个mock测试器
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context; // 容器上下文

    private String source;
    private String sourceEmpty;
    private String prettySource;
    private String prettySourceEmpty;

    @Before
    public void init() {
        // 初始化mock测试器
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        source = JsonSupport.source;
        sourceEmpty = JsonSupport.sourceEmpty;
        prettySource = JsonSupport.prettySource;
        prettySourceEmpty = JsonSupport.prettySourceEmpty;
    }

    @Test
    public void testPostFormatPretty() {
        // 创建传递的参数数据
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("body", this.source);
        try {
            // 接收发送请求后的返回结果
            MvcResult response = mockMvc.perform(
                    // 设置post请求
                    MockMvcRequestBuilders.post("/api/v1/tools/json/format")
                            // 设置消息数据类型
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            // 设置发送的数据
                            .content(JSONObject.toJSONString(map))
            )
                    // 开始模拟发送post请求
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    // 设置返回类型为json
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    // 抽取返回结果
                    .andReturn();
            log.info(response.getResponse().getContentAsString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testPostCompression() {
        // 创建传递的参数数据
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("body", this.prettySourceEmpty);
        try {
            // 接收发送请求后的返回结果
            MvcResult response = mockMvc.perform(
                    // 设置post请求
                    MockMvcRequestBuilders.post("/api/v1/tools/json/compression")
                            // 设置消息数据类型
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            // 设置发送的数据
                            .content(JSONObject.toJSONString(map))
            )
                    // 开始模拟发送post请求
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    // 设置返回类型为json
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                    // 抽取返回结果
                    .andReturn();
            log.info(response.getResponse().getContentAsString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
