package com.safetyNet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetyNet.DTO.MedicalRecordsDTO;
import com.safetyNet.exceptions.MedicalRecordsIntrouvableException;
import com.safetyNet.model.MedicalRecordsModel;
import com.safetyNet.service.MedicalRecordsService;

@RestController
public class MedicalRecordController {

	@Autowired
	MedicalRecordsService medicalRecordsService;

	// Recuperer tous les MedicalRecords
	@GetMapping("/medicalRecordALL")
	public ResponseEntity<List<MedicalRecordsDTO>> getMedicalRecordsAll() throws MedicalRecordsIntrouvableException {
		return ResponseEntity.status(HttpStatus.OK).body(medicalRecordsService.getMedicalRecords());
	}

	// Recuperer un MedicalRecord
	@GetMapping("/medicalRecord/{firstName}/{lastName}")
	public ResponseEntity<MedicalRecordsDTO> getMedicalRecond(@PathVariable String firstName,@PathVariable String lastName) throws MedicalRecordsIntrouvableException {
		
		return ResponseEntity.status(HttpStatus.OK).body(medicalRecordsService.getMedicalRecord(firstName, lastName));
	
	}

	// Ajouter un MedicalRecord

	@PostMapping(value = "/medicalRecord")
	public ResponseEntity<MedicalRecordsDTO> postMedicalRecaord(@RequestBody MedicalRecordsModel newMedicalRecord) throws MedicalRecordsIntrouvableException {

		return ResponseEntity.status(HttpStatus.OK).body( medicalRecordsService.addMedicalRecord(newMedicalRecord));

	}

	// Modifier un MedicalRecord
	@PutMapping(value = "/medicalRecord")
	public ResponseEntity<MedicalRecordsDTO> putMedicalRecaord(@RequestBody MedicalRecordsModel updateMedicalRecord) throws MedicalRecordsIntrouvableException {

		return ResponseEntity.status(HttpStatus.OK).body(  medicalRecordsService.updateMedicalRecord(updateMedicalRecord));

	}

	// Supprimer un MedicalRecord
	@DeleteMapping(value = "/medicalRecord/{firstName}/{lastName}")
	public ResponseEntity<MedicalRecordsDTO> deleteMedicalRecaord(@PathVariable String firstName, @PathVariable String lastName) throws MedicalRecordsIntrouvableException {

		return ResponseEntity.status(HttpStatus.OK).body(medicalRecordsService.removeMedicalRecord(firstName, lastName));

	}

}
