package com.safetyNet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNet.model.MedicalRecordsModel;
import com.safetyNet.model.PersonsModel;
import com.safetyNet.repository.MedicalRecordRepository;
import com.safetyNet.repository.MedicalRecordRepositoryImp;

import lombok.Data;

@Data
@Service
public class MedicalRecordsService {
	@Autowired 
	MedicalRecordRepository medicalRecordRepository;
	
	
	//Recuperer tous les MedicalRecords
	public List<MedicalRecordsModel> getMedicalRecords() {
		return medicalRecordRepository.findAll();
	}
	//Recuperer un MedicalRecord
	public MedicalRecordsModel getMedicalRecord( String firstName , String lastName)
	{
		return medicalRecordRepository.findByfirstName(firstName, lastName);
	}
	
	//Ajouter un MedicalRecords
	
	public void addMedicalRecord( MedicalRecordsModel newMedicalRecord)
	{
		medicalRecordRepository.saveMedicalRecord(newMedicalRecord);
	}
	
	public void updateMedicalRecord( MedicalRecordsModel updateMedicalRecord)
	{
		medicalRecordRepository.updateMedicalRecord(updateMedicalRecord);
	}
	
	// supprimer une dossier 
		public void removeMedicalRecord(String firstName, String lastName) {

			MedicalRecordsModel medicalRecordToDelet = medicalRecordRepository.findByfirstName(firstName, lastName);
			medicalRecordRepository.deleteMedicalRecordsModel(medicalRecordToDelet);
		}
}
