package crow.uagrm.parcial.response;


import lombok.Data;

@Data
public class ApiResponse<T> {
  private Integer statusCode;
  private String message;
  private T data;

  public ApiResponse() {}

  public ApiResponse(Integer statusCode, String message, T data){
    this.statusCode = statusCode;
    this.message = message;
    this.data = data;
  }

}
