package ink.kilig.yxy.service.impl;

import ink.kilig.yxy.domain.YxyUser;
import ink.kilig.yxy.mapper.YxyUserMapper;
import ink.kilig.yxy.service.YxyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author: Hps
 * @date: 2020/10/20 21:56
 * @description:
 */
@Service
public class YxyUserServiceImpl implements YxyUserService {
    private YxyUserMapper yxyUserMapper;

    @Autowired
    public YxyUserServiceImpl(YxyUserMapper yxyUserMapper) {
        this.yxyUserMapper=yxyUserMapper;
    }


    @Override
    public YxyUser findYxyUserByUsername(String username) {
        YxyUser userInfo = yxyUserMapper.getUserInfo(username);
        return userInfo;
    }

    @Override
    public boolean YxyuserRegister(YxyUser yxyUser) {
        //对密码进行加密
        String s = null;
        try {
            s = Base64.getEncoder().encodeToString(yxyUser.getYxyPassword().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        yxyUser.setYxyPassword(s);
        return yxyUserMapper.addYxyUser(yxyUser);
    }
}
