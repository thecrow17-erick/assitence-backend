package crow.uagrm.parcial.services.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.dto.school.management.CreateManagementDto;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.Management;
import crow.uagrm.parcial.repository.school.ManagementRepository;

@Service
public class ManagementService {
  
  @Autowired
  private ManagementRepository managementRepository;

  public Page<Management> findAllManagement(
    int skip,
    int limit
  ){
    Pageable pageable = PageRequest.of(skip, limit);
    var managements = this.managementRepository.findAll(pageable);
    return managements;
  }

  public List<Management> findManagements(){
    return this.managementRepository.findByStatus(true);
  }

  public Management createManagement(CreateManagementDto createManagementDto){
    var findMatter = this.managementRepository.findByName(createManagementDto.getName());
    if(findMatter.isPresent()){
      throw new BadRequestException(
        "el nombre se encuentra ocupado"
      );
    }
    
    var managementCreate = new Management(
      createManagementDto.getName(),
      createManagementDto.getDescription()
    );
    return this.managementRepository.save(managementCreate);
  }

  public Management findManagementById(Long id){
    var findCareer = this.managementRepository.findById(id);
    if(!findCareer.isPresent()){
      throw new NotFoundException(
        "management id " +id+" not found"
      );
    }

    return findCareer.get();
  }

  public Management updateManagementId(Long id, CreateManagementDto createManagementDto){
    var findIdManagement = this.findManagementById(id);
    var findName = this.managementRepository.findByName(createManagementDto.getName());
    if(findName.isPresent()){
      if(findIdManagement.getId() != findName.get().getId() && findIdManagement.getName() != findName.get().getName()){
        throw new BadRequestException(
          "el nombre de la gestion no esta disponible"
        );
      }
    }
    findIdManagement.setName(createManagementDto.getName());
    findIdManagement.setDescription(createManagementDto.getDescription());
    return this.managementRepository.save(findIdManagement);
  }

  public Management deleteManagementById(Long id){
    var findManagement = this.findManagementById(id);
    findManagement.setStatus(!findManagement.isStatus());
    return this.managementRepository.save(findManagement);
  }
}
