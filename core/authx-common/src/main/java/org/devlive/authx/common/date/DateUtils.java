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
package org.devlive.authx.common.date;

import org.devlive.authx.common.support.DateSuooprt;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p> DateUtils </p>
 * <p> Description : DateUtils </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-24 11:35 </p>
 * <p> Author Email: : <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
public class DateUtils {

    /**
     * format date to yyyy-mm-dd hh:mm:ss
     *
     * @return formart date id
     */
    public static String formatYmdhms() {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DateSuooprt.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
        return format.format(new Date());
    }

}
