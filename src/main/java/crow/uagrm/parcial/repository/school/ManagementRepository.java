package crow.uagrm.parcial.repository.school;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.Management;
import java.util.List;



@Repository
public interface ManagementRepository extends JpaRepository<Management,Long> {  
  Optional<Management> findById(Long id);
  Page<Management> findAll(Pageable pageable);
  Optional<Management> findByName(String name);
  List<Management> findByStatus(boolean status);
}
