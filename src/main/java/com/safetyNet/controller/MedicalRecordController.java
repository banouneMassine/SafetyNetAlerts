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

import com.safetyNet.model.MedicalRecordsModel;
import com.safetyNet.service.MedicalRecordsService;

@RestController
public class MedicalRecordController {

	@Autowired
	MedicalRecordsService  medicalRecordsService;
	
	//Recuperer tous les MedicalRecords
	@GetMapping("/medicalRecordALL")
	public ResponseEntity<List<MedicalRecordsModel>> getMedicalRecordsAll()
	{
		return ResponseEntity.status(HttpStatus.OK).body(medicalRecordsService.getMedicalRecords());
	}
	
	//Recuperer un  MedicalRecord
	@GetMapping("/medicalRecord/{firstName}/{lastName}")
	public ResponseEntity<MedicalRecordsModel> getMedicalRecond(@PathVariable String firstName ,@PathVariable String lastName)
	{
		return ResponseEntity.status(HttpStatus.OK).body(medicalRecordsService.getMedicalRecord(firstName , lastName));
	}
	
	// Ajouter un MedicalRecord
	
	@PostMapping(value = "/medicalRecord")
	public ResponseEntity<String> postMedicalRecaord(@RequestBody MedicalRecordsModel newMedicalRecord)
	{
		if(newMedicalRecord != null)
		{
			medicalRecordsService.addMedicalRecord(newMedicalRecord);
	     	return ResponseEntity.ok("le dossier est ajouté avec succès.");
		}else
		{
			return  ResponseEntity.notFound().build();
		}
		
		
	}
	
	// Modifier un MedicalRecord
	@PutMapping(value = "/medicalRecord")
	public ResponseEntity<String> putMedicalRecaord(@RequestBody MedicalRecordsModel updateMedicalRecord)
	{
		if(updateMedicalRecord != null)
		{
			medicalRecordsService.updateMedicalRecord(updateMedicalRecord);
			return ResponseEntity.ok("le dossier est mis à jour avec succès.");
		}else
		{
			return  ResponseEntity.notFound().build();
		}
	}
	
	// Supprimer un MedicalRecord
	@DeleteMapping(value = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<String> deleteMedicalRecaord(@PathVariable  String firstName , @PathVariable  String lastName) 
	{
    	if(firstName != null && lastName != null)
    	{
    		medicalRecordsService.removeMedicalRecord(firstName,lastName );
     	   return ResponseEntity.ok("le dossier est supprimée avec succès.");
    	}
	    else 
	    {
	        return ResponseEntity.notFound().build();
	    }
    }
	
}
