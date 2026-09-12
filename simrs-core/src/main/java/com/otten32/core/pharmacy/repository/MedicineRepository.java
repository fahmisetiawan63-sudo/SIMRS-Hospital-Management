package com.otten32.core.pharmacy.repository;

import com.otten32.core.pharmacy.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    
    Optional<Medicine> findByCode(String code);
    
    Page<Medicine> findByName(String name, Pageable pageable);
    
    @Query("SELECT m FROM Medicine m WHERE m.name LIKE CONCAT('%', :searchTerm, '%') OR m.code LIKE CONCAT('%', :searchTerm, '%')")
    Page<Medicine> searchMedicines(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    Page<Medicine> findByStatus(String status, Pageable pageable);
    
    @Query("SELECT m FROM Medicine m WHERE m.expiryDate < :expiryDate AND m.status = 'ACTIVE'")
    Page<Medicine> findExpiredMedicines(@Param("expiryDate") LocalDate expiryDate, Pageable pageable);
    
    @Query("SELECT m FROM Medicine m WHERE m.quantity < 10 AND m.status = 'ACTIVE'")
    Page<Medicine> findLowStockMedicines(Pageable pageable);
}
