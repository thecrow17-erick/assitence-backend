package crow.uagrm.parcial.services.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.repository.user.UserRepository;
import crow.uagrm.parcial.security.SecurityUser;

@Service
public class SecurityUserService implements UserDetailsService{
  private final UserRepository userRepository;
  
  public SecurityUserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username)   {
    var optUser = this.userRepository.findByEmail(username);
    if(optUser.isPresent()){
      return new SecurityUser(optUser.get());
    }
    System.out.println("error aqui");
    throw new UsernameNotFoundException("User not found"+ username);
  }
  

  
}
