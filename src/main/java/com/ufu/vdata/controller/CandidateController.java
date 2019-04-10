package com.ufu.vdata.controller;

import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.Election;
import com.ufu.vdata.repository.CandidateListRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
class CandidateController {

    @Autowired
    CandidateListRepository candidateListRepository;

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

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{candidateId}")
    void deleteCandidate(@PathVariable UUID candidateId) {
        candidateListRepository.deleteById(candidateId);
    }



}
