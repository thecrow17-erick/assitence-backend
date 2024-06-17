package crow.uagrm.parcial.services.school;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.repository.school.ModuleRepository;
import lombok.AllArgsConstructor;
import crow.uagrm.parcial.dto.school.module.CreateModuleDto;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.Module;

@Service
@AllArgsConstructor
public class ModuleService {
  
  @Autowired
  private ModuleRepository moduleRepository;

  public Page<Module> findAll(int skip, int limit){
    Pageable pageable = PageRequest.of(skip, limit);
    return this.moduleRepository.findAll(pageable);
  }

  public List<Module> finModules(){
    return this.moduleRepository.findByStatus(true);
  }

  public Module createModule(CreateModuleDto createModuleDto){
    var moduleFind = this.moduleRepository.findByNro(createModuleDto.getNro());
    if(moduleFind.isPresent()){
      throw new BadRequestException(
        "El numero de modulo no se encuentra disponible"
      );
    }
    var moduleCreate = new Module(
      createModuleDto.getNro(),
      createModuleDto.getDescription()
    );
    return this.moduleRepository.save(moduleCreate);
  }

  public Module findByIdModule(Long id){
    var findModule = this.moduleRepository.findById(id);
    if(!findModule.isPresent()){
      throw new NotFoundException(
        "modulo id" +id+"not found"
      );
    }
    return findModule.get();
  }

  public Module updateModuleIdd(Long id, CreateModuleDto createModuleDto){
    var findIdModule = this.findByIdModule(id);
    
    var findNro = this.moduleRepository.findByNro(createModuleDto.getNro());
    if(findNro.isPresent() && findIdModule.getNro() != findNro.get().getNro()){
      throw new BadRequestException(
        "el numero del modulo no esta disponible"
      );
    }
    findIdModule.setDescription(createModuleDto.getDescription());
    findIdModule.setNro(createModuleDto.getNro());
    return this.moduleRepository.save(findIdModule);
  }

  public Module deleteModuleId(Long id){
    var findModuleId = this.findByIdModule(id);
    
    findModuleId.setStatus(!findModuleId.isStatus());

    return this.moduleRepository.save(findModuleId);
  }
}
