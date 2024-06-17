package crow.uagrm.parcial.repository.school;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long>{
  List<Group> findAll();
  Optional<Group> findByName(String name);
  Optional<Group> findById(Long id);
}
