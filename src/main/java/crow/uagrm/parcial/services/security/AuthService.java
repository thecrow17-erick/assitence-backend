package crow.uagrm.parcial.services.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.dto.auth.LoginDto;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.error.exception.UnauthorizedException;
import crow.uagrm.parcial.repository.user.UserRepository;
import crow.uagrm.parcial.response.auth.ResponseAuthLogin;
import crow.uagrm.parcial.security.SecurityUser;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

  @Autowired
  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public ResponseAuthLogin login(LoginDto loginDto){
    var user = userRepository.findByEmail(loginDto.getEmail());
    if(!user.isPresent()){
      throw new NotFoundException("El email no se encuentra disponible");
    }
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.get().getName(), loginDto.getPassword()));
    } catch (BadCredentialsException e) {
      throw new UnauthorizedException(e.getLocalizedMessage());
    }
    UserDetails userDetails = user.map(SecurityUser::new).orElseThrow();
    String token = jwtService.getToken(userDetails);

    if(loginDto.getTokenFMC() != null){
      user.get().setTokenFMC(loginDto.getTokenFMC());
      this.userRepository.save(user.get());
    }

    return new ResponseAuthLogin(
      token,
      user.get()
    );
  }

  public boolean validatePassword(String rawPassword, String encodedPassword) {
    return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
  }
}
