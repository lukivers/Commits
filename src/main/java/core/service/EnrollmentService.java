package core.service;

import core.dto.EnrollmentDTO;
import core.entity.Course;
import core.entity.Enrollment;
import core.entity.Student;
import core.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    @Transactional
    public Enrollment create(EnrollmentDTO dto) {
        Student student = studentService.findById(dto.getStudentId());
        Course course = courseService.findById(dto.getCourseId());

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrollmentDate(dto.getEnrollmentDate() != null ? dto.getEnrollmentDate() : java.time.LocalDate.now())
                .completed(dto.getCompleted() != null ? dto.getCompleted() : false)
                .build();

        log.info("Студент {} записан на курс {}", student.getFullName(), course.getTitle());
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> findByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
}