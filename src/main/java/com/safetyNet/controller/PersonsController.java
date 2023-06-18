package com.safetyNet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.DTO.PersonsDTO;
import com.safetyNet.exceptions.PersonIntrovableExeption;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.service.PersonsService;

@RestController
public class PersonsController {

	@Autowired
	private PersonsService personsService;

	@GetMapping("/personALL")
	public ResponseEntity<List<PersonsDTO>> getPersonsALL() throws PersonIntrovableExeption  {
		
		return ResponseEntity.status(HttpStatus.OK).body(personsService.getPersons());
	}

	@GetMapping("/person/{firstName}/{lastName}")
	public ResponseEntity<PersonsDTO> getPerson(@PathVariable String firstName, @PathVariable String lastName) throws PersonIntrovableExeption {
		return ResponseEntity.status(HttpStatus.OK).body(personsService.getPerson(firstName, lastName));
	}

	// ajouter une personne au fichier JSON
	@PostMapping(value = "/person")
	public ResponseEntity<PersonsDTO> postPerson(@RequestBody PersonsModel preson) throws PersonIntrovableExeption {

		return ResponseEntity.status(HttpStatus.OK).body(personsService.addPerson(preson));

	}

	// supprimer une personne du fichier JSON
	@DeleteMapping(value = "/person/{firstName}/{lastName}")
	public ResponseEntity<PersonsDTO> deletePerson(@PathVariable String firstName, @PathVariable String lastName) throws PersonIntrovableExeption {
	
		return ResponseEntity.status(HttpStatus.OK).body( personsService.removePerson(firstName, lastName));

	}

	// Modifier une personne du fichier JSON
	@PutMapping(value = "/person")
	public ResponseEntity<PersonsDTO> putPerson(@RequestBody PersonsModel newPreson) throws PersonIntrovableExeption {

		return ResponseEntity.status(HttpStatus.OK).body(personsService.updatePerson(newPreson));

	}
}
