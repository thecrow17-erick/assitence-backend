package crow.uagrm.parcial.controllers.user;

import crow.uagrm.parcial.dto.user.CreateUserDto;
import crow.uagrm.parcial.models.User;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.user.UserCreateResponse;
import crow.uagrm.parcial.services.user.UserService;
import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;






@RestController
@RequestMapping(path = "user")
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping(path = "teacher")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Page<User>> getMethodName(
    @RequestParam(required = false) Integer skip,
    @RequestParam(required = false) Integer limit    
    ){
      var users  = userService.findAllUser(skip, limit);
      return new ApiResponse<>(HttpStatus.OK.value(), "list users", users);
  }
  
  @PostMapping(path = "teacher")
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<UserCreateResponse> postMethodName(@Valid @RequestBody CreateUserDto createUserDto) {
    return new ApiResponse<>(
      HttpStatus.CREATED.value(),
      "USUARIO CREADO",
      new UserCreateResponse(userService.UserCreate(createUserDto))
    );
  }

  @PutMapping(path="teacher/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<UserCreateResponse> putMethodName(@PathVariable Long id) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "PASSWORD DEL USUARIO ACTUALIZADO",
      new UserCreateResponse(this.userService.ResetPasswordById(id))
    );
  }


  @DeleteMapping(path="teacher/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<UserCreateResponse> deleteUserById(@PathVariable Long id) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "USUARIO ELIMINADO",
      new UserCreateResponse(this.userService.DeleteUserTeacher(id))
    );
  }
}
