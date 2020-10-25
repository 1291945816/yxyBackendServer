package ink.kilig.yxy.mapper;

import ink.kilig.yxy.domain.Ablum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author: Hps
 * @date: 2020/10/24 4:04
 * @description:
 */
@Mapper
@Repository
public interface YxyUserAblumMapper {
    @Insert("insert into yxyUserAlbum(ablumName,ablumCreateTime,yxyUserName) values(#{ablumName},#{ablumCreateTime},#{username})")
     void insertAblum(String ablumName,String ablumCreateTime,String username);
}
