package br.com.vitordeazambuja.apicursos.course.controllers;

import br.com.vitordeazambuja.apicursos.course.dto.CreateCourseDTO;
import br.com.vitordeazambuja.apicursos.course.dto.UpdateCourseDTO;
import br.com.vitordeazambuja.apicursos.course.entities.CourseEntity;
import br.com.vitordeazambuja.apicursos.course.services.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateCourseDTO dto){
        try{
            var result = this.courseService.create(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(required = false) String name, @RequestParam(required = false) String category){
        return ResponseEntity.ok().body(this.courseService.getAll(name, category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody UpdateCourseDTO dto){
        try{
            return ResponseEntity.ok().body(this.courseService.update(id, dto));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try{
            courseService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> toggleActive(@PathVariable UUID id){
        try{
            return ResponseEntity.ok().body(this.courseService.toggleActive(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
