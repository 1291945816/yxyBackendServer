package ink.kilig.yxy.mapper;

import ink.kilig.yxy.domain.Ablum;
import ink.kilig.yxy.domain.PrivatePicture;
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
            "	a.ablumId AS ablumId,\n" +
            "	a.ablumName AS ablumName,\n" +
            "	a.ablumCreateTime AS ablumCreateTime,\n" +
            "	COUNT( b.pictureId ) AS nums \n" +
            "FROM\n" +
            "	yxyUserAlbum a\n" +
            "	LEFT JOIN yxyPicture b ON a.ablumId = b.ablumId \n" +
            "WHERE\n" +
            "	a.yxyUserName = #{username} \n" +
            "GROUP BY\n" +
            "	a.ablumId")
    List<Ablum> getAblumInfo(String username);


    @Select("SELECT\n" +
            "c.pictureId as id,\n" +
            "c.publishVisiable as publish,\n" +
            "c.thumbnailPath as thumbnailUrl,\n" +
            "c.picturePath as imgUrl\n" +
            "FROM yxyUser a,yxyUserAlbum b,yxyPicture c\n" +
            "WHERE a.yxyUserName=#{username} AND a.yxyUserName=b.yxyUserName AND c.ablumId=b.ablumId ORDER BY c.pictureCreateTime DESC LIMIT #{pageNum},#{size}")
    List<PrivatePicture> getPictureOfAlbum(String username,Long pageNum,Long size);
}
