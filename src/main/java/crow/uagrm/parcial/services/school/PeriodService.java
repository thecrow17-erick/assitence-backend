package crow.uagrm.parcial.services.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import crow.uagrm.parcial.dto.school.period.CreatePeriodDto;
import crow.uagrm.parcial.error.exception.BadRequestException;
import crow.uagrm.parcial.error.exception.NotFoundException;
import crow.uagrm.parcial.models.Period;
import crow.uagrm.parcial.repository.school.PeriodRepository;

@Service
public class PeriodService {
  
  @Autowired
  private PeriodRepository periodRepository;

  @Autowired
  private ManagementService managementService;

  @Autowired
  private TypePeriodService typePeriodService;

  public Page<Period> findAllPeriods(
    int skip,
    int limit
  ){
    Pageable pageable = PageRequest.of(skip, limit);
    Page<Period> periods = this.periodRepository.findAll(pageable);
    return periods;
  }

  public Period finOnePeriodTrue(){
    var find = this.findPeriods();
    System.out.println(find);
    if(find.size() == 0 || find.size() >= 2){
      throw new NotFoundException(
        "Solo un periodo debe estar activo"
      );
    }
    return find.get(0);
  }

  public List<Period> findPeriods(){
    return this.periodRepository.findByStatus(true);
  }

  public Period createPeriod(CreatePeriodDto createPeriodDto){
    var findManagement = this.managementService.findManagementById(createPeriodDto.getManagement_id());
    var findTypePeriod = this.typePeriodService.findTypePeriodById(createPeriodDto.getType_period_id());
    System.out.println(findManagement.toString());
    var findPeriod = this.periodRepository.findByName(createPeriodDto.getName());
    if(findPeriod.isPresent()){
      throw new BadRequestException(
        "period name invalid"
      );
    }
    var createPeriod = new Period(
      createPeriodDto.getName(),
      createPeriodDto.getInit_time(),
      createPeriodDto.getFinish_time(),
      findTypePeriod,
      findManagement
    );
    return this.periodRepository.save(createPeriod);
  }

  public Period updatePeriod(Long id,CreatePeriodDto createPeriodDto){
    var findManagement = this.managementService.findManagementById(createPeriodDto.getManagement_id());
    var findTypePeriod = this.typePeriodService.findTypePeriodById(createPeriodDto.getType_period_id());
    var findPeriodId = this.findByIdPeriod(id);
    var findPeriod = this.periodRepository.findByName(createPeriodDto.getName());
    if(findPeriod.isPresent() && findPeriod.get().getId() != findPeriodId.getId()){
      throw new BadRequestException(
        "period name invalid"
      );
    }
    findPeriodId.setName(createPeriodDto.getName());
    findPeriodId.setManagement(findManagement);
    findPeriodId.setTypePeriod(findTypePeriod);
    findPeriodId.setEndDate(createPeriodDto.getFinish_time());
    findPeriodId.setStartDate(createPeriodDto.getInit_time());

    return this.periodRepository.save(findPeriodId);
  }

  public Period findByIdPeriod(Long id){
    var findId = this.periodRepository.findById(id);
    if(findId.isEmpty()){
      throw new NotFoundException(
        "period id " + id+ " not found"
      );
    }
    return findId.get();
  }

  public Period deletePeriod(Long id){
    var findId = this.findByIdPeriod(id);

    findId.setStatus(!findId.isStatus());

    return this.periodRepository.save(findId);
  }

}
