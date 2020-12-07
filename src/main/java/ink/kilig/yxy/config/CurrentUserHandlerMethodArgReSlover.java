package ink.kilig.yxy.config;

import ink.kilig.yxy.domain.CurrentUser;
import ink.kilig.yxy.domain.YxyUser;
import ink.kilig.yxy.utils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author: Hps
 * @date: 2020/12/7 20:30
 * @description:
 */
@Component
public class CurrentUserHandlerMethodArgReSlover implements HandlerMethodArgumentResolver {
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    public CurrentUserHandlerMethodArgReSlover(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(CurrentUser.class) != null
                && methodParameter.getParameterType() == String.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String token = nativeWebRequest.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        return (String)username;
    }
}
