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

	@GetMapping("/person/{firstName}/{lastName}")
	public ResponseEntity<PersonsModel> getPerson(@PathVariable String firstName ,@PathVariable String lastName) {
		return ResponseEntity.status(HttpStatus.OK).body(personsService.getPerson(firstName, lastName));
	}

	// ajouter une personne au fichier JSON
	@PostMapping(value = "/person")
	public ResponseEntity<String> postPerson(@RequestBody PersonsModel preson) {
		
		if(preson !=  null)
    	{
			personsService.addPerson(preson);
     	   return ResponseEntity.ok("la personne est ajouté avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
	}

	// supprimer une personne du fichier JSON
	@DeleteMapping(value = "/person/{firstName}/{lastName}")
    public ResponseEntity<String> deletePerson(@PathVariable  String firstName , @PathVariable  String lastName) 
	{
    	if(firstName != null && lastName != null)
    	{
    		personsService.removePerson(firstName,lastName );
     	   return ResponseEntity.ok("la personne est supprimée avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
    }

	// Modifier une personne du fichier JSON
	@PutMapping(value = "/person")
	public ResponseEntity<String>  putPerson(@RequestBody PersonsModel newPreson) {
		
		if(newPreson != null )
    	{
			personsService.updatePerson(newPreson);
     	   return ResponseEntity.ok("Les informations de la personne sont mises à jour avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
	}
}
