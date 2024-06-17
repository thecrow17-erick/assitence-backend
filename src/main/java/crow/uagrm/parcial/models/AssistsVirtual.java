package crow.uagrm.parcial.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "assistsVirtual", schema = "public")
public class AssistsVirtual extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
    length = 255,
    name = "evidence",
    nullable = false
  )
  private String evidence;

  @OneToOne(
    cascade = CascadeType.ALL
  )
  @JoinColumn(
    name = "assists_id"
  )
  private Assists assists;

  public AssistsVirtual(
    String evidence,
    Assists assists
  ){
    super();
    this.assists = assists;
    this.evidence = evidence;
  }

}
