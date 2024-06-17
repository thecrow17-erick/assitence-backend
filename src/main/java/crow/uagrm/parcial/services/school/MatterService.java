package crow.uagrm.parcial.services.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.dto.school.matter.CreateMatterDto;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.Matter;
import crow.uagrm.parcial.repository.school.CareerRepository;
import crow.uagrm.parcial.repository.school.MatterRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MatterService {
  

  @Autowired
  private MatterRepository matterRepository;

  @Autowired
  private CareerRepository careerRepository;

  public Page<Matter> findAllMatter(int skip, int limit){
    Pageable pageable = PageRequest.of(skip, limit);
    var matters = this.matterRepository.findAll(pageable);
    return matters;
  }

  public List<Matter> findMatters(){
    return this.matterRepository.findByStatus(true);
  }

  public Matter createMatter(CreateMatterDto createMatterDto){
    var findMatter = this.matterRepository.findByCode(createMatterDto.getCode().toUpperCase());
    if(findMatter.isPresent()){
      throw new BadRequestException(
        "codigo se encuentra ocupado"
      );
    }
    findMatter = this.matterRepository.findByName(createMatterDto.getName());
    if(findMatter.isPresent()){
      throw new BadRequestException(
        "name se encuentra ocupado"
      );
    }
    var careerFind = this.careerRepository.findById(createMatterDto.getCareer_id());
    if(!careerFind.isPresent()){
      throw new NotFoundException(
        "la carrera no se encuentra disponible"
      );
    }
    var matterCreate = new Matter(
      createMatterDto.getName(),
      createMatterDto.getCode(),
      careerFind.get()
    );
    return this.matterRepository.save(matterCreate);
  }


  public Matter findMatterById(Long id){
    var findMatter = this.matterRepository.findById(id);
    if(!findMatter.isPresent()){
      throw new NotFoundException(
        "matter id " +id +" not found"
      );
    }
    return findMatter.get();
  }

  public Matter updateMatter(Long id,CreateMatterDto createMatterDto){
    System.out.println(createMatterDto);
    var findMatterId = this.findMatterById(id);
    System.out.println(findMatterId);
    var findMatter = this.matterRepository.findByCode(createMatterDto.getCode().toUpperCase());
    System.out.println(findMatter.get());
    if(findMatter.isPresent() && findMatter.get().getId() != findMatterId.getId()){
      throw new BadRequestException(
        "codigo se encuentra ocupado"
      );
    }
    findMatter = this.matterRepository.findByName(createMatterDto.getName());
    if(findMatter.isPresent()&& findMatter.get().getId() != findMatterId.getId()){
      throw new BadRequestException(
        "name se encuentra ocupado"
      );
    }
    var careerFind = this.careerRepository.findById(createMatterDto.getCareer_id());
    if(!careerFind.isPresent()){
      throw new NotFoundException(
        "la carrera no se encuentra disponible"
      );
    }
    var matterUpdate = findMatterId;
    matterUpdate.setCode(createMatterDto.getCode());
    matterUpdate.setName(createMatterDto.getName());
    matterUpdate.setCareer(careerFind.get());
    
    return this.matterRepository.save(matterUpdate);
  }

  public Matter deleteMatterId(Long id){
    var findMatterId = this.findMatterById(id);

    findMatterId.setStatus(!findMatterId.isStatus());

    return this.matterRepository.save(findMatterId);
  }

  
}
