package top.lemna.security.app.config;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import top.lemna.security.app.properties.OAuth2ClientProperties;
import top.lemna.security.app.properties.SecurityProperties;

@Configuration
@EnableAuthorizationServer
public class FoxAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private final JwtAccessTokenConverter jwtAccessTokenConverter;

  private final BCryptPasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired(required = false)
  private TokenEnhancer jwtTokenEnhancer;

  public FoxAuthorizationServerConfig(JwtAccessTokenConverter jwtAccessTokenConverter,
      BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
    this.jwtAccessTokenConverter = jwtAccessTokenConverter;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(new JwtTokenStore(jwtAccessTokenConverter))
        .authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter);

    if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
      TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
      List<TokenEnhancer> enhancers = new ArrayList<>();
      enhancers.add(jwtTokenEnhancer);
      enhancers.add(jwtAccessTokenConverter);
      enhancerChain.setTokenEnhancers(enhancers);
      endpoints.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter);
    }
  }

  @Override
  public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {

    InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
    if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
      for (OAuth2ClientProperties client : securityProperties.getOauth2().getClients()) {
        builder//
            .withClient(client.getClientId())//
            .secret(passwordEncoder.encode(client.getClientSecret()))//
            .authorizedGrantTypes("refresh_token", "password")//
            .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds())//
            .refreshTokenValiditySeconds(2592000)//
            .scopes("all");
      }
    }
  }

}
