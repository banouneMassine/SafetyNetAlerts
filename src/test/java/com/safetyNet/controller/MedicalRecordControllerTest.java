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

import com.safetyNet.DTO.MedicalRecordsDTO;
import com.safetyNet.exceptions.MedicalRecordsIntrouvableException;
import com.safetyNet.model.MedicalRecordsModel;
import com.safetyNet.service.MedicalRecordsService;

@ExtendWith(MockitoExtension.class)
class MedicalRecordControllerTest {

	@InjectMocks
	MedicalRecordController medicalRecordController;
	
	@Mock
	MedicalRecordsService medicalRecordsService;
	
	@Test
	@DisplayName("Tester la récupération de la liste des dossier")
	void test_getMedicalRecondsALL1()  {
		//GIVEN
		List<MedicalRecordsDTO> listDossiers = new ArrayList<>();
		listDossiers.add(new MedicalRecordsDTO());
		
		when(medicalRecordsService.getMedicalRecords()).thenReturn(listDossiers);
		//WHEN
		ResponseEntity<List<MedicalRecordsDTO>> result = medicalRecordController.getMedicalRecordsAll();	
		//THEN
		assertEquals(listDossiers, result.getBody());
		verify(medicalRecordsService, times(1)).getMedicalRecords();
		
	}
	@Test
	@DisplayName("Tester la récupération d'un dossier d'une personne a partir de son non/prenom")
	void getMedicalRecond_wheneEntringFirstNameAndLastName_ThenRuturngetMedicalRecond() throws Exception {
		//GIVEN
		MedicalRecordsDTO dossier = new MedicalRecordsDTO();
		dossier.setFirstName("wayne");
		dossier.setLastName("ROONEY");
		
		when(medicalRecordsService.getMedicalRecord("wayne","ROONEY")).thenReturn(dossier);
		//WHEN
		ResponseEntity<MedicalRecordsDTO> result = medicalRecordController.getMedicalRecond("wayne","ROONEY");	
		//THEN
		assertEquals(dossier, result.getBody());
		verify(medicalRecordsService, times(1)).getMedicalRecord("wayne","ROONEY");
		
	}
	
	@Test
	@DisplayName("Tester la récupération d'un dossier d'une personne introuvable ")
	void getMedicalRecond_wheneEntringAnNotFoundgetMedicalRecond_ThenRuturnException() throws Exception {
		//GIVEN		
		when(medicalRecordsService.getMedicalRecord("wayne","ROONEY")).thenThrow(new MedicalRecordsIntrouvableException("Le dossier de wayne  ROONEY est introuvable"));
		//WHEN
		
		MedicalRecordsIntrouvableException thrown =assertThrows(MedicalRecordsIntrouvableException.class, () -> medicalRecordController.getMedicalRecond("wayne","ROONEY"));

		//THEN
		assertEquals(thrown.getMessage(),"Le dossier de wayne  ROONEY est introuvable" );
		verify(medicalRecordsService, times(1)).getMedicalRecord("wayne","ROONEY");
		
	}
	
	@Test
	@DisplayName("Tester l'ajout d'une dossier")
	void postPerson_wheneEntringPerson_ThenRuturnPerson() throws Exception {
		//GIVEN
		MedicalRecordsModel dossier = new MedicalRecordsModel();
		dossier.setFirstName("wayne");
		dossier.setLastName("ROONEY");
		
		MedicalRecordsDTO dossierDTO = new MedicalRecordsDTO();
		dossierDTO.setFirstName("wayne");
		dossierDTO.setLastName("ROONEY");
		
		when(medicalRecordsService.addMedicalRecord(dossier)).thenReturn(dossierDTO);
		//WHEN
		ResponseEntity<MedicalRecordsDTO> result = medicalRecordController.postMedicalRecaord(dossier);	
		//THEN
		assertEquals(dossierDTO, result.getBody());
		verify(medicalRecordsService, times(1)).addMedicalRecord(dossier);
		
	}
	
	@Test
	@DisplayName("Tester l'ajout d'un dossier d'une personne null ")
	void getPerson_wheneAddingAnNotFoundPerson_ThenRuturnException() throws Exception {
		//GIVEN		
		when(medicalRecordsService.addMedicalRecord(null)).thenThrow(new MedicalRecordsIntrouvableException("Le dossier ne peut pas etre ajouté"));
		//WHEN
		
		MedicalRecordsIntrouvableException thrown =assertThrows(MedicalRecordsIntrouvableException.class, () -> medicalRecordController.postMedicalRecaord(null));

		//THEN
		assertEquals(thrown.getMessage(),"Le dossier ne peut pas etre ajouté" );
		verify(medicalRecordsService, times(1)).addMedicalRecord(null);
	}
	
	@Test
	@DisplayName("Tester la supprission d'un dossier a partir de son non/prenom")
	void removePerson_wheneEntringFirstNameAndLastName_ThenRuturnPerson() throws Exception {
		//GIVEN
		MedicalRecordsDTO dossierDTO = new MedicalRecordsDTO();
		dossierDTO.setFirstName("wayne");
		dossierDTO.setLastName("ROONEY");
		
		when(medicalRecordsService.removeMedicalRecord("wayne","ROONEY")).thenReturn(dossierDTO);
		//WHEN
		ResponseEntity<MedicalRecordsDTO> result = medicalRecordController.deleteMedicalRecaord("wayne","ROONEY");	
		//THEN
		assertEquals(dossierDTO, result.getBody());
		verify(medicalRecordsService, times(1)).removeMedicalRecord("wayne","ROONEY");
		
	}
	
	@Test
	@DisplayName("Tester la supprission d'un dossier d'une personne introuvable ")
	void deletePerson_wheneEntringAnNotFoundPerson_ThenRuturnException() throws Exception {
		//GIVEN		
		when(medicalRecordsService.removeMedicalRecord("wayne","ROONEY")).thenThrow(new MedicalRecordsIntrouvableException("Le dossier de wayne ROONEY est introuvable"));
		
		//WHEN
		MedicalRecordsIntrouvableException thrown =assertThrows(MedicalRecordsIntrouvableException.class, () -> medicalRecordController.deleteMedicalRecaord("wayne","ROONEY"));

		//THEN
		assertEquals(thrown.getMessage(),"Le dossier de wayne ROONEY est introuvable" );
		verify(medicalRecordsService, times(1)).removeMedicalRecord("wayne","ROONEY");
		
	}
	
	@Test
	@DisplayName("Tester la modif d'un dossier")
	void putMedicalRecaord_wheneEntringMedicalRecord_ThenRuturnMedicalRecord() throws Exception {
		//GIVEN
		MedicalRecordsModel dossier = new MedicalRecordsModel();
		dossier.setFirstName("wayne");
		dossier.setLastName("ROONEY");
		
		MedicalRecordsDTO medicalRecordsDTO = new MedicalRecordsDTO();
		medicalRecordsDTO.setFirstName("wayne");
		medicalRecordsDTO.setLastName("ROONEY");
		
		when(medicalRecordsService.updateMedicalRecord(dossier)).thenReturn(medicalRecordsDTO);
		
		//WHEN
		ResponseEntity<MedicalRecordsDTO> result = medicalRecordController.putMedicalRecaord(dossier);	
		
		//THEN
		assertEquals(medicalRecordsDTO, result.getBody());
		verify(medicalRecordsService, times(1)).updateMedicalRecord(dossier);
		
	}
	
	@Test
	@DisplayName("Tester la modif d'un dossier null ")
	void putPerson_wheneAddingAnNotFoundPerson_ThenRuturnException() throws Exception {
		//GIVEN		
		when(medicalRecordsService.updateMedicalRecord(null)).thenThrow(new MedicalRecordsIntrouvableException("Le dossier de wayne  RONNEY est introuvable"));
		
		//WHEN
		MedicalRecordsIntrouvableException thrown =assertThrows(MedicalRecordsIntrouvableException.class, () -> medicalRecordController.putMedicalRecaord(null));

		//THEN
		assertEquals(thrown.getMessage(),"Le dossier de wayne  RONNEY est introuvable" );
		verify(medicalRecordsService, times(1)).updateMedicalRecord(null);
	}
}
