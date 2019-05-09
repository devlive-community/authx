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
package com.bootstack.model.page;

import org.springframework.data.domain.*;

import java.util.List;

/**
 * <p> PageModel </p>
 * <p> Description : PageModel </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-25 22:46 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public class PageModel<T> extends PageImpl<T> implements Page<T> {

    /**
     * Constructor of {@code PageImpl}.
     *
     * @param content  the content of this page, must not be {@literal null}.
     * @param pageable the paging information, can be {@literal null}.
     * @param total    the total amount of items available. The total might be adapted considering the length of the content
     */
    public PageModel(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    /**
     * Front-end Passenger 1 is the start page and 0 of spring data JPA is the start page.
     */
    public static Pageable request(Pageable pageable) {
        return new PageRequest(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
    }

    /**
     * Rewrite the current page, add 1 to the current page and return to the front desk after adding 0 to 1 to the start page of spring data JPA
     */
    @Override
    public int getNumber() {
        return super.getNumber() + 1;
    }

    public static Pageable getPageable(Integer page, Integer size) {
        return PageModel.request(new PageRequest(page, size));
    }

    /**
     * get pageable info from page info and sort
     *
     * @param page page number
     * @param size page size
     * @param sort data sort
     * @return pageable info
     */
    public static Pageable getPageableAndSort(Integer page, Integer size, Sort sort) {
        return PageModel.request(new PageRequest(page, size, sort));
    }

}
