package com.safetyNet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.safetyNet.model.MedicalRecordsModel;

import com.safetyNet.repository.MedicalRecordRepository;

import lombok.Data;

@Data
@Service
public class MedicalRecordsService {
	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	// Recuperer tous les MedicalRecords
	public List<MedicalRecordsModel> getMedicalRecords() {
		return medicalRecordRepository.findAll();
	}

	// Recuperer un MedicalRecord
	public MedicalRecordsModel getMedicalRecord(String firstName, String lastName) {
		return medicalRecordRepository.findByfirstName(firstName, lastName);
	}

	// Ajouter un MedicalRecords

	public ResponseEntity<String> addMedicalRecord(MedicalRecordsModel newMedicalRecord) {

		if (newMedicalRecord != null) {
			medicalRecordRepository.saveMedicalRecord(newMedicalRecord);
			return ResponseEntity.ok("le dossier est ajouté avec succès.");
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	public ResponseEntity<String> updateMedicalRecord(MedicalRecordsModel updateMedicalRecord) {

		if (updateMedicalRecord != null) {
			medicalRecordRepository.updateMedicalRecord(updateMedicalRecord);
			return ResponseEntity.ok("le dossier est mis à jour avec succès.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// supprimer une dossier
	public ResponseEntity<String> removeMedicalRecord(String firstName, String lastName) {

		if (firstName != null && lastName != null) {
			MedicalRecordsModel medicalRecordToDelet = medicalRecordRepository.findByfirstName(firstName, lastName);
			medicalRecordRepository.deleteMedicalRecordsModel(medicalRecordToDelet);
			return ResponseEntity.ok("le dossier est supprimée avec succès.");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
