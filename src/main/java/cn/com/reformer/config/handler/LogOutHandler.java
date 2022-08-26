package cn.com.reformer.config.handler;

import cn.com.reformer.utils.ApiWrapMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
 * @Date: 2022/08/25/15:11
 * @Description: 退出处理
 */
@Component
@Slf4j
public class LogOutHandler implements LogoutSuccessHandler {

    //放置一些退出方法
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登出成功");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(ApiWrapMapper.success("登出成功").toString());
        writer.flush();
        writer.close();

    }
}
