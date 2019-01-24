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

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * <p> CacheManager </p>
 * <p> Description : CacheManager </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 19:52 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Component(value = "cacheManager")
public interface CacheManager {

    /**
     * put cache
     *
     * @param key   cache key
     * @param cache cache value
     */
    void put(String key, Cache cache);

    /**
     * put cache
     *
     * @param key     cache key
     * @param cache   cache value
     * @param timeOut timeout
     */
    void put(String key, Object cache, long timeOut);

    /**
     * get cache
     *
     * @param key cache key
     * @return cache
     */
    Cache get(String key);

    /**
     * get all cache
     *
     * @return all cache
     */
    Map<String, Cache> getAll();

    /**
     * in cache
     *
     * @param key cache key
     * @return in cache
     */
    boolean contains(String key);

    /**
     * clean all cache
     */
    void clearAll();

    /**
     * clean cache
     *
     * @param key cache key
     */
    void clear(String key);

    /**
     * cache timeout?
     *
     * @param key cache key
     * @return is timeout
     */
    boolean timeOut(String key);

    /**
     * get all key
     *
     * @return all cache keys
     */
    Set<String> getAllKeys();

    /**
     * flush cache
     *
     * @param key   cache key
     * @param cache cache value
     */
    void refer(String key, Object cache);

}