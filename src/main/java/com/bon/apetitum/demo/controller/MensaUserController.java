package com.bon.apetitum.demo.controller;

import com.bon.apetitum.demo.entity.MensaUser;
import com.bon.apetitum.demo.service.MensaUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actions")
public class MensaUserController {
    @Autowired
    private MensaUserService mensaUserService;

    @GetMapping("/mensaUsers")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<Iterable<MensaUser>>(mensaUserService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/mensaUsers/{id}")
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<MensaUser>(mensaUserService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/mensaUsers")
    public ResponseEntity<?> newMensaUser(@Valid @RequestBody MensaUser newMensaUser) {
        return new ResponseEntity<MensaUser>(mensaUserService.save(newMensaUser), HttpStatus.CREATED);
    }

    @PutMapping("/mensaUsers/{id}")
    public ResponseEntity<?> replaceMensaUser(@PathVariable int id, @Valid @RequestBody MensaUser newMensaUser) {
        newMensaUser.setId(id);
        mensaUserService.save(newMensaUser);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/mensaUsers/{id}")
    public ResponseEntity<?> deleteMensaUser(@PathVariable int id) {
        mensaUserService.deleteById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
