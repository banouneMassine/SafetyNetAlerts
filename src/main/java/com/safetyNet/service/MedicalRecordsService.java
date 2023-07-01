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


/**
 * Cette class traite les action du CRUD pour l'entite rMedicalRecordsModel.
 */

@Service
public class MedicalRecordsService { 
	
	private static final Logger logger = LogManager.getLogger("MedicalRecordsService");
	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	
	
	/**
     * Cette méthode recupere la liste des MedicalRecords.
     * @param /.
     * @return listMedicalRecordsDTO = la liste des MedicalRecordsonnes.
     */
	public List<MedicalRecordsDTO> getMedicalRecords()  {

		List<MedicalRecordsDTO> listMedicalRecordsDTO = new ArrayList<>();
		for(MedicalRecordsModel medicalRecord : medicalRecordRepository.findAll() )
		{
			listMedicalRecordsDTO.add(this.convertToDTO(medicalRecord));
		}
		logger.info("Récuperer la liste des dossiers ");
		return listMedicalRecordsDTO;
	}

	// Recuperer un MedicalRecord
	/**
     * Cette méthode recupere un MedicalRecord d'une personne via son nom / prenom.
     * @param firstName : prenom de la personne
     * @param lastName : nom de la personne 
     * @return MedicalRecordsDTO = le dossier rechereche.
     */
	public MedicalRecordsDTO getMedicalRecord(String firstName, String lastName) throws MedicalRecordsIntrouvableException {
		MedicalRecordsModel  myMedicalRecord = medicalRecordRepository.findByName(firstName, lastName) ; 
		if(myMedicalRecord ==  null )
		{
			logger.error("Le dossier est introuvable");
			throw  new MedicalRecordsIntrouvableException("Le dossier de "+ firstName + "  " + lastName + " est introuvable");
		}
		logger.info("Récuperer le dossier "+firstName+" "+ lastName );
		return this.convertToDTO(myMedicalRecord);
	}

	
	/**
     * Cette méthode ajoute un dossier(MedicalRecord) a la liste des MedicalRecords.
     * @param newMedicalRecord : le dossier a ajoute
     * @return PersonsDTO =  le dossier DTO qui a ete ajoutee.
     */
	public MedicalRecordsDTO addMedicalRecord(MedicalRecordsModel newMedicalRecord) throws MedicalRecordsIntrouvableException {

		if (newMedicalRecord != null) {
			
			logger.info("Ajouter le dossier "+newMedicalRecord.getFirstName()+" "+ newMedicalRecord.getLastName() );
			return this.convertToDTO(medicalRecordRepository.saveMedicalRecord(newMedicalRecord));
		} else
		{
			logger.error("Le dossier ne peut pas etre ajouté");
			throw new MedicalRecordsIntrouvableException("Le dossier ne peut pas etre ajouté");
		}
		

	}
	
	/**
     * Cette méthode modifiee une personne .
     * @param newPreson : le dossier a modifier avec de nouvelles donnees 
     * @return MedicalRecordsDTO = le dossier DTO qui a ete modifiee.
     */
	public MedicalRecordsDTO updateMedicalRecord(MedicalRecordsModel updateMedicalRecord) throws MedicalRecordsIntrouvableException {
		MedicalRecordsModel medicalRecord= medicalRecordRepository.updateMedicalRecord(updateMedicalRecord);
			if( medicalRecord ==  null )
				{
					logger.error("Le dossier de "+updateMedicalRecord.getFirstName()+" "+ updateMedicalRecord.getLastName() +" est introuvable" );
					throw new MedicalRecordsIntrouvableException("Le dossier de "+ updateMedicalRecord.firstName + "  " + updateMedicalRecord.lastName + " est introuvable");
				}
			
			logger.info("Modifier le dossier de "+updateMedicalRecord.getFirstName()+" "+ updateMedicalRecord.getLastName() );
			return this.convertToDTO(medicalRecord);
	
	}

	
	/**
     * Cette méthode supprime une MedicalRecord(dossier) de la liste des MedicalRecords.
     * @param firstName : prenom de la personne
     * @param lastName : nom de la personne 
     * @return PersonsDTO = le dossier  qui a ete supprimee.
     */
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
	
	/**
     * Cette méthode convertie une instance MedicalRecordsModel en une instance MedicalRecordsDTO.
     * @param medicalRecordsModel 
     * @return MedicalRecordsDTO 
     */
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
