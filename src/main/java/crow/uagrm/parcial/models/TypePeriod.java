package crow.uagrm.parcial.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "typePeriod", schema = "public")
public class TypePeriod extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
    name = "description",
    length = 255,
    nullable = false
  )
  private String description;

  @Column(nullable = false)
  private boolean status = true;

  public TypePeriod(String description){
    super();
    this.description = description;
  }
}
