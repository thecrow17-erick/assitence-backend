package crow.uagrm.parcial.services.user;

import lombok.AllArgsConstructor;
import crow.uagrm.parcial.dto.user.CreateUserDto;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.User;
import crow.uagrm.parcial.repository.user.RoleRepository;
import crow.uagrm.parcial.repository.user.UserRepository;
import crow.uagrm.parcial.utils.RoleName;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService { 

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  public User UserCreate(CreateUserDto createUserDto){
    var findUser = this.userRepository.findByEmail(createUserDto.getEmail());
    if(findUser.isPresent()){
      throw new BadRequestException(
        "El usuario con email "+ findUser.get().getEmail() +" se encuentra en el sistema"
      );
    }
    findUser = this.userRepository.findByPhone(createUserDto.getPhone());
    if(findUser.isPresent()){
      throw new BadRequestException(
        "El numero +591 "+findUser.get().getPhone()+" ya se encuentra en uso en el sistema"
      );
    }

    try {
      var role = this.roleRepository.findByName(RoleName.DOCENTE);
      // Hacer algo con el roleName
      String leeterUser=String.valueOf(createUserDto.getName().charAt(0)); 
      var userCreate = new User(
        createUserDto.getName().toLowerCase(), 
        createUserDto.getEmail(), 
        new BCryptPasswordEncoder().encode(
          leeterUser.toUpperCase().toUpperCase()+"."+createUserDto.getPhone()
        ), 
        createUserDto.getPhone(),     
        List.of(
          role.get()
        )
      );
      this.userRepository.save(userCreate);
      return userCreate;
  } catch (IllegalArgumentException e) {
      throw new NotFoundException("Rol not found");
  }

  }

  public Page<User> findAllUser(int skip, int limit){
    Pageable pageable = PageRequest.of(skip, limit);
    var role = this.roleRepository.findByName(RoleName.DOCENTE);
    var users = this.userRepository.findByRoles(List.of(role.get()) ,pageable);
    return users;
  }

  public User findUserById(Long id){
    var findUserId = this.userRepository.findById(id);
    if(findUserId.isEmpty()){
      throw new NotFoundException(
        "user not found"
      );
    }
    return findUserId.get();
  }


  public Optional<User> findByUserName(String name){
    var user = this.userRepository.findByName(name);
    return user;
  }

  @Transactional
  public User ResetPasswordById(Long id){
    var findUserId = this.userRepository.findById(id);
    if(findUserId.isPresent()){
      User userUpdate = findUserId.get();
      String leeterUser=String.valueOf(userUpdate.getName().charAt(0)); 
      userUpdate.setPassword(
        new BCryptPasswordEncoder().encode(
          leeterUser.toUpperCase()+"."+userUpdate.getPhone()
        )
      );
      return this.userRepository.save(userUpdate);
    }else{
      throw new NotFoundException(
        "User with id" +id+" not found"
      );
    }
  }

  @Transactional
  public User DeleteUserTeacher(Long id){
    var findUserId = this.userRepository.findById(id);
    if(findUserId.isPresent()){
      User userUpdate = findUserId.get();
      userUpdate.setStatus(!userUpdate.isStatus());
      return this.userRepository.save(userUpdate);
    }else{
      throw new NotFoundException(
        "User with id" +id+" not found"
      );
    }
  } 
  

}
