package cn.com.reformer.config;


import cn.com.reformer.config.handler.*;
import cn.com.reformer.service.impl.UserLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author Nipppppp
 * @Date: 2022/08/24/14:19
 * @Description: 安全配置
 * <p>
 * springboot2.0\spring5.0之后WebSecurityConfigurerAdapter已经过时
 * 本次采用配置过滤链的形
 * </p>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)    // 启用方法级别的权限认证
@Slf4j
public class SecurityConfig {


    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailHandler loginFailHandler;
    @Autowired
    private LogOutHandler logOutHandler;
    @Autowired
    private NoAuthHandler noAuthHandler;
    @Autowired
    private UserLoginServiceImpl loginService;
    @Autowired
    private PassWordEncode passWordEncode;
    @Autowired
    private ToLoginPageHandler loginPageHandler;


    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //给默认的认证方式重写实现方法
        http.getSharedObject(AuthenticationManagerBuilder.class).
                userDetailsService(loginService).
                passwordEncoder(passWordEncode);
        return http
                .httpBasic()
                .and()
                /*匿名请求：不须要进行登陆拦截的url*/
                .authorizeRequests()
                .antMatchers("/test","/checkLogin").permitAll()//这里可以对绝对路由进行放行
//                .antMatchers("/test/admin").hasRole("admin")//当用户的角色是为admin时能够访问这个目录
                .anyRequest().authenticated()//其余的路径都是登陆后才可访问
                .and()
                /*登陆配置*/
                .formLogin()
                .successHandler(loginSuccessHandler)//登陆成功处理
                .failureHandler(loginFailHandler)//登陆失败处理
                .loginProcessingUrl("/plusLogin")//前端登陆请求地址 springsecurity默认/login 要前后端分离的形式
                .permitAll()
                .and()
                /*登出配置*/
                .logout()
                .permitAll()
                .logoutSuccessHandler(logOutHandler) //退出处理
                .and()
                .exceptionHandling()
                .accessDeniedHandler(noAuthHandler)  //无权限时的处理
                .authenticationEntryPoint(loginPageHandler) //未登陆时处理
                .and()
                .cors() //跨域
                .and()

                .rememberMe().and()                //关闭csrf防御，相似于防火墙，不关闭上面的设置不会真正生效。
                .csrf().disable().build();


    }


    /**
     * 配置跨源访问(CORS)
     *
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


}
