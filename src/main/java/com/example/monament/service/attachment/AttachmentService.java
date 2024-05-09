package com.example.monament.service.attachment;

import com.example.monament.dto.ApiResult;
import com.example.monament.dto.AttachmentDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AttachmentService {

    ApiResult<AttachmentDTO> uploadFile(MultipartHttpServletRequest request);

    ResponseEntity<?> downloadFile(Long id, String view, HttpServletResponse response);
}
