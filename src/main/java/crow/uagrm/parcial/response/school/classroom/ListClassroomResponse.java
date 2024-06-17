package crow.uagrm.parcial.response.school.classroom;

import java.util.List;

import crow.uagrm.parcial.models.Classroom;
import lombok.Data;

@Data
public class ListClassroomResponse {
  private List<Classroom> classrooms;

  public ListClassroomResponse(List<Classroom> classrooms){
    this.classrooms = classrooms;
  }

}
