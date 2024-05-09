package com.example.monament.service.monument;

import com.example.monament.dto.AddMonumentDTO;
import com.example.monament.dto.ApiResult;
import com.example.monament.enums.MonumentRegion;
import com.example.monament.model.Monument;
import org.springframework.data.domain.Page;

import java.util.List;


public interface MonumentService {
    ApiResult<Boolean> add(AddMonumentDTO addMonumentDTO);

    ApiResult<Monument> getById(Long id);

    ApiResult<Page<Monument>> list(int page, int size, String search);

    ApiResult<List<Monument>> getByRegion(MonumentRegion region);

    ApiResult<List<Monument>> all();

}
