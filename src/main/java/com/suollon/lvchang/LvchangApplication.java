package com.suollon.lvchang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class LvchangApplication {

	public static void main(String[] args) {
		SpringApplication.run(LvchangApplication.class, args);
	}

}
