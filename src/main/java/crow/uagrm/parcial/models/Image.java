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
@Table
public class Image extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
    length = 50,
    name = "name",
    nullable = false
  )
  private String name;

  @Column(
    length = 10,
    name = "extension",
    nullable = false
  )
  private String extension;

  @Column(
    name = "size",
    nullable = false
  )
  private Integer size;


  @Column(
    length = 255,
    name = "url",
    nullable = false
  )
  private String url;


  public Image(
    String name,
    String extension,
    Integer size,
    String url
  ){
    super();
    this.extension = extension;
    this.name = name;
    this.size = size;
    this.url = url;
  }
}


