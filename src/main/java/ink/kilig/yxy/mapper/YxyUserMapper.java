package ink.kilig.yxy.mapper;

import ink.kilig.yxy.domain.YxyUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author: Hps
 * @date: 2020/10/20 21:48
 * @description:
 */
@Mapper
@Repository
public interface YxyUserMapper {
    @Select("select * from yxyUser where yxyUserName=#{username}")
    YxyUser getUserInfo(String username);

    @Insert("insert into yxyUser(yxyUserName,yxyPassword,yxyNickName) value(#{yxyUserName},#{yxyPassword},#{yxyNickName})")
    boolean addYxyUser(YxyUser yxyUser);

    @Update("update yxyUser set yxyUserAvatar=#{filePath} where yxyUserName=#{username}")
    boolean uploadUserAvatar(String filePath,String username);


    @Update("update yxyUser set yxyNickName=#{nickname} where yxyUserName=#{username}")
    void updateUserNickname(String nickname,String username);

    @Update("update yxyUser set yxyPassword=#{password} where yxyUserName=#{username}")
    void updatePassword(String password,String username);

    @Update("update yxyUser set yxyUserIntro=#{yxyUserIntro} where yxyUserName=#{username}")
    void updateIntro(String yxyUserIntro,String username);




}
