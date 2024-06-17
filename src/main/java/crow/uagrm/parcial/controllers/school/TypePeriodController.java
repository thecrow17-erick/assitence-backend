package crow.uagrm.parcial.controllers.school;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.dto.school.typePeriod.CreateTypePeriodDto;
import crow.uagrm.parcial.models.TypePeriod;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.school.typePeriod.CreateTypePeriodResponse;
import crow.uagrm.parcial.response.school.typePeriod.ListTypePeriodResponse;
import crow.uagrm.parcial.services.school.TypePeriodService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping(path = "type-period")
public class TypePeriodController {

  @Autowired
  private TypePeriodService typePeriodService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Page<TypePeriod>> allTypesPeriod(
    @RequestParam(required = false) Integer skip,
    @RequestParam(required = false) Integer limit
  ) {
      return new ApiResponse<>(
        HttpStatus.OK.value(),
        "lista de tipos de periodos",
        this.typePeriodService.findAllTypePeriod(skip, limit)
      );
  }
  
  @GetMapping(path = "list-select")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ListTypePeriodResponse> selectCareers() {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE TIPOS DE PERIODOS PARA SELECCIONAR",
      new ListTypePeriodResponse(this.typePeriodService.findPeriods())
    );
  }


  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<CreateTypePeriodResponse> allTypesPeriod(
    @Valid @RequestBody CreateTypePeriodDto createTypePeriodDto
  ) {
    return new ApiResponse<>(
      HttpStatus.CREATED.value(),
      "tipo de periodo creado",
      new CreateTypePeriodResponse(this.typePeriodService.createTypePeriod(createTypePeriodDto))
    );
  }

  @GetMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateTypePeriodResponse> findIdTypePeriod(
    @PathVariable Long id
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "ver el tipo de periodo",
      new CreateTypePeriodResponse(this.typePeriodService.findTypePeriodById(id))
    );
  }

  @PutMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateTypePeriodResponse> updateTypePeriod(
    @PathVariable Long id,
    @Valid @RequestBody CreateTypePeriodDto createTypePeriodDto
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "se actualizo el tipo de periodo",
      new CreateTypePeriodResponse(this.typePeriodService.updateTypePeriodId(id, createTypePeriodDto))
    );
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateTypePeriodResponse> deleteIdTypePeriod(
    @PathVariable Long id
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "se elimino el tipo de periodo",
      new CreateTypePeriodResponse(this.typePeriodService.deleteTypePeriodById(id))
    );
  }
}
