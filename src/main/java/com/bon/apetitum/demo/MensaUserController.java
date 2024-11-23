package com.bon.apetitum.demo;

import com.bon.apetitum.demo.entity.MensaUser;
import com.bon.apetitum.demo.service.MensaUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MensaUserController {
    @Autowired
    private MensaUserService mensaUserService;

    @GetMapping("/mensaUsers")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<Iterable<MensaUser>>(mensaUserService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/MensaUsers/{id}")
    public ResponseEntity<?> findOne(@PathVariable int id) {
        return new ResponseEntity<MensaUser>(mensaUserService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/MensaUsers")
    public ResponseEntity<?> newMensaUser(@Valid @RequestBody MensaUser newMensaUser) {
        return new ResponseEntity<MensaUser>(mensaUserService.save(newMensaUser), HttpStatus.CREATED);
    }

    @PutMapping("/MensaUsers/{id}")
    public ResponseEntity<?> replaceMensaUser(@PathVariable int id, @Valid @RequestBody MensaUser newMensaUser) {
        newMensaUser.setId(id);
        mensaUserService.save(newMensaUser);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/MensaUsers/{id}")
    public ResponseEntity<?> deleteMensaUser(@PathVariable int id) {
        mensaUserService.deleteById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
