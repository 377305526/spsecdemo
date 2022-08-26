package cn.com.reformer.config.handler;

import cn.com.reformer.utils.ApiWrapMapper;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
 * @Date: 2022/08/25/9:50
 * @Description: 自定义登陆成功handler
 */
@Component
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {


    /**
     * 前后端分离
     * 成功的登陆应该返回给前端对应的数据
     *
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        //TODO 返回前端成功数据
        log.info("登陆成功处理");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(ApiWrapMapper.success("登陆成功").toString());
        writer.flush();
        writer.close();
    }

}
