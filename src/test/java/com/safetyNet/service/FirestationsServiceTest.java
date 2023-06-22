package com.safetyNet.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

import com.safetyNet.DTO.FireStationsDTO;
import com.safetyNet.exceptions.FireStationIntrouvableException;
import com.safetyNet.model.FirestationsModel;
import com.safetyNet.repository.FirestationsRepository;

@ExtendWith(MockitoExtension.class)
class FirestationsServiceTest {
	
	@InjectMocks
	FirestationsService firestationsServic ;
	
	@Mock 
	FirestationsRepository firestationsRepository;
	
	@Test
	@DisplayName("Tester la récupération de toutes les stations ")
	void getFireStations_RuternListOfAllStations()
	{
		//GIVEN
		List<FirestationsModel> listFireStations = new ArrayList<>();
		listFireStations.add(new FirestationsModel());
		listFireStations.add(new FirestationsModel());
		
		when(firestationsRepository.findAll()).thenReturn(listFireStations);
		
		//WHEN
		List<FireStationsDTO> listFireStationsDTO =firestationsServic.getFireStations();
		
		//THENE
		verify(firestationsRepository, times(1)).findAll();
		assertThat(listFireStationsDTO.size()).isEqualTo(2);
	} 
	
	@Test
	@DisplayName("Tester la récupération d'une station ")
	void getFireStation_whenEnteringTheAdresse_thenReturnTheFireStationsDTO()
	{
		//GIVEN
		FirestationsModel fireStations = new FirestationsModel();
		fireStations.setAddress("adresse de station");
		String adresse = "adresse de station";
		
		when(firestationsRepository.findByAdresse(adresse)).thenReturn(fireStations);
		
		//WHEN
		FireStationsDTO fireStationsDTO =assertDoesNotThrow(() -> firestationsServic.getFireStation(adresse));
		
		//THENE
		verify(firestationsRepository, times(1)).findByAdresse(adresse);
		assertThat(fireStationsDTO.getAddress()).isEqualTo(adresse);
	} 
	
	@Test
	@DisplayName("Tester que quand la station recherchée n'est pas trouvée alors une exception est levée")
	void getFireStation_whenEnteringAnNotFoundAdresse_thenReturnException()
	{
		//GIVEN
		String adresse = "adresse de station";
		
		when(firestationsRepository.findByAdresse(adresse)).thenReturn(null);
		
		//WHEN
		FireStationIntrouvableException thrown =assertThrows(FireStationIntrouvableException.class ,() -> firestationsServic.getFireStation(adresse));
		
		//THENE
		verify(firestationsRepository, times(1)).findByAdresse(adresse);
		assertThat(thrown.getMessage()).isEqualTo("La station "+ adresse + "  est introuvable");
	}
	
	
	@Test
	@DisplayName("Tester l'ajout d'une station ")
	void addFireStation_whenEnteringAnNewStation_theAaddItAndRuternIt()
	{
		//GIVEN
		FirestationsModel newFirestation = new FirestationsModel();
		newFirestation.setAddress("adresse de station 1");
		when(firestationsRepository.saveFireStation(newFirestation)).thenReturn(newFirestation);
	
		//WHEN
		FireStationsDTO newFireStationDTO =assertDoesNotThrow(() -> firestationsServic.addFireStation(newFirestation));
		
		//THENE
		verify(firestationsRepository,times(1)).saveFireStation(newFirestation);
		assertThat(newFireStationDTO.getAddress()).isEqualTo(newFirestation.getAddress());
	}
	
	@Test
	@DisplayName("Tester l'ajout d'une station null , une exception est levée ")
	void addFireStation_whenEnteringAnNotFoundStation_theReturnException ()
	{
		//GIVEN
		
		
		//WHEN
		FireStationIntrouvableException thrown =assertThrows(FireStationIntrouvableException.class,() -> firestationsServic.addFireStation(null));
		
		//THENE
		assertThat(thrown.getMessage()).isEqualTo("Impossible d'ajouter une station vide");
	}
	
	@Test
	@DisplayName("Tester la modification d'une station")
	void updateFireStations_whenEnteringAnStation_theUpdatItAndReturnIt ()
	{
		//GIVEN
		FirestationsModel fireStation = new FirestationsModel();
		fireStation.setAddress("adresse de station de modofication");
		
		
		when(firestationsRepository.updateFireStation(fireStation)).thenReturn(fireStation);
		//WHEN
		FireStationsDTO updateFireStationDTO = assertDoesNotThrow(()-> firestationsServic.updateFireStations(fireStation));
		//THENE
		verify(firestationsRepository,times(1)).updateFireStation(fireStation);
		assertThat(updateFireStationDTO.getAddress()).isEqualTo(fireStation.getAddress());

	}
	
	@Test
	@DisplayName("Tester la modification d'une station introuvable -> renvoie une exception")
	void updateFireStations_whenEnteringAnNotFoundStation_thenReturnException()
	{
		//GIVEN
		FirestationsModel fireStation = new FirestationsModel();
		fireStation.setAddress("adresse de station de modofication");
		
		when(firestationsRepository.updateFireStation(fireStation)).thenReturn(null);
		//WHEN
		FireStationIntrouvableException thrown =assertThrows(FireStationIntrouvableException.class,()-> firestationsServic.updateFireStations(fireStation));
		//THENE
		verify(firestationsRepository,times(1)).updateFireStation(fireStation);
		assertThat(thrown.getMessage()).isEqualTo("La station "+ fireStation.address + " est introuvable");

	}
	
	@Test
	@DisplayName("Tester la supprission d'une station")
	void deleteFireStations_whenEnteringAddresOfStation_thenDeleteItandRuternIt()
	{
		//GIVEN
		FirestationsModel fireStation = new FirestationsModel();
		fireStation.setStation(5);
		fireStation.setAddress("adresse de station a supprimer");
		String adresse = "adresse de station a supprimer";
		when(firestationsRepository.findByAdresse(adresse)).thenReturn(fireStation);
		when(firestationsRepository.removeFireStation(fireStation)).thenReturn(fireStation);
		//WHEN
		FireStationsDTO deleteFireStationDTO = assertDoesNotThrow(()->  firestationsServic.deleteFireStations(adresse));
		//THENE
		verify(firestationsRepository,times(1)).findByAdresse(adresse);
		verify(firestationsRepository,times(1)).removeFireStation(fireStation);
		assertThat(deleteFireStationDTO.getAddress()).isEqualTo(fireStation.getAddress());
		assertThat(deleteFireStationDTO.getStation()).isEqualTo(fireStation.getStation());

	}
	
	@Test
	@DisplayName("Tester que la supprission d'une station introuvable ne renvoie une exception")
	void deleteFireStations_whenEnteringAnNotFoundAddres_thenReturnException()
	{
		//GIVEN
		String adresse = "station a supprimer";
		when(firestationsRepository.findByAdresse(adresse)).thenReturn(null);
		
		//WHEN
		FireStationIntrouvableException thrown = assertThrows(FireStationIntrouvableException.class,()->  firestationsServic.deleteFireStations(adresse));
		
		//THENE
		verify(firestationsRepository,times(1)).findByAdresse(adresse);
		assertThat(thrown.getMessage()).isEqualTo("La station "+ adresse + " est introuvable");

	}
	
	@Test
	@DisplayName("Tester la conversion d'un MedicalRecordsModel a un medicalRecordsDTO")
	void convertToDTO_whenEnteringAnMedicalRecordsModel_thenReturnMedicalRecordsDTO()
	{
		//GIVEN
		FirestationsModel fireStation = new FirestationsModel();
		fireStation.setStation(9);
		fireStation.setAddress("adresse de station ");
		
		//WHEN
		FireStationsDTO fireStationDTO = firestationsServic.convertToDTO(fireStation);
		
		//THENE 
		assertThat(fireStationDTO.getStation()).isEqualTo(fireStation.getStation());
		assertThat(fireStationDTO.getAddress()).isEqualTo(fireStation.getAddress());
		
	}
}
