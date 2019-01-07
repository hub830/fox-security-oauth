package top.lemna.security.app.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import top.lemna.security.app.jwt.FoxJwtTokenEnhancer;

@Configuration
public class JwtSecurityConfig {

  @Bean
  @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
  public TokenEnhancer jwtTokenEnhancer() {
    return new FoxJwtTokenEnhancer();
  }
}

