package crow.uagrm.parcial.controllers.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.dto.school.module.CreateModuleDto;
import crow.uagrm.parcial.models.Module;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.school.module.CreateModuleResponse;
import crow.uagrm.parcial.response.school.module.ListModuleResponse;
import crow.uagrm.parcial.services.school.ModuleService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping(path = "module")
public class ModuleController {
  
  @Autowired
  private ModuleService moduleService;


  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Page<Module>> findAllModules(
    @RequestParam(required = false) Integer skip,
    @RequestParam(required = false) Integer limit
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE MODULOS",
      this.moduleService.findAll(skip, limit)
    );
  }


  @GetMapping(path = "list-select")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ListModuleResponse> allmodules() {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE MODULOS",
      new ListModuleResponse(this.moduleService.finModules())
    );
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<CreateModuleResponse> postMethodName(
    @Valid @RequestBody CreateModuleDto createModuleDto
  ) {
    return new ApiResponse<CreateModuleResponse>(
      HttpStatus.CREATED.value(), 
      "MODULO CREADO", 
      new CreateModuleResponse(this.moduleService.createModule(createModuleDto))
    );
  }
  

  @GetMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateModuleResponse> findAllModules(
    @PathVariable Long id
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "DETALLES DEL MODULO",
      new CreateModuleResponse(this.moduleService.findByIdModule(id))
    );
  }

  @PutMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateModuleResponse> putMethodName(
    @PathVariable Long id, 
    @Valid @RequestBody CreateModuleDto createModuleDto
    ) {
      return new ApiResponse<>(
        HttpStatus.OK.value(), 
        "MODULO ACTUALIZADO", 
        new CreateModuleResponse(this.moduleService.updateModuleIdd(id, createModuleDto))
      );
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateModuleResponse> deleteModule(
    @PathVariable Long id 
    ) {
      return new ApiResponse<>(
        HttpStatus.OK.value(), 
        "MODULO ELIMINADO", 
        new CreateModuleResponse(this.moduleService.deleteModuleId(id))
      );
  }
}
