package crow.uagrm.parcial.security;

import org.springframework.security.core.GrantedAuthority;

import crow.uagrm.parcial.models.Role;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
  private final Role role;

  @Override
  public String getAuthority() {
    return role.getName().toString();
  }
}
