package core.service;

import core.dto.StudentDTO;
import core.entity.Student;
import core.entity.StudentProfile;
import core.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public Student create(StudentDTO dto) {
        Student student = Student.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();

        Student savedStudent = studentRepository.save(student);

        StudentProfile profile = StudentProfile.builder()
                .student(savedStudent)
                .university(dto.getUniversity())
                .specialty(dto.getSpecialty())
                .courseYear(dto.getCourseYear())
                .build();

        savedStudent.setProfile(profile);

        log.info("Создан новый студент: {}", dto.getFullName());
        return studentRepository.save(savedStudent);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Студент не найден"));
    }
}