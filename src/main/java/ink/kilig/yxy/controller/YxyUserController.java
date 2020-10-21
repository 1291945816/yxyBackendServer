package ink.kilig.yxy.controller;

import com.wf.captcha.utils.CaptchaUtil;
import ink.kilig.yxy.domain.Result;
import ink.kilig.yxy.domain.YxyUser;
import ink.kilig.yxy.service.YxyUserService;
import ink.kilig.yxy.utils.JwtTokenUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/10/20 20:30
 * @description:
 */
@RestController
@RequestMapping("/yxyUser")
public class YxyUserController {
    private YxyUserService yxyUserService;
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    public YxyUserController(YxyUserService yxyUserService,JwtTokenUtils jwtTokenUtils) {
        this.yxyUserService=yxyUserService;
        this.jwtTokenUtils=jwtTokenUtils;
    }

    /**
     * 提供用户注册接口
     * @param request
     * @param registerInfo
     * @return 注册是否成功接口
     */
    @PostMapping("/register")
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
       }else
        if ( username == null || username.equals("")
        || password == null || password.equals("") || nickname ==null || nickname.equals("")
        )
        {
            return Result.falure("抱歉，用户名或密码或昵称为空，请填完必需信息。");
        }else {
            YxyUser yxyUser=new YxyUser();
            yxyUser.setYxyNickName(nickname);
            yxyUser.setYxyPassword(password);
            yxyUser.setYxyUserName(username);
            try {
                if (yxyUserService.YxyuserRegister(yxyUser)){
                    return Result.success(null,"用户注册成功，欢迎您加入伊享云大家庭。");
                }
                //校验用户名
            } catch (DuplicateKeyException de) {
                return Result.falure("抱歉，注册失败，用户名已经存在了,请更换用户名进行注册");
            }
        }
        return Result.falure("抱歉，由于未知的原因，注册失败，请重试，若还出现该问题，请联系客服人员。");
    }

    /**
     * 提供用户登录接口
     * @param loginUserInfo
     * @return 返回token信息
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody Map<String,String> loginUserInfo){
        String username = loginUserInfo.get("username");
        String password=loginUserInfo.get("password");
        if (username != null && password != null){
            YxyUser yxyUser = yxyUserService.findYxyUserByUsername(username);
            if(yxyUser==null)
                return Result.falure("抱歉，用户名错误或用户不存在，请先注册再尝试登录。");
            String yxyPassword = yxyUser.getYxyPassword();
            if(yxyPassword.equals(DigestUtils.sha256Hex(password)))
                return Result.success(jwtTokenUtils.generateToken(yxyUser),"登录成功。");
            else
                return Result.falure("抱歉，你输入的密码错误，请重新输入...");
        }
        return Result.falure("抱歉，用户名或密码不能够为空");
    }
    /**
     * 用户上传头像
     */
    @PostMapping("/uploadAvatar")
    public Result<String> uploadAvatar(@RequestParam MultipartFile file,HttpServletRequest request){
        String token=request.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        YxyUser user =new YxyUser();
        user.setYxyUserName(username);
        if (yxyUserService.uploadAvatar(file,user))
            return Result.success(null,"头像上传成功.");
        return Result.falure("由于未知的原因，头像上传失败,请重新尝试。");
    }


    /**
     *展示用户头像的
     */
    @GetMapping(value = "/myAvatar",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_PNG_VALUE})
    public byte[] getAvatar(HttpServletRequest request){
        String token=request.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        YxyUser user = yxyUserService.findYxyUserByUsername(username);
        byte[] bytes=null;
        try {
            FileInputStream stream = new FileInputStream(new File(user.getYxyUserAvatar()));
             bytes = new byte[stream.available()];
            stream.read(bytes,0,stream.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    /**
     * 修改昵称
     */
    @PostMapping("/updateNickname")
    public Result<String> updateNickname(@RequestBody Map<String,String> userInfo,HttpServletRequest request){
        String token=request.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        String nickname = userInfo.get("nickname");
        if(nickname != null){
            yxyUserService.updateUserNickNameByusername(nickname,username);
            return Result.success(null,"修改昵称成功!");
        }
        return Result.falure("噢，天呐，居然无法修改昵称!");
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    public Result<String> updatePassword(@RequestBody Map<String,String> userInfo,HttpServletRequest request){
        String token=request.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        String oldpassword = userInfo.get("oldpassword"); //旧密码
        String newpassword = userInfo.get("newpassword"); //新密码
        if (oldpassword != null && newpassword != null){
            if (yxyUserService.changepasswordByusername(oldpassword,newpassword,username))
                return Result.success(null,"密码修改成功，请重新登录。");
            else
                return Result.falure("原密码错误，修改密码失败.");
        }
        return Result.falure("很遗憾，由于未知的原因，密码修改失败.");
    }

    /**
     * 修改个人简介
     */
    @PostMapping("/updateUserIntro")
    public Result<String> updateUserIntro(@RequestBody Map<String,String> userInfo,HttpServletRequest request){
        String token=request.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        String userIntro = userInfo.get("userIntro");
        if(userIntro != null){
            yxyUserService.updateUserIntro(userIntro,username);
            return Result.success(null,"更新个人简介成功");
        }
        return Result.falure("可能是你提供个人简介的方式不对，所以更新失败了.");

    }

    /**
     * 获取用户信息
     */
    @GetMapping("/userInfo")
    public Result<YxyUser> getUserInfo(HttpServletRequest request){
        String token=request.getHeader("token");
        String username = jwtTokenUtils.getUsernameFromToken(token);
        return Result.success(yxyUserService.getUserInfo(username),"获取用户信息成功!");
    }



}
