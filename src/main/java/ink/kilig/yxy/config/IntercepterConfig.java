package ink.kilig.yxy.config;

import ink.kilig.yxy.interceptor.CorsInterceptor;
import ink.kilig.yxy.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        //排除掉验证码以及注册和登陆，其他都要拦截
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns("/yxyUser/register/**")
                .excludePathPatterns("/captcha/**")
                .excludePathPatterns("/yxyUser/login/**");
    }
}
