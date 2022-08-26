package cn.com.reformer;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * <p>
 * 启动类
 * </p>
 *
 * @author Nipppppp
 * @since 2022-08-24
 */
@SpringBootApplication
@MapperScan("cn.com.reformer.mapper")
public class KeyfreePlusApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeyfreePlusApplication.class, args);
    }
}
