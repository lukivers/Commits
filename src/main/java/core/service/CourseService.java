package core.service;

import core.dto.CourseDTO;
import core.entity.Course;
import core.entity.Instructor;
import core.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorService instructorService;

    @Transactional
    public Course create(CourseDTO dto) {
        Instructor instructor = instructorService.findById(dto.getInstructorId());

        Course course = Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .durationHours(dto.getDurationHours())
                .level(dto.getLevel())
                .instructor(instructor)
                .published(dto.getPublished() != null ? dto.getPublished() : false)
                .build();

        log.info("Создан курс: {}", dto.getTitle());
        return courseRepository.save(course);
    }

    public Page<Course> findAllWithFilterAndPagination(String title, String level, Boolean published, Pageable pageable) {
        if (title != null && !title.isBlank()) {
            return courseRepository.findByTitleContainingIgnoreCase(title, pageable);
        }
        if (level != null) {
            return courseRepository.findByLevel(level, pageable);
        }
        if (published != null) {
            return courseRepository.findByPublished(published, pageable);
        }
        return courseRepository.findAll(pageable);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Курс не найден"));
    }

    @Transactional
    public void updateCoverImage(Long courseId, String thumbnailPath) {
        Course course = findById(courseId);
        course.setThumbnailPath(thumbnailPath);
        courseRepository.save(course);
    }
}