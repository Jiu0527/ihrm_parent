package cn.sh.softline.system;

import cn.sh.softline.common.utils.JwtUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("cn.sh.softline.system.mapper")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }

}
