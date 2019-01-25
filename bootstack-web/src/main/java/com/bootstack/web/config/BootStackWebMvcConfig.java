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
package com.bootstack.web.config;

import com.bootstack.web.BootStackWebSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * <p> BootStackWebMvcConfig </p>
 * <p> Description : BootStackWebMvcConfig </p>
 * <p> Author : qianmoQ </p>
 * <p> Version : 1.0 </p>
 * <p> Create Time : 2019-01-25 16:19 </p>
 * <p> Author Email: <a href="mailTo:shichengoooo@163.com">qianmoQ</a> </p>
 */
@Configuration
public class BootStackWebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Bean
    @Description("bootstack system thymeleaf config")
    public ClassLoaderTemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        String templatePrefix = environment.getProperty(BootStackWebSupport.CONFIG_WEB_PREFIX + ".prefix");
        Assert.notNull(templatePrefix, "template prefix config must not null");
        templateResolver.setPrefix(templatePrefix);

        String templateSuffix = environment.getProperty(BootStackWebSupport.CONFIG_WEB_PREFIX + ".suffix");
        Assert.notNull(templateSuffix, "template suffix config must not null");
        templateResolver.setSuffix(templateSuffix);

        Boolean templateCacheable = Boolean.valueOf(environment.getProperty(BootStackWebSupport.CONFIG_WEB_PREFIX + ".cache"));
        templateResolver.setCacheable(templateCacheable);

        String templateMode = environment.getProperty(BootStackWebSupport.CONFIG_WEB_PREFIX + ".mode");
        Assert.notNull(templateSuffix, "template mode config must not null");
        templateResolver.setTemplateMode(templateMode);

        String templateEncoding = environment.getProperty(BootStackWebSupport.CONFIG_WEB_PREFIX + ".encoding");
        Assert.notNull(templateSuffix, "template encoding config must not null");
        templateResolver.setCharacterEncoding(templateEncoding);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding(BootStackWebSupport.CONFIG_WEB_PREFIX + ".encoding");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(environment.getProperty(BootStackWebSupport.CONFIG_WEB_PREFIX + ".static-relative-location"))
                .addResourceLocations(environment.getProperty(BootStackWebSupport.CONFIG_WEB_PREFIX + ".static-location"));
        super.addResourceHandlers(registry);
    }

}