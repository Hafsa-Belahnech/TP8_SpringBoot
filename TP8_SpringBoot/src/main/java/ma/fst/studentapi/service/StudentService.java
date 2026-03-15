package ma.fst.studentapi.service;

import ma.fst.studentapi.dto.StudentRequestDTO;
import ma.fst.studentapi.dto.StudentResponseDTO;
import ma.fst.studentapi.entity.Student;
import ma.fst.studentapi.exception.ResourceNotFoundException;
import ma.fst.studentapi.mapper.StudentMapper;
import ma.fst.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;


    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    //convertit le DTO en entité, enregistre l’entité en base, convertit le résultat sauvegardé en DTO de réponse
    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        Student student = studentMapper.toEntity(dto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponseDTO(savedStudent);
    }


    //Récupère la liste de toutes les entités depuis le repository, puis transforme chaque entité en DTO
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }


    //rechercher un étudiant par identifiant
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable avec l'id : " + id));

        return studentMapper.toResponseDTO(student);
    }

    //recherche l’étudiant existant
    //met à jour ses champs à partir du DTO
    //sauvegarde l’objet modifié
    //retourne un DTO de réponse
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable avec l'id : " + id));

        studentMapper.updateEntityFromDTO(dto, student);
        Student updatedStudent = studentRepository.save(student);

        return studentMapper.toResponseDTO(updatedStudent);
    }



    //Vérifier l’existence de l’étudiant et le supprimer
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant introuvable avec l'id : " + id));

        studentRepository.delete(student);
    }
}