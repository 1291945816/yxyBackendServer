package ink.kilig.yxy.po;

/**
 * @author: Hps
 * @date: 2020/11/2 20:58
 * @description:
 */
public class CommentPO {
    private String yxyUserName;
    private String pictureId;

    private String comment;
    private String commentTime;

    public String getYxyUserName() {
        return yxyUserName;
    }

    public void setYxyUserName(String yxyUserName) {
        this.yxyUserName = yxyUserName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    @Override
    public String toString() {
        return "CommentPO{" +
                "yxyUserName='" + yxyUserName + '\'' +
                ", comment='" + comment + '\'' +
                ", commentTime='" + commentTime + '\'' +
                '}';
    }
}
