package crow.uagrm.parcial.response.school.module;

import lombok.Data;

import crow.uagrm.parcial.models.Module;

@Data
public class CreateModuleResponse {
  private Module module;

  public CreateModuleResponse(crow.uagrm.parcial.models.Module module){
    this.module=module;
  }
}
