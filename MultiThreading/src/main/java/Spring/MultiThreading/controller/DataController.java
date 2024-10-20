package Spring.MultiThreading.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import Spring.MultiThreading.service.DataProcessingService;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataProcessingService dataProcessingService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadData(
            @RequestParam("accounts") MultipartFile accountsFile,
            @RequestParam("customers") MultipartFile customersFile) {
        
        try {
        	System.out.println("Upload method called");
            dataProcessingService.processFiles(accountsFile, customersFile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing files: " + e.getMessage());
        }
        
        return ResponseEntity.ok("Data processed successfully");
    }
}

