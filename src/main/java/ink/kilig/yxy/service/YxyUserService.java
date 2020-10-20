package ink.kilig.yxy.service;

import ink.kilig.yxy.domain.YxyUser;

/**
 * @author: Hps
 * @date: 2020/10/20 21:56
 * @description:
 */
public interface YxyUserService {
    YxyUser findYxyUserByUsername(String username);

    boolean YxyuserRegister(YxyUser yxyUser);
}
