package crow.uagrm.parcial.repository.user;

import crow.uagrm.parcial.models.User;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import crow.uagrm.parcial.models.Role;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
  List<User>      findAllByName(String name); 
  Page<User>      findByRoles(List<Role> roles, Pageable pageable);
  Optional<User>  findById(Long id);
  Optional<User>  findByName(String name);
  Optional<User>  findByEmail(String email);
  Optional<User>  findByPhone(String phone);

}
