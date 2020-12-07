package ink.kilig.yxy.domain;

/**
 * @author: Hps
 * @date: 2020/10/24 3:59
 * @description:
 */
public class Ablum {
    private String ablumId;
    private String ablumName;
    private int nums;

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    private String ablumCreateTime;

    public String getAblumId() {
        return ablumId;
    }

    public void setAblumId(String ablumId) {
        this.ablumId = ablumId;
    }

    public String getAblumName() {
        return ablumName;
    }

    public void setAblumName(String ablumName) {
        this.ablumName = ablumName;
    }

    public String getAblumCreateTime() {
        return ablumCreateTime;
    }

    public void setAblumCreateTime(String ablumCreateTime) {
        this.ablumCreateTime = ablumCreateTime;
    }

    @Override
    public String toString() {
        return "Ablum{" +
                "ablumId='" + ablumId + '\'' +
                ", ablumName='" + ablumName + '\'' +
                ", ablumCreateTime='" + ablumCreateTime + '\'' +
                '}';
    }
}
