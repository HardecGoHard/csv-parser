package com.laba18.csvparser.controller;

import com.laba18.csvparser.dto.MccCodeDto;
import com.laba18.csvparser.dto.TransactionDto;
import com.laba18.csvparser.dto.TransactionTableDto;
import com.laba18.csvparser.dto.TransactionTypeDto;
import com.laba18.csvparser.service.MccCodeService;
import com.laba18.csvparser.service.TransactionService;
import com.laba18.csvparser.service.TransactionTypeService;
import com.laba18.csvparser.util.DataParserFromCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@Controller
@RequestMapping
public class CsvTableController {
    private final TransactionService transactionService;
    private final MccCodeService mccCodeService;
    private final TransactionTypeService transactionTypeService;
    private final DataParserFromCSV dataParserFromCSV;

    @Value("${csv.storage.nameCSV}")
    private  String path;

    @Autowired
    public CsvTableController(TransactionService transactionService,
                              MccCodeService mccCodeService,
                              TransactionTypeService transactionTypeService,
                              DataParserFromCSV dataParserFromCSV
    ) {
        this.transactionService = transactionService;
        this.mccCodeService = mccCodeService;
        this.transactionTypeService = transactionTypeService;
        this.dataParserFromCSV = dataParserFromCSV;
    }

    @GetMapping
    public ModelAndView getHomePage() {
        return new ModelAndView("index");
    }

    @PostMapping("/upload-csv-file")
    public ModelAndView uploadCSVFile(@RequestParam("transaction") MultipartFile transactions,
                                      @RequestParam("transaction_type") MultipartFile transactionTypes,
                                      @RequestParam("mcc_codes") MultipartFile mccCodes) {
        List<TransactionTypeDto> transactionTypeDtoList =
                dataParserFromCSV.parseDataFromFileToEntityDtoList(transactionTypes, TransactionTypeDto.class);
        List<MccCodeDto> mccCodeDtoList =
                dataParserFromCSV.parseDataFromFileToEntityDtoList(mccCodes, MccCodeDto.class);
        List<TransactionDto> transactionDtoList =
                dataParserFromCSV.parseDataFromFileToEntityDtoList(transactions, TransactionDto.class);

        mccCodeService.saveAllMccCodes(mccCodeDtoList);
        transactionTypeService.saveAllTransactionTypes(transactionTypeDtoList);
        transactionService.saveAllTransactions(transactionDtoList);

        ModelAndView modelAndView = new ModelAndView("transaction_table");
        modelAndView.addObject("transactions", transactionService.getAllTransactionsGroupingByDate());
        return modelAndView;
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> getFile() throws FileNotFoundException {
        List<TransactionTableDto> transactionTableDtoList = transactionService.getAllTransactionsGroupingByDate();
        dataParserFromCSV.parseFromEntityToCsv(transactionTableDtoList);
        File file = new File(path);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length())
                .body(resource);
    }

}
