package top.lemna.security.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private PasswordEncoder passwordEncoder;


  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

    if (!username.equals("user") && !username.equals("admin")) {
      throw new UsernameNotFoundException("User " + username + " not found.");
    }

    String password = passwordEncoder.encode("123456");
    return new User(username, password, true, true, true, true,
        getAuthorities(username));
  }

  private Collection<? extends GrantedAuthority> getAuthorities(String username) {
    final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    if(username.equals("user"))
    {

      authorities.add(new SimpleGrantedAuthority("PRIVILEGE_USER_READ"));
    }else {

      authorities.add(new SimpleGrantedAuthority("PRIVILEGE_ADMIN_READ"));
    }
    return authorities;
  }


}
