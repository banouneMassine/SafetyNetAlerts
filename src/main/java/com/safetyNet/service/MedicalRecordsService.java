package com.safetyNet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.safetyNet.exceptions.MedicalRecordsIntrouvableException;
import com.safetyNet.model.MedicalRecordsModel;

import com.safetyNet.repository.MedicalRecordRepository;

import lombok.Data;

@Data
@Service
public class MedicalRecordsService {
	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	// Recuperer tous les MedicalRecords
	public List<MedicalRecordsModel> getMedicalRecords() throws MedicalRecordsIntrouvableException {
		if(medicalRecordRepository.findAll().isEmpty()) throw new MedicalRecordsIntrouvableException("La liste des dossiers est vide");
		return medicalRecordRepository.findAll();
	}

	// Recuperer un MedicalRecord
	public MedicalRecordsModel getMedicalRecord(String firstName, String lastName) throws MedicalRecordsIntrouvableException {
		if(medicalRecordRepository.findByfirstName(firstName, lastName) ==  null )throw  new MedicalRecordsIntrouvableException("Le dossier de "+ firstName + "  " + lastName + " est introuvable");
		return medicalRecordRepository.findByfirstName(firstName, lastName);
	}

	// Ajouter un MedicalRecords

	public MedicalRecordsModel addMedicalRecord(MedicalRecordsModel newMedicalRecord) {

		if (newMedicalRecord != null) {
			return medicalRecordRepository.saveMedicalRecord(newMedicalRecord);
		} 
		return null;

	}

	public MedicalRecordsModel updateMedicalRecord(MedicalRecordsModel updateMedicalRecord) throws MedicalRecordsIntrouvableException {

		if (updateMedicalRecord != null) {
			
			if(medicalRecordRepository.updateMedicalRecord(updateMedicalRecord) ==  null )throw new MedicalRecordsIntrouvableException("Le dossier de "+ updateMedicalRecord.firstName + "  " + updateMedicalRecord.lastName + " est introuvable");
			return medicalRecordRepository.updateMedicalRecord(updateMedicalRecord);
		}
		return null;
	}

	// supprimer une dossier
	public MedicalRecordsModel removeMedicalRecord(String firstName, String lastName) throws MedicalRecordsIntrouvableException {

		if (firstName != null && lastName != null) {
			MedicalRecordsModel medicalRecordToDelet = medicalRecordRepository.findByfirstName(firstName, lastName);
			if(medicalRecordToDelet ==  null )throw new MedicalRecordsIntrouvableException("Le dossier de "+ firstName + "  " + lastName + " est introuvable");
			return medicalRecordRepository.deleteMedicalRecordsModel(medicalRecordToDelet); 
		} 
		return null ;
	}
}
