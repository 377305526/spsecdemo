package cn.com.reformer.config;

import cn.com.reformer.utils.MD5Util;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sun.security.provider.MD5;

import java.util.Base64;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author Nipppppp
 * @Date: 2022/08/25/9:41
 * @Description: 自定义密码加密类
 * <p>
 * <p>
 * 目前对于密码加密仅使用MD5方式做demo
 * 正式开始编写的时候 可以参考keyfree对密码加盐的形式
 *
 */
@Component
public class PassWordEncode implements PasswordEncoder {


    /**
     * 加密
     *
     * @param rawPassword
     * @return
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.getMD5String(rawPassword.toString());
    }

    /**
     * securtiy主要的对比方式
     * loadUser之后拿到数据库加密的密码
     * 和当前拿到的页面密码最比较
     * 如果相同 会调用succesHandler 如果不同 调用failHandler
     *
     * @param rawPassword     前端传递的明文密码
     * @param encodedPassword 数据库中的加密密码
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.getMD5String(rawPassword.toString()));
    }

}
