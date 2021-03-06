package top.lemna.security.app.authorize;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;


@Component
public class FoxAuthorizeConfigManager implements AuthorizeConfigManager {

  @Autowired
  private List<AuthorizeConfigProvider> authorizeConfigProviders;

  @Override
  public void config(
      ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
    for (AuthorizeConfigProvider authorizeConfigProvider : authorizeConfigProviders) {
      authorizeConfigProvider.config(config);
    }
  }
}
