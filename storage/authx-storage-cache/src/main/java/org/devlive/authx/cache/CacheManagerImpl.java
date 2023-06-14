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

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p> CacheManagerImpl </p>
 * <p> Description : CacheManagerImpl </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 19:57 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public class CacheManagerImpl implements CacheManager {

    // Buffer store, using ConcurrentHashMap for thread security
    private static Map<String, Cache> cacheContainer = new ConcurrentHashMap<>();

    @Override
    public void put(String key, Cache cache) {
        cacheContainer.put(key, cache);
    }

    @Override
    public void put(String key, Object cache, long timeOut) {
        timeOut = timeOut > 0 ? timeOut : 0;
        cacheContainer.put(key, new Cache(key, cache, timeOut));
    }

    @Override
    public Cache get(String key) {
        if (contains(key)) {
            return cacheContainer.get(key);
        }
        return null;
    }

    @Override
    public Map<String, Cache> getAll() {
        return cacheContainer;
    }

    @Override
    public boolean contains(String key) {
        return cacheContainer.containsKey(key);
    }

    @Override
    public void clearAll() {
        cacheContainer.clear();
    }

    @Override
    public void clear(String key) {
        cacheContainer.remove(key);
    }

    @Override
    public boolean timeOut(String key) {
        if (!contains(key)) {
            // If there is no buffer data in the buffer, it is indicated that the data is out of date.
            return true;
        }
        Cache cache = cacheContainer.get(key);
        if (cache.getTimeOut() == 0 || (System.currentTimeMillis() - cache.getLastRefer()) >= cache.getTimeOut()) {
            return true;
        }
        return false;
    }

    @Override
    public Set<String> getAllKeys() {
        return cacheContainer.keySet();
    }

    @Override
    public void refer(String key, Object cache) {
        if (contains(key) && !timeOut(key)) {
            cacheContainer.put(key, new Cache(key, cache));
        }
    }

}