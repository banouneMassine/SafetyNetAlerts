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
	public MedicalRecordsModel findByfirstName(String firstName, String lastName) {
		
		
		for (MedicalRecordsModel medicalRecordIteration : this.MedicalRecordsList )
		{
			if(medicalRecordIteration.firstName.equalsIgnoreCase(firstName) && medicalRecordIteration.lastName.equalsIgnoreCase(lastName))
			{
				return medicalRecordIteration;
			}
		}
		return null ; 
	}

	@Override
	public void saveMedicalRecord(MedicalRecordsModel newMedicalRecord) {
		this.MedicalRecordsList.add(newMedicalRecord);
	}

	@Override
	public void updateMedicalRecord(MedicalRecordsModel MedicalRecordModify) {
		MedicalRecordsModel medicalRecordsSearche = this.findByfirstName(MedicalRecordModify.firstName, MedicalRecordModify.lastName);
		medicalRecordsSearche.birthdate= MedicalRecordModify.birthdate;
		//medicalRecordsSearche.= MedicalRecordModify.medications;
		
		
		
		//ajoute la modification de medications
	}

	@Override
	public void deleteMedicalRecordsModel(MedicalRecordsModel MedicalRecordDelete) 
	{
		
		this.MedicalRecordsList.remove(MedicalRecordDelete);
	}

	

}
