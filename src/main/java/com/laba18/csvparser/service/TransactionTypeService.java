package com.laba18.csvparser.service;

import com.laba18.csvparser.repository.TransactionTypesRepository;
import com.laba18.csvparser.dto.TransactionTypeDto;
import com.laba18.csvparser.entity.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionTypeService {
    private final TransactionTypesRepository transactionTypesRepository;

    @Autowired
    public TransactionTypeService(TransactionTypesRepository transactionTypesRepository) {
        this.transactionTypesRepository = transactionTypesRepository;
    }

    @Transactional
    public void saveAllTransactionTypes(List<TransactionTypeDto> transactionTypeDtoList) {
        transactionTypesRepository.truncateTable();
        List<TransactionType> transactionTypes = transactionTypeDtoList.stream()
                .map(this::mapTransactionTypeFromDto)
                .collect(Collectors.toList());
        transactionTypesRepository.saveAll(transactionTypes);
    }

    private TransactionType mapTransactionTypeFromDto(TransactionTypeDto transactionTypeDto) {
        TransactionType transactionType = new TransactionType();
        transactionType.setDescription(transactionTypeDto.getDescription());
        transactionType.setId(transactionTypeDto.getTypeCode());
        return transactionType;
    }
}
