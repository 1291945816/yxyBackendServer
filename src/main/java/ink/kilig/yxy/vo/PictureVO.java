package ink.kilig.yxy.vo;

/**
 * @author: Hps
 * @date: 2020/10/26 22:15
 * @description:
 */
public class PictureVO  {
    private String imgID; //图片id
    private String displayImgUrl; //图片的url
    private String displayImgName; //展示的图片名称
    private int    starNum; //图片的点赞数量
    private String authorName; //作者名称
    private String authorId;//作者ID
    private boolean isStared=false; //是否点赞
    private int downloadNum; //下载数量
    private String thumbnailUrl; //缩图
    private String authorAvatar;

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getImgID() {
        return imgID;
    }

    public void setImgID(String imgID) {
        this.imgID = imgID;
    }

    public String getDisplayImgUrl() {
        return displayImgUrl;
    }

    public void setDisplayImgUrl(String displayImgUrl) {
        this.displayImgUrl = displayImgUrl;
    }

    public String getDisplayImgName() {
        return displayImgName;
    }

    public void setDisplayImgName(String displayImgName) {
        this.displayImgName = displayImgName;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public boolean isStared() {
        return isStared;
    }

    public void setStared(boolean stared) {
        isStared = stared;
    }

    public int getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(int downloadNum) {
        this.downloadNum = downloadNum;
    }

}
