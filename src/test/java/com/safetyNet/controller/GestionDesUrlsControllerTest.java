package com.safetyNet.controller;

import static org.junit.jupiter.api.Assertions.*;
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

import com.safetyNet.DTO.ChildDTO;
import com.safetyNet.DTO.FireDTO;
import com.safetyNet.DTO.FloodDTO;
import com.safetyNet.DTO.PersonInfoDTO;
import com.safetyNet.DTO.PersonsCoveredByTheFirestationDTO;
import com.safetyNet.service.GestionDesUrlsService;

@ExtendWith(MockitoExtension.class)
class GestionDesUrlsControllerTest {

	@InjectMocks
	GestionDesUrlsController gestionDesUrlsController ;
	
	@Mock
	GestionDesUrlsService gestionDesUrlsService ;
	
	@Test
	@DisplayName("Tester la récupération de la liste des personnes couvertes par la station correspondante")
	void listOfPeopleCoveredByFirestation_wheneEntringStationNumber_ThenRuturnListOfPersons()
	{
		//GIVEN
		int stationNumber = 3;
		List<PersonsCoveredByTheFirestationDTO> listDesPersonnesCorrespondants = new ArrayList<>();
		listDesPersonnesCorrespondants.add(new PersonsCoveredByTheFirestationDTO());
		when(gestionDesUrlsService.getListOfPeopleCoveredByTheFirestation(stationNumber)).thenReturn(listDesPersonnesCorrespondants);
		
		//WHEN
		ResponseEntity<List<PersonsCoveredByTheFirestationDTO>> result = gestionDesUrlsController.listOfPeopleCoveredByFirestation(stationNumber);
		
		//THEN
		assertEquals(listDesPersonnesCorrespondants, result.getBody());
		verify(gestionDesUrlsService).getListOfPeopleCoveredByTheFirestation(stationNumber);
	}
	
	
	
	@Test
	@DisplayName("Tester la récupération de la liste des enfants d'une adresse")
	void listOfChildLivingAtThisAddress_wheneEntringAdresse_ThenRuturnListOfChild()
	{
		//GIVEN
		String adresse = "adresse 1";
		List<ChildDTO> listDesEnfantsCorrespondants = new ArrayList<>();
		listDesEnfantsCorrespondants.add(new ChildDTO());
		when(gestionDesUrlsService.getListOfChild(adresse)).thenReturn(listDesEnfantsCorrespondants);
		
		//WHEN
		ResponseEntity<List<ChildDTO>> result = gestionDesUrlsController.listOfChildLivingAtThisAddress(adresse);
		
		//THEN
		assertEquals(listDesEnfantsCorrespondants, result.getBody());
		verify(gestionDesUrlsService).getListOfChild(adresse);
	}
	
	@Test
	@DisplayName("Tester la récupération de la liste des enfants d'une adresse")
	void listOfPersonsWithFireStationByAdresse_wheneEntringAdresse_ThenRuturnListOfPersoneAndFireStation()
	{
		//GIVEN
		int stationNumber = 1;
		List<String> listDesNumurosCorrespondants = new ArrayList<>();
		listDesNumurosCorrespondants.add("111-222-333");
		when(gestionDesUrlsService.getListofNumber(stationNumber)).thenReturn(listDesNumurosCorrespondants);
		
		//WHEN
		ResponseEntity<List<String>> result = gestionDesUrlsController.listOfNumberPhoneByFireStation(stationNumber);
		
		//THEN
		assertEquals(listDesNumurosCorrespondants, result.getBody());
		verify(gestionDesUrlsService).getListofNumber(stationNumber);
	}
	
	@Test
	@DisplayName("Tester la récupération de la lists des habitants vivant à l’adresse donnée ainsi que le numéro de la station desservant")
	void listOfNumberPhoneByFireStation_wheneEntringStationNumber_ThenRuturnListOfNumberPhone()
	{
		//GIVEN
		String adresse = "adresse 2";
		List<FireDTO> listdesHabitants = new ArrayList<>();
		listdesHabitants.add(new FireDTO());
		when(gestionDesUrlsService.getListOfPersonAndFirestation(adresse)).thenReturn(listdesHabitants);
		
		//WHEN
		 ResponseEntity<List<FireDTO>> result = gestionDesUrlsController.listOfPersonsWithFireStationByAdresse(adresse);
		
		//THEN
		assertEquals( listdesHabitants, result.getBody());
		verify(gestionDesUrlsService).getListOfPersonAndFirestation(adresse);
	}
	
	@Test
	@DisplayName("Tester la récupération de la lists de tous les foyers desservis par la caserne")
	void listOFHomesServedByFireStations_wheneEntringStationNumber_ThenRuturnListOfFoyer()
	{
		//GIVEN
		List<Integer> stationNumber =  new ArrayList<>() ;
		stationNumber.add(2);
		List<FloodDTO> listdesFoyer = new ArrayList<>();
		listdesFoyer.add(new FloodDTO());
		when(gestionDesUrlsService.getListOfServedByFireStations(stationNumber)).thenReturn(listdesFoyer);
		
		//WHEN
		ResponseEntity<List<FloodDTO>> result = gestionDesUrlsController.listOFHomesServedByFireStations(stationNumber);
		
		//THEN
		assertEquals( listdesFoyer, result.getBody());
		verify(gestionDesUrlsService).getListOfServedByFireStations(stationNumber);
	}
	
	@Test
	@DisplayName("Tester la récupération des adresses mail de tous les habitants de la ville")
	void listOfEmailByCity_wheneEntringcity_ThenRuturnListOfEmails()
	{
		//GIVEN
		String city =  "paris" ;
	
		List<String> listDesEmailCorrespondants = new ArrayList<>();
		listDesEmailCorrespondants.add("toto@gmail.com");
		when(gestionDesUrlsService.getListOFEmail(city)).thenReturn(listDesEmailCorrespondants);
		
		//WHEN
		ResponseEntity<List<String>> result = gestionDesUrlsController.listOfEmailByCity(city);
		
		//THEN
		assertEquals( listDesEmailCorrespondants, result.getBody());
		verify(gestionDesUrlsService).getListOFEmail(city);
	}
	
	@Test
	@DisplayName("Tester la récupération des adresses mail de tous les habitants de la ville")
	void listOfPersonInfos_wheneEntringFirstAndLastName_ThenRuturnListOfInfos()
	{
		//GIVEN
		String firstName =  "jo" ;
		String lastName =  "jonas" ;
		
		PersonInfoDTO personInfoDTO = new PersonInfoDTO();
		personInfoDTO.setLastName("jo");
		personInfoDTO.setAddress("jonas");
		personInfoDTO.setAge(30) ;
		personInfoDTO.setEmail("hhh@gmail.com");
		personInfoDTO.setAllergies(new ArrayList<>());
		personInfoDTO.setMedications(new ArrayList<>());
		personInfoDTO.setFamilles(new ArrayList<>());
	
		List<String> listDesEmailCorrespondants = new ArrayList<>();
		listDesEmailCorrespondants.add("toto@gmail.com");
		when(gestionDesUrlsService.getPersonInfos(firstName , lastName)).thenReturn(personInfoDTO);
		
		//WHEN
		 ResponseEntity<PersonInfoDTO>result = gestionDesUrlsController.listOfPersonInfos(firstName , lastName);
		
		//THEN
		assertEquals( personInfoDTO, result.getBody());
		verify(gestionDesUrlsService).getPersonInfos(firstName , lastName);
	}
	
}
