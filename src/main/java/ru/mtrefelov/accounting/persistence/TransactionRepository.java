package ru.mtrefelov.accounting.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ru.mtrefelov.accounting.core.TransactionType;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
  Set<TransactionEntity> findByType(String type);

  @Query("""
      SELECT t
      FROM TransactionEntity t
      WHERE :from <= t.executedAt
      AND t.executedAt <= :to
      """)
  Set<TransactionEntity> findBetweenDates(LocalDate from, LocalDate to);

  Set<TransactionEntity> findByCategory(String category);

  Set<TransactionEntity> findBySubcategory(String subcategory);

  @Modifying
  @Query("""
      UPDATE TransactionEntity t
      t.total = :total,
      t.type = :type,
      t.category = :category,
      t.executedAt = :executedAt
      WHERE t.id = :id
      """)
  void updateOne(
      @Param("id") Long id,
      @Param("total") BigDecimal total,
      @Param("type") TransactionType type,
      @Param("category") String category,
      @Param("executedAt") LocalDate executedAt);
}
