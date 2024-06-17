package crow.uagrm.parcial.services.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.dto.school.typePeriod.CreateTypePeriodDto;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.TypePeriod;
import crow.uagrm.parcial.repository.school.TypePeriodRepository;

@Service
public class TypePeriodService {
  
  @Autowired
  private TypePeriodRepository typePeriodRepository;

  public Page<TypePeriod> findAllTypePeriod(
    int skip,
    int limit
  ){
    Pageable pageable = PageRequest.of(skip, limit);
    var managements = this.typePeriodRepository.findAll(pageable);
    return managements;
  }

  public List<TypePeriod> findPeriods(){
    return this.typePeriodRepository.findByStatus(true);
  }


  public TypePeriod createTypePeriod(CreateTypePeriodDto createTypePeriodDto){
    var findType = this.typePeriodRepository.findByDescription(createTypePeriodDto.getDescription());
    if(findType.isPresent()){
      throw new BadRequestException(
        "el la descripcion se encuentra ocupado"
      );
    }

    var managementCreate = new TypePeriod(
      createTypePeriodDto.getDescription()
    );
    return this.typePeriodRepository.save(managementCreate);
  }

  public TypePeriod findTypePeriodById(Long id){
    var findCareer = this.typePeriodRepository.findById(id);
    if(!findCareer.isPresent()){
      throw new NotFoundException(
        "career id " +id+" not found"
      );
    }

    return findCareer.get();
  }

  public TypePeriod updateTypePeriodId(Long id, CreateTypePeriodDto createTypePeriodDto){
    var findIdManagement = this.findTypePeriodById(id);
    var findName = this.typePeriodRepository.findByDescription(createTypePeriodDto.getDescription());
    if(findName.isPresent()){
      if(findIdManagement.getId() != findName.get().getId() && findIdManagement.getDescription() != findName.get().getDescription()){
        throw new BadRequestException(
          "la descripcion del tipo de periodo no esta disponible"
        );
      }
    }
    findIdManagement.setDescription(createTypePeriodDto.getDescription());
    return this.typePeriodRepository.save(findIdManagement);
  }

  public TypePeriod deleteTypePeriodById(Long id){
    var findManagement = this.findTypePeriodById(id);
    findManagement.setStatus(!findManagement.isStatus());
    return this.typePeriodRepository.save(findManagement);
  }

}
