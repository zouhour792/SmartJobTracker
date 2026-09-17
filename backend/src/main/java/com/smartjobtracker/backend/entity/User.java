package com.smartjobtracker.backend.entity;
import jakarta.persistence.*;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.Instant;
@Entity 
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    
    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role = Role.CANDIDATE;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist 
    void onCreate() {
        createdAt = Instant.now();
        updatedAt = createdAt;
    }
    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }
}
