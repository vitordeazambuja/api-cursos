package br.com.vitordeazambuja.apicursos.course.services;

import br.com.vitordeazambuja.apicursos.course.dto.CourseResponseDTO;
import br.com.vitordeazambuja.apicursos.course.dto.CreateCourseDTO;
import br.com.vitordeazambuja.apicursos.course.dto.UpdateCourseDTO;
import br.com.vitordeazambuja.apicursos.course.entities.CourseEntity;
import br.com.vitordeazambuja.apicursos.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseResponseDTO create(@org.checkerframework.checker.nullness.qual.MonotonicNonNull CreateCourseDTO dto) {
        this.courseRepository
                .findByName(dto.getName())
                .ifPresent((user)->{
                    throw new RuntimeException("Curso ja existente");
                });

        CourseEntity course = new CourseEntity();
        course.setName(dto.getName());
        course.setCategory(dto.getCategory());
        course.setActive(true);

        return CourseResponseDTO.from(courseRepository.save(course));
    }

    public List<CourseResponseDTO> getAll(String name, String category) {

        List<CourseEntity> courses;

        if (name != null && category != null) {
            courses = courseRepository.findByNameAndCategory(name, category);

        } else if (name != null) {
            courses = courseRepository.findByName(name)
                    .map(List::of)
                    .orElse(List.of());

        } else if (category != null) {
            courses = courseRepository.findByCategory(category)
                    .map(List::of)
                    .orElse(List.of());

        } else {
            courses = courseRepository.findAll();
        }

        return courses.stream()
                .map(CourseResponseDTO::from)
                .toList();
    }


    public CourseResponseDTO update(UUID id, UpdateCourseDTO dto){
        CourseEntity course = this.courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso nao encontrado"));

        if(dto.getName() != null){
            course.setName(dto.getName());
        }

        if(dto.getCategory() != null){
            course.setCategory(dto.getCategory());
        }

        return CourseResponseDTO.from(courseRepository.save(course));
    }

    public void delete(UUID id){
        if(!this.courseRepository.existsById(id)){
            throw new RuntimeException("Curso nao encontrado");
        }
        this.courseRepository.deleteById(id);
    }

    public CourseResponseDTO toggleActive(UUID id){
        CourseEntity course = this.courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso nao encontrado"));

        Boolean status = course.getActive();
        course.setActive(status == null ? true : !status);

        return CourseResponseDTO.from(courseRepository.save(course));
    }
}
