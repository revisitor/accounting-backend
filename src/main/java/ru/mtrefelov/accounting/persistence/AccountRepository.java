package ru.mtrefelov.accounting.persistence;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
  @Modifying
  @Query("UPDATE AccountEntity a SET a.name = :name WHERE a.id = :id")
  void updateOne(@Param("id") Integer id, @Param("name") String name);
}
