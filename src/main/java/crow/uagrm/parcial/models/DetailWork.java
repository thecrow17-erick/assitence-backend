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
@Table(name = "detailWork",schema = "public")
public class DetailWork extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(
    cascade = CascadeType.ALL
  )
  @JoinColumn(
    nullable = false,
    name = "work_id"
  )
  private Workload workload;


  @ManyToOne(
    cascade = CascadeType.ALL
  )
  @JoinColumn(
    nullable = false,
    name = "matter_id"
  )
  private Matter matter;

  @ManyToOne(
    cascade = CascadeType.ALL
  )
  @JoinColumn(
    nullable = false,
    name = "group_id"
  )
  private Group group;


  public DetailWork(
    Workload workload,
    Group group,
    Matter matter
  ){
    super();
    this.workload = workload;
    this.group = group;
    this.matter = matter;
  }
}
