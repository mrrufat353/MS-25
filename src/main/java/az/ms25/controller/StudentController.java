package az.ms25.controller;

import az.ms25.domain.SearchCriteria;
import az.ms25.domain.Student;
import az.ms25.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/students")
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/list")
    public List<Student> getListByCriteria(@RequestBody List<SearchCriteria> dto) {
        return studentService.getAllByCriteria(dto);
    }


}
