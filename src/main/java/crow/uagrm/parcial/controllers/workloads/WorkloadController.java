package crow.uagrm.parcial.controllers.workloads;

import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.dto.workloads.workload.CreateWordloadDto;
import crow.uagrm.parcial.models.Workload;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.services.workloads.WorkloadService;
import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;



@RestController
@RequestMapping(path = "workload")
public class WorkloadController {
  
  @Autowired
  private WorkloadService workloadService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Page<Workload>> findAllWorkLoads(
    @RequestParam(required = false) Integer skip,
    @RequestParam(required = false) Integer limit, 
    @RequestParam(required = false) Long user_id 
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DEL USUARIO DE SUS CARGAS HORARIAS",
      this.workloadService.findWorkLoadUser(skip, limit, user_id)
    );
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<Workload> createdWorks(
    @Valid @RequestBody CreateWordloadDto createWordloadDto
  ) {
    return new ApiResponse<>(
      HttpStatus.CREATED.value(),
      "LISTA DEL USUARIO DE SUS CARGAS HORARIAS",
      this.workloadService.createWorkLoad(createWordloadDto)
    );
  }
  
}
