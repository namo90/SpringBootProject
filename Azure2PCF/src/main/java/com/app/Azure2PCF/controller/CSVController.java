package com.app.Azure2PCF.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.Azure2PCF.dto.UserDataConvertorDto;
import com.app.Azure2PCF.dto.excelDataDto;
import com.app.Azure2PCF.dto.orderRequestDto;
import com.app.Azure2PCF.helper.CSVHelper;
import com.app.Azure2PCF.model.ExcelToDb;
import com.app.Azure2PCF.model.Order;
import com.app.Azure2PCF.model.ResponseMessage;
import com.app.Azure2PCF.repository.ExcelToDbRepository;
import com.app.Azure2PCF.service.CSVService;

@CrossOrigin(origins = "*")
@Controller
//@RequestMapping("/api/csv")
public class CSVController {

  @Autowired
  CSVService fileService;
  @Autowired
  ExcelToDbRepository excelToDbRepository;
  @Autowired
	UserDataConvertorDto userDataConvertorDto;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (CSVHelper.hasCSVFormat(file)) {
      try {
        fileService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/csv/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message,""));
  }

  @GetMapping("/tutorials")
  public ResponseEntity<List<ExcelToDb>> getAllTutorials() {
    try {
      List<ExcelToDb> tutorials = fileService.getAllTutorials();

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/download")
  public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName) {
    InputStreamResource file = new InputStreamResource(fileService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }
  
  @PostMapping("/exceltodb")
	public ResponseEntity<Object> placeOrder(@RequestBody List<ExcelToDb> excelToDb) {
	  
	  List<ExcelToDb> db=new ArrayList();
	// List<ExcelToDb>  ex=userDataConvertorDto.dtoToEntityExcelList(excelToDb);
	 excelToDbRepository.saveAll(excelToDb).forEach(db::add);
		//return customerOrderServiceImpl.saveOrder(order);
	
	return null;
	}
}