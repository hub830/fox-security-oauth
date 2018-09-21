package top.lemna.security.app.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;
import top.lemna.security.app.properties.SecurityConstants;

@Component
@Order(Integer.MIN_VALUE)
public class FoxAuthorizeConfigProvider implements AuthorizeConfigProvider {

    
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*")
        .permitAll();
    }

}

