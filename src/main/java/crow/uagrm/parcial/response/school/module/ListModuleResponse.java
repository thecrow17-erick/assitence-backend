package crow.uagrm.parcial.response.school.module;

import java.util.List;

import crow.uagrm.parcial.models.Module;
import lombok.Data;

@Data
public class ListModuleResponse {
  private List<Module> modules; 

  public ListModuleResponse(List<Module> modules){
    this.modules = modules;
  }

}
