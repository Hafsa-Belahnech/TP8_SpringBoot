package ma.fst.studentapi.controller;

import jakarta.validation.Valid;
import ma.fst.studentapi.dto.StudentRequestDTO;
import ma.fst.studentapi.dto.StudentResponseDTO;
import ma.fst.studentapi.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //gère des requêtes HTTP, les réponses doivent être sérialisées en JSON
@RequestMapping("/api/students") // fixer le chemin de base de toutes les routes
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //Validation est importante dans le POST et PUT
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO addStudent(@Valid @RequestBody StudentRequestDTO dto) {
        return studentService.addStudent(dto);
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentResponseDTO updateStudent(@PathVariable Long id,
                                            @Valid @RequestBody StudentRequestDTO dto) {
        return studentService.updateStudent(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}