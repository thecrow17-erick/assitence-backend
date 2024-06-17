package crow.uagrm.parcial.repository.school;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import crow.uagrm.parcial.models.Classroom;
import java.util.List;



public interface ClassroomRepository extends JpaRepository<Classroom,Long> {
  Page<Classroom>     findAll(Pageable pageable);
  Optional<Classroom> findByNro(String nro);
  Optional<Classroom> findById(Long id);
  List<Classroom> findByStatus(boolean status);
  Optional<Classroom> findByCodeQR(UUID codeQR);
}
