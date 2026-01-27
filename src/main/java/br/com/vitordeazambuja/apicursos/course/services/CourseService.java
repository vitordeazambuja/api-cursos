package br.com.vitordeazambuja.apicursos.course.services;

import br.com.vitordeazambuja.apicursos.course.entities.CourseEntity;
import br.com.vitordeazambuja.apicursos.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /* POSSIVEL FUTURA IMPLEMENTACAO DO GET
    public Iterable<CourseEntity> getAll(){
        return this.courseRepository.findAll();
    }
     */

}
