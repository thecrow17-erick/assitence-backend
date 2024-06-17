package crow.uagrm.parcial.models;

import java.time.LocalDate;

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
@Table(name = "period", schema = "public")
public class Period extends Auditable {
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
    unique = true,
    nullable = false,
    name = "start_date"
  )
  private LocalDate startDate;
  
  @Column(
    unique = true,
    nullable = false,
    name = "end_date"
  )
  private LocalDate endDate;

  @ManyToOne(
    cascade = CascadeType.PERSIST
  )
  @JoinColumn(
    name = "typePeriod_id",
    nullable = false
  )
  private TypePeriod typePeriod;

  @ManyToOne(
    cascade = CascadeType.PERSIST
  )
  @JoinColumn(
    name = "management_id",
    nullable = false
  )
  private Management management;

  @Column(nullable = false)
  private boolean status = true;


  public Period(
    String name,
    LocalDate startDate,
    LocalDate endDate,
    TypePeriod typePeriod,
    Management management
  ){
    super();
    this.name = name;
    this.endDate = endDate;
    this.startDate = startDate;
    this.management = management;
    this.typePeriod = typePeriod;
  }
}
