package top.lemna.security.app.properties;

import lombok.Data;

@Data
public class OAuth2Properties {

  private String jwtSigningKey = "fox";

  private OAuth2ClientProperties[] clients = {};
}
