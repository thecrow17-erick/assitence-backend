package crow.uagrm.parcial.repository.school;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.TypePeriod;
import java.util.List;




@Repository
public interface TypePeriodRepository extends JpaRepository<TypePeriod,Long>{
  Optional<TypePeriod> findById(Long id);
  Page<TypePeriod>  findAll(Pageable pageable);
  Optional<TypePeriod> findByDescription(String description);
  List<TypePeriod> findByStatus(boolean status);
}
