package ink.kilig.yxy.mapper;

import ink.kilig.yxy.domain.YxyUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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

}
