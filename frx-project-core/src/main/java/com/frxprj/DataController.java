package com.frxprj;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class DataController {

    @Autowired
    DataItemRepository dataItemRepository;

    DataController(DataItemRepository repository) {
        this.dataItemRepository = repository;
    }

    @GetMapping("/data")
    public ResponseEntity<List<DataItem>> getAllData(@RequestParam(required = false, name = "type", defaultValue = "") String param) {
        try {
            List<DataItem> data = new ArrayList<>();
            switch (param) {
                case "5m":
                    dataItemRepository.findAll5m().forEach(data::add);
                    break;
                case "1h":
                    dataItemRepository.findAll1h().forEach(data::add);
                    break;
                case "1d":
                    dataItemRepository.findAll1d().forEach(data::add);
                    break;
                case "1w":
                    dataItemRepository.findAll1w().forEach(data::add);
                    break;
                default:
                    dataItemRepository.findAll().forEach(data::add);
                    break;
            }
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}
