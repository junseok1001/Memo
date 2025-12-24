package com.sourjelly.memo.config;


import com.sourjelly.memo.common.FileManger;
import com.sourjelly.memo.interceptor.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private PermissionInterceptor interceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registory){
        registory.addResourceHandler("/images/**")
                 .addResourceLocations("file:///" + FileManger.FILE_UPLOAD_PATH);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/logout", "/css/**", "/images/**");
    }
}
