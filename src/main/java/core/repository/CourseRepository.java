package core.repository;

import core.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Course> findByLevel(String level, Pageable pageable);
    Page<Course> findByPublished(Boolean published, Pageable pageable);
}