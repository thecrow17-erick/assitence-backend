package crow.uagrm.parcial.repository.school;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.Career;
import crow.uagrm.parcial.models.Matter;

import java.util.List;



@Repository
public interface MatterRepository extends JpaRepository<Matter, Long>{
  Page<Matter> findAll(Pageable pageable);
  Optional<Matter> findById(Long id);

  List<Matter> findByCareer(Career career);

  List<Matter> findByStatus(boolean status);


  Optional<Matter> findByName(String name);
  Optional<Matter> findByCode(String code);
}
