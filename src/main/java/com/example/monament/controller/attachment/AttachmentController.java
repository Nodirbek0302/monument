package com.example.monament.controller.attachment;

import com.example.monament.dto.ApiResult;
import com.example.monament.dto.AttachmentDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping(AttachmentController.BASE_PATH)
public interface AttachmentController {

    String BASE_PATH = "/attachment";
    String UPLOAD_PATH = "/upload";

    @PostMapping(UPLOAD_PATH)
    ApiResult<AttachmentDTO> uploadFile(MultipartHttpServletRequest request);


    @GetMapping("{id}")
    ResponseEntity<?> downloadFile(@PathVariable Long id,
                                   @RequestParam(defaultValue = "inline") String view,
                                   HttpServletResponse response);
}
