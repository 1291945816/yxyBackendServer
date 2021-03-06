package ink.kilig.yxy.mapper;

import ink.kilig.yxy.domain.CommentInfo;
import ink.kilig.yxy.domain.YxyUser;
import ink.kilig.yxy.po.CommentPO;
import ink.kilig.yxy.po.PictureInfoPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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
            ",pictureInfo,ablumId,thumbnailPath) values(#{pictureName},#{pictureCreateTime},#{picturePath},#{publishVisiable},#{pictureInfo},#{ablumId},#{thumbnailPath})")
    boolean upload(PictureInfoPO pictureInfoPO);

    @Select("select picturePath from yxyPicture where pictureId=#{pictureId} limit 1")
    String getPicturePath(Long pictureId);

    @Select("SELECT\n" +
            "yxyUser.yxyUserAvatar,\n"+
            "	yxyUser.yxyNickName,\n" +
            "	yxyUser.yxyUserName,\n" +
            "	yxyPicture.starNum,\n" +
            "	yxyPicture.downloadSum,\n" +
            "	yxyPicture.pictureId,\n" +
            "	yxyPicture.pictureInfo,\n" +
            "	yxyPicture.pictureName,\n" +
            "	yxyPicture.picturePath,\n" +
            "	yxyPicture.publishVisiable,\n" +
            "yxyPicture.thumbnailPath,"+
            "	yxyPicture.pictureCreateTime\n" +
            "FROM\n" +
            "	yxyUser,\n" +
            "	yxyPicture,\n" +
            "	yxyUserAlbum \n" +
            "WHERE\n" +
            "	yxyUser.yxyUserName = yxyUserAlbum.yxyUserName \n" +
            "	AND yxyUserAlbum.ablumId = yxyPicture.ablumId \n" +
            "	AND yxyPicture.publishVisiable = TRUE\n"+
            "order by yxyPicture.pictureCreateTime desc"+
            "   LIMIT #{pageNum},#{size}")
    List<PictureInfoPO> getPulishPicture(Long pageNum,Long size);

    @Select("select pictureId from yxyUserStar where yxyUserName=#{username} and isStar=1")
    List<String> getStaredPictureByusername(String username);

    @Select("select isStar from yxyUserStar where yxyUserName=#{username} and pictureId=#{pictureId} limit 1")
    boolean isPictureExist(String username,String pictureId);

    @Update("UPDATE yxyUserStar SET yxyUserStar.isStar=#{isStar} WHERE pictureId=#{pictureId} AND yxyUserName=#{username} ")
    void updatStar(String username,String pictureId,boolean isStar);

    @Update("UPDATE yxyPicture SET yxyPicture.starNum=#{starNum} WHERE yxyPicture.pictureId=#{pictureId}")
    void updateStarNum(long starNum,String pictureId);

    @Select("select yxyPicture.starNum from yxyPicture where yxyPicture.pictureId=#{pictureId} ")
    long getStarNum(String pictureId);

    @Select("select * from yxyPicture where yxyPicture.pictureId=#{pictureId} ")
    PictureInfoPO getPictureInfo(String pictureId);

    @Update("UPDATE yxyPicture SET yxyPicture.downloadSum=#{downloadSum} WHERE yxyPicture.pictureId=#{pictureId}")
    void updateDownloadSum(int downloadSum,String pictureId);


    @Insert("insert into yxyUserStar(yxyUserName,pictureId,isStar) values(#{yxyUserName},#{pictureId},1)")
    void insertStar(String yxyUserName,String pictureId);

    @Update("UPDATE yxyUserComment SET yxyUserComment.comment=#{comment},yxyUserComment.comment_time=#{commentTime} WHERE pictureId=#{pictureId} AND yxyUserName=#{yxyUserName} ")
    void updateComment(CommentPO commentPO);

    @Insert("insert into yxyUserComment values(#{yxyUserName},#{pictureId},#{comment},#{commentTime})")
    void insertComment(CommentPO commentPO);

    @Select(
            "SELECT\n" +
                    "	yxyUser.yxyUserName,\n" +
                    "	yxyUser.yxyNickName,\n" +
                    "yxyUser.yxyUserAvatar,\n"+
                    "	yxyUserComment.`comment`,\n" +
                    "	yxyUserComment.comment_time \n" +
                    "FROM\n" +
                    "	yxyUserComment\n" +
                    "	JOIN yxyUser ON yxyUser.yxyUserName = yxyUserComment.yxyUserName \n" +
                    "WHERE\n" +
                    "	yxyUserComment.pictureId=#{pictureId}"
    )
    List<CommentInfo> getCommentInfo(String pictureId);

    @Select("SELECT\n" +
            "pictureInfo,\n" +
            "a.pictureName,\n" +
            "a.starNum,\n" +
            "a.pictureCreateTime,\n" +
            "a.downloadSum,\n" +
            "b.yxyUserName,\n" +
            "b.yxyNickName,\n" +
            "b.yxyUserAvatar\n" +
            "FROM yxyPicture a,yxyUser b,yxyUserAlbum c\n" +
            "WHERE\n" +
            "a.pictureId=#{pictureId} AND a.ablumId=c.ablumId AND b.yxyUserName =c.yxyUserName")
    PictureInfoPO getPictureDetail(String pictureId);



}
