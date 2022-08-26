package cn.com.reformer.config.handler;

import cn.com.reformer.utils.ApiWrapMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author Nipppppp
 * @Date: 2022/08/25/15:29
 * @Description: 未登录用户提醒登陆 重写方法为了重定向分离前端登录页 不走springsecurity默认/login
 */
@Component
@Slf4j
public class ToLoginPageHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("未登陆用户访问");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(ApiWrapMapper.error("您还登陆，请重新登陆").toString());
        writer.flush();
        writer.close();
    }
}
