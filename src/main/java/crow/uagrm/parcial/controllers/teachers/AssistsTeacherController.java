package crow.uagrm.parcial.controllers.teachers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import crow.uagrm.parcial.dto.teacher.MarkAssistsDto;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.teacher.AllAssistsTeacherResponse;
import crow.uagrm.parcial.response.teacher.MarkAssistResponse;
import crow.uagrm.parcial.response.teacher.MarkAssistVirtualResponse;
import crow.uagrm.parcial.response.teacher.MarkToleranceResponse;
import crow.uagrm.parcial.services.teachers.AssistsTeacherService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping(path = "assists")
public class AssistsTeacherController {
  @Autowired
  private AssistsTeacherService assistsTeacherService;

  @GetMapping(path = "{id}/class")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<AllAssistsTeacherResponse> getMethodName(
    @PathVariable Long id
  ) {
      System.out.println(id);
      return new ApiResponse<>(
        HttpStatus.OK.value(),
        "LAS CLASES DE HOY DIA",
        new AllAssistsTeacherResponse(this.assistsTeacherService.allAssistsPending(id))
      );
  }


  @PutMapping(path = "{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ApiResponse<MarkAssistResponse> markAssists(
    @PathVariable Long id,
    @Valid @RequestBody MarkAssistsDto assistsDto
  ) {
      return new ApiResponse<>(
        HttpStatus.ACCEPTED.value(),
        "ASISTENCIA MARCADA",
        new MarkAssistResponse(this.assistsTeacherService.markAssistanceQr(assistsDto.getCodeQr(), id))
      );
  }
  

  @PutMapping(path = "{id}/virtual")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ApiResponse<MarkAssistVirtualResponse> markAssists(
    @PathVariable Long id,
    @RequestBody MultipartFile file
  ) {
      return new ApiResponse<>(
        HttpStatus.ACCEPTED.value(),
        "ASISTENCIA VIRTUAL MARCADA",
        new MarkAssistVirtualResponse(this.assistsTeacherService.markAssistsVirtual(id, file))
      );
  }

  @PutMapping(path = "{id}/tolerance")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public ApiResponse<MarkToleranceResponse> markTolerance(
    @PathVariable Long id,
    @RequestBody MultipartFile file
  ) {
      return new ApiResponse<>(
        HttpStatus.ACCEPTED.value(),
        "ASISTENCIA VIRTUAL MARCADA",
        new MarkToleranceResponse(this.assistsTeacherService.markToleranceVirtual(id, file))
      );
  }
}
