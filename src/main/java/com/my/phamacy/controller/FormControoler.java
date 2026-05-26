package com.my.phamacy.controller;


import com.my.phamacy.dto.DocumentDto;
import com.my.phamacy.dto.KakaoApiResponseDto;
import com.my.phamacy.service.KakaoAddressSearchService;
import com.my.phamacy.service.KakaoCategorySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class FormControoler {
    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final KakaoCategorySearchService kakaoCategorySearchService;
    @GetMapping
    public String mainForm(){
        return "main";
    }

    @GetMapping("/output")
    public String outputForm(){
        return "output";
    }

    @PostMapping("/search")
    public String searchAddress(@RequestParam("address")String address){
        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);
        log.info("결과 :" + kakaoApiResponseDto);
        // 결과중 Documents만 빼서 dto에 저장
        DocumentDto documentDto = kakaoApiResponseDto
                .getDocumentList().get(0);
        log.info("도큐먼트 만 출력 :" + documentDto);

        KakaoApiResponseDto kakaoApiCategoryDto = kakaoCategorySearchService.resultCategorySearch(
                documentDto.getLatitude(), documentDto.getLongitude());
        log.info("카테고리 검색 결과 : " + kakaoApiCategoryDto);
        return "output";
    }
}
