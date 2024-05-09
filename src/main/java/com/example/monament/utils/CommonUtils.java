package com.example.monament.utils;

import com.example.monament.controller.attachment.AttachmentController;
import lombok.experimental.UtilityClass;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@UtilityClass
public class CommonUtils {

//    public static User getCurrentUserFromContext() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication == null || authentication.getPrincipal().equals("anonymousUser"))
//            throw RestException.restThrow("OKa yopiq yul", HttpStatus.UNAUTHORIZED);
//
//        return (User) authentication.getPrincipal();
//    }

    public static String makeFileUrl(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath().path(AttachmentController.BASE_PATH)
                .path("/"+id.toString()).toUriString();
    }
}
