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
package org.devlive.authx.test.common.json;

import org.devlive.authx.common.json.JsonUtils;
import org.devlive.authx.test.JsonSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p> JsonUtilsTest </p>
 * <p> Description : JsonUtilsTest </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-21 17:35 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Slf4j
public class JsonUtilsTest {

    private String source;
    private String prettySource;

    @Before
    public void init() {
        source = JsonSupport.source;
        prettySource = JsonSupport.prettySource;
    }

    @Test
    public void testSource() {
        log.info(source);
    }

    @Test
    public void testPrettySource() {
        log.info(prettySource);
    }

    @Test
    public void testPrettyFormat() {
        log.info(JsonUtils.formatPretty(source));
    }

    @Test
    public void testFormatToJsonObject() {
        log.info(JsonUtils.formatToJsonObject(source).getClass().getName());
    }

    @Test
    public void testCompressionFormat() {
        log.info("Source: \n" + prettySource);
        log.info("Compression: \n" + JsonUtils.compression(prettySource));
    }

    /**
     * 测试私有方法
     */
    @Test
    public void testBuildJsonParser() {
        try {
            Method method = JsonUtils.class.getDeclaredMethod("buildJsonParser", null);
            // Method对象继承自java.lang.reflect.AccessibleObject,父类方法setAccessible可调
            // 将此对象的accessible标志设置为指示的布尔值:
            //      值为true则指示反射的对象在使用时应该取消Java语言访问检查
            //      值为false则指示反射的对象应该实施Java语言访问检查
            // 要访问私有方法必须将accessible设置为true,否则抛java.lang.IllegalAccessException
            method.setAccessible(true);
            Object result = method.invoke(JsonUtils.class, null);
            log.info(result.toString());
            Assert.assertNotNull(result);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
            log.error(ex.getMessage());
        }
    }

}
