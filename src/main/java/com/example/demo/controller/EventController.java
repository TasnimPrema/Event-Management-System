package com.example.demo.controller;



import com.example.demo.model.Event;
import com.example.demo.repo.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {
    @Autowired
    private EventRepo eventRepo;

    @GetMapping("/getAllEvents")
    public ResponseEntity<List<Event>> getAllEvents() {
        try{
            List<Event> eventList = new ArrayList<>();
            eventRepo.findAll().forEach(eventList::add);
            if(eventList.isEmpty()){
                return new ResponseEntity<>(eventList,HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>( HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> eventData = eventRepo.findById(id);
        if(eventData.isPresent()) {
            return new ResponseEntity<>(eventData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addBook")
    public ResponseEntity<Event> addEvent(@RequestBody Event event) {
        Event eventObj =  eventRepo.save(event);
        return new ResponseEntity<>(eventObj, HttpStatus.OK);
    }





}
