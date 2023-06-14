package com.safetyNet.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetyNet.model.FirestationsModel;

@SpringBootTest
class FirestationsRepositoryImpTest {

	@Autowired
	FirestationsRepository firestationsRepository;
	
	@Test
	@DisplayName("Testet la recupération des stations")
	void findAll_testTheRecoveryOfAllStations()
	{
		//GIVEN
		
		//WHEN
		List<FirestationsModel> listDesStations =  firestationsRepository.findAll();
		
		//THENE
		assertThat(listDesStations).isNotEmpty();
	}
	
	@Test
	@DisplayName("Tester la recupération d'une station grace a une adresse")
	void findByAdresse_testThatWhenEnterAdress_thenReturnTheStation()
	{
		//GIVEN
		String adresse = "29 15th St";
		
		FirestationsModel firestationExpeted = new FirestationsModel();
		firestationExpeted.setAddress("29 15th St");
		firestationExpeted.setStation(2);
		
		//WHEN
		FirestationsModel acualFirestation = firestationsRepository.findByAdresse(adresse);
		//THENE
		assertThat(acualFirestation).usingRecursiveComparison().isEqualTo(firestationExpeted);
		
	}
	
	@Test
	@DisplayName("Tester la recupération la liste des stations grace au numéro de la station")
	void findByStationNumber_testThatWhenEnterStationNumber_thenReturnTheListStations()
	{
		//GIVEN
		int station = 4;
		String adresseExpected = "489 Manchester St";
		
		//WHEN
		List<FirestationsModel> acualListFirestation = firestationsRepository.findByStationNumber(station);
		//THENE
		assertThat(acualListFirestation).isNotEmpty();
		assertThat(acualListFirestation.get(0).getAddress()).isEqualTo(adresseExpected);
	}
	
	@Test
	@DisplayName("Tester l'ajout d'une nouvelle station")
	void saveFireStation_whenAddNewStation_thenReturnTheStation()
	{
		//GIVEN
		FirestationsModel firestationadded = new FirestationsModel();
		firestationadded.setAddress("8 rue jacque");
		firestationadded.setStation(5);
		
		//WHEN
		FirestationsModel newFirestation = firestationsRepository.saveFireStation(firestationadded);
		//THENE
		assertThat(newFirestation).usingRecursiveComparison().isEqualTo(firestationadded);
		assertThat(firestationsRepository.findAll()).contains(firestationadded);
	}
	
	@Test
	@DisplayName("Tester l'impossibilté  d'ajouter une station dans la liste des stations")
	@Disabled
	void saveFireStation_whenAddBadStation_thenReturnNull()
	{
		//GIVEN
		FirestationsModel firestationadded =null;
				
		//WHEN
		FirestationsModel newFirestation = firestationsRepository.saveFireStation(firestationadded);
		//THENE
		assertThat(newFirestation).isNull();
	}
	
	@Test
	@DisplayName("Tester la supprission d'une station")
	void removeFireStation_testWhenDeletStation_theneReturnStation()
	{
		//GIVEN
		/*FirestationsModel firestationToDelete = new FirestationsModel();
		firestationToDelete.setAddress("644 Gershwin Cir");
		firestationToDelete.setStation(1);*/
		FirestationsModel	firestationToDelete =firestationsRepository.findByAdresse("644 Gershwin Cir");
		//WHEN
		FirestationsModel firestationtDelete = firestationsRepository.removeFireStation(firestationToDelete);
		
		//THENE
		assertThat(firestationtDelete).isEqualTo(firestationToDelete);
		assertThat(firestationsRepository.findAll()).doesNotContain(firestationToDelete);
	}
	
	@Test
	@DisplayName("Tester la supprission d'une station qui n'existe pas dans la liste des stations")
	void removeFireStation_testWheneStationNotInList_ThenReturnNull()
	{
		//GIVEN
		FirestationsModel firestationToDelete = new FirestationsModel();
		firestationToDelete.setAddress("47 route de dampierre");
		firestationToDelete.setStation(6);

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
		FirestationsModel firestationToUpdate = new FirestationsModel();
		firestationToUpdate.setAddress("951 LoneTree Rd");
		firestationToUpdate.setStation(6);
		
		//WHEN
		FirestationsModel newStation = firestationsRepository.updateFireStation(firestationToUpdate);
		
		//THENE
		assertThat(newStation.station).isEqualTo(6);
	}
	
	@Test
	@DisplayName("Tester l'impossibilité de  modifier le numéro station pour une station qui n'existe pas")
	void updateFireStation_testUpdateStationNumber_WheneStationNotFoundInList_theneReturnNull()
	{
		//GIVEN
		FirestationsModel firestationToUpdate = new FirestationsModel();
		firestationToUpdate.setAddress("adresse inconnue");
		firestationToUpdate.setStation(6);
		
		//WHEN
		FirestationsModel newStation = firestationsRepository.updateFireStation(firestationToUpdate);
		
		//THENE
		assertThat(newStation).isNull();
	}
}
