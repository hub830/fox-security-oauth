package top.lemna.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import top.lemna.security.app.properties.SecurityProperties;

@Configuration
@EnableWebSecurity
@ComponentScan("top.lemna.security.app")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityProperties securityProperties;


  /*
   * @Override protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
   * auth.userDetailsService(userDetailsService);
   * auth.authenticationProvider(accountAuthenticationProvider); }
   */
  /*
   * @Override
   * 
   * @Bean public AuthenticationManager authenticationManagerBean() throws Exception { return
   * super.authenticationManagerBean(); }
   */

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    jwtAccessTokenConverter.setSigningKey(securityProperties.getOauth2().getJwtSigningKey());
    return jwtAccessTokenConverter;
  }

}
