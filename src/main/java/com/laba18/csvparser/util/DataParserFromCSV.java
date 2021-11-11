package com.laba18.csvparser.util;

import com.laba18.csvparser.dto.MccCodeDto;
import com.laba18.csvparser.dto.TransactionTypeDto;
import com.laba18.csvparser.exception.DataParseCsvException;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class DataParserFromCSV  {
    public static <T> List<T> parseDataFromFileToEntityDtoList(MultipartFile file, Class<T> clazz) {
        List<T> beans;
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBeanBuilder builder = new CsvToBeanBuilder(reader).withType(clazz);
            if (clazz.equals(MccCodeDto.class) || clazz.equals(TransactionTypeDto.class)){
                builder.withSeparator(';');
            }else {
                builder.withSeparator(',');
            }
             beans = builder.build()
                     .parse();
        } catch (RuntimeException | IOException e) {
            throw new DataParseCsvException("Something went wrong, check format and try parse again" +
                    "\nThe reason: "+e.getMessage());
        }
        return beans;
    }
}
