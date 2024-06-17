package crow.uagrm.parcial.repository.school;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.Period;
import java.util.List;



@Repository
public interface PeriodRepository extends JpaRepository<Period,Long> {
  Page<Period> findAll(Pageable pageable);
  Optional<Period> findById(Long id);
  Optional<Period> findByName(String name);
  List<Period> findByStatus(boolean status);
}
