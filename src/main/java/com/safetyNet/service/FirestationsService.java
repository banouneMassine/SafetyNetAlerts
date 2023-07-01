package com.safetyNet.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetyNet.DTO.FireStationsDTO;
import com.safetyNet.exceptions.FireStationIntrouvableException;
import com.safetyNet.model.FirestationsModel;
import com.safetyNet.repository.FirestationsRepository;

/**
 * Cette class traite les action du CRUD pour l'entite FireStationModel.
 */

@Service
public class FirestationsService {

	private static final Logger logger = LogManager.getLogger("FirestationsService");
	
	@Autowired
	FirestationsRepository firestationsRepository;
	
	/**
     * Cette méthode ajoute une station a la liste des stations.
     * @param newFirestations : la station a ajoutee
     * @return FireStationsDTO =  la station DTO qui a ete ajoutee.
     */
	public FireStationsDTO addFireStation( FirestationsModel newFirestations) throws FireStationIntrouvableException
	{
		if(newFirestations ==  null)
    	{
			logger.error("Impossible d'ajouter une station vide" );
			throw new FireStationIntrouvableException("Impossible d'ajouter une station vide");
			
    	}
		logger.info("Ajouter la station  "+ newFirestations.getAddress() );
		return this.convertToDTO(firestationsRepository.saveFireStation(newFirestations));
	   
	}
	
	/**
     * Cette méthode modifiee une station .
     * @param updateFireStations : la station a modifier avec de nouvelles donnees 
     * @return FireStationsDTO = la station DTO qui a ete modifiee.
     */
	public FireStationsDTO updateFireStations(FirestationsModel updateFireStations) throws FireStationIntrouvableException
	{
			FirestationsModel Firestation =firestationsRepository.updateFireStation(updateFireStations);
			if(Firestation == null) 
			{
				logger.error("La station  "+updateFireStations.getAddress() +" est introuvable" );
				throw new FireStationIntrouvableException("La station "+ updateFireStations.address + " est introuvable");
			}
			logger.info("Modifier la station  "+ updateFireStations.getAddress() );
			return this.convertToDTO(Firestation);

	}

	/**
     * Cette méthode supprime une station de la liste des stations.
     * @param adresse : adresse de la personne 
     * @return FireStationsDTO = la station  qui a ete supprimee.
     */
	public FireStationsDTO deleteFireStations( String adresse) throws FireStationIntrouvableException
	{
		
			FirestationsModel FireStationToDelete =firestationsRepository.findByAdresse(adresse);
			if(FireStationToDelete == null ) 
				{
					logger.error("La station  "+ adresse +" est introuvable" );
					throw new FireStationIntrouvableException("La station "+ adresse + " est introuvable");
				}
			logger.info("Supprimer la station  "+ FireStationToDelete.getAddress() );
			return	this.convertToDTO(firestationsRepository.removeFireStation(FireStationToDelete));
    	
	}
	
	/**
     * Cette méthode recupere la liste des stations.
     * @param /.
     * @return listFireStationsDTO = la liste des stations.
     */
	public List<FireStationsDTO> getFireStations() 
	{
		List<FireStationsDTO> listFireStationsDTO = new ArrayList<>();
		for(FirestationsModel firestation :  firestationsRepository.findAll())
		{
			listFireStationsDTO.add(this.convertToDTO(firestation));
		}
		logger.info("Récuperer la liste des stations");
		return listFireStationsDTO;
		
	} 
	
	
	/**
     * Cette méthode recupere une station via son adresse.
     * @param adresse : adresse de la station
     * @return firestationDTO = la station recherchee.
     */
	public FireStationsDTO getFireStation(String adresse) throws FireStationIntrouvableException
	{
		FirestationsModel myFirestationsModel = firestationsRepository.findByAdresse(adresse);
		if( myFirestationsModel == null)
			{
				logger.error("La station "+ adresse + "  est introuvable");
				throw new FireStationIntrouvableException("La station "+ adresse + "  est introuvable");
			}
		logger.info("Récuperer la liste " + adresse);
		return this.convertToDTO(myFirestationsModel);
	}
	
	/**
     * Cette méthode convertie une instance FirestationsModel en une instance FireStationsDTO.
     * @param firestationsModel 
     * @return FireStationsDTO 
     */
	public FireStationsDTO convertToDTO(FirestationsModel firestationsModel)
	{
		FireStationsDTO fireStationsDTO = new FireStationsDTO();
		fireStationsDTO.setAddress(firestationsModel.getAddress());
		fireStationsDTO.setStation(firestationsModel.getStation());
		
		return fireStationsDTO ; 
	}
	
}
