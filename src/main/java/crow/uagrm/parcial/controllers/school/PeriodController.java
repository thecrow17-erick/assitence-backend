package crow.uagrm.parcial.controllers.school;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.dto.school.period.CreatePeriodDto;
import crow.uagrm.parcial.models.Period;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.school.period.CreatePeriodResponse;
import crow.uagrm.parcial.response.school.period.ListPeriodResponse;
import crow.uagrm.parcial.services.school.PeriodService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "period")
public class PeriodController {
  
  @Autowired
  private PeriodService periodService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Page<Period>> allPeriods(
    @RequestParam(required = false) Integer skip,
    @RequestParam(required = false) Integer limit
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE PERIODOS",
      this.periodService.findAllPeriods(skip, limit)
    );
  }
  
  @GetMapping(path = "list-select")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ListPeriodResponse> selectCareers() {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE PERIODOS PARA SELECCIONAR",
      new ListPeriodResponse(this.periodService.findPeriods())
    );
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<CreatePeriodResponse> createPeriod(
    @Valid @RequestBody CreatePeriodDto createPeriodDto  
  ){
    return new ApiResponse<>(
      HttpStatus.CREATED.value(),
      "PERIODO CREADO",
      new CreatePeriodResponse(this.periodService.createPeriod(createPeriodDto))
    );
  }

  @PutMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreatePeriodResponse> updatePeriod(
    @PathVariable Long id,
    @Valid @RequestBody CreatePeriodDto createPeriodDto
  ){
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "PERIODO ACTUALIZADO",
      new CreatePeriodResponse(this.periodService.updatePeriod(id,createPeriodDto))
    );
  }

  @GetMapping(path = "{id}")  
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreatePeriodResponse> getPeriodId(
    @PathVariable Long id 
  ){
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "VER ELIMINADO",
      new CreatePeriodResponse(this.periodService.findByIdPeriod(id))
    );
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreatePeriodResponse> deletePeriod(
    @PathVariable Long id 
  ){
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "PERIODO ELIMINADO",
      new CreatePeriodResponse(this.periodService.deletePeriod(id))
    );
  }

}
