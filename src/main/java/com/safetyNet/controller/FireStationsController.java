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

import com.safetyNet.model.FirestationsModel;
import com.safetyNet.service.FirestationsService;

@RestController
public class FireStationsController {
	
	
	@Autowired
	FirestationsService fireStationsService ;
	
	@GetMapping("/firestationAll")
	public ResponseEntity<List<FirestationsModel>> getFireStationsAll()
	{
		
		return ResponseEntity.status(HttpStatus.OK).body(fireStationsService.getFireStations());
	}
	@GetMapping("/firestation/{adresse}")
	public ResponseEntity<FirestationsModel>getFireStation(@PathVariable String adresse)
	{
		
		return ResponseEntity.status(HttpStatus.OK).body(fireStationsService.getFireStation(adresse));
	}
	
	@PostMapping(value = "/firestation")
	public ResponseEntity<String>   postFireStation(@RequestBody  FirestationsModel newFirestations)
	{
		return fireStationsService.addFireStation(newFirestations);
	}
	
	
	@PutMapping(value="/firestation")
	public ResponseEntity<String> putFireStation(@RequestBody FirestationsModel updateFireStations)
	{
		return fireStationsService.updateFireStations(updateFireStations);
	}
	
	@DeleteMapping(value = "/firestation/{adresse}")
	public ResponseEntity<String> deleteFireStation(@PathVariable  String adresse)
	{
		return fireStationsService.deleteFireStations(adresse);
	}
	

}
