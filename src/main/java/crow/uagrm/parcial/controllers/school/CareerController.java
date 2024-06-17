package crow.uagrm.parcial.controllers.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.dto.school.career.CreateCareerDto;
import crow.uagrm.parcial.models.Career;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.school.career.CareerCreateResponse;
import crow.uagrm.parcial.response.school.career.ListCareerResponse;
import crow.uagrm.parcial.services.school.CareerService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "career")
public class CareerController {
  @Autowired
  private CareerService careerService;


  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Page<Career>> getMethodName(
    @RequestParam(required = false) Integer skip,
    @RequestParam(required = false) Integer limit  
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE LAS CARRERAS",
      this.careerService.findAllCareer(skip,limit)
    );
  }

  @GetMapping(path = "list-select")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ListCareerResponse> selectCareers() {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE CARRERAS PARA SELECCIONAR",
      new ListCareerResponse(this.careerService.findCareers())
    );
  }
  

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<CareerCreateResponse> careerCreate(@Valid @RequestBody CreateCareerDto createCareerDto) {
    return new ApiResponse<>(
      HttpStatus.CREATED.value(),
      "CARRERA CREADA CORRECTAMENTE",
      new CareerCreateResponse(this.careerService.createCareer(createCareerDto))
    );
  }
  
  
  @GetMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CareerCreateResponse> careerFindId(
    @PathVariable Long id
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LA CARRERA" + id,
      new CareerCreateResponse(this.careerService.findCareerById(id))
    );
  }


  @PutMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CareerCreateResponse> updatedById(
    @PathVariable Long id,
    @Valid @RequestBody CreateCareerDto createCareerDto
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "SE ACTUALIZO LA CARRERA",
      new CareerCreateResponse(this.careerService.updateCareerById(id, createCareerDto))
    );
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CareerCreateResponse> deleteById(
    @PathVariable Long id
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "SE ACTUALIZO LA CARRERA",
      new CareerCreateResponse(this.careerService.deleteCareerById(id))
    );
  }
  
}
