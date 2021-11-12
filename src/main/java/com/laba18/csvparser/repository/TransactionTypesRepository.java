package com.laba18.csvparser.repository;

import com.laba18.csvparser.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTypesRepository extends JpaRepository<TransactionType, Long> {
    @Modifying
    @Query(value = "TRUNCATE TABLE tr_type CASCADE", nativeQuery = true)
    void truncateTable();

}
