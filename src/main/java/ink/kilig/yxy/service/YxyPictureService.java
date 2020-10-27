package ink.kilig.yxy.service;

import ink.kilig.yxy.domain.PictureVO;
import ink.kilig.yxy.domain.UploadPictureInfo;
import ink.kilig.yxy.domain.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hps
 * @date: 2020/10/26 20:35
 * @description:
 */
public interface YxyPictureService {
    Result<String> uploadPicture(UploadPictureInfo uploadPictureInfo);
    byte[] getPictureByid(Long pictureId);
    Result<List<PictureVO>> getPublishPicture(String username,Long pageNum,Long size);

}
