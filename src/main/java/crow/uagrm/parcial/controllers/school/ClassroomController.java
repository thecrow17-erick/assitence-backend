package crow.uagrm.parcial.controllers.school;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crow.uagrm.parcial.dto.school.classroom.CreateClassroomDto;
import crow.uagrm.parcial.models.Classroom;
import crow.uagrm.parcial.response.ApiResponse;
import crow.uagrm.parcial.response.school.classroom.CreateClassroomResponse;
import crow.uagrm.parcial.response.school.classroom.ListClassroomResponse;
import crow.uagrm.parcial.services.school.ClassroomService;
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
@RequestMapping(path = "classroom")
public class ClassroomController {
  
  @Autowired
  private ClassroomService classroomService;

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<Page<Classroom>> allClassroom(
    @RequestParam(required = false) Integer skip,
    @RequestParam(required = false) Integer limit 
  ) {
    return new ApiResponse<Page<Classroom>>(
      HttpStatus.OK.value(),
      "LISTA DE LAS AULAS",
      this.classroomService.findAllClassroom(skip, limit)
    );
  }

  @GetMapping(path = "list-select")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<ListClassroomResponse> selectCareers() {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "LISTA DE AULAS PARA SELECCIONAR",
      new ListClassroomResponse(this.classroomService.findClassrooms())
    );
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public ApiResponse<CreateClassroomResponse> createClassroom(
      @Valid @RequestBody CreateClassroomDto createClassroomDto
    ) {
    return new ApiResponse<>(
      HttpStatus.CREATED.value(),
      "AULA CREADA CORRECTAMENTE",
      new CreateClassroomResponse(this.classroomService.createClassroom(createClassroomDto))
    );
  }
  
  @GetMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateClassroomResponse> allClassroom(
    @PathVariable Long id
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "DETALLE DEL AULA",
      new CreateClassroomResponse(this.classroomService.findByIdClassroom(id))
    );
  }

  @PutMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateClassroomResponse> updateClassroom(
    @PathVariable Long id,
    @Valid @RequestBody CreateClassroomDto createClassroomDto
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "AULA ACTUALIZADA",
      new CreateClassroomResponse(this.classroomService.updateClassroomById(id, createClassroomDto))
    );
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public ApiResponse<CreateClassroomResponse> deleteClassroom(
    @PathVariable Long id
  ) {
    return new ApiResponse<>(
      HttpStatus.OK.value(),
      "AULA ELIMINADA",
      new CreateClassroomResponse(this.classroomService.deleteClassroom(id))
    );
  }

  @PostMapping(path = "{id}/generate-QR")
  public ResponseEntity<byte[]> generatedQR(
    @PathVariable Long id
  )throws Exception {
    BufferedImage qrImage = this.classroomService.generateQRCode(id);
    
    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    ImageIO.write(qrImage, "jpeg", baos);
    byte[] imageBytes = baos.toByteArray();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_JPEG);
    headers.setContentLength(imageBytes.length);
    return ResponseEntity.ok().headers(headers).body(imageBytes);

  }
  

}
