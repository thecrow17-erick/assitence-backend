package crow.uagrm.parcial.services.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.dto.school.career.CreateCareerDto;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.Career;
import crow.uagrm.parcial.repository.school.CareerRepository;

@Service
public class CareerService {
  
  @Autowired
  private CareerRepository careerRepository;


  public Page<Career> findAllCareer(int skip, int limit){
    Pageable pageable = PageRequest.of(skip, limit);
    var careers = this.careerRepository.findAll(pageable);
    return careers;
  }

  public List<Career> findCareers(){
    return this.careerRepository.findByStatus(true);
  }

  public Career createCareer(CreateCareerDto createCareerDto){
    var findCareer = this.careerRepository.findByName(createCareerDto.getName().toLowerCase());
    if(findCareer.isPresent()){
      throw new BadRequestException(
        "nombre de la carrera no se encuentra disponible"
      );
    }
    var careerCreate = new Career(
      createCareerDto.getName().toLowerCase()
    );
    return this.careerRepository.save(careerCreate);
  }

  public Career findCareerById(Long id){
    var findCareer = this.careerRepository.findById(id);
    if(!findCareer.isPresent()){
      throw new NotFoundException(
        "career id " +id+" not found"
      );
    }
    return findCareer.get();
  }

  public Career updateCareerById(Long id, CreateCareerDto createCareerDto){
    var findCareer = this.careerRepository.findById(id);
    if(!findCareer.isPresent()){
      throw new NotFoundException(
        "career id " +id +" not found"
      );
    }
    var updateCareer = findCareer.get();
    updateCareer.setName(createCareerDto.getName().toLowerCase());
    return this.careerRepository.save(updateCareer);
  }

  public Career deleteCareerById(Long id){
    var findCareer = this.careerRepository.findById(id);
    if(!findCareer.isPresent()){
      throw new NotFoundException(
        "career id " +id +" not found"
      );
    }
    var updateCareer = findCareer.get();
    updateCareer.setStatus(!updateCareer.isStatus());
    return this.careerRepository.save(updateCareer);
  }
  

}
