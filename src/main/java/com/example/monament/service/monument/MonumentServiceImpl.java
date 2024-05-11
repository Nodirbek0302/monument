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

import java.time.LocalDate;
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
        Monument monument = monumentRepository.findById(id).orElseThrow(() -> RestException.restThrow("not found", HttpStatus.BAD_REQUEST));
        monument.setAttachments(getAttachment(monument.getAttachments()));
        return ApiResult.successResponse(monument);
    }

    @Override
    public ApiResult<Page<Monument>> list(int page, int size, String search) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Monument> monuments = monumentRepository.findAllByNameEngContainingIgnoreCaseOrNameUzContainingIgnoreCase(search, search, pageRequest);

        for (Monument monument : monuments) {
            monument.setAttachments(getAttachment(monument.getAttachments()));
        }
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
        List<Monument> monumentRegion = monumentRepository.findAllByMonumentRegion(region);
        for (Monument monument : monumentRegion) {
            monument.setAttachments(getAttachment(monument.getAttachments()));
        }
        return  ApiResult.successResponse(monumentRegion);
    }

    @Override
    public ApiResult<List<Monument>> all() {
        List<Monument> all = monumentRepository.findAll();
        for (Monument monument : all) {
            monument.setAttachments(getAttachment(monument.getAttachments()));
        }
        return ApiResult.successResponse(all);
    }

    private List<Attachment> getAttachment(List<Attachment> all){
        for (Attachment attachment : all) {
            attachment.setContentURL("attachment/"+attachment.getId()+"?view=inline");
        }
        return all;
    }

    @Override
    public ApiResult<List<Monument>> getByYears(LocalDate s, LocalDate s1) {
        List<Monument> between = monumentRepository.findAllByBuildAtBetween(s, s1);
        for (Monument monument : between) {
            monument.setAttachments(getAttachment(monument.getAttachments()));
        }
        return ApiResult.successResponse(between);
    }
}
