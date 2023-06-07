package com.safetyNet.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.safetyNet.DTO.MedicalRecordsDTO;

import com.safetyNet.exceptions.MedicalRecordsIntrouvableException;
import com.safetyNet.model.MedicalRecordsModel;

import com.safetyNet.repository.MedicalRecordRepository;




@Service
public class MedicalRecordsService {
	
	private static final Logger logger = LogManager.getLogger("MedicalRecordsService");
	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	// Recuperer tous les MedicalRecords
	public List<MedicalRecordsDTO> getMedicalRecords() throws MedicalRecordsIntrouvableException {
		if(medicalRecordRepository.findAll().isEmpty()) throw new MedicalRecordsIntrouvableException("La liste des dossiers est vide");
		List<MedicalRecordsDTO> listMedicalRecordsDTO = new ArrayList<>();
		for(MedicalRecordsModel medicalRecord : medicalRecordRepository.findAll() )
		{
			listMedicalRecordsDTO.add(this.convertToDTO(medicalRecord));
		}
		logger.info("Récuperer la liste des dossiers ");
		return listMedicalRecordsDTO;
	}

	// Recuperer un MedicalRecord
	public MedicalRecordsDTO getMedicalRecord(String firstName, String lastName) throws MedicalRecordsIntrouvableException {
		if(medicalRecordRepository.findByfirstName(firstName, lastName) ==  null )throw  new MedicalRecordsIntrouvableException("Le dossier de "+ firstName + "  " + lastName + " est introuvable");
		logger.info("Récuperer le dossier "+firstName+" "+ lastName );
		return this.convertToDTO(medicalRecordRepository.findByfirstName(firstName, lastName));
	}

	// Ajouter un MedicalRecords

	public MedicalRecordsDTO addMedicalRecord(MedicalRecordsModel newMedicalRecord) {

		if (newMedicalRecord != null) {
			
			logger.info("Ajouter le dossier "+newMedicalRecord.getFirstName()+" "+ newMedicalRecord.getLastName() );
			return this.convertToDTO(medicalRecordRepository.saveMedicalRecord(newMedicalRecord));
		} 
		return null;

	}

	public MedicalRecordsDTO updateMedicalRecord(MedicalRecordsModel updateMedicalRecord) throws MedicalRecordsIntrouvableException {

		if (updateMedicalRecord != null) {
			
			if(medicalRecordRepository.updateMedicalRecord(updateMedicalRecord) ==  null )
				{
					logger.error("Le dossier "+updateMedicalRecord.getFirstName()+" "+ updateMedicalRecord.getLastName() +" est introuvable" );
					throw new MedicalRecordsIntrouvableException("Le dossier de "+ updateMedicalRecord.firstName + "  " + updateMedicalRecord.lastName + " est introuvable");
				}
			
			logger.info("Modifier le dossier "+updateMedicalRecord.getFirstName()+" "+ updateMedicalRecord.getLastName() );
			return this.convertToDTO(medicalRecordRepository.updateMedicalRecord(updateMedicalRecord));
		}
		return null;
	}

	// supprimer une dossier
	public MedicalRecordsDTO removeMedicalRecord(String firstName, String lastName) throws MedicalRecordsIntrouvableException {

		if (firstName != null && lastName != null) {
			MedicalRecordsModel medicalRecordToDelet = medicalRecordRepository.findByfirstName(firstName, lastName);
			if(medicalRecordToDelet ==  null )
				{
				logger.error("Le dossier "+firstName+" "+ lastName +" est introuvable" );
					throw new MedicalRecordsIntrouvableException("Le dossier de "+ firstName + "  " + lastName + " est introuvable");
				}
			logger.info("Supprimer le dossier de "+firstName+" "+ lastName);
			return this.convertToDTO(medicalRecordRepository.deleteMedicalRecordsModel(medicalRecordToDelet)); 
		} 
		return null ;
	}
	
	public MedicalRecordsDTO convertToDTO(MedicalRecordsModel medicalRecordsModel)
	{
		MedicalRecordsDTO medicalRecordsDTO = new MedicalRecordsDTO();
		medicalRecordsDTO.setFirstName(medicalRecordsModel.getFirstName());  
		medicalRecordsDTO.setLastName(medicalRecordsModel.getLastName())  ;
		medicalRecordsDTO.setBirthdate(medicalRecordsModel.getBirthdate()) ;
		medicalRecordsDTO.setAllergies(medicalRecordsModel.getAllergies()) ;
		medicalRecordsDTO.setMedications(medicalRecordsModel.getMedications())  ;
		return medicalRecordsDTO ; 
	}
}
