package top.lemna.security.login.app.validate.code;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import top.lemna.user.TestAuthorizationDemoApplication;

/**
 * 密码模式登录
 * 
 * @author mux
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestAuthorizationDemoApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
public class JwtTokenEnhancerTest {

  @Autowired
  TestRestTemplate template;


  /**
   * TOKEN可以通过USER模块的登录测试用例获得
   */
  private String token =
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NDY4NzIyMDksInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUFJJVklMRUdFX0FETUlOX1JFQUQiXSwianRpIjoiOGQ3OThmNWMtMWM5ZS00ZWNjLTg0ZjgtOTVhNGVjMTZmODllIiwiY2xpZW50X2lkIjoiZm94Iiwic2NvcGUiOlsiYWxsIl19.aPmF06LPdVYzuxTa32yYl2jfjRV_7yW4FFgLHezZlug";

  private HttpHeaders headers;

  @Before
  public void setup() {

    headers = new HttpHeaders();
    headers.add("deviceId", "007");
    headers.add("api-version", "1.0");
    headers.add("Authorization", "bearer " + token);// 登录
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);


  }

  @Test
  public void testMe() {
    MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();// 参数放入一个map中，restTemplate不能用hashMap
    // 将请求参数放入map中
    HttpEntity<MultiValueMap<String, String>> request =
        new HttpEntity<MultiValueMap<String, String>>(param, headers);

    ResponseEntity<String> response =
        template.exchange("/user/me", HttpMethod.GET, request, String.class);
    log.info("-----------------------------------------------------------------------");
    log.info("-----------------请求完成，返回 response:{}", response);
    log.info("-----------------------------------------------------------------------");
  }
}
