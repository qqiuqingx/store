package cn.tedu.store;


import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@MapperScan("cn.tedu.store.mapper")
@Configuration
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
	@Bean
	public MultipartConfigElement multipartConfigMax() {
		MultipartConfigFactory mcf=new MultipartConfigFactory();
		mcf.setMaxFileSize(DataSize.ofBytes(100*1024*1024));
		mcf.setMaxRequestSize(DataSize.ofBytes(100*1024*1024));
		MultipartConfigElement mce=
				mcf.createMultipartConfig();
		return mce;
	}
}
