package com.safetyNet.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.DTO.ChildDTO;
import com.safetyNet.DTO.EmailByCityDTO;
import com.safetyNet.DTO.FireDTO;
import com.safetyNet.DTO.FloodDTO;
import com.safetyNet.DTO.PersonInfoDTO;
import com.safetyNet.DTO.PersonsCoveredByTheFirestationDTO;
import com.safetyNet.DTO.PhoneNumberByStationNumberDTO;
import com.safetyNet.service.GestionDesUrlsService;

@RestController
public class GestionDesUrlsController {
	@Autowired
	GestionDesUrlsService gestionDesUrlsService ; 
	
	//Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante
	@GetMapping("/firestation")
	public ResponseEntity<List<PersonsCoveredByTheFirestationDTO>> listOfPeopleCoveredByFirestation(@RequestParam("stationNumber") int stationNumber )
	{
		return ResponseEntity.status(HttpStatus.OK).body(gestionDesUrlsService.getListOfPeopleCoveredByTheFirestation(stationNumber));
	}
	
	
	//Cette url doit retourner une liste d'enfants habitant à cette adresse
	@GetMapping("/childAlert")
	public ResponseEntity<List<ChildDTO>> listOfChildLivingAtThisAddress(@RequestParam("address") String address)
	{
		return ResponseEntity.status(HttpStatus.OK).body(gestionDesUrlsService.getListOfChild(address));
	}
	
	//Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de pompiers
	@GetMapping("/phoneAlert")
	public ResponseEntity<List<PhoneNumberByStationNumberDTO>> listOfNumberPhoneByFireStation(@RequestParam("stationNumber") int stationNumber )
	{
		return ResponseEntity.status(HttpStatus.OK).body(gestionDesUrlsService.getListofNumber(stationNumber));
	}
	
	//Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne de pompiers la desservant
	@GetMapping("/fire")
	public ResponseEntity<List<FireDTO>> listOfPersonsWithFireStationByAdresse(@RequestParam("address") String address)
	{
		return ResponseEntity.status(HttpStatus.OK).body(gestionDesUrlsService.getListOfPersonAndFirestation(address));
	}
	
	
	//Cette url doit retourner une liste de tous les foyers desservis par la caserne.
	@GetMapping("/flood")
	public ResponseEntity<List<FloodDTO>> listOFHomesServedByFireStations(@RequestParam("stations") List<Integer> station)
	{
		return  ResponseEntity.status(HttpStatus.OK).body(gestionDesUrlsService.getListOfServedByFireStations(station));
	}
	
	
	
	//Cette url doit retourner les adresses mail de tous les habitants de la ville.
	@GetMapping("/communityEmail")
	public ResponseEntity<List<EmailByCityDTO>> listOfEmailByCity(@RequestParam("city") String city)
	{
		return ResponseEntity.status(HttpStatus.OK).body(gestionDesUrlsService.getListOFEmail(city));
	}
	
	@GetMapping("/personInfo")
	public  ResponseEntity<PersonInfoDTO> listOfPersonInfos(@RequestParam("firstName") String firstName , @RequestParam("lastName")  String lastName)
	{
		return ResponseEntity.status(HttpStatus.OK).body(gestionDesUrlsService.getPersonInfos(firstName , lastName));
	}
}
