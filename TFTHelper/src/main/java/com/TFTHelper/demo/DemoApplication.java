package com.TFTHelper.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication {

	public static int N = 0;
	public static List<Deck> metaDecks = new ArrayList<>();
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		TFTHelper tftHelper = TFTHelper.getInstance();
//		InputService inputService = InputService.getInstance();
		// 기본값 설정
		
		tftHelper.init(N, metaDecks);	// 추천 메타 설정하기 ( 서버를 띄움과 동시에 실행)
	}

	
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5500")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }
}
