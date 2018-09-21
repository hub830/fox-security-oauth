package top.lemna.security.app.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import top.lemna.security.app.support.SimpleResponse;

@Slf4j
@Component
public class FoxAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {
    log.info("登录失败");
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.getWriter().write(JSON.toJSONString(new SimpleResponse(exception.getMessage())));
  }
}
