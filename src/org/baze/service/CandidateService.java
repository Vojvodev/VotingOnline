package org.baze.service;

import org.baze.dataaccess.CandidateDataAccess;
import org.baze.model.Candidate;

public class CandidateService {
	
	
	public Candidate selectByFnameAndLname(String fname, String lname) {
		return CandidateDataAccess.getByFnameAndLname(fname, lname);	
	}
	
	public Candidate selectById(int id) {
		return CandidateDataAccess.getById(id);	
	}
	
	public boolean deleteCandidate(int id) {
		return CandidateDataAccess.delete(id);	
	}
	
	
	public boolean createCandidate(String fname, String lname, int partyId) {
		return CandidateDataAccess.create(fname, lname, partyId);
	}
	
	public boolean updateCandidate(int candidateId, String fname, String lname, int partyId) {
		return CandidateDataAccess.update(candidateId, fname, lname, partyId);
	}
	
}
