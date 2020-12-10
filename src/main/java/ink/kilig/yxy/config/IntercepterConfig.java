package ink.kilig.yxy.config;

import ink.kilig.yxy.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.util.List;

/**
 * @author: Hps
 * @date: 2020/10/20 22:21
 * @description:
 */
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor interceptor;
    @Autowired
    private CurrentUserHandlerMethodArgReSlover currentUserHandlerMethodArgReSlover;


    /**
     * 参数解析
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserHandlerMethodArgReSlover);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //排除掉验证码以及注册和登陆，其他都要拦截
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/yxyUser/register/**")
                .excludePathPatterns("/captcha/**")
                .excludePathPatterns("/yxyUser/login/**");
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("/data/tmp");
        return factory.createMultipartConfig();
    }

    /**
     * 这个是跨域处理
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS","PATCH")
                .allowedHeaders("token")
                .allowCredentials(true)
                .maxAge(86400);
    }
}
