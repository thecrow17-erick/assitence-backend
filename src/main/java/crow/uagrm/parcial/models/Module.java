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
@Entity
@NoArgsConstructor
@Table(name = "module",schema = "public")
public class Module extends Auditable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
    length = 10,
    nullable = false,
    name = "nro"
  )
  private String nro;

  @Column(
    length = 255,
    nullable = false,
    name = "description"
  )
  private String description;

  @Column(nullable = false)
  private boolean status = true;


  public Module(
    String nro,
    String description
  ){
    this.description = description;
    this.nro = nro;
  } 

}
