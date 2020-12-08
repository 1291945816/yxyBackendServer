package ink.kilig.yxy.mapper;

import ink.kilig.yxy.domain.YxyUser;
import ink.kilig.yxy.domain.YxyUserDetail;
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
    @Select("select u.yxyUserName,u.yxyPassword  from yxyUser u where yxyUserName=#{username}")
    YxyUser getUserInfo(String username);

    @Select("SELECT \n" +
            " a.yxyUserName,\n" +
            " a.yxyUserIntro,\n" +
            " a.yxyNickName,\n" +
            "	a.yxyUserAvatar,\n" +
            "	a.yxyPassword,\n" +
            " SUM(starNum) as starNums,\n" +
            " SUM(c.publishVisiable) as publishSum\n" +
            " FROM\n" +
            "  yxyUser a,yxyUserAlbum b, yxyPicture c\n" +
            "	WHERE\n" +
            "	a.yxyUserName=b.yxyUserName AND b.ablumId=c.ablumId AND a.yxyUserName=#{username} ")
    YxyUserDetail getUserDetail(String username);

    @Select("SELECT \n" +
            "COUNT(d.`comment`)\n" +
            " FROM\n" +
            "  yxyUser a,yxyUserAlbum b, yxyPicture c,yxyUserComment d\n" +
            "	WHERE\n" +
            "	a.yxyUserName=b.yxyUserName AND b.ablumId=c.ablumId AND a.yxyUserName=#{username} AND c.pictureId=d.pictureId")
    long getCommentByUsername(String username);




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
