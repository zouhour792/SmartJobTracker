package com.smartjobtracker.backend.repository;
import com.smartjobtracker.backend.entity.JobApplication;
import com.smartjobtracker.backend.entity.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUserIdOrderByAppliedAtDesc(Long userID);
    List<JobApplication> findByUserIdAndStatus(Long userId, ApplicationStatus status);
    Optional<JobApplication> findByIdAndUserId(Long id, Long userId);
    long countByUserIdAndStatus(Long userId, ApplicationStatus status);
}
