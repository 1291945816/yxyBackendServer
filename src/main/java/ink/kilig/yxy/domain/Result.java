package ink.kilig.yxy.domain;

/**
 * @author: Hps
 * @date: 2020/10/20 20:31
 * @description:
 */
public class Result<T> {

    private String code;
    private String message;
    private T data;
    public Result() {
    }
    public static <T> Result<T> success(T data,String message){
        Result<T> result=new Result<>();
        result.setCode(ResponseCode.SuccessResult.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    public static <T> Result<T> falure(String message){
        Result<T> result=new Result<>();
        result.setCode(ResponseCode.FailureResult.getCode());
        result.setMessage(message);
        result.setData(null);
        return result;
    }
    public static <T> Result<T> success(String message){
        Result<T> result=new Result<>();
        result.setCode(ResponseCode.SuccessResult.getCode());
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
