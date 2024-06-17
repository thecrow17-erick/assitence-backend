package crow.uagrm.parcial.response.school.classroom;

import crow.uagrm.parcial.models.Classroom;
import lombok.Data;

@Data
public class CreateClassroomResponse {
  private Classroom classroom;

  public CreateClassroomResponse(Classroom classroom){
    this.classroom = classroom;
  }
}
