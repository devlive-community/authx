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
package com.bootstack.test;

/**
 * <p> JsonSupport </p>
 * <p> Description : JsonSupport </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-21 19:47 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public class JsonSupport {

    public final static String source = "{\"msg\":\"ok\",\"dataObject\":[{\"avg\":39737,\"count\":78647,\"time\":\"20190516\",\"total\":3125242938}],\"success\":true,\"sessionId\":\"sessionId\"}";
    public final static String sourceEmpty = "";
    public final static String prettySource = "{\n" +
            "  \"msg\": \"ok\",\n" +
            "  \"dataObject\": [\n" +
            "    {\n" +
            "      \"avg\": 39737,\n" +
            "      \"count\": 78647,\n" +
            "      \"time\": \"20190516\",\n" +
            "      \"total\": 3125242938\n" +
            "    }\n" +
            "  ],\n" +
            "  \"success\": true,\n" +
            "  \"sessionId\": \"sessionId\"\n" +
            "}";
    public final static String prettySourceEmpty = "";

}
