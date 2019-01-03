package top.lemna.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import lombok.extern.slf4j.Slf4j;

/**
 * Oauth 密码登录模式
 * 
 * @author mux
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OauthPasswordControllerTest {

  @Autowired
  TestRestTemplate restTemplate;
  HttpEntity<MultiValueMap> requestEntity;

  @Before
  public void setup() {
    // body
    MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    requestBody.add("grant_type", "password");
    requestBody.add("username", "admin");
    requestBody.add("password", "123456");
    requestBody.add("scope", "read");
    // HttpEntity
    requestEntity = new HttpEntity<MultiValueMap>(requestBody);

  }

  @Test
  public void testSignin() {
    // post
    ResponseEntity<String> responseEntity =
        restTemplate.withBasicAuth("fox", "foxsecret").postForEntity("/oauth/token", requestEntity, String.class);
    log.info("--------------------- responseEntity:{}", responseEntity.getBody());
  }
  
  @Test
  public void testSignin2() {
    

    ResponseEntity<String> response =
        restTemplate.withBasicAuth("fox", "foxsecret").postForEntity("/oauth/token?grant_type=password&username=admin&password=123456&scope=all", requestEntity, String.class);
    
    log.info("--------------------- responseEntity:{}", response.getBody());
  }

}
