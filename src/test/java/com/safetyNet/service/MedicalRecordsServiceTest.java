package com.safetyNet.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import com.safetyNet.DTO.MedicalRecordsDTO;
import com.safetyNet.exceptions.MedicalRecordsIntrouvableException;

import com.safetyNet.model.MedicalRecordsModel;

import com.safetyNet.repository.MedicalRecordRepository;

@ExtendWith(MockitoExtension.class)
class MedicalRecordsServiceTest {

	@Mock
	MedicalRecordRepository medicalRecordRepository;
	
	@InjectMocks
	MedicalRecordsService medicalRecordsService ;
	
	@Test
	@DisplayName("Tester la récupération de la liste des dossiers ")
	void getMedicalRecords_RuternListOfAllMedicalRecordsDTO()
	{ 
		//GIVEN
		List<MedicalRecordsModel> listDesDossier =  new ArrayList<>();

		listDesDossier.add(new MedicalRecordsModel());
		listDesDossier.add(new MedicalRecordsModel());
		
		when(medicalRecordRepository.findAll()).thenReturn(listDesDossier);
		
		
		//WHEN
		List<MedicalRecordsDTO> listeDesDossierDTO = medicalRecordsService.getMedicalRecords();
	
		//THEN
		verify(medicalRecordRepository, times(1)).findAll();
		assertThat(listeDesDossierDTO.size()).isEqualTo(2);
	}

		
	@Test
	@DisplayName("Tester la récupération de la liste des dossiers ")
	void getMedicalRecord_whenEnteringTheFirstAndLastName_thenReturnTheMedicalRecordDTO()
	{
		//GIVEN
		MedicalRecordsModel medicalRecord =new MedicalRecordsModel();
		medicalRecord.setFirstName("wayen");
		medicalRecord.setLastName("ROONEY");
		
		when(medicalRecordRepository.findByName("wayen", "ROONEY")).thenReturn(medicalRecord);
		
		//WHEN
		MedicalRecordsDTO medicalRecordsDTO = assertDoesNotThrow(() ->  medicalRecordsService.getMedicalRecord("wayen", "ROONEY"));
		//THEN
		verify(medicalRecordRepository, times(1)).findByName(any(String.class),any(String.class));
		assertThat(medicalRecordsDTO.getFirstName()).isEqualTo("wayen");
		assertThat(medicalRecordsDTO.getLastName()).isEqualTo("ROONEY");
	}
	
	@Test
	@DisplayName("Tester que quand la personne recherchée n'est pas trouvée alors une exception est levée")
	void getMedicalRecord_whenEnteringAnUnidentifiedName_thenReturnException()
	{
		//GIVEN
		String firstName = "wayen";
		String lastName = "ROONEY";
		String exceptionMessage = "Le dossier de "+ firstName + "  " + lastName + " est introuvable";
		when(medicalRecordRepository.findByName(any(String.class), any(String.class))).thenReturn(null);
		
		//WHEN
		MedicalRecordsIntrouvableException thrown = assertThrows(MedicalRecordsIntrouvableException.class , () ->  medicalRecordsService.getMedicalRecord(firstName,lastName));
		//THEN
		verify(medicalRecordRepository, times(1)).findByName(any(String.class),any(String.class));
		assertThat(thrown.getMessage()).isEqualTo(exceptionMessage);
	
	}
	
	@Test
	@DisplayName("Tester l'ajout d'un MedicalRecords")
	void addMedicalRecord_whenEnteringAnMidicalRecord_thenAddItAndReturnIt()
	{
		//GIVEN
		MedicalRecordsModel medicalRecord = new MedicalRecordsModel();
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Jonse");
		
		when(medicalRecordRepository.saveMedicalRecord(medicalRecord)).thenReturn(medicalRecord);
		//WHEN
		MedicalRecordsDTO medicalRecordDTO = assertDoesNotThrow(() ->  medicalRecordsService.addMedicalRecord(medicalRecord));
		
		//THENE 
		verify(medicalRecordRepository , times(1)).saveMedicalRecord(any(MedicalRecordsModel.class));
		assertThat(medicalRecordDTO.getLastName()).isEqualTo(medicalRecord.getLastName());
		assertThat(medicalRecordDTO.getFirstName()).isEqualTo(medicalRecord.getFirstName());
	}
	
	@Test
	@DisplayName("Tester l'mpossibilité d'ajouter un MedicalRecords null")
	void addMedicalRecord_whenEnteringAnNullMidicalRecord_thenReturnException()
	{
	
		//WHEN
		MedicalRecordsIntrouvableException thrown = assertThrows(MedicalRecordsIntrouvableException.class, () ->  medicalRecordsService.addMedicalRecord(null));
		
		//THENE 
		assertThat(thrown.getMessage()).isEqualTo("Le dossier ne peut pas etre ajouté");
	}
	

	@Test
	@DisplayName("Tester la modification d'un MedicalRecords ")
	void updateMedicalRecord_whenEnteringAnMidicalRecord_thenUpdateItAndReturnIt()
	{
		//GIVEN
		MedicalRecordsModel medicalRecord = new MedicalRecordsModel();
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Jonse");
		
		when(medicalRecordRepository.updateMedicalRecord(medicalRecord)).thenReturn(medicalRecord);
		//WHEN
		MedicalRecordsDTO medicalRecordsDTO = assertDoesNotThrow(()-> medicalRecordsService.updateMedicalRecord(medicalRecord));
		
		//THENE 
		verify(medicalRecordRepository, times(1)).updateMedicalRecord(medicalRecord);
		assertThat(medicalRecordsDTO.getFirstName()).isEqualTo(medicalRecord.getFirstName());
		assertThat(medicalRecordsDTO.getLastName()).isEqualTo(medicalRecord.getLastName());
	}
	
	@Test
	@DisplayName("Tester l'impossibilité de modifier un midicalRecord introuvable ")
	void updateMedicalRecord_whenEnteringAnNotFoundMidicalRecord_thenReturnException()
	{
		//GIVEN
		MedicalRecordsModel medicalRecord = new MedicalRecordsModel();
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Jonse");
		
		when(medicalRecordRepository.updateMedicalRecord(medicalRecord)).thenReturn(null);
		//WHEN
		MedicalRecordsIntrouvableException thrown = assertThrows(MedicalRecordsIntrouvableException.class, () ->  medicalRecordsService.updateMedicalRecord(medicalRecord));
		//THENE 
		verify(medicalRecordRepository, times(1)).updateMedicalRecord(medicalRecord);
		assertThat(thrown.getMessage()).isEqualTo("Le dossier de "+ medicalRecord.firstName + "  " + medicalRecord.lastName + " est introuvable");
	}
	
	@Test
	@DisplayName("Tester la supprission d'un midicalRecord ")
	void removeMedicalRecord_whenEnteringMidicalRecord_thenDeletItAndReturnIt()
	{
		//GIVEN
		String firstName = "Jo";
		String lastName = "Jonse";
		
		MedicalRecordsModel medicalRecord = new MedicalRecordsModel();
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Jonse");
		
		when(medicalRecordRepository.findByName(firstName,lastName)).thenReturn(medicalRecord);
		when(medicalRecordRepository.deleteMedicalRecordsModel(medicalRecord)).thenReturn(medicalRecord);
		
		//WHEN
		MedicalRecordsDTO medicalRecordsDTO = assertDoesNotThrow(()-> medicalRecordsService.removeMedicalRecord(firstName,lastName));
		//THENE 
		verify(medicalRecordRepository, times(1)).findByName(firstName,lastName);
		verify(medicalRecordRepository, times(1)).deleteMedicalRecordsModel(medicalRecord);
		assertThat(medicalRecordsDTO.getFirstName()).isEqualTo(medicalRecord.getFirstName());
		assertThat(medicalRecordsDTO.getLastName()).isEqualTo(medicalRecord.getLastName());
	}
	
	@Test
	@DisplayName("Tester l'impossibilité de supprimer un midicalRecord introuvable ")
	void removeMedicalRecord_whenEnteringAnNotFoundMidicalRecord_thenReturnException()
	{
		//GIVEN
		MedicalRecordsModel medicalRecord = new MedicalRecordsModel();
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Jonse");
		String firstName = "Jo";
		String lastName = "Jonse";
		
		when(medicalRecordRepository.findByName(firstName,lastName)).thenReturn(null);
		//WHEN
		MedicalRecordsIntrouvableException thrown = assertThrows(MedicalRecordsIntrouvableException.class, () ->  medicalRecordsService.removeMedicalRecord(firstName,lastName));
		//THENE 
		verify(medicalRecordRepository, times(1)).findByName(firstName,lastName);
		assertThat(thrown.getMessage()).isEqualTo("Le dossier de "+ firstName + "  " + lastName + " est introuvable");
	}
	
	@Test
	@DisplayName("Tester la conversion d'un MedicalRecordsModel a un medicalRecordsDTO")
	void convertToDTO_whenEnteringAnMedicalRecordsModel_thenReturnMedicalRecordsDTO()
	{
		//GIVEN
		MedicalRecordsModel medicalRecord = new MedicalRecordsModel();
		medicalRecord.setFirstName("Jo");
		medicalRecord.setLastName("Jonse");
		medicalRecord.setBirthdate("19/06/2023") ;
		
		//WHEN
		MedicalRecordsDTO	medicalRecordsDTO = medicalRecordsService.convertToDTO(medicalRecord);
		
		//THENE 
		assertThat(medicalRecordsDTO.getFirstName()).isEqualTo(medicalRecord.getFirstName());
		assertThat(medicalRecordsDTO.getLastName()).isEqualTo(medicalRecord.getLastName());
		assertThat(medicalRecordsDTO.getBirthdate()).isEqualTo(medicalRecord.getBirthdate());
	}
}
