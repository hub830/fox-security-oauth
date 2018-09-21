package top.lemna.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import top.lemna.security.app.authentication.FoxAuthenticationSuccessHandler;
import top.lemna.security.app.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import top.lemna.security.app.authorize.AuthorizeConfigManager;
import top.lemna.security.app.properties.SecurityConstants;
import top.lemna.security.app.validate.code.ValidateCodeSecurityConfig;

@Configuration
@EnableResourceServer
public class FoxResourceServerConfig extends ResourceServerConfigurerAdapter {
  @Autowired
  private JwtAccessTokenConverter jwtAccessTokenConverter;

  @Autowired
  private AuthenticationFailureHandler foxAuthenticationFailureHandler;

  @Autowired
  private FoxAuthenticationSuccessHandler foxAuthenticationSuccessHandler;

  @Autowired
  private ValidateCodeSecurityConfig validateCodeSecurityConfig;
  
  @Autowired
  private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

  @Autowired
  private AuthorizeConfigManager authorizeConfigManager;
  
  @Override
  public void configure(final ResourceServerSecurityConfigurer resources) {
    resources.tokenStore(new JwtTokenStore(jwtAccessTokenConverter));
  }

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    http//
        .formLogin()//
        .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)//
        .failureHandler(foxAuthenticationFailureHandler)//
        .successHandler(foxAuthenticationSuccessHandler)//
    ;

    http.apply(validateCodeSecurityConfig);
    http.apply(smsCodeAuthenticationSecurityConfig);
    
    http.csrf().disable();

    authorizeConfigManager.config(http.authorizeRequests());    
  }
}

