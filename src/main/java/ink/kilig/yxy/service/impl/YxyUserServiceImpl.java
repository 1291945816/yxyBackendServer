package ink.kilig.yxy.service.impl;

import ink.kilig.yxy.domain.YxyUser;
import ink.kilig.yxy.mapper.YxyUserMapper;
import ink.kilig.yxy.service.YxyUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Base64;
import java.util.Objects;

/**
 * @author: Hps
 * @date: 2020/10/20 21:56
 * @description:
 */
@Service
public class YxyUserServiceImpl implements YxyUserService {
    private final Logger logger=LoggerFactory.getLogger(YxyUserServiceImpl.class);

    private final YxyUserMapper yxyUserMapper;

    private final String fileRootPath;


    @Autowired
    public YxyUserServiceImpl(YxyUserMapper yxyUserMapper,
                              @Value("${file.avatar}") String fileRootPath) {
        this.fileRootPath=fileRootPath;
        this.yxyUserMapper=yxyUserMapper;
    }


    @Override
    public YxyUser findYxyUserByUsername(String username) {
        YxyUser userInfo = yxyUserMapper.getUserInfo(username);
        return userInfo;
    }

    @Override
    public boolean YxyuserRegister(YxyUser yxyUser) throws DuplicateKeyException {
        //对密码进行加密
        String hex = DigestUtils.sha256Hex(yxyUser.getYxyPassword());
        yxyUser.setYxyPassword(hex);
        return yxyUserMapper.addYxyUser(yxyUser);
    }

    /**
     * 上传头像
     * @param yxyUser
     * @return
     */
    @Override
    public boolean uploadAvatar(MultipartFile avatar, YxyUser yxyUser) {
        String filePath = (new StringBuffer(this.fileRootPath))
                .append(yxyUser.getYxyUserName())
                .append(Objects.requireNonNull(avatar.getOriginalFilename())
                .substring(avatar.getOriginalFilename().lastIndexOf('.')))
                .toString();

        File file = new File(filePath);
        if (!file.exists()){
            file.mkdirs();
        }else
        {
            try {
                //保存文件
                avatar.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info(filePath);
            //这里应该是将路径存到数据库中
            yxyUserMapper.uploadUserAvatar(filePath,yxyUser.getYxyUserName());
             return true;
        }
        return false;
    }

    /**
     * 更新昵称
     * @param nickname
     * @param username
     */
    @Override
    public void updateUserNickNameByusername(String nickname, String username) {
        yxyUserMapper.updateUserNickname(nickname,username);
    }

    /**
     * 修改密码
     * @param oldPwd
     * @param newPwd
     * @param username
     * @return
     */
    @Override
    public boolean changepasswordByusername(String oldPwd, String newPwd, String username) {
        YxyUser userInfo = yxyUserMapper.getUserInfo(username);
        if(!DigestUtils.sha256Hex(oldPwd).equals(userInfo.getYxyPassword())) return false;
        String newpassword = DigestUtils.sha256Hex(newPwd);
        yxyUserMapper.updatePassword(newpassword,username);
        return true;
    }

    @Override
    public void updateUserIntro(String userIntro, String username) {
        yxyUserMapper.updateIntro(userIntro,username);
    }

    @Override
    public YxyUser getUserInfo(String username) {
        YxyUser userInfo = yxyUserMapper.getUserInfo(username);
        userInfo.setYxyUserAvatar("");
        userInfo.setYxyPassword("");
        return userInfo;
    }
}