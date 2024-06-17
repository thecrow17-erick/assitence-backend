package crow.uagrm.parcial.models;


import java.util.UUID;

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
@Entity
@NoArgsConstructor
@Table(name = "classroom",schema = "public")
public class Classroom extends Auditable {
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

  @Column(
    length = 255,
    name = "code_qr"
  )
  private UUID codeQR;

  @Column(nullable = false)
  private boolean status = true;

  @ManyToOne(
    cascade = CascadeType.PERSIST
  )
  @JoinColumn(
    name = "module_id"
  )
  private Module module;

  public Classroom(
    String nro,
    String description,
    Module module
  ){
    this.description = description;
    this.nro = nro;
    this.module = module;
  }
}
