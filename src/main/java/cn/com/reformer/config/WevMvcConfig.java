package cn.com.reformer.config;

import cn.com.reformer.config.filter.UserTokenFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author Nipppppp
 * @Date: 2022/08/24/15:23
 * @Description:
 */
@Configuration
@Slf4j
public class WevMvcConfig implements WebMvcConfigurer {



    /**
     * 配置的过滤器
     * <p>
     * 这里进行接口是否进行放行
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserTokenFilter())
                .addPathPatterns("/**");//所有接口都处理
//                .excludePathPatterns("/test");//除了test接口
    }


}
