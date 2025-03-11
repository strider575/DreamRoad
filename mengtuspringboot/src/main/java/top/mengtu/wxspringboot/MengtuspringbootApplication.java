package top.mengtu.wxspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@MapperScan("top.mengtu.wxspringboot.mapper")
public class MengtuspringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MengtuspringbootApplication.class, args);
    }

    @GetMapping("/hello")
    public String Hello() {
        return "Hello World!";
    }

}
