package ink.kilig.yxy.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ink.kilig.yxy.domain.Result;
import ink.kilig.yxy.domain.YxyUser;
import ink.kilig.yxy.mapper.YxyUserMapper;
import ink.kilig.yxy.service.YxyUserService;
import ink.kilig.yxy.utils.JwtTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/10/20 21:43
 * @description:
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private YxyUserService yxyUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String token=request.getHeader("token");
        Map<String,String> object=null;
        response.setContentType("application/json; charset=UTF-8");
       if(token!= null && !token.equals("") && !jwtTokenUtils.isTokenExpired(token)){
           String username = jwtTokenUtils.getUsernameFromToken(token);
           YxyUser realUser = yxyUserService.findYxyUserByUsername(username);
           if(realUser != null) return true;
           else {
               object=new HashMap<>();
               object.put("code","500");
               object.put("message","抱歉，用户不存在，请你先注册再进行登录。");
               String s = (new ObjectMapper()).writeValueAsString(object);
               response.getWriter().write(s);
               return false;
           }
       }else {
           object=new HashMap<>();
           object.put("code","500");
           object.put("message","抱歉，你的账户信息已经过期或者你未提供有效的验证信息,请重新登录。");
           String s = (new ObjectMapper()).writeValueAsString(object);
           response.getWriter().write(s);
       }
       return false;
    }
}
