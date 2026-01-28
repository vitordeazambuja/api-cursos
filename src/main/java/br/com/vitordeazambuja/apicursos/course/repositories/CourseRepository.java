package br.com.vitordeazambuja.apicursos.course.repositories;

import br.com.vitordeazambuja.apicursos.course.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    Optional<CourseEntity> findByName(String name);
    Optional<CourseEntity> findByCategory(String category);
    List<CourseEntity> findByNameAndCategory(String name, String category);
}
