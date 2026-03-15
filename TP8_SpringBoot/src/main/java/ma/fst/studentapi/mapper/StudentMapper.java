package ma.fst.studentapi.mapper;

import ma.fst.studentapi.dto.StudentRequestDTO;
import ma.fst.studentapi.dto.StudentResponseDTO;
import ma.fst.studentapi.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    //Cette méthode prend un StudentRequestDTO et construit un objet Student
    //Elle est utilisée lorsqu’une requête POST ou PUT envoie un JSON vers l’API
    public Student toEntity(StudentRequestDTO dto) {
        Student student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setMajor(dto.major());
        student.setAge(dto.age());
        return student;
    }

    //Cette méthode prend un objet Student provenant de la base et construit un StudentResponseDTO,
    //Elle est utilisée lorsque l’API doit renvoyer une réponse JSON propre au client
    public StudentResponseDTO toResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getMajor(),
                student.getAge()
        );
    }

    //Cette méthode met à jour une entité existante à partir des données reçues.
    //Cela évite de recréer entièrement un objet lorsqu’un étudiant existe déjà en base
    public void updateEntityFromDTO(StudentRequestDTO dto, Student student) {
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setMajor(dto.major());
        student.setAge(dto.age());
    }
}

//L’annotation @Component permet à Spring de détecter automatiquement cette classe et de la gérer comme un bean.