package com.safetyNet.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetyNet.model.MedicalRecordsModel;
@Repository
public class MedicalRecordRepositoryImp implements MedicalRecordRepository{
	private List<MedicalRecordsModel> MedicalRecordsList = new ArrayList<>();
	
	@Override
	public void addMedicalRecord(MedicalRecordsModel MedicalRecord) {
		this.MedicalRecordsList.add(MedicalRecord);
	}
	
	@Override
	public List<MedicalRecordsModel> findAll() {
		return this.MedicalRecordsList;
	}

	@Override
	public MedicalRecordsModel findByName(String firstName, String lastName) {
	
		
		for (MedicalRecordsModel medicalRecordIteration : this.MedicalRecordsList )
		{
			if(medicalRecordIteration.getFirstName().equalsIgnoreCase(firstName) && medicalRecordIteration.getLastName().equalsIgnoreCase(lastName))
			{
				return medicalRecordIteration;
			}
		}
		return null ; 
	}

	@Override
	public MedicalRecordsModel saveMedicalRecord(MedicalRecordsModel newMedicalRecord) {
		this.MedicalRecordsList.add(newMedicalRecord);
		return newMedicalRecord;
	}

	@Override
	public MedicalRecordsModel updateMedicalRecord(MedicalRecordsModel MedicalRecordModify) {
		MedicalRecordsModel medicalRecordsSearche = this.findByName(MedicalRecordModify.getFirstName(), MedicalRecordModify.getLastName());
		if (medicalRecordsSearche != null)
		{
			medicalRecordsSearche.setBirthdate(MedicalRecordModify.getBirthdate());
			return medicalRecordsSearche;
		}
		return null ;
		
		//ajoute la modification de medications
	}

	@Override
	public MedicalRecordsModel deleteMedicalRecordsModel(MedicalRecordsModel MedicalRecordDelete) 
	{
		
		if(this.MedicalRecordsList.remove(MedicalRecordDelete))
		{
			return MedicalRecordDelete;
		}
		return null ; 
	}

	

}
