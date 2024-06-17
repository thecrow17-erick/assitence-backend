package crow.uagrm.parcial.response.auth;

import crow.uagrm.parcial.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseAuthLogin {
  private String token;
  private User   user;
}
