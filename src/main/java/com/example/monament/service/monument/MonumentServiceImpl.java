package com.example.monament.service.monument;

import com.example.monament.dto.AddMonumentDTO;
import com.example.monament.dto.ApiResult;
import com.example.monament.enums.MonumentRegion;
import com.example.monament.exceptions.RestException;
import com.example.monament.model.Attachment;
import com.example.monament.model.Monument;
import com.example.monament.repository.AttachmentRepository;
import com.example.monament.repository.MonumentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MonumentServiceImpl implements MonumentService {

    private final MonumentRepository monumentRepository;
    private final AttachmentRepository attachmentRepository;
    private final ModelMapper mapper;

    @Override
    public ApiResult<Boolean> add(AddMonumentDTO addMonumentDTO) {
        List<Attachment> attachments = findAttachment(addMonumentDTO.attachments());

        Monument monument = Monument.builder()
                .nameUz(addMonumentDTO.nameUz())
                .nameEng(addMonumentDTO.nameEng())
                .buildAt(addMonumentDTO.buildAt())
                .lon(addMonumentDTO.lon())
                .lat(addMonumentDTO.lat())
                .descriptionUz(addMonumentDTO.descriptionUz())
                .descriptionEng(addMonumentDTO.descriptionEng())
                .monumentRegion(addMonumentDTO.monumentRegion())
                .attachments(attachments)
                .build();

        monumentRepository.save(monument);
        return ApiResult.successResponse(true);
    }

    @Override
    public ApiResult<Monument> getById(Long id) {
        return ApiResult.successResponse(monumentRepository.findById(id).orElseThrow(() -> RestException.restThrow("not found",HttpStatus.BAD_REQUEST)));
    }

    @Override
    public ApiResult<Page<Monument>> list(int page, int size, String search) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Monument> monuments = monumentRepository.findAllByNameEngContainingIgnoreCaseOrNameUzContainingIgnoreCase(search, search, pageRequest);
        return ApiResult.successResponse(monuments);
    }

    private List<Attachment> findAttachment(List<Long> all) {
        List<Attachment> attachments = new ArrayList<>();
        for (Long i : all) {
            attachments.add(attachmentRepository.findById(i).orElseThrow(() -> RestException.restThrow("attachment not found = " + i, HttpStatus.BAD_REQUEST)));
        }
        return attachments;
    }

    @Override
    public ApiResult<List<Monument>> getByRegion(MonumentRegion region) {

        return  ApiResult.successResponse(monumentRepository.findAllByMonumentRegion(region));
    }

    @Override
    public ApiResult<List<Monument>> all() {
        return ApiResult.successResponse(monumentRepository.findAll());
    }
}
