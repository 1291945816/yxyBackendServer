package ink.kilig.yxy.domain;

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
    private String authorProfileImgUrl; //图片作者信息
    private boolean isStared; //是否点赞
    private int downloadNum; //下载数量
    private String ImgDetailUrl;//图片详情链接  返回相关的信息

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

    public String getAuthorProfileImgUrl() {
        return authorProfileImgUrl;
    }

    public void setAuthorProfileImgUrl(String authorProfileImgUrl) {
        this.authorProfileImgUrl = authorProfileImgUrl;
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

    public String getImgDetailUrl() {
        return ImgDetailUrl;
    }

    public void setImgDetailUrl(String imgDetailUrl) {
        ImgDetailUrl = imgDetailUrl;
    }
}
