package crow.uagrm.parcial.repository.worloads;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.Period;
import crow.uagrm.parcial.models.User;
import crow.uagrm.parcial.models.Workload;
import java.util.List;



@Repository
public interface WorkloadRepository extends JpaRepository<Workload,Long>{
  Page<Workload>      findAllByUser(Pageable pagable, User user);
  Optional<Workload>  findByUser(User user);
  Optional<Workload>  findByPeriod(Period period);
  List<Workload>      findAllByPeriod(Period period);
}
