package com.laba18.csvparser.util;

import com.laba18.csvparser.dto.MccCodeDto;
import com.laba18.csvparser.dto.TransactionTableDto;
import com.laba18.csvparser.dto.TransactionTypeDto;
import com.laba18.csvparser.exception.DataParseCsvException;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class DataParserFromCSV {

    @Value("${csv.storage.path.nameCSV}")
    private String path;

    public  <T> List<T> parseDataFromFileToEntityDtoList(MultipartFile file, Class<T> clazz) {
        List<T> beans;
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBeanBuilder builder = new CsvToBeanBuilder(reader).withType(clazz);
            if (clazz.equals(MccCodeDto.class) || clazz.equals(TransactionTypeDto.class)) {
                builder.withSeparator(';');
            } else {
                builder.withSeparator(',');
            }
            beans = builder.build()
                    .parse();
        } catch (RuntimeException | IOException e) {
            throw new DataParseCsvException("Something went wrong, check format and try parse again" +
                    "\nThe reason: " + e.getMessage());
        }
        return beans;
    }

    public void parseFromEntityToCsv(List<TransactionTableDto> transactionTableDtoList) {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(path));
        ) {
            StatefulBeanToCsv<TransactionTableDto> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            beanToCsv.write(transactionTableDtoList);
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
    }
}
