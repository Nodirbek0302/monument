package com.example.monament.controller;

import com.example.monament.dto.AddMonumentDTO;
import com.example.monament.dto.ApiResult;
import com.example.monament.enums.MonumentRegion;
import com.example.monament.model.Monument;
import com.example.monament.service.monument.MonumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/monument")
@RequiredArgsConstructor
public class MonumentController {

    private final MonumentService monumentService;

    @PostMapping("/add")
    public HttpEntity<ApiResult<Boolean>> add(@Valid @RequestBody AddMonumentDTO addMonumentDTO){
        return ResponseEntity.ok(monumentService.add(addMonumentDTO));
    }

    @GetMapping("/{id}")
    public HttpEntity<ApiResult<Monument>> getById(@PathVariable Long id){
        return ResponseEntity.ok(monumentService.getById(id));
    }

    @GetMapping("/listSearch")
    public HttpEntity<ApiResult<Page<Monument>>> listSearch(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(defaultValue = "") String search){
     return ResponseEntity.ok(monumentService.list(page,size,search));
    }

    @GetMapping("/getByRegion")
    public HttpEntity<ApiResult<List<Monument>>> getByRegions(MonumentRegion region){
        return ResponseEntity.ok(monumentService.getByRegion(region));
    }

    @GetMapping("/list")
    public HttpEntity<ApiResult<List<Monument>>> list(){
        return ResponseEntity.ok(monumentService.all());
    }



}
