package com.example.monament.dto;

import com.example.monament.enums.MonumentRegion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.*;

public record AddMonumentDTO(@NotBlank String nameUz,
                             @NotBlank String nameEng,
                             LocalDate buildAt, String lon, String lat,
                             @NotBlank String descriptionUz,
                             @NotBlank String descriptionEng,
                             @NotNull MonumentRegion monumentRegion,
                             @NotNull List<Long> attachments) {
}
