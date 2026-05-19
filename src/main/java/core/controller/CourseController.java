package core.controller;

import core.dto.CourseDTO;
import core.dto.EnrollmentDTO;
import core.dto.StudentDTO;
import core.entity.Course;
import core.entity.Enrollment;
import core.entity.Instructor;
import core.entity.Student;
import core.service.CourseService;
import core.service.EnrollmentService;
import core.service.InstructorService;
import core.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Online Course Platform", description = "Платформа онлайн-курсов")
public class CourseController {

    private final CourseService courseService;
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;

    // ==================== INSTRUCTORS ====================
    @PostMapping("/instructors")
    public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody Instructor instructor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.create(instructor));
    }

    @GetMapping("/instructors")
    public List<Instructor> getAllInstructors() {
        return instructorService.findAll();
    }

    // ==================== COURSES ====================
    @GetMapping("/courses")
    public Page<Course> getAllCourses(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String level,
            @RequestParam(required = false) Boolean published,
            @Parameter(hidden = true) @PageableDefault(size = 10, sort = "title") Pageable pageable) {

        return courseService.findAllWithFilterAndPagination(title, level, published, pageable);
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(courseDTO));
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    // ==================== STUDENTS ====================
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(studentDTO));
    }

    // ==================== ENROLLMENTS ====================
    @PostMapping("/enrollments")
    public ResponseEntity<Enrollment> createEnrollment(@Valid @RequestBody EnrollmentDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.create(dto));
    }

    @GetMapping("/enrollments")
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.findAll();
    }
}
