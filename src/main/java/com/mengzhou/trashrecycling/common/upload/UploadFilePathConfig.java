package com.mengzhou.trashrecycling.common.upload;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 资源映射配置
 *
 * @author ZHOUTONG
 * @date 2019年08月06日 8:47
 */
@Configuration
public class UploadFilePathConfig extends WebMvcConfigurationSupport {

    @Value("${file.basePhysicPath}")
    private String staticAccessPath;
    @Value("${file.baseLogicPath}")
    private String uploadFolder;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadFolder)
                .addResourceLocations("file:" + staticAccessPath);

        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

}
