package ink.kilig.yxy.service;

import ink.kilig.yxy.domain.YxyUser;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author: Hps
 * @date: 2020/10/20 21:56
 * @description:
 */
public interface YxyUserService {
    YxyUser findYxyUserByUsername(String username);
    boolean YxyuserRegister(YxyUser yxyUser) throws DuplicateKeyException;
    boolean uploadAvatar(MultipartFile avatar, YxyUser yxyUser);
    void updateUserNickNameByusername(String nickname,String username);
    boolean changepasswordByusername(String oldPwd,String newPwd,String username);
    void updateUserIntro(String userIntro,String username);
    YxyUser getUserInfo(String username);

}
