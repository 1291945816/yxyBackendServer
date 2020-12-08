package ink.kilig.yxy.domain;

/**
 * @author: Hps
 * @date: 2020/12/8 9:11
 * @description:
 */
public class YxyUserDetail extends YxyUser {
    private long starNums; //用户发表的图片点赞数
    private long publishSum; //用户发表的总数
    private long commentSum; //用户受评论的总数

    public long getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(long commentSum) {
        this.commentSum = commentSum;
    }

    public long getStarNums() {
        return starNums;
    }

    public void setStarNums(long starNums) {
        this.starNums = starNums;
    }

    public long getPublishSum() {
        return publishSum;
    }

    public void setPublishSum(long publishSum) {
        this.publishSum = publishSum;
    }

    @Override
    public String toString() {
        return "YxyUserDetail{" +
                "starNums=" + starNums +
                ", publishSum=" + publishSum +
                ", commentSum=" + commentSum +
                "} " + super.toString();
    }
}
