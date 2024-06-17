package crow.uagrm.parcial.models;


import jakarta.persistence.CascadeType;
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
@Table(name = "workload",schema = "public")
public class Workload extends Auditable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(
    cascade = CascadeType.ALL
  )
  @JoinColumn(
    name = "user_id",
    nullable = false
  )
  private User user;


  @ManyToOne(
    cascade = CascadeType.ALL
  )
  @JoinColumn(
    name = "period_id",
    nullable = false
  )
  private Period period;
  

  public Workload(
    User user,
    Period period
  ){
    super();
    this.user = user;
    this.period = period;
  }
  

}
