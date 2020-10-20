package ink.kilig.yxy.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: Hps
 * @date: 2020/10/20 20:17
 * @description:
 */
@RestController
public class CaptchaController {
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response){
        try {
            CaptchaUtil.out(120,40,4,request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
