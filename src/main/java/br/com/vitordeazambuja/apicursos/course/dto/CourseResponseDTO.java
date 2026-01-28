package br.com.vitordeazambuja.apicursos.course.dto;

import br.com.vitordeazambuja.apicursos.course.entities.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CourseResponseDTO {
    private UUID id;
    private String name;
    private String category;
    private Boolean active;

    public static CourseResponseDTO from(CourseEntity courseEntity){
        return new CourseResponseDTO(
                courseEntity.getId(),
                courseEntity.getName(),
                courseEntity.getCategory(),
                courseEntity.getActive()
        );
    }
}
