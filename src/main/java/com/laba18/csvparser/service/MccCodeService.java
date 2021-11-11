package com.laba18.csvparser.service;

import com.laba18.csvparser.repository.MccCodeRepository;
import com.laba18.csvparser.dto.MccCodeDto;
import com.laba18.csvparser.entity.MccCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MccCodeService {
    private final MccCodeRepository mccCodeRepository;

    public MccCodeService(MccCodeRepository mccCodeRepository) {
        this.mccCodeRepository = mccCodeRepository;
    }

    @Transactional
    public void saveAllMccCodes(List<MccCodeDto> mccCodeDtoList) {
        mccCodeRepository.truncateTable();
        List<MccCode> mccCodeList = mccCodeDtoList.stream().map(this::mapMccCodeFromDto).collect(Collectors.toList());
        mccCodeRepository.saveAll(mccCodeList);
    }

    private MccCode mapMccCodeFromDto(MccCodeDto mccCodeDto) {
        System.out.println(mccCodeDto);
        MccCode mccCode = new MccCode();
        mccCode.setId(mccCodeDto.getMccCode());
        mccCode.setMccDescription(mccCodeDto.getMccDescription());
        return mccCode;
    }

}
