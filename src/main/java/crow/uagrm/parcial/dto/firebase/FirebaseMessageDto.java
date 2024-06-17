package crow.uagrm.parcial.dto.firebase;

import lombok.Data;

@Data
public class FirebaseMessageDto {
  
  private String message;
  private String title;
  private String token_FMC;

  public FirebaseMessageDto(String message,String title,String token_FMC){
    this.message = message;
    this.title = title;
    this.token_FMC = token_FMC;
  }
}
