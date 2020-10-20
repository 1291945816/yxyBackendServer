package ink.kilig.yxy.controller;

import com.wf.captcha.utils.CaptchaUtil;
import ink.kilig.yxy.domain.Result;
import ink.kilig.yxy.domain.YxyUser;
import ink.kilig.yxy.service.YxyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/10/20 20:30
 * @description:
 */
@RestController
public class YxyUserController {
    private YxyUserService yxyUserService;

    @Autowired
    public YxyUserController(YxyUserService yxyUserService) {
        this.yxyUserService=yxyUserService;
    }

    @PostMapping("/yxyUser/register")
    public Result<String> register(HttpServletRequest request, @RequestBody Map<String,String> registerInfo){
        String verCode = registerInfo.get("verCode");
        String username = registerInfo.get("username");
        String password = registerInfo.get("password");
        String nickname = registerInfo.get("nickname");
        //校验验证码是否是正确的 注册
        if(verCode == null || !CaptchaUtil.ver(verCode,request)){
           System.out.println(CaptchaUtil.ver(verCode,request));
           CaptchaUtil.clear(request);
           return Result.falure("抱歉，验证码错误!");
       }else if ( username == null || username.equals("")
        || password == null || password.equals("") || nickname ==null || nickname.equals("")
        )
        {
            return Result.falure("抱歉，用户名或密码或昵称为空，请填完必需信息。");
        }else {
            YxyUser yxyUser=new YxyUser();
            yxyUser.setYxyNickName(nickname);
            yxyUser.setYxyPassword(password);
            yxyUser.setYxyUserName(username);
            if (yxyUserService.YxyuserRegister(yxyUser)){
             Result.success(null,"用户注册成功，欢迎您加入伊享云大家庭。");
            }
        }
        return Result.falure("抱歉，由于未知的原因，注册失败");
    }

}
