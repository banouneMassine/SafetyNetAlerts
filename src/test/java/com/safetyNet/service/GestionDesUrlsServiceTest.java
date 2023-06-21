package com.safetyNet.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.safetyNet.DTO.ChildDTO;
import com.safetyNet.DTO.FireDTO;
import com.safetyNet.DTO.FloodDTO;
import com.safetyNet.DTO.PersonInfoDTO;
import com.safetyNet.DTO.PersonsCoveredByTheFirestationDTO;
import com.safetyNet.model.FirestationsModel;
import com.safetyNet.model.MedicalRecordsModel;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.FirestationsRepository;
import com.safetyNet.repository.MedicalRecordRepository;
import com.safetyNet.repository.PersonsRepository;
@ExtendWith(MockitoExtension.class)
class GestionDesUrlsServiceTest {

	@InjectMocks
	GestionDesUrlsService gestionDesUrlsService ;
	
	@Mock
	PersonsRepository personsRepository;
	
	@Mock
	FirestationsRepository firestationsRepository;
	
	@Mock
	MedicalRecordRepository medicalRecordRepository;
	
	@Test
	@DisplayName("tester la récupération d'une liste de personne a partir d'eun numero de station")
	void getListOfPeopleCoveredByTheFirestation_wheneEnteringTheStatioNumber_theneRuternListOfPerson()
	{
		//GIVEN
		int stationNumber = 1 ;
		List<PersonsModel> listPersons = new ArrayList<>();
		PersonsModel person1 = new PersonsModel();
		person1.setAddress("adresse 1");
		
		PersonsModel person2 = new PersonsModel();
		person2.setAddress("adresse 2");
		
		PersonsModel person3 = new PersonsModel();
		person3.setAddress("adresse 3");
		
		listPersons.add(person1);
		listPersons.add(person2);
		listPersons.add(person3);
		when( personsRepository.findAll()).thenReturn(listPersons);
		
		List<FirestationsModel> listFireStations = new ArrayList<>();
		FirestationsModel firestation1	= new FirestationsModel();
		firestation1.setAddress("adresse 1");
		
		FirestationsModel firestation2	= new FirestationsModel();
		firestation2.setAddress("adresse 2");
		
		listFireStations.add(firestation1); 
		listFireStations.add(firestation2);
		
		when(firestationsRepository.findByStationNumber(stationNumber)).thenReturn(listFireStations);
		
		//WHEN
		List<PersonsCoveredByTheFirestationDTO> listDesPersonsCoveredByTheFirestationDTO = gestionDesUrlsService.getListOfPeopleCoveredByTheFirestation(stationNumber);
		
		//THEN
		verify(personsRepository, times(1)).findAll();
		verify(firestationsRepository, times(1)).findByStationNumber(stationNumber);
		assertThat(listDesPersonsCoveredByTheFirestationDTO.size()).isEqualTo(2);
	}
	
	
	@Test
	@DisplayName("tester la récupération d'une liste d'enfants habitant à une adresse")
	void getListOfChild_wheneEnteringAdresse_theneRuternListOfChidren()
	{
		//GIVEN
		List<PersonsModel> listPersons = new ArrayList<>();
		PersonsModel person1 = new PersonsModel();
		person1.setFirstName("wayne");
		person1.setLastName("Ronney");
		person1.setAddress("adresse 1");
		
		PersonsModel person2 = new PersonsModel();
		person2.setFirstName("David");
		person2.setLastName("Beckham");
		person2.setAddress("adresse 2");
		
		PersonsModel person3 = new PersonsModel();
		person3.setFirstName("David");
		person3.setLastName("De GEA");
		person3.setAddress("adresse 3");
		
		listPersons.add(person1);
		listPersons.add(person2);
		listPersons.add(person3);
		when( personsRepository.findByAdresse(any(String.class))).thenReturn(listPersons);
		
		List<MedicalRecordsModel> listDesDossier =  new ArrayList<>();
		 
		MedicalRecordsModel enfant1 = new MedicalRecordsModel();
		enfant1.setFirstName("wayne");
		enfant1.setLastName("Ronney");
		enfant1.setBirthdate("01/01/2009");
		MedicalRecordsModel enfant2 = new MedicalRecordsModel();
		enfant2.setFirstName("David");
		enfant2.setLastName("Beckham");
		enfant2.setBirthdate("01/01/2012");
		MedicalRecordsModel personne = new MedicalRecordsModel();
		personne.setFirstName("David");
		personne.setLastName("De GEA");
		personne.setBirthdate("01/01/1993");
		
		
		listDesDossier.add(enfant1);
		listDesDossier.add(enfant2);
		listDesDossier.add(personne); 
		
		when(medicalRecordRepository.findAll()).thenReturn(listDesDossier);
		
		//WHEN
		List<ChildDTO> listDesEnfantsCorrespondants = gestionDesUrlsService.getListOfChild("adresse");
		
		//THEN
		verify(medicalRecordRepository, times(1)).findAll();
		verify(personsRepository, times(1)).findByAdresse(any(String.class));
		assertThat(listDesEnfantsCorrespondants.size()).isEqualTo(2);
		assertThat(listDesEnfantsCorrespondants.get(0).getAge()).isLessThan(18);
	} 
	
	@Test
	@DisplayName("tester la récupération de la liste des numero de telephone a partir d'un numero de station")
	void getListofNumber_wheneEnteringStationNumber_theneRuternListOfPhoneNumber()
	{
		//Given
		
		List<String> listExpected = Arrays.asList("444-1234-999", "333-1534-111");
		int stationNumber =2 ;
		List<FirestationsModel> listFireStations = new ArrayList<>();
		FirestationsModel firestation1	= new FirestationsModel();
		firestation1.setAddress("adresse 1");
		firestation1.setStation(stationNumber);
		
		listFireStations.add(firestation1);
		FirestationsModel firestation2	= new FirestationsModel();
		firestation2.setAddress("adresse 2");
		firestation2.setStation(stationNumber);
		
		listFireStations.add(firestation2);
		
		
		when(firestationsRepository.findByStationNumber(stationNumber)).thenReturn(listFireStations);
		List<PersonsModel> listPersons = new ArrayList<>();
	
		PersonsModel person1 = new PersonsModel();
		person1.setAddress("adresse 1");
		person1.setPhone("444-1234-999");
		listPersons.add(person1);
		
		PersonsModel person2 = new PersonsModel();
		person2.setAddress("adresse 2");
		person2.setPhone("333-1534-111");
		listPersons.add(person2);
		
		when( personsRepository.findAll()).thenReturn(listPersons);
        //WHEN
        List<String> listDesNumurosCorrespondants = gestionDesUrlsService.getListofNumber(stationNumber);
        
        //THEN
        verify(firestationsRepository).findByStationNumber(stationNumber) ;
        verify(personsRepository).findAll();
        assertThat(listDesNumurosCorrespondants).isEqualTo(listExpected);

	}
	
	@Test
	@DisplayName("tester la récupération des habitants vivant à l’adresse donnée + que le num de la station ")
	void getListOfPersonAndFirestation_wheneEnteringAddress_theneRuternListOfPersonsAndPhoneNumber()
	{
		//Given
		String adresse = "adresse 1";
		List<PersonsModel> listPersons = new ArrayList<>();
		PersonsModel person1 = new PersonsModel();
		person1.setFirstName("wayne");
		person1.setLastName("Ronney");
		person1.setAddress("adresse 1");
		person1.setPhone("333-1534-111");
		
		PersonsModel person2 = new PersonsModel();
		person2.setFirstName("David");
		person2.setLastName("Beckham");
		person2.setAddress("adresse 1");
		
		PersonsModel person3 = new PersonsModel();
		person3.setFirstName("David");
		person3.setLastName("De GEA");
		person3.setAddress("adresse 3");
		person2.setPhone("333-1534-222");
		
		listPersons.add(person1);
		listPersons.add(person2);
		listPersons.add(person3);
		when( personsRepository.findByAdresse(adresse)).thenReturn(listPersons);

		List<MedicalRecordsModel> listDesDossier =  new ArrayList<>();
		 
		MedicalRecordsModel enfant1 = new MedicalRecordsModel();
		enfant1.setFirstName("wayne");
		enfant1.setLastName("Ronney");
		enfant1.setBirthdate("01/01/2009");
		enfant1.setMedications(new ArrayList<>());
		enfant1.setAllergies(new ArrayList<>());
		MedicalRecordsModel enfant2 = new MedicalRecordsModel();
		enfant2.setFirstName("David");
		enfant2.setLastName("Beckham");
		enfant2.setBirthdate("01/01/2012");
		enfant2.setMedications(new ArrayList<>());
		enfant2.setAllergies(new ArrayList<>());
		
		MedicalRecordsModel personne = new MedicalRecordsModel();
		personne.setFirstName("jo");
		personne.setLastName("De GEA");
		personne.setBirthdate("01/01/1993");
		personne.setMedications(new ArrayList<>());
		personne.setAllergies(new ArrayList<>());
		
		listDesDossier.add(enfant1);
		listDesDossier.add(enfant2);
		listDesDossier.add(personne); 
		
		when(medicalRecordRepository.findAll()).thenReturn(listDesDossier);
		
		//WHEN 
		List<FireDTO> listdesHabitants = gestionDesUrlsService.getListOfPersonAndFirestation(adresse);
		
		//THEN 
		assertThat(listdesHabitants.size()).isEqualTo(2);
		assertThat(listdesHabitants.get(0).getFirstName()).isEqualTo("wayne");
		assertThat(listdesHabitants.get(1).getLastName()).isEqualTo("Beckham");
		assertThat(listdesHabitants.get(1).getAge()).isEqualTo(12);
	}
	
	@ParameterizedTest(name = "tester la recuperation pour la station numero {0}")
	@ValueSource(ints = { 1, 2 })
	@DisplayName("tester la récupération des habitants vivant à l’adresse donnée + que le num de la station ")
	void getListOfServedByFireStations_wheneEnterinListOfStationNumber_theneRuternHouseHold(int arg)
	{
		//GIVEN
		List<Integer> stations = new ArrayList<Integer>();
		stations.add(arg);
		
		List<FirestationsModel> listFireStations = new ArrayList<>();
		FirestationsModel firestation1	= new FirestationsModel();
		firestation1.setAddress("adresse 1");
		
		FirestationsModel firestation2	= new FirestationsModel();
		firestation2.setAddress("adresse 2");
		
		listFireStations.add(firestation1); 
		listFireStations.add(firestation2);
		
		when(firestationsRepository.findByStationNumber(arg)).thenReturn(listFireStations);
		
		List<PersonsModel> listPersons = new ArrayList<>();
		
		PersonsModel person1 = new PersonsModel();
		person1.setFirstName("wayne");
		person1.setLastName("Ronney");
		person1.setAddress("adresse 1");
		person1.setPhone("444-1234-999");
		listPersons.add(person1);
		
		PersonsModel person2 = new PersonsModel();
		person2.setFirstName("David");
		person2.setLastName("Beckham");
		person2.setAddress("adresse 2");
		person2.setPhone("333-1534-111");
		listPersons.add(person2);
		
		PersonsModel person3 = new PersonsModel();
		person3.setFirstName("David");
		person3.setLastName("De GEA");
		person3.setAddress("adresse 4");
		person3.setPhone("333-1534-111");
		listPersons.add(person3);
		
		when( personsRepository.findAll()).thenReturn(listPersons);
		
		List<MedicalRecordsModel> listDesDossier =  new ArrayList<>();
		 
		MedicalRecordsModel enfant1 = new MedicalRecordsModel();
		enfant1.setFirstName("wayne");
		enfant1.setLastName("Ronney");
		enfant1.setBirthdate("01/01/2009");
		MedicalRecordsModel enfant2 = new MedicalRecordsModel();
		enfant2.setFirstName("David");
		enfant2.setLastName("Beckham");
		enfant2.setBirthdate("01/01/2012");
		MedicalRecordsModel personne = new MedicalRecordsModel();
		personne.setFirstName("David");
		personne.setLastName("De GEA");
		personne.setBirthdate("01/01/1993");
		
		
		listDesDossier.add(enfant1);
		listDesDossier.add(enfant2);
		listDesDossier.add(personne); 
		
		when(medicalRecordRepository.findAll()).thenReturn(listDesDossier);
        //WHEN
		List<FloodDTO> floodDTOs = gestionDesUrlsService.getListOfServedByFireStations(stations);
		
		//THEN
		verify(firestationsRepository).findByStationNumber(arg);
		verify(personsRepository).findAll();
		verify(medicalRecordRepository).findAll();
		FloodDTO floodDTO = floodDTOs.get(0);
		assertThat(floodDTO.getAdresse()).isEqualTo("adresse 1");
		
		assertThat( floodDTO.getFireDTO().get(0).getFirstName()).isEqualTo("wayne");
		
		assertThat(floodDTOs.size()).isEqualTo(2);
		
	}
	
	@Test
	@DisplayName("Tester la recuperation  des infos des personnes en fonction du nom prenom")
	void getPersonInfos_wheneEnteringName_theneRuternInfosPerson()
	{
		//GIVEN 
		PersonsModel person1 = new PersonsModel();
		person1.setFirstName("wayne");
		person1.setLastName("Ronney");
		
		
		PersonsModel person2 = new PersonsModel();
		person2.setFirstName("tity");
		person2.setLastName("Ronney");
		
		PersonsModel person3 = new PersonsModel();
		person3.setFirstName("toto");
		person3.setLastName("Ronney");
		
		
		when(personsRepository.findByName(any(String.class),any(String.class))).thenReturn(person1);
		
		List<PersonsModel> familles = new ArrayList<>();
		familles.add(person2);
		familles.add(person3);
		when(personsRepository.getFamilles(person1)).thenReturn(familles);
		
		List<MedicalRecordsModel> listDesDossier =  new ArrayList<>();
		
		MedicalRecordsModel personne = new MedicalRecordsModel();
		personne.setFirstName("wayne");
		personne.setLastName("Ronney");
		personne.setBirthdate("01/01/1993");
		
		listDesDossier.add(personne); 
		
		when(medicalRecordRepository.findAll()).thenReturn(listDesDossier);
		
		//WHEN 
		PersonInfoDTO personInfoDTO = gestionDesUrlsService.getPersonInfos("wayne","Ronney");
		//
		verify(personsRepository).findByName(any(String.class),any(String.class));
		verify(personsRepository).getFamilles(person1);
		verify(medicalRecordRepository).findAll();
		
		assertThat(personInfoDTO.getLastName()).isEqualTo("Ronney");
		assertThat(personInfoDTO.getAge()).isEqualTo(31);
		assertTrue(personInfoDTO.getFamilles().contains(person2));
		assertTrue(personInfoDTO.getFamilles().contains(person3));
	}
	
	@Test
	@DisplayName("tester la récupération de la liste des email d'une ville")
	void getListOFEmail_wheneEnteringCity_theneRuternListOfEmail()
	{
		//Given
		String city = "Guyancourt";
		List<String> listExpected = Arrays.asList("person1@gmail.com", "person2@gmail.com");
		List<PersonsModel> listPersons  = new ArrayList<>();
		PersonsModel person1 = new PersonsModel();
		person1.setCity("Guyancourt");
		person1.setEmail("person1@gmail.com");
		listPersons.add(person1);
		
		PersonsModel person2 = new PersonsModel();
		person2.setCity("Guyancourt");
		person2.setEmail("person2@gmail.com");
		listPersons.add(person2);
		
		
		when( personsRepository.findAll()).thenReturn(listPersons);
        //WHEN
        List<String> listDesEmailCorrespondants = gestionDesUrlsService.getListOFEmail(city);
         
        //THEN
       
        verify(personsRepository).findAll();
        assertThat(listDesEmailCorrespondants).isEqualTo(listExpected);

	}
	
	@Test
	@DisplayName("tester le calcul de l'age a partir d'une date")
	void calculateAge_wheneEnteringDate_theneRuternage()
	{
		//Given
		String date = "01/01/2000";
		
		//WHEN
		int age = GestionDesUrlsService.calculateAge(date);
		//THEN
		assertThat(age).isEqualTo(24);
	}
}
