package ink.kilig.yxy.mapper;

import ink.kilig.yxy.domain.Ablum;
import ink.kilig.yxy.domain.PrivatePicture;
import ink.kilig.yxy.domain.YxyUser;
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
            "FROM yxyPicture c\n" +
            "WHERE  c.ablumId=#{ablumId} ORDER BY c.pictureCreateTime DESC ")
    List<PrivatePicture> getPictureOfAlbum(String ablumId);






    /**
     * 获取用户点赞集合
     */

    @Select("\tSELECT\n" +
            "\t\tc.yxyNickName,\n" +
            "\t\tc.yxyUserName\n" +
            "\tFROM yxyUserStar b,yxyUser c\n" +
            "\tWHERE  b.yxyUserName = c.yxyUserName AND b.pictureId=#{pictureId}AND b.isStar=TRUE")
    List<YxyUser> getStaredDetails(String pictureId);


    /**
     * 更新状态
     */
    @Update("UPDATE yxyPicture as a SET a.publishVisiable=#{publish} WHERE a.pictureId=#{pictureId}")
    public void updatePublish(boolean publish,String pictureId);


    @Delete("DELETE from yxyPicture WHERE yxyPicture.pictureId=#{pictureId}")
    public void deletePicture(String pictureId);


}
