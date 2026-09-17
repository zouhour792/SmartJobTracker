package com.smartjobtracker.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "interviews", indexes = {
    @Index(name = "idx_interviews_application_id", columnList = "application_id")
})
@Getter
@Setter 
@NoArgsConstructor 
public class Interview {
    @Id 
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private Instant scheduledAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InterviewType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InterviewOutcome outcome = InterviewOutcome.PENDING;

    @Column(length = 255)
    private String location;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    private JobApplication application;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;


    @PrePersist 
    void onCreate() {
        createdAt = Instant.now();
    }
}
