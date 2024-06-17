package crow.uagrm.parcial.utils;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import crow.uagrm.parcial.models.Role;
import crow.uagrm.parcial.models.User;
import crow.uagrm.parcial.repository.user.RoleRepository;
import crow.uagrm.parcial.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class Runner  implements CommandLineRunner{
  
  private final UserRepository userRepository;  
  private final RoleRepository roleRepository;  

  @Override
  public void run(String... args)throws Exception{
    if(this.roleRepository.count() == 0){
      this.roleRepository.saveAll(
        List.of(
          new Role(RoleName.ADMIN),
          new Role(RoleName.DOCENTE)
        )
      );
    }

    if(this.userRepository.count() == 0){
      this.userRepository.saveAll(
        List.of(
          new User(
          "admin", 
          "admin@admin.com", 
          new BCryptPasswordEncoder().encode("123456"), 
          "78162812",
          List.of(this.roleRepository.findByName(RoleName.ADMIN).get())
          ),
          new User(
            "Peinado",
            "peinado@gmail.com",
            new BCryptPasswordEncoder().encode("123456"),
            "61223491",
            List.of(this.roleRepository.findByName(RoleName.DOCENTE).get())
          )
        )
      );
    }
  }

}
