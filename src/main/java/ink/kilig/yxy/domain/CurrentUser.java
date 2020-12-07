package ink.kilig.yxy.domain;

import java.lang.annotation.*;

/**
 * @author: Hps
 * @date: 2020/12/7 20:27
 * @description: 用于标记对象 获取当前的用户的信息
 */
@Target({ElementType.PARAMETER})  //所修饰的对象范围
@Retention(RetentionPolicy.RUNTIME) //保留的时间
@Documented
public @interface CurrentUser {
}
