package top.lemna.security.app.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.lemna.security.app.properties.SecurityProperties;
import top.lemna.security.app.validate.code.image.ImageCodeGenerator;
import top.lemna.security.app.validate.code.sms.DefaultSmsCodeSender;
import top.lemna.security.app.validate.code.sms.SmsCodeSender;

@Configuration
public class ValidateCodeBeanConfig {
    
    @Autowired
    private SecurityProperties securityProperties;
    
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator(); 
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }
    
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
