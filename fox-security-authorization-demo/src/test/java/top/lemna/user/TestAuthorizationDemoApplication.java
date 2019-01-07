package top.lemna.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import top.lemna.security.app.properties.SecurityProperties;
import top.lemna.security.app.validate.code.ValidateCodeGenerator;
import top.lemna.security.user.validate.code.image.MyImageCodeGenerator;

@SpringBootApplication
public class TestAuthorizationDemoApplication {
  
  @Autowired
  private SecurityProperties securityProperties;

  @Bean
  public ValidateCodeGenerator imageValidateCodeGenerator() {
    MyImageCodeGenerator codeGenerator = new MyImageCodeGenerator();
    codeGenerator.setSecurityProperties(securityProperties);
    return codeGenerator;
  }
  
  public static void main(String[] args) {
    SpringApplication.run(TestAuthorizationDemoApplication.class, args);
  }
}
