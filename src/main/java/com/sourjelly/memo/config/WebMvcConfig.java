package com.sourjelly.memo.config;


import com.sourjelly.memo.common.FileManger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registory){
        registory.addResourceHandler("/images/**")
                 .addResourceLocations("file:///" + FileManger.FILE_UPLOAD_PATH);
    }
}
