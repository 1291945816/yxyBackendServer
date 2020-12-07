package ink.kilig.yxy.po;

import org.springframework.stereotype.Component;

/**
 * @author: Hps
 * @date: 2020/10/26 21:16
 * @description:
 */

public class PictureInfoPO {
    private long pictureId;
    private String pictureName;
    private String pictureCreateTime;
    private String picturePath;
    private int starNum;
    private boolean publishVisiable;
    private String pictureInfo;
    private long ablumId;
    private int downloadSum;
    private String yxyNickName;
    private String yxyUserName;
    private String thumbnailPath;
    private String yxyUserAvatar;

    public String getYxyUserAvatar() {
        return yxyUserAvatar;
    }

    public void setYxyUserAvatar(String yxyUserAvatar) {
        this.yxyUserAvatar = yxyUserAvatar;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getYxyNickName() {
        return yxyNickName;
    }

    public void setYxyNickName(String yxyNickName) {
        this.yxyNickName = yxyNickName;
    }

    public String getYxyUserName() {
        return yxyUserName;
    }

    public void setYxyUserName(String yxyUserName) {
        this.yxyUserName = yxyUserName;
    }

    @Override
    public String toString() {
        return "PictureInfoPO{" +
                "pictureId=" + pictureId +
                ", pictureName='" + pictureName + '\'' +
                ", pictureCreateTime='" + pictureCreateTime + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", starNum=" + starNum +
                ", publishVisiable=" + publishVisiable +
                ", pictureInfo='" + pictureInfo + '\'' +
                ", ablumId=" + ablumId +
                ", downloadSum=" + downloadSum +
                '}';
    }

    public long getPictureId() {
        return pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getPictureCreateTime() {
        return pictureCreateTime;
    }

    public void setPictureCreateTime(String pictureCreateTime) {
        this.pictureCreateTime = pictureCreateTime;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

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

    public long getAblumId() {
        return ablumId;
    }

    public void setAblumId(long ablumId) {
        this.ablumId = ablumId;
    }

    public int getDownloadSum() {
        return downloadSum;
    }

    public void setDownloadSum(int downloadSum) {
        this.downloadSum = downloadSum;
    }
}
