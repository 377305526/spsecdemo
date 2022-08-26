package cn.com.reformer.controller;

import cn.com.reformer.service.IPlusAdminService;
import cn.com.reformer.utils.ApiWrapMapper;
import cn.com.reformer.utils.ApiWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author Nipppppp
 * @Date: 2022/08/24/9:35
 * @Description:
 */
@Controller
@AllArgsConstructor
@Slf4j
public class TestController {


    private final IPlusAdminService adminService;

    @GetMapping("/test")
    @ResponseBody
    public String test(String uid) {
        return "nipppppp";
    }

    @GetMapping("/test2")
    @ResponseBody
    @PreAuthorize("hasAuthority('keyfreeplus:member:search')")
    public String test2(String id) {
        return adminService.getById(id).toString();
    }


    @GetMapping("/test3")
    @ResponseBody
    @PreAuthorize("hasAuthority('keyfreeplus:member:add')")
    public String test3(String id) {
        return adminService.getById(id).toString();
    }


    @GetMapping("/checkLogin")
    @ResponseBody
    public ApiWrapper checkLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getPrincipal().toString().equals("anonymousUser")) {
            return ApiWrapMapper.success("您已登陆无需重复登陆");
        } else {
            return ApiWrapMapper.illegalParameter("您未登陆");
        }
    }
}