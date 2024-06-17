package crow.uagrm.parcial.error.dto;


import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
  private Integer       statudCode;
  private List<String>  message;
  private HttpStatus    error;
}
