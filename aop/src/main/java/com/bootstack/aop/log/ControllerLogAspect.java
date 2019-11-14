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
package com.bootstack.aop.log;

import com.bootstack.storage.mongodb.model.system.SystemLogToMongoDbModel;
import com.bootstack.storage.mongodb.service.system.log.SystemLogToMongoDbService;
import com.bootstack.storage.mysql.model.system.interfaces.SystemInterfaceModel;
import com.bootstack.storage.mysql.model.system.log.SystemLogModel;
import com.bootstack.storage.mysql.model.system.log.SystemLogTypeModel;
import com.bootstack.storage.mysql.model.system.method.SystemMethodModel;
import com.bootstack.storage.mysql.model.user.UserModel;
import com.bootstack.storage.mysql.service.system.interfaces.SystemInterfaceService;
import com.bootstack.storage.mysql.service.system.log.SystemLogService;
import com.bootstack.storage.mysql.service.system.method.SystemMethodService;
import com.bootstack.storage.mysql.service.user.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p> ControllerLogAspect </p>
 * <p> Description : ControllerLogAspect </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-05-07 17:13 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Aspect
@Component
public class ControllerLogAspect {

    @Autowired
    private UserService userService;

    @Autowired
    private SystemLogService systemLogService;

    @Autowired
    private SystemInterfaceService systemInterfaceService;

    @Autowired
    private SystemMethodService systemMethodService;

    @Autowired
    private SystemLogToMongoDbService systemLogToMongoDbService;

    @Pointcut("execution(* com.bootstack.core.controller..*.*(..))")
    private void controller() {
    }

    @Before("controller()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        UserModel user = null;
        if (!ObjectUtils.isEmpty(request.getUserPrincipal())) {
            // TODO: 后期加入到懒缓冲中
            user = this.userService.getModelByName(request.getUserPrincipal().getName());
        } else {
            // 抽取系统白名单数据
            SystemMethodModel systemMethodModel = this.systemMethodService.getByMethod(request.getMethod());
            // 系统白名单数据使用系统默认用户
            if (!ObjectUtils.isEmpty(systemMethodModel)) {
                SystemInterfaceModel systemInterfaceModel = this.systemInterfaceService.getByPathAndMethodsIn(request.getServletPath(), systemMethodModel);
                if (!ObjectUtils.isEmpty(systemInterfaceModel)) {
                    user = (UserModel) this.userService.getModelById(1L);
                }
            }
        }
        SystemLogModel log = new SystemLogModel();
        log.setUrl(request.getServletPath());
        log.setArgs(Arrays.toString(joinPoint.getArgs()));
        log.setClazz(joinPoint.getSignature().getDeclaringTypeName());
        log.setClassMethod(joinPoint.getSignature().getName());
        log.setMethod(request.getMethod());
        log.setRemoteIp(request.getRemoteAddr());
        SystemLogTypeModel logType = new SystemLogTypeModel();
        logType.setId(4L);
        log.setType(logType);
        log.setUser(user);
        this.systemLogService.insertModel(log);
        // 存储日志信息到Mongo中
        SystemLogToMongoDbModel logToMongoDbModel = new SystemLogToMongoDbModel();
        logToMongoDbModel.setUrl(request.getServletPath());
        logToMongoDbModel.setArgs(Arrays.toString(joinPoint.getArgs()));
        logToMongoDbModel.setClazz(joinPoint.getSignature().getDeclaringTypeName());
        logToMongoDbModel.setClassMethod(joinPoint.getSignature().getName());
        logToMongoDbModel.setMethod(request.getMethod());
        logToMongoDbModel.setRemoteIp(request.getRemoteAddr());
        logToMongoDbModel.setUserId(user.getId());
        logToMongoDbModel.setUserName(user.getName());
        this.systemLogToMongoDbService.insertModel(logToMongoDbModel);
    }

    @AfterReturning(returning = "response", pointcut = "controller()")
    public void doAfterReturning(Object response) throws Throwable {
        // 处理完请求，返回内容
//        System.out.println(response);
    }

}
