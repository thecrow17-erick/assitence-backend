package crow.uagrm.parcial.controllers.auth;

import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.dto.auth.LoginDto;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.auth.ResponseAuthLogin;
import crow.uagrm.parcial.services.security.AuthService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "auth")
public class AuthController {
  
  @Autowired
  private AuthService authService;


  @PostMapping("login")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ApiResponse<ResponseAuthLogin> postMethodName(@Valid @RequestBody LoginDto loginDto) {
    return new ApiResponse<>(
      HttpStatus.ACCEPTED.value(),
      "Logeado correctamente",
      authService.login(loginDto)
    );
  }
  
}
