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
package com.bootstack.core.support;

import com.bootstack.common.support.PageSupport;
import com.bootstack.model.page.PageModel;
import com.bootstack.param.page.PageParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

/**
 * <p> ParamSupport </p>
 * <p> Description : ParamSupport </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-03-25 14:41 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public class ParamSupport {

    /**
     * get pageable from page param
     *
     * @param param page param
     * @return pageable info
     */
    public static Pageable getPageable(PageParam param) {
        // sort by column and mode
        if (!ObjectUtils.isEmpty(param.getSortColumn()) && !ObjectUtils.isEmpty(param.getSortMode())) {
            return PageModel.getPageableAndSort(param.getPage(), param.getSize(), new Sort(Sort.Direction.fromString(param.getSortMode().name()),
                    param.getSortColumn()));
        }
        // sort column is empty, sort by mode
        if (!ObjectUtils.isEmpty(param.getSortMode())) {
            return PageModel.getPageableAndSort(param.getPage(), param.getSize(), new Sort(Sort.Direction.fromString(param.getSortMode().name()),
                    PageSupport.DEFAULT_ORDER_PROPERTY_CREATE_TIME));
        }
        // sort mode is empty, sort by custom column
        if (!ObjectUtils.isEmpty(param.getSortColumn())) {
            return PageModel.getPageableAndSort(param.getPage(), param.getSize(), new Sort(Sort.Direction.DESC,
                    param.getSortColumn()));
        }
        // sort by default column and default mode
        return PageModel.getPageableAndSort(param.getPage(), param.getSize(), new Sort(Sort.Direction.DESC,
                PageSupport.DEFAULT_ORDER_PROPERTY_CREATE_TIME));
    }

}
