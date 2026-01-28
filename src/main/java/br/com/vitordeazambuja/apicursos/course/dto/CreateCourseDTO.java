package br.com.vitordeazambuja.apicursos.course.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateCourseDTO {

    @NotBlank(message = "name e obrigatorio")
    private String name;

    @NotBlank(message = "category e obrigatorio")
    private String category;
}
