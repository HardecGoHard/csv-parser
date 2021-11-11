package com.laba18.csvparser.service;

import com.laba18.csvparser.dto.TransactionDto;
import com.laba18.csvparser.entity.MccCode;
import com.laba18.csvparser.entity.Transaction;
import com.laba18.csvparser.entity.TransactionType;
import com.laba18.csvparser.exception.DataParseCsvException;
import com.laba18.csvparser.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;


    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void saveAllTransactions(List<TransactionDto> transactionDtoList) {
        transactionRepository.truncateTable();
        List<Transaction> transactionList = transactionDtoList.stream()
                .map(this::mapToTransactionFromDto)
                .collect(Collectors.toList());
        transactionRepository.saveAll(transactionList);
    }

    private Transaction mapToTransactionFromDto(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCustomerId(transactionDto.getCustomerId());
        transaction.setTrDay(parseDateStringToInteger(transactionDto.getTrDatetime()));
        transaction.setTerminalId(transactionDto.getTerminalId());

        MccCode mccCode = new MccCode();
        mccCode.setId(transactionDto.getMccCode());
        transaction.setMccCode(mccCode);

        TransactionType transactionType = new TransactionType();
        transactionType.setId(transactionDto.getTrTypeCode());
        transaction.setTrType(transactionType);
        return transaction;
    }

    private Integer parseDateStringToInteger(String dateTime) {
        int day;
        String[] tokens = dateTime.split(" ");
        if (tokens.length < 2) {
            throw new DataParseCsvException("Can't parse date, input date: " + dateTime);
        }
        try {
            day = Integer.parseInt(tokens[0]);
        } catch (RuntimeException e) {
            throw new DataParseCsvException("Can't parse date, input date: " + tokens[0]);
        }
        return day;
    }

}
