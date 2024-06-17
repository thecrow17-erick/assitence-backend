package crow.uagrm.parcial.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import crow.uagrm.parcial.models.Role;
import java.util.Optional;

import crow.uagrm.parcial.utils.RoleName;


public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName name);
}
