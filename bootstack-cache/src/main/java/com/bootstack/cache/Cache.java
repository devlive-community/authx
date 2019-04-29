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
package com.bootstack.cache;

import lombok.ToString;

/**
 * <p> Cache </p>
 * <p> Description : Cache </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 19:50 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@ToString
public class Cache {

    private String key; // cache key
    private Object value; // cache id
    private long firstTime = System.currentTimeMillis(); // cache time
    private long timeOut = 0; // cache dead time, 0 not dead
    private long lastRefer = System.currentTimeMillis(); // last flush time

    public Cache() {
    }

    public Cache(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Cache(String key, Object value, long timeOut) {
        this.key = key;
        this.value = value;
        this.timeOut = timeOut;
    }

    public Cache(String key, Object value, long timeOut, long lastRefer) {
        this.key = key;
        this.value = value;
        this.timeOut = timeOut;
        this.lastRefer = lastRefer;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(long firstTime) {
        this.firstTime = firstTime;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public long getLastRefer() {
        return lastRefer;
    }

    public void setLastRefer(long lastRefer) {
        this.lastRefer = lastRefer;
    }

}
