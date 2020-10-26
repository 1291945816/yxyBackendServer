package ink.kilig.yxy.mapper;

import ink.kilig.yxy.po.PictureInfoPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

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

    @Select("select picturePath from yxyPicture where pictureId=#{pictureId} ")
    String getPicturePath(Long pictureId);
}
