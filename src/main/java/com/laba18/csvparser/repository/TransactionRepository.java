package com.laba18.csvparser.repository;

import com.laba18.csvparser.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Modifying
    @Query(value = "TRUNCATE TABLE transactions CASCADE", nativeQuery = true)
    void truncateTable();

}
