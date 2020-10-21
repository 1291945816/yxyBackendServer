package ink.kilig.yxy.domain;

/**
 * @author: Hps
 * @date: 2020/10/20 20:33
 * @description:
 */
public enum ResponseCode {
    SuccessResult("200"),FailureResult("500");

    private String code;

    private ResponseCode(String code) {
        this.code = code;
    }



    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "code='" + code + '\'' +
                '}';
    }

    public void setCode(String code) {
        this.code = code;
    }
}
