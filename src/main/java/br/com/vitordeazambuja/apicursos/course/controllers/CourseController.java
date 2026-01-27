package br.com.vitordeazambuja.apicursos.course.controllers;

import br.com.vitordeazambuja.apicursos.course.entities.CourseEntity;
import br.com.vitordeazambuja.apicursos.course.services.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody CourseEntity courseEntity){
        try{
            var result = this.courseService.create(courseEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /* POSSIVEL FUTURA IMPLEMENTAÇÃO DO GET
    @GetMapping("/")
    public ResponseEntity<Object> get(HttpServletRequest request){
        try{
            return ResponseEntity.ok().body(request.getAttribute("courses"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
     */

}
