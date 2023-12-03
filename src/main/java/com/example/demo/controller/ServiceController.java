package com.example.demo.controller;



import com.example.demo.model.Service;
import com.example.demo.repo.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ServiceController {
    @Autowired
    private ServiceRepo serviceRepo;

    @GetMapping("/getAllServices")
    public ResponseEntity<List<Service>> getAllServices() {
        try{
            List<Service> serviceList = new ArrayList<>();
            serviceRepo.findAll().forEach(serviceList::add);
            if(serviceList.isEmpty()){
                return new ResponseEntity<>(serviceList,HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getServiceById/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        Optional<Service> serviceData = serviceRepo.findById(id);
        if(serviceData.isPresent()) {
            return new ResponseEntity<>(serviceData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addService")
    public ResponseEntity<Service> addEvent(@RequestBody Service service) {
        Service serviceObj =  serviceRepo.save(service);
        return new ResponseEntity<>(serviceObj, HttpStatus.OK);
    }





}
