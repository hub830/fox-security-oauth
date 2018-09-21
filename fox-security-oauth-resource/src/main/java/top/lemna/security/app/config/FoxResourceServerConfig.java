package top.lemna.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import top.lemna.security.app.authorize.AuthorizeConfigManager;

@Configuration
@EnableResourceServer
public class FoxResourceServerConfig extends ResourceServerConfigurerAdapter {
  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Autowired
  private AuthorizeConfigManager authorizeConfigManager;

  @Override
  public void configure(final ResourceServerSecurityConfigurer resources) {
    resources.tokenStore(new JwtTokenStore(jwtAccessTokenConverter));
  }

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().anyRequest().authenticated();

    authorizeConfigManager.config(http.authorizeRequests());
  }
}

