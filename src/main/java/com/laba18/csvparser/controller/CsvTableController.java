package com.laba18.csvparser.controller;

import com.laba18.csvparser.dto.MccCodeDto;
import com.laba18.csvparser.dto.TransactionDto;
import com.laba18.csvparser.dto.TransactionTypeDto;
import com.laba18.csvparser.service.MccCodeService;
import com.laba18.csvparser.service.TransactionService;
import com.laba18.csvparser.service.TransactionTypeService;
import com.laba18.csvparser.util.DataParserFromCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping
public class CsvTableController {
    private final TransactionService transactionService;
    private final MccCodeService mccCodeService;
    private final TransactionTypeService transactionTypeService;

    @Autowired
    public CsvTableController(TransactionService transactionService, MccCodeService mccCodeService, TransactionTypeService transactionTypeService) {
        this.transactionService = transactionService;
        this.mccCodeService = mccCodeService;
        this.transactionTypeService = transactionTypeService;
    }

    @GetMapping
    public ModelAndView getHomePage() {
        return new ModelAndView("index");
    }

    @PostMapping("/upload-csv-file")
    public ModelAndView uploadCSVFile(@RequestParam("transaction") MultipartFile transactions,
                                      @RequestParam("transaction_type") MultipartFile transactionTypes,
                                      @RequestParam("mcc_codes") MultipartFile mccCodes, Model model) {
        List<TransactionTypeDto> transactionTypeDtoList =
                DataParserFromCSV.parseDataFromFileToEntityDtoList(transactionTypes, TransactionTypeDto.class);
        List<MccCodeDto> mccCodeDtoList =
                DataParserFromCSV.parseDataFromFileToEntityDtoList(mccCodes, MccCodeDto.class);
        List<TransactionDto> transactionDtoList =
                DataParserFromCSV.parseDataFromFileToEntityDtoList(transactions, TransactionDto.class);

        mccCodeService.saveAllMccCodes(mccCodeDtoList);
        transactionTypeService.saveAllTransactionTypes(transactionTypeDtoList);
        transactionService.saveAllTransactions(transactionDtoList);

        RedirectView view = new RedirectView();
        view.setUrl("/");
        view.setExposeModelAttributes(false);
        return new ModelAndView(view);
    }
}
