package com.laba18.csvparser.repository;

import com.laba18.csvparser.entity.MccCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MccCodeRepository extends JpaRepository<MccCode, Long> {
    @Modifying
    @Query(value = "TRUNCATE TABLE mcc_codes CASCADE ", nativeQuery = true)
    void truncateTable();
}
