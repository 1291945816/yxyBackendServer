package ink.kilig.yxy.service;

import ink.kilig.yxy.domain.CommentInfo;
import ink.kilig.yxy.domain.YxyUser;
import ink.kilig.yxy.po.PictureInfoPO;
import ink.kilig.yxy.vo.PictureVO;
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

    Result<List<PictureVO>> getPublishPicture(String username,Long pageNum,Long size);
    Result<String> star(@RequestBody Map<String,String> map,HttpServletRequest request);
    Result<String> downloadCount(String pictureId);
    Result<String> commentPicture(String pictureId,String comment,String username);
    Result<List<CommentInfo>> getComments(String pictureId);
    Result<PictureInfoPO> getPictureDetail(String pictureId);


}
