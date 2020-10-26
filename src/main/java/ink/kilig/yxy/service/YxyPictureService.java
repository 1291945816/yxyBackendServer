package ink.kilig.yxy.service;

import ink.kilig.yxy.domain.UploadPictureInfo;
import ink.kilig.yxy.domain.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Hps
 * @date: 2020/10/26 20:35
 * @description:
 */
public interface YxyPictureService {
    Result<String> uploadPicture(UploadPictureInfo uploadPictureInfo);
    byte[] getPictureByid(Long pictureId);
}
