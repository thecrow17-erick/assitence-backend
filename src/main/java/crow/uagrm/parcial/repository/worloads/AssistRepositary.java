package crow.uagrm.parcial.repository.worloads;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import crow.uagrm.parcial.models.Assists;
import crow.uagrm.parcial.models.DetailClass;

import java.util.List;



@Repository
public interface AssistRepositary extends JpaRepository<Assists,Long> {
  List<Assists> findByDetailClass(DetailClass detailClass);
  Optional<Assists> findById(Long id);
}
