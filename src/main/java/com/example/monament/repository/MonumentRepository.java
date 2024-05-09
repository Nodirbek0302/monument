package com.example.monament.repository;

import com.example.monament.enums.MonumentRegion;
import com.example.monament.model.Monument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface MonumentRepository extends JpaRepository<Monument,Long> {

    List<Monument> findAllByMonumentRegion(MonumentRegion monumentRegion);
    Page<Monument> findAllByNameEngContainingIgnoreCaseOrNameUzContainingIgnoreCase(String nameEng, String nameUz, Pageable pageable);
}
