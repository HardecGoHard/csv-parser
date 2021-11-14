package com.laba18.csvparser.repository;

import com.laba18.csvparser.dto.TransactionTableDto;
import com.laba18.csvparser.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Modifying
    @Query(value = "TRUNCATE TABLE transactions CASCADE", nativeQuery = true)
    void truncateTable();

    @Query("SELECT new com.laba18.csvparser.dto.TransactionTableDto(concat(t.trDay,' день'), AVG(t.amount), t.mccCode.id) " +
            "FROM Transaction AS t " +
            "GROUP BY t.trDay,t.mccCode.id " +
            "HAVING 3 < (SELECT COUNT(tr.mccCode.id) " +
            "                   FROM Transaction AS  tr" +
            "                   WHERE tr.trDay = t.trDay AND t.mccCode.id = tr.mccCode.id)" +
            "ORDER BY t.trDay")
    List<TransactionTableDto> getAllTransactionsGroupingByDateTime();
}
