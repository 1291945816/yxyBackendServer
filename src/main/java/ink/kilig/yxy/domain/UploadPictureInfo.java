package ink.kilig.yxy.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author: Hps
 * @date: 2020/10/26 20:41
 * @description:
 */
public class UploadPictureInfo implements Serializable {
    private boolean publishVisiable; //是否公布到广场
    private String pictureInfo; //照片的相关信息
    private String ablumId; //相c册Id
    private String pictureName; //照片名字
    private MultipartFile file; //文件信息


    public boolean isPublishVisiable() {
        return publishVisiable;
    }

    public void setPublishVisiable(boolean publishVisiable) {
        this.publishVisiable = publishVisiable;
    }

    public String getPictureInfo() {
        return pictureInfo;
    }

    public void setPictureInfo(String pictureInfo) {
        this.pictureInfo = pictureInfo;
    }

    public String getAblumId() {
        return ablumId;
    }

    public void setAblumId(String ablumId) {
        this.ablumId = ablumId;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
