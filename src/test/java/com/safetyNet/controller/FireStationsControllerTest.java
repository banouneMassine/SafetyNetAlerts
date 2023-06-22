package com.safetyNet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.safetyNet.DTO.FireStationsDTO;
import com.safetyNet.exceptions.FireStationIntrouvableException;
import com.safetyNet.model.FirestationsModel;
import com.safetyNet.service.FirestationsService;

@ExtendWith(MockitoExtension.class)
class FireStationsControllerTest {

	@InjectMocks
	FireStationsController fireStationsController;
	
	@Mock
	FirestationsService fireStationsService ;
	
	@Test
	@DisplayName("Tester la récupération de la liste des stations")
	void test_getFireStationsAll()  
	{//GIVEN
		List<FireStationsDTO> listStation = new ArrayList<>();
		listStation.add(new FireStationsDTO());
		
		when(fireStationsService.getFireStations()).thenReturn(listStation);
		//WHEN
		ResponseEntity<List<FireStationsDTO>> result = fireStationsController.getFireStationsAll();	
		//THEN
		assertEquals(listStation, result.getBody());
		verify(fireStationsService, times(1)).getFireStations();
	}
	
	@Test
	@DisplayName("Tester la récupération d'une station a partir de son adresse")
	void getFireStation_wheneEntringAdresse_ThenRuturnStation() throws Exception {
		//GIVEN
		FireStationsDTO station = new FireStationsDTO();
		station.setAddress("adresse 1");
		
		when(fireStationsService.getFireStation("adresse 1")).thenReturn(station);
		//WHEN
		ResponseEntity<FireStationsDTO> result = fireStationsController.getFireStation("adresse 1");	
		//THEN 
		assertEquals(station, result.getBody());
		verify(fireStationsService, times(1)).getFireStation("adresse 1");
		
	}
	
	@Test
	@DisplayName("Tester la récupération d'une station introuvable ")
	void getFireStation_wheneEntringAnNotFoundAdresse_ThenRuturnException() throws Exception {
		//GIVEN		
		when(fireStationsService.getFireStation("adresse 1")).thenThrow(new FireStationIntrouvableException("La station adresse1  est introuvable"));
		//WHEN
		
		FireStationIntrouvableException thrown =assertThrows(FireStationIntrouvableException.class, () -> fireStationsController.getFireStation("adresse 1"));

		//THEN
		assertEquals(thrown.getMessage(),"La station adresse1  est introuvable" );
		verify(fireStationsService, times(1)).getFireStation("adresse 1");
		
	}
	
	@Test
	@DisplayName("Tester l'ajout d'une station")
	void postFireStation_wheneEntringStation_ThenRuturnStation() throws Exception {
		//GIVEN
		FirestationsModel fireStations = new FirestationsModel();
		fireStations.setAddress("adresse 1");;
		fireStations.setStation(10);
		
		FireStationsDTO fireStationsDTO = new FireStationsDTO();
		fireStationsDTO.setAddress("adresse 1");;
		fireStationsDTO.setStation(10);
		
		when(fireStationsService.addFireStation(fireStations)).thenReturn(fireStationsDTO);
		//WHEN
		ResponseEntity<FireStationsDTO> result = fireStationsController.postFireStation(fireStations);	
		//THEN
		assertEquals(fireStationsDTO, result.getBody());
		verify(fireStationsService, times(1)).addFireStation(fireStations);
		
	}
	
	@Test
	@DisplayName("Tester l'ajout d'une station null ")
	void postFireStation_wheneAddingAnNotFoundStation_ThenRuturnException() throws Exception {
		//GIVEN		
		when(fireStationsService.addFireStation(null)).thenThrow(new FireStationIntrouvableException("Impossible d'ajouter une station vide"));
		//WHEN
		
		FireStationIntrouvableException thrown =assertThrows(FireStationIntrouvableException.class, () -> fireStationsController.postFireStation(null));

		//THEN
		assertEquals(thrown.getMessage(),"Impossible d'ajouter une station vide" );
		verify(fireStationsService, times(1)).addFireStation(null);
	}
	
	@Test
	@DisplayName("Tester la supprission d'une station a partir d'une adresse")
	void deleteFireStation_wheneEntringFirstNameAndLastName_ThenRuturnStation() throws Exception {
		//GIVEN
		FireStationsDTO fireStationsDTO = new FireStationsDTO();
		fireStationsDTO.setAddress("adresse 1");;
		fireStationsDTO.setStation(10);
		
		when(fireStationsService.deleteFireStations("adresse 1")).thenReturn(fireStationsDTO);
		//WHEN
		ResponseEntity<FireStationsDTO> result = fireStationsController.deleteFireStation("adresse 1");	
		//THEN
		assertEquals(fireStationsDTO, result.getBody());
		verify(fireStationsService, times(1)).deleteFireStations("adresse 1");
		
	}
	
	@Test
	@DisplayName("Tester la supprission d'une station introuvable ")
	void deleteFireStation_wheneEntringAnNotFoundPerson_ThenRuturnException() throws Exception {
		//GIVEN		
		when(fireStationsService.deleteFireStations("adresse 1")).thenThrow(new FireStationIntrouvableException("La station  adresse 1 est introuvable"));
		//WHEN
		
		FireStationIntrouvableException thrown =assertThrows(FireStationIntrouvableException.class, () -> fireStationsController.deleteFireStation("adresse 1"));

		//THEN
		assertEquals(thrown.getMessage(),"La station  adresse 1 est introuvable");
		verify(fireStationsService, times(1)).deleteFireStations("adresse 1");
	}
	@Test
	@DisplayName("Tester la modif d'une station")
	void putFireStation_wheneEntringStation_ThenRuturnStation() throws Exception {
		//GIVEN
		FirestationsModel fireStations = new FirestationsModel();
		fireStations.setAddress("adresse 1");;
		fireStations.setStation(10);
		
		FireStationsDTO fireStationsDTO = new FireStationsDTO();
		fireStationsDTO.setAddress("adresse 1");;
		fireStationsDTO.setStation(10);
		
		when(fireStationsService.updateFireStations(fireStations)).thenReturn(fireStationsDTO);
		//WHEN
		ResponseEntity<FireStationsDTO> result = fireStationsController.putFireStation(fireStations);	
		//THEN
		assertEquals(fireStationsDTO, result.getBody());
		verify(fireStationsService, times(1)).updateFireStations(fireStations);
		
	}
	
	@Test
	@DisplayName("Tester la modif d'une personne null ")
	void putFireStation_wheneAddingAnNotFoundStation_ThenRuturnException() throws Exception {
		//GIVEN		
		FirestationsModel fireStations = new FirestationsModel();
		fireStations.setAddress("adresse 1");;
		fireStations.setStation(10);
		
		when(fireStationsService.updateFireStations(fireStations)).thenThrow(new FireStationIntrouvableException("La station "+ fireStations.address + " est introuvable"));
		//WHEN
		
		FireStationIntrouvableException thrown =assertThrows(FireStationIntrouvableException.class, () -> fireStationsController.putFireStation(fireStations));

		//THEN
		assertEquals(thrown.getMessage(),"La station "+ fireStations.address + " est introuvable" );
		verify(fireStationsService, times(1)).updateFireStations(fireStations);
	}
}
