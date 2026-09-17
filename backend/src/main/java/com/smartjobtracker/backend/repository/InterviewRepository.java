package com.smartjobtracker.backend.repository;
import com.smartjobtracker.backend.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByApplicationIdOrderBySchedulesAtAsc(Long applicationId);
    Optional<Interview> findByIdAndApplicationUserId(Long id, Long userId);
    
}
