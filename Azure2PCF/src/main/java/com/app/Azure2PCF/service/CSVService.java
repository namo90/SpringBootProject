package com.app.Azure2PCF.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.Azure2PCF.helper.CSVHelper;
import com.app.Azure2PCF.model.ExcelToDb;
import com.app.Azure2PCF.repository.ExcelToDbRepository;

@Service
public class CSVService {
  @Autowired
  ExcelToDbRepository repository;

  public void save(MultipartFile file) {
    try {
      List<ExcelToDb> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
      repository.saveAll(tutorials);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public ByteArrayInputStream load() {
    List<ExcelToDb> tutorials = repository.findAll();

    ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
    return in;
  }

  public List<ExcelToDb> getAllTutorials() {
    return repository.findAll();
  }
}
