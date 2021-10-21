package com.laba18.csvparser.controller;

import com.laba18.csvparser.dto.TransactionDto;
import com.laba18.csvparser.util.DataParserFromCSV;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping
public class CsvTableController {
    @GetMapping
    public String getHomePage(){

        return "index";
    }
    @PostMapping("/upload-csv-file")
    public String uploadCSVFile(@RequestParam("transaction") MultipartFile transactions, Model model) {
       List<TransactionDto> transactionDtoList =
               DataParserFromCSV.parseDataFromFileToEntityDto(transactions, TransactionDto.class);
       transactionDtoList.forEach(x->System.out.print(x+" "));
        return "redirect:";
    }
}
