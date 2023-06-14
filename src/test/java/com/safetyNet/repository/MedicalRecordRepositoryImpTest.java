package com.safetyNet.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.safetyNet.model.MedicalRecordsModel;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedicalRecordRepositoryImpTest {

	@Autowired
	MedicalRecordRepository medicalRecordRepositoryImp ;
	
	

	@Test
	@DisplayName("Tester que la liste des dossier est bien récupérée")
	void findAll_testTheRecoveryOfAllMedicalRecord()
	{
		//GIVEN
		
		//WHEN
		List<MedicalRecordsModel> listDesDossiers = medicalRecordRepositoryImp.findAll();
		//THENE
		assertThat(listDesDossiers).isNotEmpty();
	}
	
	
	@Test
	@DisplayName("Tester que quand je saisi un nom et un prenom alors je récupère le dossier ")
	void findByName_testThatWhenEnterName_thenRetrieveTheMedicalRecord()
	{
		//GIVEN
		String firstName = "Zach";
		String lastName = "Zemicks";
				
		MedicalRecordsModel medicalRecordExpeted = new MedicalRecordsModel();
		medicalRecordExpeted.setFirstName("Zach");
		medicalRecordExpeted.setLastName("Zemicks");
		medicalRecordExpeted.setBirthdate("03/06/2017");
		medicalRecordExpeted.setAllergies(new ArrayList<>());
		medicalRecordExpeted.setMedications(new ArrayList<>());
		
        		
		//WHEN
		MedicalRecordsModel actualMedicalRecord = medicalRecordRepositoryImp.findByName(firstName , lastName);
		
		//THENE
		assertThat(actualMedicalRecord).usingRecursiveComparison().isEqualTo(medicalRecordExpeted);
		
	}
	
	@DisplayName("Tester que quand je saisi un nom/prenom qui n'existe pas alors je récupère une valeur null")
	@ParameterizedTest(name = "{0} + {1} doit retourner null")
	@CsvSource({ "a,b", "John,b", "a,Boyd" })
	void findByName_testThatWhenNameThatDoesntExist_thenRetrieveNull(String arg1 , String arg2)
	{
		//GIVEN
		String firstName = arg1;
		String lastName = arg2;
		
		//WHEN
		MedicalRecordsModel actualMedicalRecord = medicalRecordRepositoryImp.findByName(firstName , lastName);
		
		//THENE
		assertThat(actualMedicalRecord).isNull();
	}
	
	@Test
	@DisplayName("Tester l'ajout d'un dossier dans la liste des dossierss")
	void saveMedicalRecord_testAddMedicalRecordInListOfMedicalRecords()
	{
		//GIVEN
		MedicalRecordsModel MedicalRecordadded = new MedicalRecordsModel();
		MedicalRecordadded.setFirstName("Lionel");
		MedicalRecordadded.setLastName("Messi");
		MedicalRecordadded.setBirthdate("03/06/1985");
		MedicalRecordadded.setAllergies(new ArrayList<>());
		MedicalRecordadded.setMedications(new ArrayList<>());
		//WHEN
		MedicalRecordsModel newMedicalRecord = medicalRecordRepositoryImp.saveMedicalRecord(MedicalRecordadded);
		//THENE
		
		assertThat(newMedicalRecord).usingRecursiveComparison().isEqualTo(MedicalRecordadded);
		assertThat(medicalRecordRepositoryImp.findAll()).contains(MedicalRecordadded);
	}
	
	@Test
	@DisplayName("Tester l'impossibilté  d'ajouter un dossier dans la liste des dossierss")
	@Disabled
	void saveMedicalRecord_wheneMedicalREcordInBadFormat_TheneReturnNull()
	{
		//GIVEN
			MedicalRecordsModel MedicalRecordadded = null;
		//WHEN
			MedicalRecordsModel newMedicalRecord = medicalRecordRepositoryImp.saveMedicalRecord(MedicalRecordadded);
		//THENE	
		assertThat(newMedicalRecord).isNull();
	}
	
	@Test
	@DisplayName("Tester la modification de la date de naissance qui se trouve dans le dossier d'une personne")
	void updateMedicalRecord_wheneMedicalRecordInList_theneUpdateIt()
	{
		//GIVEN
		
		MedicalRecordsModel medicalRecordToUpdate = new MedicalRecordsModel();
		medicalRecordToUpdate.setFirstName("Roger");
		medicalRecordToUpdate.setLastName("Boyd");
		medicalRecordToUpdate.setBirthdate("14/06/2023");
		medicalRecordToUpdate.setAllergies(new ArrayList<>());
		medicalRecordToUpdate.setMedications(new ArrayList<>());

		//WHEN
		MedicalRecordsModel newMedicalRecord = medicalRecordRepositoryImp.updateMedicalRecord(medicalRecordToUpdate);
		//THENE	
		assertThat(newMedicalRecord.getBirthdate()).isEqualTo("14/06/2023");
		
	}
	
	@Test
	@DisplayName("Test l'impossibilité de changer une date de naissance d'un dossier d'une personne qui n'existe pas ")
	void updateMedicalRecord_wheneMedicalRecordNotFoundInList_theneReturnNull()
	{
		//GIVEN
		
		MedicalRecordsModel medicalRecordExpeted = new MedicalRecordsModel();
		medicalRecordExpeted.setFirstName("jymmy");
		medicalRecordExpeted.setLastName("a");
		medicalRecordExpeted.setBirthdate("14/06/2023");
		medicalRecordExpeted.setAllergies(new ArrayList<>());
		medicalRecordExpeted.setMedications(new ArrayList<>());
 
		//WHEN
		MedicalRecordsModel newMedicalRecord = medicalRecordRepositoryImp.updateMedicalRecord(medicalRecordExpeted);
		//THENE	
		assertThat(newMedicalRecord).isNull();
		
	}
	
	@Test
	@DisplayName("Tester la suppression d'un dossier de la list des dossiers ")
	void deleteMedicalRecordsModel_testWheneMedicalRecordInList_ThenDeletIt()
	{
		//GIVEN
		/*MedicalRecordsModel medicalRecordToDelete = new MedicalRecordsModel();
		medicalRecordToDelete.setFirstName("Jonanathan");
		medicalRecordToDelete.setLastName("Marrack");
		medicalRecordToDelete.setBirthdate("01/03/1989");
		medicalRecordToDelete.setAllergies(new ArrayList<>());
		medicalRecordToDelete.setMedications(new ArrayList<>());*/
		MedicalRecordsModel medicalRecordToDelete =medicalRecordRepositoryImp.findByName("Jonanathan","Marrack");
		//WHEN
		MedicalRecordsModel MedicalRecordDelete = medicalRecordRepositoryImp.deleteMedicalRecordsModel(medicalRecordToDelete);
		//THENE	
		assertThat(MedicalRecordDelete).isEqualTo(medicalRecordToDelete);
		assertThat(medicalRecordRepositoryImp.findAll()).doesNotContain(medicalRecordToDelete) ; //.contains(MedicalRecordadded);
	}
	
	@Test
	@DisplayName("Tester la suppression d'un dossier qui n'existe pas dans la liste des dossier")
	void deleteMedicalRecordsModel_testWheneMedicalRecordNotInList_ThenReturnNull()
	{
		//GIVEN

		MedicalRecordsModel medicalRecordToDelete = new MedicalRecordsModel();
		medicalRecordToDelete.setFirstName("jymmy");
		medicalRecordToDelete.setLastName("a");
		medicalRecordToDelete.setBirthdate("14/06/2023");
		medicalRecordToDelete.setAllergies(new ArrayList<>());
		medicalRecordToDelete.setMedications(new ArrayList<>());
		//WHEN
		MedicalRecordsModel MedicalRecordDelete = medicalRecordRepositoryImp.deleteMedicalRecordsModel(medicalRecordToDelete);
		//THENE	
		assertThat(MedicalRecordDelete).isNull();
	}
}
