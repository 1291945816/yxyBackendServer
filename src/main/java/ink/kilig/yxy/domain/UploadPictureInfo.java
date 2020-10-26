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
    private String ablumId; //相册Id
    private String pittureName; //照片名字
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

    public String getPittureName() {
        return pittureName;
    }

    @Override
    public String toString() {
        return "UploadPictureInfo{" +
                "publishVisiable=" + publishVisiable +
                ", pictureInfo='" + pictureInfo + '\'' +
                ", ablumId='" + ablumId + '\'' +
                ", pittureName='" + pittureName + '\'' +
                ", file=" + file +
                '}';
    }

    public void setPittureName(String pittureName) {
        this.pittureName = pittureName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
