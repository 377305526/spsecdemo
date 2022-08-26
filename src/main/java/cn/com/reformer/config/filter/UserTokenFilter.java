package cn.com.reformer.config.filter;

import cn.com.reformer.service.IAdminPermissionService;
import cn.com.reformer.service.IPlusAdminService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author Nipppppp
 * @Date: 2022/08/24/14:26
 * @Description: 接口handle处理
 */
@Slf4j
public class UserTokenFilter implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在springsecurity校验过后（true或忽略且webmvccofig未忽略 接口的预处理
        log.info("接口预处理");


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getPrincipal().toString().equals("anonymousUser")) {
            log.info("已登陆用户访问");
        } else {
            log.info("匿名登陆用户访问");
        }


        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
