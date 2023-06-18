package com.safetyNet.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import com.safetyNet.model.FirestationsModel;

@ExtendWith(MockitoExtension.class)
class FirestationsRepositoryImpTest {


	
	@InjectMocks
	FirestationsRepositoryImp firestationsRepository;
	
	
	
	@Test
	@DisplayName("Testet la recupération des stations")
	void findAll_testTheRecoveryOfAllStations()
	{
		//GIVEN
		firestationsRepository.addFirestation(new FirestationsModel());
		//WHEN
		List<FirestationsModel> listDesStations  =  firestationsRepository.findAll();
		
		//THENE
		assertThat(listDesStations).isNotEmpty();
		assertThat(listDesStations.size()).isEqualTo(1);
	}
	
	@Test
	@DisplayName("Tester la recupération d'une station grace a une adresse")
	void findByAdresse_testThatWhenEnterAdress_thenReturnTheStation()
	{
		//GIVEN
		String adresse = "adresse 1"; 
		
		FirestationsModel firestation = new FirestationsModel();
		firestation.setAddress("adresse 1");
		firestation.setStation(10);
		
		firestationsRepository.addFirestation(firestation);
		
		//WHEN
		FirestationsModel acualFirestation = firestationsRepository.findByAdresse(adresse);
		//THENE
		assertThat(acualFirestation.getAddress()).isEqualTo(adresse);
		assertThat(acualFirestation.getStation()).isEqualTo(10);
		
		
	}
	
	@Test
	@DisplayName("Tester la recupération de la liste des stations grace au numéro de la station")
	void findByStationNumber_testThatWhenEnterStationNumber_thenReturnTheListStations()
	{
		//GIVEN
		int station = 1;
		String adresseExpected2 = "adresse 2";
		
		FirestationsModel firestation1 = new FirestationsModel();
		firestation1.setAddress("adresse 1");
		firestation1.setStation(1);
		
		FirestationsModel firestation2 = new FirestationsModel();
		firestation2.setAddress("adresse 2");
		firestation2.setStation(1);
		
		firestationsRepository.addFirestation(firestation1);
		firestationsRepository.addFirestation(firestation2);
		//WHEN
		List<FirestationsModel> acualListFirestation = firestationsRepository.findByStationNumber(station);
		
		
		//THENE
		assertThat(acualListFirestation.size()).isEqualTo(2);
		assertThat(acualListFirestation.get(1).getAddress()).isEqualTo(adresseExpected2);
		
	} 
	
	@Test
	@DisplayName("Tester l'ajout d'une nouvelle station")
	void saveFireStation_whenAddNewStation_thenReturnTheStation()
	{
		//GIVEN
		FirestationsModel firestationadded = new FirestationsModel(); 		
		//WHEN
		FirestationsModel newFirestation = firestationsRepository.saveFireStation(firestationadded);
		//THENE
		assertThat(newFirestation).usingRecursiveComparison().isEqualTo(firestationadded);
		assertThat(firestationsRepository.findAll().size()).isEqualTo(1);
	}
	
	
	@Test
	@DisplayName("Tester la supprission d'une station")
	void removeFireStation_testWhenDeletStation_theneReturnStation()
	{
		//GIVEN
		FirestationsModel firestationToDelete = new FirestationsModel();
		firestationToDelete.setAddress("644 Gershwin Cir");
		firestationToDelete.setStation(1);
		
		firestationsRepository.addFirestation(firestationToDelete);
	
		//WHEN
		FirestationsModel firestationtDelete = firestationsRepository.removeFireStation(firestationToDelete);
		
		//THENE
		assertThat(firestationtDelete).isEqualTo(firestationToDelete);
		assertThat(firestationsRepository.findAll()).doesNotContain(firestationToDelete);
		assertThat(firestationsRepository.findAll().size()).isEqualTo(0);
	}
	
	@Test
	@DisplayName("Tester la supprission d'une station qui n'existe pas dans la liste des stations")
	void removeFireStation_testWheneStationNotInList_ThenReturnNull()
	{
		//GIVEN
	
		FirestationsModel firestationToDelete = new FirestationsModel();
		
		//WHEN
		FirestationsModel firestationtDelete = firestationsRepository.removeFireStation(firestationToDelete);
		
		//THENE
		assertThat(firestationtDelete).isNull();
	}
	
	@Test
	@DisplayName("Tester la modification du numéro station")
	void updateFireStation_testUpdateStationNumber()
	{
		//GIVEN
		FirestationsModel firestation1 = new FirestationsModel();
		firestation1.setAddress("951 LoneTree Rd");
		firestation1.setStation(1);
		firestationsRepository.addFirestation(firestation1);
		
		FirestationsModel firestation2 = new FirestationsModel();
		firestation2.setAddress("951 LoneTree Rd");
		firestation2.setStation(2);
		//WHEN
		FirestationsModel newStation = firestationsRepository.updateFireStation(firestation2);
		
		//THENE
		assertThat(newStation.station).isEqualTo(2);
	}
	
	@Test
	@DisplayName("Tester l'impossibilité de  modifier le numéro station pour une station qui n'existe pas")
	void updateFireStation_testUpdateStationNumber_WheneStationNotFoundInList_theneReturnNull()
	{
		//GIVEN
		FirestationsModel firestationAdded = new FirestationsModel();
		firestationAdded.setAddress("adresse");
		firestationAdded.setStation(1);
		
		firestationsRepository.addFirestation(firestationAdded);
		
		FirestationsModel firestationInconnue= new FirestationsModel();
		firestationAdded.setAddress("adresse inconnue");
		firestationAdded.setStation(1);
		
		
		//WHEN
		FirestationsModel newStation = firestationsRepository.updateFireStation(firestationInconnue);
	
		//THENE
		assertThat(newStation).isNull();
	}
}
