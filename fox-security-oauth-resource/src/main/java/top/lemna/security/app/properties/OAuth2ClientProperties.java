package top.lemna.security.app.properties;

import lombok.Data;

@Data
public class OAuth2ClientProperties {

  private String clientId;

  private String clientSecret;

  private int accessTokenValidateSeconds = 7200;
}
