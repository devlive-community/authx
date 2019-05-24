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
package com.bootstack.test.pinyin;

import com.bootstack.common.pinyin.PinYinUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * <p> PinYinUtilsTest </p>
 * <p> Description : PinYinUtilsTest </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-21 01:58 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public class PinYinUtilsTest {

    private String chinese;
    private String prefix;
    private String fullPrefix;

    @Before
    public void init() {
        chinese = "测试中文";
        prefix = "C";
        fullPrefix = "CDZW";
    }

    @Test
    public void testGetFirstOneSpell() {
        Assert.assertEquals(PinYinUtils.getFirstOneSpell(chinese), prefix);
    }

    @Test
    public void testGetFirstOneSpellToUpper() {
        Assert.assertEquals(PinYinUtils.getFirstOneSpellToUpper(chinese), prefix);
    }

}
