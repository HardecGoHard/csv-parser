package com.laba18.csvparser.util;

import com.laba18.csvparser.exception.DataParseCsvException;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

public class DataParserFromCSV  {
    public static <T> List<T> parseDataFromFileToEntityDto(MultipartFile file, Class<T> clazz) {
        List<T> beans;
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            beans = new CsvToBeanBuilder(reader)
                    .withType(clazz)
                    .build()
                    .parse();
        } catch (RuntimeException | IOException e) {
            throw new DataParseCsvException("Something went wrong, check format and try parse again" +
                    "\nThe reason: "+e.getMessage());
        }
        return beans;
    }
}
