package crow.uagrm.parcial.repository.worloads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import crow.uagrm.parcial.models.DetailClass;
import crow.uagrm.parcial.models.DetailWork;

import java.util.List;


@Repository
public interface DetailClassRepository extends JpaRepository<DetailClass,Long> {
  List<DetailClass> findAllByDetailWork(DetailWork detailWork);  

}
