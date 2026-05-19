package core.service;

import core.entity.Instructor;
import core.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    @Transactional
    public Instructor create(Instructor instructor) {
        log.info("Создание инструктора: {}", instructor.getFullName());
        return instructorRepository.save(instructor);
    }

    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    public Instructor findById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Инструктор не найден"));
    }

    @Transactional
    public Instructor update(Long id, Instructor updated) {
        Instructor existing = findById(id);
        existing.setFullName(updated.getFullName());
        existing.setBio(updated.getBio());
        return instructorRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        instructorRepository.deleteById(id);
        log.info("Удалён инструктор id: {}", id);
    }
}