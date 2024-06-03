package com.fizzy.imgesland.config;

import com.fizzy.imgesland.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${cors.enabled}")
    private static boolean corsEnabled;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将匹配上/files/**虚拟路径的url映射到文件上传到服务器的目录，获取静态资源
        registry.addResourceHandler("/" + FileUtil.pathPattern + "/**").addResourceLocations("file:" + FileUtil.fileDir);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Configuration
    public static class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            if (corsEnabled) {
                registry.addMapping("/**")
                        //是否发送Cookie
                        .allowCredentials(true)
                        //放行哪些原始域
                        .allowedOriginPatterns("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .exposedHeaders("*");
            }
        }
    }
}
