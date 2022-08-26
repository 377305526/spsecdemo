package cn.com.reformer.config.handler;

import cn.com.reformer.utils.ApiWrapMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
 * @Date: 2022/08/25/15:13
 * @Description: 无权限
 */
@Component
@Slf4j
public class NoAuthHandler implements AccessDeniedHandler {

    /**
     * 访问无权限时的处理
     *
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("无权限处理");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(ApiWrapMapper.authFail("您没有该权限").toString());
        writer.flush();
        writer.close();
    }
}
