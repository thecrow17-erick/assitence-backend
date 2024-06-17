package crow.uagrm.parcial.controllers.school;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.dto.school.management.CreateManagementDto;
import crow.uagrm.parcial.models.Management;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.school.management.CreateManagementResponse;
import crow.uagrm.parcial.response.school.management.ListManagementResponse;
import crow.uagrm.parcial.services.school.ManagementService;
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
@RequestMapping(path = "management")
public class ManagementController {
  
  @Autowired
  private ManagementService managementService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Page<Management>> allManagement(
    @RequestParam(required = false) Integer skip,
    @RequestParam(required = false) Integer limit
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "list managements",
      this.managementService.findAllManagement(skip, limit)
    );
  }
  
  @GetMapping(path = "list-select")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ListManagementResponse> selectCareers() {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE GESTIONES PARA SELECCIONAR",
      new ListManagementResponse(this.managementService.findManagements())
    );
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<CreateManagementResponse> postMethodName(
    @Valid @RequestBody CreateManagementDto createManagementDto
    ) {
    return new ApiResponse<>(
      HttpStatus.CREATED.value(),
      "gestion creada",
      new CreateManagementResponse(this.managementService.createManagement(createManagementDto))
    );
  }

  @GetMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateManagementResponse> findIdManagement(
    @PathVariable Long id
    ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "find management",
      new CreateManagementResponse(this.managementService.findManagementById(id))
    );
  }
  
  @PutMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateManagementResponse> updateManagement(
    @PathVariable Long id,
    @Valid @RequestBody CreateManagementDto createManagementDto
    ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "gestion actualizada",
      new CreateManagementResponse(this.managementService.updateManagementId(id,createManagementDto))
    );
  }


  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateManagementResponse> deleteManagement(
    @PathVariable Long id
    ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "gestion eliminada",
      new CreateManagementResponse(this.managementService.deleteManagementById(id))
    );
  }
}
