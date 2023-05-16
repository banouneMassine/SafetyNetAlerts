package com.safetyNet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.model.PersonsModel;
import com.safetyNet.service.PersonsService;

@RestController
public class PersonsController {

	@Autowired
    private PersonsService personsService;

    @GetMapping("/personALL")
    public ResponseEntity<List<PersonsModel>> getPersonsALL() {
        return ResponseEntity.status(HttpStatus.OK).body(personsService.getPersons());
    }
    
    @GetMapping("/person/{firstName}")
    public ResponseEntity<PersonsModel> getPerson(@PathVariable String firstName)
    {
    	return ResponseEntity.status(HttpStatus.OK).body(personsService.getPerson(firstName));
    }
    
    // ajouter une personne au fichier JSON 
    @PostMapping(value = "/person")
    public void ajouterProduit(@RequestBody PersonsModel preson) {
    	personsService.addPerson(preson);
    }
}
