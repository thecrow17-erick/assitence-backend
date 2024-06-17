package crow.uagrm.parcial.models;


import java.util.List;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "user",schema = "public")
public class User extends Auditable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(
    name = "name", 
    length = 50,
    nullable = false
  )
  private String name;

  @Column(
    name = "email",
    length = 50,
    nullable = false
  )
  private String email;

  @Column(
    name = "password",
    length = 255,
    nullable = false
  )
  private String password;

  @Column(
    name = "phone",
    length = 8,
    nullable = false
  )
  private String phone;

  @Column(nullable = false)
  private boolean status = true;  

  @Column(
    name = "token_FMC",
    length = 255,
    nullable = true
  )
  private String tokenFMC;

  @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
  private List<Role> roles;


  public User(
    String      name,
    String      email,
    String      password,
    String      phone,
    List<Role>  roles
  ){
    super();
    this.name = name;
    this.email = email;
    this.password = password;
    this.phone = phone;
    this.roles = roles;
  }
}
