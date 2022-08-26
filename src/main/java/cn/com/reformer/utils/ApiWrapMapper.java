package cn.com.reformer.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author Nipppppp
 * @Date: 2022/08/25/9:55
 * @Description:
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



public class ApiWrapMapper {
    private ApiWrapMapper() {
    }

    public static <E> ApiWrapper<E> illegalParameter() {
        return wrap(40000, "参数非法");
    }

    public static <E> ApiWrapper<E> illegalParameter(String resultInfo) {
        return wrap(40000, StringUtils.isBlank(resultInfo) ? "参数非法" : resultInfo);
    }

    public static <E> ApiWrapper<E> authFail(String resultInfo) {
        return wrap(40003, StringUtils.isBlank(resultInfo) ? "权限验证错误" : resultInfo);
    }

    public static <E> ApiWrapper<E> error() {
        return wrap(50000, "内部异常");
    }

    public static <E> ApiWrapper<E> error(String resultInfo) {
        return wrap(50000, StringUtils.isBlank(resultInfo) ? "内部异常" : resultInfo);
    }

    public static <E> ApiWrapper<E> success() {
        return wrap(20000, "操作成功");
    }

    public static <E> ApiWrapper<E> success(E o) {
        return wrap(20000, "操作成功", o);
    }

    public static <E> ApiWrapper<E> wrap(int result, String resultInfo, E o) {
        return new ApiWrapper(result, resultInfo, o);
    }

    public static <E> ApiWrapper<E> wrap(int result, String resultInfo) {
        return (ApiWrapper<E>) wrap(result, resultInfo, (Object)null);
    }
}

