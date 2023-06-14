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
package org.devlive.authx.cache;

/**
 * <p> CacheListener </p>
 * <p> Description : CacheListener </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 19:59 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public class CacheListener {

    private CacheManager cacheManager;

    public CacheListener(CacheManagerImpl cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void listen() {
        new Thread(() -> {
            cacheManager.getAllKeys().stream().filter(key -> cacheManager.timeOut(key)).forEach(key -> cacheManager.clear(key));
        }).start();
    }


}