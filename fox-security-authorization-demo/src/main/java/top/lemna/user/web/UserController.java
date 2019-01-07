/**
 * 
 */
package top.lemna.user.web;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import top.lemna.security.app.properties.SecurityProperties;

/**
 * @author zhailiang
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private SecurityProperties securityProperties;
  
	@GetMapping("/me")
	public Object getCurrentUser(Authentication user, HttpServletRequest request) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
		
		Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
					.parseClaimsJws(token).getBody();
		
		String company = (String) claims.get("test");
		
		System.out.println(company);
		
		return user;
	}



}
