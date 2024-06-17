package crow.uagrm.parcial.controllers.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.dto.school.matter.CreateMatterDto;
import crow.uagrm.parcial.models.Matter;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.school.matter.ListMatterResponse;
import crow.uagrm.parcial.response.school.matter.MatterCreateResponse;
import crow.uagrm.parcial.services.school.MatterService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping(path = "matter")
public class MatterController {
  
  @Autowired
  private MatterService matterService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Page<Matter>> listMatters(
    @RequestParam(required = false) Integer skip,
    @RequestParam(required = false) Integer limit 
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE LAS MATERIAS",
      this.matterService.findAllMatter(skip, limit)
    );
  }

  @GetMapping(path = "list-select")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ListMatterResponse> selectCareers() {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE MATERIAS PARA SELECCIONAR",
      new ListMatterResponse(this.matterService.findMatters())
    );
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<MatterCreateResponse> createMatter(
    @Valid @RequestBody CreateMatterDto createMatterDto
  ) {
    return new ApiResponse<>(
      HttpStatus.CREATED.value(),
      "MATERIA CREADA",
      new MatterCreateResponse(this.matterService.createMatter(createMatterDto))
    );
  }
  

  @GetMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<MatterCreateResponse> byIdMatter(
    @PathVariable Long id
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE LAS MATERIAS",
      new MatterCreateResponse(this.matterService.findMatterById(id))
    );
  }

  @PutMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<MatterCreateResponse> updateMatter(
    @PathVariable Long id,
    @Valid @RequestBody CreateMatterDto createMatterDto
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "MATERIA ACTUALIZADA",
      new MatterCreateResponse(this.matterService.updateMatter(id,createMatterDto))
    );
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<MatterCreateResponse> deleteMatter(
    @PathVariable Long id
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "MATERIA ELIMINADA",
      new MatterCreateResponse(this.matterService.deleteMatterId(id))
    );
  }
}
