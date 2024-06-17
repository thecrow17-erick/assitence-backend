package crow.uagrm.parcial.models;

import java.time.DayOfWeek;
import java.time.LocalTime;

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
@Entity
@NoArgsConstructor
@Table(name = "detailClass",schema = "public")
public class DetailClass extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
    name = "start_time",
    nullable = false
  )
  private LocalTime startTime; 

  @Column(
    name = "end_time",
    nullable = false
  )
  private LocalTime endTime; 

  @Column(
    length = 50,
    nullable = false,
    name = "day"
  )
  @Enumerated(EnumType.STRING)
  private DayOfWeek day;

  @ManyToOne(
    cascade = CascadeType.ALL
  )
  @JoinColumn(
    name = "classroom_id",
    nullable = false
  )
  private Classroom classroom;

  @ManyToOne(
    cascade = CascadeType.ALL
  )
  @JoinColumn(
    name = "detailWork_id",
    nullable = false
  )
  private DetailWork detailWork;

  public DetailClass(
    DayOfWeek day,
    LocalTime startTime,
    LocalTime endTime,
    Classroom classroom,
    DetailWork detailWork
  ){
    super();
    this.classroom = classroom;
    this.day = day;
    this.endTime = endTime;
    this.startTime = startTime;
    this.detailWork = detailWork;
  }
  
}
