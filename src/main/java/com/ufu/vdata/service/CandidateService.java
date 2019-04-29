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

    public Candidate createCandidate(Candidate candidate) {
        return candidateListRepository.save(candidate);
    }

    public void deleteCandidate(UUID uuid) {
        candidateListRepository.deleteById(uuid);
    }

    public Candidate uploadFile(MultipartFile file) {
       return inputFileProcessor.createCandidateFromFile(file);
    }

}
