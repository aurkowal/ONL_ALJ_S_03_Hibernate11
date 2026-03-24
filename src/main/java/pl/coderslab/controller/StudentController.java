package pl.coderslab.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.dto.StudentDto;
import pl.coderslab.repository.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student ->
                        {
                            StudentDto studentDTO = new StudentDto();
                            studentDTO.setId(student.getId());
                            studentDTO.setFirstName(student.getFirstName());
                            studentDTO.setLastName(student.getLastName());
                            studentDTO.setIndexNumber(student.getIndexNumber());
                            studentDTO.setAverageGrade(student.getAverageGrade());
                            return studentDTO;
                        }
                ).toList();
    }
}