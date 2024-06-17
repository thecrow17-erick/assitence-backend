package crow.uagrm.parcial.controllers.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.dto.school.group.CreateGroupDto;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.school.group.CreateGroupResponse;
import crow.uagrm.parcial.response.school.group.ListGroupResponse;
import crow.uagrm.parcial.services.school.GroupService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "group")
public class GroupController {
  
  @Autowired
  private GroupService groupService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ListGroupResponse> getMethodName() {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE GRUPOS",
      new ListGroupResponse(this.groupService.findAllGroups())
    );
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<CreateGroupResponse> postMethodName(
    @Valid @RequestBody CreateGroupDto createGroupDto
  ) {
    return new ApiResponse<>(
      HttpStatus.CREATED.value(),
      "GRUPO CREADO",
      new CreateGroupResponse(this.groupService.createdGroup(createGroupDto))
    );
  }
  
  

}
