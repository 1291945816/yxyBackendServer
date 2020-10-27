package ink.kilig.yxy.mapper;

import ink.kilig.yxy.po.PictureInfoPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author: Hps
 * @date: 2020/10/26 21:10
 * @description:
 */
@Mapper
@Repository
public interface YxyPictureMapper {
    @Insert("insert into yxyPicture(pictureName,pictureCreateTime,picturePath,publishVisiable" +
            ",pictureInfo,ablumId) values(#{pictureName},#{pictureCreateTime},#{picturePath},#{publishVisiable},#{pictureInfo},#{ablumId})")
    boolean upload(PictureInfoPO pictureInfoPO);

    @Select("select picturePath from yxyPicture where pictureId=#{pictureId} limit 1")
    String getPicturePath(Long pictureId);

    @Select("SELECT\n" +
            "	yxyUser.yxyNickName,\n" +
            "	yxyUser.yxyUserName,\n" +
            "	yxyPicture.starNum,\n" +
            "	yxyPicture.downloadSum,\n" +
            "	yxyPicture.pictureId,\n" +
            "	yxyPicture.pictureInfo,\n" +
            "	yxyPicture.pictureName,\n" +
            "	yxyPicture.picturePath,\n" +
            "	yxyPicture.publishVisiable,\n" +
            "	yxyPicture.pictureCreateTime\n" +
            "FROM\n" +
            "	yxyUser,\n" +
            "	yxyPicture,\n" +
            "	yxyUserAlbum \n" +
            "WHERE\n" +
            "	yxyUser.yxyUserName = yxyUserAlbum.yxyUserName \n" +
            "	AND yxyUserAlbum.ablumId = yxyPicture.ablumId \n" +
            "	AND yxyPicture.publishVisiable = TRUE\n"+
            "   LIMIT #{pageNum},#{size}")
    List<PictureInfoPO> getPulishPicture(Long pageNum,Long size);

    @Select("select pictureId from yxyUserStar where yxyUserName=#{username} and isStar=1")
    List<String> getStaredPictureByusername(String username);

    @Select("select isStar from yxyUserStar where yxyUserName=#{username} and pictureId=#{pictureId} limit 1")
    boolean isPictureExist(String username,String pictureId);

    @Update("UPDATE yxyUserStar SET yxyUserStar.isStar=0 WHERE pictureId=#{pictureId} AND yxyUserName=#{username} ")
    void updatStar(String username,String pictureId);

    @Update("UPDATE yxyPicture SET yxyPicture.starNum=#{starNum} WHERE yxyPicture.pictureId=#{pictureId}")
    void updateStarNum(String starNum,String pictureIs);

    @Insert("insert into yxyUserStar(yxyUserName,pictureId,isStar) values(#{yxyUserName},#{pictureId},1)")
    void insertStar(String yxyUserName,String pictureId);



}
