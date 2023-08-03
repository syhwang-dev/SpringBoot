package edu.pnu;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class ToiletProjectApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(ToiletProjectApplication.class, args);
//	}
//
//}
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import edu.pnu.config.WebConfig;
@SpringBootApplication
@Import(WebConfig.class) // WebConfig 클래스 등록
public class ToiletProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ToiletProjectApplication.class, args);
	}
}
