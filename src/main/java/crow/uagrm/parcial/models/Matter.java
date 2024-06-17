package crow.uagrm.parcial.models;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "matter", schema = "public")
public class Matter extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
    name = "code",
    length = 10,
    nullable = false
  )
  private String code;

  @Column(
    name = "name",
    length = 50,
    nullable = false
  )
  private String name;

  @Column(nullable = false)
  private boolean status = true; 

  @ManyToOne(
    cascade = CascadeType.PERSIST
  )
  @JoinColumn(
    name = "career_id",
    nullable = false
  )
  private Career career;

  public Matter(String name,String code,Career career){
    super();
    this.name = name;
    this.code = code;
    this.career = career;
  }
}
