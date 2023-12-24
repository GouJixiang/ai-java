package com.occn.ai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AiJavaApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AiJavaApplication.class, args);
        log.info("----- AI Server 启动成功 -----");
    }

}
