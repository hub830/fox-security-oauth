package top.lemna.security.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "fox.security.oauth")
public class SecurityProperties {

  private String jwtSigningKey = "s1f41234pwqdqkl4l12ghg9853123sd";
}
