package crow.uagrm.parcial.response.user;

import crow.uagrm.parcial.models.User;
import lombok.Data;

@Data
public class UserCreateResponse {
  private User user;

  public UserCreateResponse(User user){
    this.user = user;
  }
}
