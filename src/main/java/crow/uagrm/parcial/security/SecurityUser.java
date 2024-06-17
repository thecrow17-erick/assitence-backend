package crow.uagrm.parcial.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import crow.uagrm.parcial.models.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

  private final User user;

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getName();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return user.getRoles().stream().map(SecurityAuthority::new).toList();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
  
}
