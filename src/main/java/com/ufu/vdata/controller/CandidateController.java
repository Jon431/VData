package com.ufu.vdata.controller;

import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.Election;
import com.ufu.vdata.repository.CandidateListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
class CandidateController {

    @Autowired
    CandidateListRepository candidateListRepository;

    @RequestMapping(method = RequestMethod.GET)
    List<Candidate> getElectionCandidates(@RequestParam(value = "election_id", required = false) String electionId) {
        if (electionId != null) {
            return candidateListRepository.findAllByElection(new Election(electionId)); }

        else
            return candidateListRepository.findAll();

    }

    @RequestMapping(method = RequestMethod.POST)
    Candidate createCandidate(@RequestBody Candidate newCandidate) {


        return candidateListRepository.save(newCandidate);
    }

}
