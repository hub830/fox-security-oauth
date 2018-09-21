package top.lemna.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("top.lemna.security")
@SpringBootApplication
public class AuthorizationDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthorizationDemoApplication.class, args);
  }
}
