package com.ufu.vdata.controller;

import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.Election;
import com.ufu.vdata.repository.CandidateListRepository;
import com.ufu.vdata.service.parser.InputFileProcessor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
class CandidateController {

    private final CandidateListRepository candidateListRepository;
    private final InputFileProcessor inputFileProcessor;

    @Autowired
    public CandidateController(CandidateListRepository candidateListRepository, InputFileProcessor inputFileProcessor) {
        this.candidateListRepository = candidateListRepository;
        this.inputFileProcessor = inputFileProcessor; }

    @RequestMapping(method = RequestMethod.GET)
    List<Candidate> getCandidates(@RequestParam(value = "election_id", required = false) String electionId) {
        if (electionId != null) {
            return candidateListRepository.findAllByElection(new Election(electionId)); }

        else
            return candidateListRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    Candidate createCandidate(@RequestBody Candidate newCandidate) {
        return candidateListRepository.save(newCandidate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{candidateId}")
    Candidate getCandidate(@PathVariable UUID candidateId) {
        return candidateListRepository.findById(candidateId).orElseThrow(() -> new ObjectNotFoundException(candidateId, "candidate"));
    }

    //TODO edit candidates

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{candidateId}")
    void deleteCandidate(@PathVariable UUID candidateId) {
        candidateListRepository.deleteById(candidateId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload-file")
    Candidate uploadFile(@RequestParam("file") MultipartFile file) {

            return inputFileProcessor.createCandidateFromFile(file);
    }
}
