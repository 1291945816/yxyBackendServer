package ink.kilig.yxy.service;

import ink.kilig.yxy.domain.CommentInfo;
import ink.kilig.yxy.domain.PictureVO;
import ink.kilig.yxy.domain.UploadPictureInfo;
import ink.kilig.yxy.domain.Result;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: Hps
 * @date: 2020/10/26 20:35
 * @description:
 */
public interface YxyPictureService {
    Result<String> uploadPicture(UploadPictureInfo uploadPictureInfo);
//    byte[] getPictureByid(Long pictureId);
//    byte[] getThumbnailByid(Long pictureId);
    Result<List<PictureVO>> getPublishPicture(String username,Long pageNum,Long size);
    Result<String> star(@RequestBody Map<String,String> map,HttpServletRequest request);
    Result<String> downloadCount(String pictureId);
    Result<String> commentPicture(String pictureId,String comment,String username);
    Result<List<CommentInfo>> getComments(String pictureId);

}
