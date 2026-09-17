package com.smartjobtracker.backend.repository;

import com.smartjobtracker.backend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
 List<Company> findByOwnerIdOrderByNameAsc(Long ownerId);
 Optional<Company> findByIdAndOwnerId(long id, long ownerId);
 boolean existsByOwnerIdAndNameIgnoreCase(long ownerId, String name);
}
