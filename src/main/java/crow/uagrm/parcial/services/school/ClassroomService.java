package crow.uagrm.parcial.services.school;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import crow.uagrm.parcial.dto.school.classroom.CreateClassroomDto;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.Classroom;
import crow.uagrm.parcial.repository.school.ClassroomRepository;
import crow.uagrm.parcial.repository.school.ModuleRepository;
import java.awt.image.BufferedImage;


@Service
public class ClassroomService {
  
  @Autowired
  private ClassroomRepository classroomRepository;

  @Autowired
  private ModuleRepository moduleRepository;

  public Page<Classroom> findAllClassroom(int skip,int limit){
    Pageable pageable = PageRequest.of(skip, limit);
    return this.classroomRepository.findAll(pageable);
  }

  public List<Classroom> findClassrooms(){
    return this.classroomRepository.findByStatus(true);
  }

  public Classroom createClassroom(CreateClassroomDto createClassroomDto){
    var findClassNro = this.classroomRepository.findByNro(createClassroomDto.getNro());
    var moduleFind = this.moduleRepository.findById(createClassroomDto.getModule_id());
    if(findClassNro.isPresent()){
      if(moduleFind.isPresent() && moduleFind.get().getId() == findClassNro.get().getModule().getId()){
        throw new BadRequestException(
        "esta aula ya pertenece a este modulo"
        );
      }
    } 
    if(!moduleFind.isPresent()){
      throw new NotFoundException(
        "ingrese un modulo valido"
      );
    }

    var classroomCreate = new Classroom(
      createClassroomDto.getNro(), 
      createClassroomDto.getDescription(), 
      moduleFind.get()
    );
    return this.classroomRepository.save(classroomCreate);
  }

  public Classroom findByIdClassroom(Long id){
    var findClassroom = this.classroomRepository.findById(id);
    if(!findClassroom.isPresent()){
      throw new NotFoundException(
        "classroom id " +id+" not found"
      );
    }
    return findClassroom.get();
  }

  public Classroom updateClassroomById(Long id,CreateClassroomDto createClassroomDto){
    var classFindId = this.findByIdClassroom(id);
    var findClassNro = this.classroomRepository.findByNro(createClassroomDto.getNro());
    var moduleFind = this.moduleRepository.findById(createClassroomDto.getModule_id());
    if(findClassNro.isPresent() && findClassNro.get().getId() != classFindId.getId()){
      if(moduleFind.isPresent() && moduleFind.get().getId() == findClassNro.get().getModule().getId()){
        throw new BadRequestException(
        "esta aula ya pertenece a este modulo"
        );
      }
    } 
    if(!moduleFind.isPresent()){
      throw new NotFoundException(
        "ingrese un modulo valido"
      );
    }

    classFindId.setDescription(createClassroomDto.getDescription());
    classFindId.setNro(createClassroomDto.getNro());
    classFindId.setModule(moduleFind.get());
    
    return this.classroomRepository.save(classFindId);
  }

  public Classroom deleteClassroom(Long id){
    var findClassroom = this.findByIdClassroom(id);

    findClassroom.setStatus(!findClassroom.isStatus());
  
    return this.classroomRepository.save(findClassroom);
  }
  
  public BufferedImage generateQRCode(Long id)throws Exception{
    var findClassroom = this.findByIdClassroom(id);

    UUID codeQr = UUID.randomUUID();
    findClassroom.setCodeQR(codeQr);

    this.classroomRepository.save(findClassroom);

    Map<EncodeHintType, Object> hintMap = new HashMap<>();
    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

    BitMatrix bitMatrix = new MultiFormatWriter()
      .encode(codeQr.toString(), BarcodeFormat.QR_CODE, 300, 300,hintMap); 
  
    return MatrixToImageWriter.toBufferedImage(bitMatrix);
  }


  public Classroom findQRClassroom(UUID qrCode){
    var findQr = this.classroomRepository.findByCodeQR(qrCode);
    if(findQr.isEmpty()){
      throw new NotFoundException(
        "El aula no esta registrado en la bd"
      );
    }
    return findQr.get();
  }
}
