package edu.pnu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
// @Override
// public void addCorsMappings(CorsRegistry registry) {
// registry.addMapping("/**")
// .allowedOrigins("http://10.125.121.189:3000")
// .allowedMethods("GET", "POST", "PUT", "DELETE");
// // You can add more configuration options as needed
// }
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*") // 허용할 오리진(도메인) 설정, "*"는 모든 도메인 허용
		.allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메소드 설정
		.allowedHeaders("*"); // 허용할 HTTP 헤더 설정
	}
}
