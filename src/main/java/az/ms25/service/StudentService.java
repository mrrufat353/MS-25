package az.ms25.service;

import az.ms25.domain.SearchCriteria;
import az.ms25.domain.Student;
import az.ms25.domain.StudentSpecification;
import az.ms25.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getAllBy() {
        return studentRepository.findAll(ageGreaterThan());
    }

    public List<Student> getAllByName() {
        return studentRepository.findAll(Specification.where(nameLike()).and(ageGreaterThan()));
    }

    public List<Student> jpaGreaterThan91() {
        return studentRepository.findAll(jpaGreaterThan());
    }

    public List<Student> getAllByCriteria(List<SearchCriteria> dto) {
        StudentSpecification studentSpecification = new StudentSpecification();
        dto.forEach(studentSpecification::add);
        return studentRepository.findAll(studentSpecification);
    }

    private Specification<Student> ageGreaterThan() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(Student.Fields.age), 22));
    }

    private Specification<Student> nameLike() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Student.Fields.firstName), "Mirrufat"));
    }

    private Specification<Student> jpaGreaterThan() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(Student.Fields.gpa), 81.0));
    }


}
