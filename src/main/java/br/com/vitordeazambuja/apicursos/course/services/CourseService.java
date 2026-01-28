package br.com.vitordeazambuja.apicursos.course.services;

import br.com.vitordeazambuja.apicursos.course.entities.CourseEntity;
import br.com.vitordeazambuja.apicursos.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity create(CourseEntity courseEntity) {
        this.courseRepository
                .findByName(courseEntity.getName())
                .ifPresent((user)->{
                    throw new RuntimeException("Curso ja existe");
                });
        return this.courseRepository.save(courseEntity);
    }

    public List<CourseEntity> getAll(String name, String category){

        if(name != null && category != null){
            return this.courseRepository.findByNameAndCategory(name, category);
        }

        if (name != null){
            return this.courseRepository.findByName(name)
                    .map(List::of)
                    .orElse(List.of());
        }

        if (category != null){
            return this.courseRepository.findByCategory(category)
                    .map(List::of)
                    .orElse(List.of());
        }

        return this.courseRepository.findAll();
    }
}
