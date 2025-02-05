package com.nikita.maxvalue.controller;

import com.nikita.maxvalue.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExcelController {
    private final FileService fileService;

    @Operation(summary = "Find max number in Excel file")
    @GetMapping("/max-number")
    public ResponseEntity<Long> findMaxNumber(@RequestParam String filePath, @RequestParam int n) {
        return ResponseEntity.ok(fileService.findMaxNumber(filePath, n));
    }
}
