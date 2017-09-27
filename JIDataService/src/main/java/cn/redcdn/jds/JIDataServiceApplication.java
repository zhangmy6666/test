package cn.redcdn.jds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = { "cn.redcdn.jds" })
public class JIDataServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(JIDataServiceApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JIDataServiceApplication.class);
	}

}
