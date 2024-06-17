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
@Table(name = "management", schema = "public")
public class Management extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
    length = 50,
    nullable = false,
    name = "name"
  )
  private String name;

  @Column(
    length = 60,
    nullable = false,
    name = "description"
  )
  private String description;

  @Column(nullable = false)
  private boolean status = true; 

  public Management(String description,String name){
    super();
    this.name = name;
    this.description = description;
  }
  
}
