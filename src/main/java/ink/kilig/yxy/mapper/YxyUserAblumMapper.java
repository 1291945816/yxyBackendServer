package ink.kilig.yxy.mapper;

import ink.kilig.yxy.domain.Ablum;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
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

    @Delete("delete from yxyUserAlbum where ablumId=#{ablumId} limit 1")
    boolean deleteAblum(String ablumId);

    @Update("update yxyUserAlbum set ablumName=#{newAblumName} where ablumId=#{ablumId} ")
    boolean changeAblum(String newAblumName,String ablumId);

}
