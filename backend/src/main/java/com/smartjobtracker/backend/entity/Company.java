package com.smartjobtracker.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "companies", uniqueConstraints = 
    @UniqueConstraint(columnNames = { "owner_id", "name"})
)
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(length = 100)
    private String industry;

    @Column(length = 100)
    private String city;

    @Column( length = 255)
    private String website;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
    }
}
