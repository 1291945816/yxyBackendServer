package ink.kilig.yxy.domain;

/**
 * @author: Hps
 * @date: 2020/11/1 20:03
 * @description:
 */
public class CommentInfo {
    private String yxyUserName; //评论人id
    private String yxyNickName; //评论人
    private String comment;//评论内容
    private String comment_time; //评论时间
    private String yxyUserAvatar; //评论人头像

    public String getYxyUserAvatar() {
        return yxyUserAvatar;
    }

    public void setYxyUserAvatar(String yxyUserAvatar) {
        this.yxyUserAvatar = yxyUserAvatar;
    }

    @Override
    public String toString() {
        return "CommentInfo{" +
                "yxyUserName='" + yxyUserName + '\'' +
                ", yxyNickName='" + yxyNickName + '\'' +
                ", comment='" + comment + '\'' +
                ", comment_time='" + comment_time + '\'' +
                '}';
    }

    public String getYxyUserName() {
        return yxyUserName;
    }

    public void setYxyUserName(String yxyUserName) {
        this.yxyUserName = yxyUserName;
    }

    public String getYxyNickName() {
        return yxyNickName;
    }

    public void setYxyNickName(String yxyNickName) {
        this.yxyNickName = yxyNickName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }
}
