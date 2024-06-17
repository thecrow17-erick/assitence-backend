package crow.uagrm.parcial.models;

import java.time.LocalDateTime;

import crow.uagrm.parcial.utils.TypeAsistence;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "assists", schema = "public")
public class Assists extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
    name = "time",
    nullable = false
  )
  private LocalDateTime time;

  @Column(
    name = "assists_status",
    length = 50,
    nullable = false
  )
  @Enumerated(EnumType.STRING)
  private TypeAsistence assistsStatus;

  @ManyToOne(
    cascade = CascadeType.ALL
  )
  @JoinColumn(
    name = "detailClass_id",
    nullable = false
  )
  private DetailClass detailClass;

  public Assists(
    LocalDateTime time,
    TypeAsistence assistsStatus,
    DetailClass detailClass
  ){
    super();
    this.assistsStatus = assistsStatus;
    this.time = time;
    this.detailClass=detailClass;
  }
}
