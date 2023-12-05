package com.erpm.employeeManagementService.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpm.employeeManagementService.dtos.SeniorityDto;
import com.erpm.employeeManagementService.entitys.Seniority;
import com.erpm.employeeManagementService.exceptions.SeniorityNotFoundException;
import com.erpm.employeeManagementService.repositorys.SeniorityRepository;

@Service
public class SeniorityService {

	@Autowired
	private SeniorityRepository seniorityRepository;

	public List<Seniority> getAllSeniority() {
		List<Seniority> seniorities = seniorityRepository.findAll();
		return seniorities;
	}

	public Seniority getSeniorityById(int seniorityId) throws SeniorityNotFoundException {
		Seniority seniority;
		try {
			seniority = seniorityRepository.findById(seniorityId).get();
		} catch (NoSuchElementException ex) {
			throw new SeniorityNotFoundException("seniority id +" + seniorityId + " not found");
		}
		return seniority;
	}

	public void deleteSeniorityById(int seniorityId) {
		seniorityRepository.deleteById(seniorityId);
	}

	public Seniority updateSeniorityById(SeniorityDto newSeniority) throws SeniorityNotFoundException {
		Seniority sen = getSeniorityById(newSeniority.getSeniorityid());
		sen.setSeniority(newSeniority.getSeniority());
		sen = seniorityRepository.save(sen);
		return sen;
	}

	public Seniority addSeniority(SeniorityDto sen) {
		Seniority seniority = new Seniority();
		seniority.setSeniority(sen.getSeniority());
		seniority = seniorityRepository.save(seniority);
		return seniority;
	}

}
