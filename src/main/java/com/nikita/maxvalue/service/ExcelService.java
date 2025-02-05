package com.nikita.maxvalue.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExcelService implements FileService {
    public Long findMaxNumber(String filePath, int n) {
        try (FileInputStream file = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<Long> numbers = new ArrayList<>();

            for (Row row : sheet) {
                numbers.add((long) row.getCell(0).getNumericCellValue());
            }
            if (n < 1 || n > numbers.size()) {
                log.error("Invalid n: {}", n);
                throw new IllegalArgumentException("Invalid n");
            }
            return quickSelect(numbers, 0, numbers.size() - 1, numbers.size() - n);

        } catch (IOException e) {
            log.error("Error reading file: {}", e.getMessage(), e);
            throw new RuntimeException("Error reading file", e);
        }
    }

    private long quickSelect(List<Long> nums, int left, int right, int k) {
        if (left == right) {
            return nums.get(left);
        }
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        pivotIndex = partition(nums, left, right, pivotIndex);
        if (k == pivotIndex) {
            return nums.get(k);
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(List<Long> nums, int left, int right, int index) {
        long pivotValue = nums.get(index);
        swap(nums, index, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (nums.get(i) < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(List<Long> nums, int i, int j) {
        long temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }
}
