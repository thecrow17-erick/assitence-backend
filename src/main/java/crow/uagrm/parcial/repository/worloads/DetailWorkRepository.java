package crow.uagrm.parcial.repository.worloads;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.DetailWork;
import crow.uagrm.parcial.models.Workload;
import java.util.List;



@Repository
public interface DetailWorkRepository extends JpaRepository<DetailWork,Long> {
  Optional<DetailWork>  findByWorkload(Workload workload);
  List<DetailWork>      findAllByWorkload(Workload workload);
} 
