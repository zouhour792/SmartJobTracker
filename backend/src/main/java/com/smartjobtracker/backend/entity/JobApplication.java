package com.smartjobtracker.backend.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Entity 
@Table(name = "job_applications", indexes = {
    @Index(name = "idx_job_applications_user_id", columnList = "user_id"),
    @Index(name = "idx_job_applications_status", columnList = "status")
})
@Getter 
@Setter 
@NoArgsConstructor 
public class JobApplication {
@Id 
@GeneratedValue(strategy=jakarta.persistence.GenerationType.IDENTITY)
private Long id;
@Column(nullable = false, length = 150)
private String jobTitle;
@Column(length = 500)
private String jobUrl;
@Column(length = 100)
private String location;

@Enumerated(EnumType.STRING)
@Column( length = 20)
private ContractType contractype;

@Enumerated(EnumType.STRING)
@Column(nullable = false, length = 20)
private ApplicationStatus status = ApplicationStatus.APPLIED;

@Column(nullable = false)
private LocalDate appliedAt;

@Column(columnDefinition = "TEXT")
private String notes;

@ManyToOne(fetch = FetchType.LAZY, optional = false)    
@JoinColumn(name = "user_id", nullable = false)
private User user;

@ManyToOne(fetch = FetchType.LAZY, optional = false)    
@JoinColumn(name = "company_id", nullable = false)
private Company company;

@Column(nullable = false, updatable = false)
private Instant createdAt;
@Column(nullable = false, updatable = false)
private Instant updatedAt;

@PrePersist
void onCreate() {
    this.createdAt = Instant.now();
    this.updatedAt = Instant.now();
    if (appliedAt == null) {
        appliedAt = LocalDate.now();
    }
}
@PreUpdate 
void onUpdate() {
    this.updatedAt = Instant.now();
}
}
