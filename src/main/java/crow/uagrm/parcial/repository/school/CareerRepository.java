package crow.uagrm.parcial.repository.school;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.Career;

import java.util.Optional;
import java.util.List;




@Repository
public interface CareerRepository  extends JpaRepository<Career, Long>{
  Page<Career> findAll(Pageable pageable);
  Optional<Career> findById(Long id);
  Optional<Career> findByName(String name);
  List<Career> findByStatus(boolean status);
} 
