package crow.uagrm.parcial.repository.worloads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.Tolerance;

@Repository
public interface ToleranceRepository extends JpaRepository<Tolerance,Long>{
}
