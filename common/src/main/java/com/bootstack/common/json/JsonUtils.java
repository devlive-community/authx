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
package org.devlive.authx.common.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.util.ObjectUtils;

/**
 * <p> JsonUtils </p>
 * <p> Description : JsonUtils </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-21 17:34 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public class JsonUtils {

    private static JsonParser jsonParser;

    private JsonUtils() {
    }

    /**
     * 格式化json数据
     *
     * @param source json元数据
     * @return 格式化后的json数据
     */
    public static String formatPretty(String source) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(new JsonParser().parse(source).getAsJsonObject());
    }

    /**
     * 压缩json数据
     *
     * @param source json元数据
     * @return 压缩后的json数据
     */
    public static String compression(String source) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(formatToJsonObject(source));
    }

    /**
     * 格式化数据为JsonObject
     *
     * @param source json元数据
     * @return 格式化后的JsonObject
     */
    public static JsonObject formatToJsonObject(String source) {
        return buildJsonParser().parse(source).getAsJsonObject();
    }

    /**
     * 构建JsonParser
     *
     * @return JsonParser实例
     */
    private static JsonParser buildJsonParser() {
        if (ObjectUtils.isEmpty(jsonParser)) {
            jsonParser = new JsonParser();
        }
        return jsonParser;
    }

}
