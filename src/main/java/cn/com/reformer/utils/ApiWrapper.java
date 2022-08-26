package cn.com.reformer.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author Nipppppp
 * @Date: 2022/08/25/9:52
 * @Description:
 */

import java.io.Serializable;

public class ApiWrapper<T> implements Serializable {
    private static final long serialVersionUID = 4893280118017319089L;
    public static final int SUCCESS_CODE = 20000;
    public static final String SUCCESS_MESSAGE = "操作成功";
    public static final int ERROR_CODE = 50000;
    public static final String ERROR_MESSAGE = "内部异常";
    public static final int ILLEGAL_ARGUMENT_CODE = 40000;
    public static final String ILLEGAL_ARGUMENT_MESSAGE = "参数非法";
    public static final int NOT_AUTH_CODE = 40003;
    public static final String NOT_AUTH_MESSAGE = "权限验证错误";
    private int result;
    private String resultInfo;
    private T data;

    public ApiWrapper() {
    }

    public ApiWrapper(int result, String resultInfo) {
        this(result, resultInfo, (T) null);
    }

    public ApiWrapper(int result, String resultInfo, T data) {
        this.result(result).resultInfo(resultInfo).data(data);
    }

    private ApiWrapper<T> result(int result) {
        this.setResult(result);
        return this;
    }

    private ApiWrapper<T> resultInfo(String resultInfo) {
        this.setResultInfo(resultInfo);
        return this;
    }

    public ApiWrapper<T> data(T data) {
        this.setData(data);
        return this;
    }

    public boolean success() {
        return 20000 == this.result;
    }

    public boolean error() {
        return !this.success();
    }

    public int getResult() {
        return this.result;
    }

    public String getResultInfo() {
        return this.resultInfo;
    }

    public T getData() {
        return this.data;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ApiWrapper)) {
            return false;
        } else {
            ApiWrapper<?> other = (ApiWrapper)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getResult() != other.getResult()) {
                return false;
            } else {
                Object this$resultInfo = this.getResultInfo();
                Object other$resultInfo = other.getResultInfo();
                if (this$resultInfo == null) {
                    if (other$resultInfo != null) {
                        return false;
                    }
                } else if (!this$resultInfo.equals(other$resultInfo)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ApiWrapper;
    }


    @Override
    public String toString() {
        return "ApiWrapper(result=" + this.getResult() + ", resultInfo=" + this.getResultInfo() + ", data=" + this.getData() + ")";
    }
}
