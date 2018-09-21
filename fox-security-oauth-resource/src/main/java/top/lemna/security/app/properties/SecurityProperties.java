package top.lemna.security.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "fox.security")
public class SecurityProperties {

  private OAuth2Properties oauth2 = new OAuth2Properties();
}
