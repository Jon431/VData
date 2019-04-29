package com.ufu.vdata.service;

import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.Election;
import com.ufu.vdata.repository.CandidateListRepository;
import com.ufu.vdata.service.parser.InputFileProcessor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class CandidateService {
    private final CandidateListRepository candidateListRepository;
    private  final InputFileProcessor inputFileProcessor;

    public CandidateService(CandidateListRepository candidateListRepository, InputFileProcessor inputFileProcessor) {
        this.candidateListRepository = candidateListRepository;
        this.inputFileProcessor = inputFileProcessor;
    }

    public List<Candidate> getCandidates(Election election) {
        return candidateListRepository.findAllByElection(election);
    }
    public List<Candidate> getCandidates() {
        return candidateListRepository.findAll();
    }

    public Candidate getCandidate(UUID uuid) {
        return candidateListRepository.findById(uuid).orElseThrow(() -> new ObjectNotFoundException(uuid, "candidate"));
    }

    public Candidate createCandidate(Candidate newCandidate) {
        return candidateListRepository.save(newCandidate);
    }

    public Candidate editCandidate(UUID uuid, Candidate newCandidate) {
        Candidate candidate = candidateListRepository.findById(uuid).orElseThrow(() -> new ObjectNotFoundException(uuid, "candidate"));
        candidate.setLastName(newCandidate.getLastName());
        candidate.setFirstName(newCandidate.getFirstName());
        candidate.setPatronymic(newCandidate.getPatronymic());
        candidate.setIncomeYear(newCandidate.getIncomeYear());
        candidate.setEstateDate(newCandidate.getEstateDate());
        candidate.setDocumentType(newCandidate.getDocumentType());
        candidate.setDocumentNumber(newCandidate.getDocumentNumber());
        candidate.setInn(newCandidate.getInn());
/*
        candidate.setIncomeList(newCandidate.getIncomeList());
        candidate.setRequestList(newCandidate.getRequestList());
        candidate.setSequritiesList(newCandidate.getSequritiesList());
        candidate.setCommercialList(newCandidate.getCommercialList());
        candidate.setMoneyList(newCandidate.getMoneyList());
        candidate.setEstateList(newCandidate.getEstateList());
        candidate.setTransportList(newCandidate.getTransportList());
        candidate.setStockList(newCandidate.getStockList());
*/
//TODO change it
        return candidateListRepository.save(candidate);

    }

    public void deleteCandidate(UUID uuid) {
        candidateListRepository.deleteById(uuid);
    }

    public Candidate uploadFile(MultipartFile file) {
       return inputFileProcessor.createCandidateFromFile(file);
    }

}
