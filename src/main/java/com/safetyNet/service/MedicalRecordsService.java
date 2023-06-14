package com.safetyNet.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.safetyNet.DTO.MedicalRecordsDTO;

import com.safetyNet.exceptions.MedicalRecordsIntrouvableException;
import com.safetyNet.exceptions.PersonIntrovableExeption;
import com.safetyNet.model.MedicalRecordsModel;

import com.safetyNet.repository.MedicalRecordRepository;




@Service
public class MedicalRecordsService {
	
	private static final Logger logger = LogManager.getLogger("MedicalRecordsService");
	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	// Recuperer tous les MedicalRecords
	public List<MedicalRecordsDTO> getMedicalRecords() throws MedicalRecordsIntrouvableException {

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
		MedicalRecordsModel  myMedicalRecord = medicalRecordRepository.findByName(firstName, lastName) ; 
		if(myMedicalRecord ==  null )
		{
			logger.error("Le dossier est introuvable");
			throw  new MedicalRecordsIntrouvableException("Le dossier de "+ firstName + "  " + lastName + " est introuvable");
		}
		logger.info("Récuperer le dossier "+firstName+" "+ lastName );
		return this.convertToDTO(medicalRecordRepository.findByName(firstName, lastName));
	}

	// Ajouter un MedicalRecords

	public MedicalRecordsDTO addMedicalRecord(MedicalRecordsModel newMedicalRecord) throws PersonIntrovableExeption {

		if (newMedicalRecord != null) {
			
			logger.info("Ajouter le dossier "+newMedicalRecord.getFirstName()+" "+ newMedicalRecord.getLastName() );
			return this.convertToDTO(medicalRecordRepository.saveMedicalRecord(newMedicalRecord));
		} else
		{
			logger.error("Le dossier ne peut pas etre ajouté");
			throw new PersonIntrovableExeption("Le dossier ne peut pas etre ajouté");
		}
		

	}

	public MedicalRecordsDTO updateMedicalRecord(MedicalRecordsModel updateMedicalRecord) throws MedicalRecordsIntrouvableException {

		
			
			if(medicalRecordRepository.updateMedicalRecord(updateMedicalRecord) ==  null )
				{
					logger.error("Le dossier de "+updateMedicalRecord.getFirstName()+" "+ updateMedicalRecord.getLastName() +" est introuvable" );
					throw new MedicalRecordsIntrouvableException("Le dossier de "+ updateMedicalRecord.firstName + "  " + updateMedicalRecord.lastName + " est introuvable");
				}
			
			logger.info("Modifier le dossier de "+updateMedicalRecord.getFirstName()+" "+ updateMedicalRecord.getLastName() );
			return this.convertToDTO(medicalRecordRepository.updateMedicalRecord(updateMedicalRecord));
	
	}

	// supprimer une dossier
	public MedicalRecordsDTO removeMedicalRecord(String firstName, String lastName) throws MedicalRecordsIntrouvableException {

	
			MedicalRecordsModel medicalRecordToDelet = medicalRecordRepository.findByName(firstName, lastName);
			if(medicalRecordToDelet ==  null )
				{
					logger.error("Le dossier de "+firstName+" "+ lastName +" est introuvable" );
					throw new MedicalRecordsIntrouvableException("Le dossier de "+ firstName + "  " + lastName + " est introuvable");
				}
			logger.info("Supprimer le dossier de "+firstName+" "+ lastName);
			return this.convertToDTO(medicalRecordRepository.deleteMedicalRecordsModel(medicalRecordToDelet)); 
	
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
