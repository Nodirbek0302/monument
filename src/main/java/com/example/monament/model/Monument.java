package com.example.monament.model;

import com.example.monament.enums.MonumentRegion;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String buildAt;
    String lon;
    String lat;
    String descriptionUz;
    String descriptionEng;
    @Enumerated(EnumType.STRING)
    MonumentRegion monumentRegion;
    @OneToMany
    List<Attachment> attachments;

}
