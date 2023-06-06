package com.safetyNet.DTO;

import java.util.List;

public class FloodDTO {
	
	public String adresse;
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	

	public List<FireDTO> fireDTO ;

	public List<FireDTO> getFireDTO() {
		return fireDTO;
	}

	public void setFireDTO(List<FireDTO> fireDTO) {
		this.fireDTO = fireDTO;
	}

}
