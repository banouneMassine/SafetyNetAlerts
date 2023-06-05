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

import com.safetyNet.DTO.FireStationsDTO;
import com.safetyNet.exceptions.FireStationIntrouvableException;
import com.safetyNet.model.FirestationsModel;
import com.safetyNet.service.FirestationsService;

@RestController
public class FireStationsController {
	
	
	@Autowired
	FirestationsService fireStationsService ;
	
	@GetMapping("/firestationAll")
	public ResponseEntity<List<FireStationsDTO>> getFireStationsAll() throws FireStationIntrouvableException
	{
		
		return ResponseEntity.status(HttpStatus.OK).body(fireStationsService.getFireStations());
	}
	@GetMapping("/firestation/{adresse}")
	public ResponseEntity<FirestationsModel>getFireStation(@PathVariable String adresse) throws FireStationIntrouvableException
	{
		
		return ResponseEntity.status(HttpStatus.OK).body(fireStationsService.getFireStation(adresse));
	}
	
	@PostMapping(value = "/firestation")
	public ResponseEntity<FireStationsDTO>   postFireStation(@RequestBody  FirestationsModel newFirestations)
	{
		return ResponseEntity.status(HttpStatus.OK).body(fireStationsService.addFireStation(newFirestations));
	}
	
	
	@PutMapping(value="/firestation")
	public ResponseEntity<FireStationsDTO> putFireStation(@RequestBody FirestationsModel updateFireStations) throws FireStationIntrouvableException
	{
		return  ResponseEntity.status(HttpStatus.OK).body(fireStationsService.updateFireStations(updateFireStations));
	}
	
	@DeleteMapping(value = "/firestation/{adresse}")
	public ResponseEntity<FireStationsDTO> deleteFireStation(@PathVariable  String adresse) throws FireStationIntrouvableException
	{
		return ResponseEntity.status(HttpStatus.OK).body(fireStationsService.deleteFireStations(adresse));
	}
	

}
