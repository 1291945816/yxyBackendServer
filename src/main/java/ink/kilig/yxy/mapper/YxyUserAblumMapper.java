package ink.kilig.yxy.mapper;

import ink.kilig.yxy.domain.Ablum;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("SELECT\n" +
            "ablumId,\n" +
            "ablumName,\n" +
            "ablumCreateTime\n" +
            "FROM yxyUserAlbum\n" +
            "WHERE yxyUserAlbum.yxyUserName=#{username}\n")
    List<Ablum> getAblumInfo(String username);
}
