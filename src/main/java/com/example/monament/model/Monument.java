package com.example.monament.model;

import com.example.monament.enums.MonumentRegion;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Monument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nameUz;
    String nameEng;
    LocalDate buildAt;
    String lon;
    String lat;
    @Size(max = 1000)
    String descriptionUz;
    @Size(max = 1000)
    String descriptionEng;
    @Enumerated(EnumType.STRING)
    MonumentRegion monumentRegion;
    @OneToMany
    List<Attachment> attachments;

}
