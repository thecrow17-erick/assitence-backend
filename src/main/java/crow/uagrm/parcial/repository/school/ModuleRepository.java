package crow.uagrm.parcial.repository.school;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.Module;
import java.util.Optional;
import java.util.List;



@Repository
public interface ModuleRepository extends JpaRepository <Module,Long>{
  Page<Module>      findAll(Pageable pageable);
  Optional<Module>  findById(Long id);
  Optional<Module> findByNro(String nro);
  List<Module> findByStatus(boolean status);
}
