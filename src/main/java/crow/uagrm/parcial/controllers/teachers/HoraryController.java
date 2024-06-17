package crow.uagrm.parcial.controllers.teachers;

import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.teacher.AllHoraryResponse;
import crow.uagrm.parcial.response.teacher.AllMatterHoraryResponse;
import crow.uagrm.parcial.services.teachers.HoraryService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping(path = "horary")
public class HoraryController {
  
  @Autowired
  private HoraryService horaryService;

  @GetMapping(path = "{id}/days")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<AllHoraryResponse> getMethodName(
    @PathVariable Long id
  ) {
      return new ApiResponse<>(
        HttpStatus.OK.value(),
        "TODOS LOS HORARIOS DEL DOCENTE",
        new AllHoraryResponse(this.horaryService.findByClassUser(id))
      );
  }

  @GetMapping(path = "{id}/matter")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<AllMatterHoraryResponse> mattersUsers(
    @PathVariable Long id
  ) {
      return new ApiResponse<>(
        HttpStatus.OK.value(),
        "TODOS LAS MATERIAS DEL DOCENTE",
        new AllMatterHoraryResponse(this.horaryService.findByDetailWorkUser(id))
      );
  }
  

}
