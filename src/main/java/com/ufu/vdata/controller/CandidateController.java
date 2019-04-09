package com.ufu.vdata.controller;

import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.Election;
import com.ufu.vdata.repository.CandidateListRepository;
import com.ufu.vdata.repository.ElectionListRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    CandidateListRepository candidateListRepository;
    @Autowired
    ElectionListRepository electionListRepository;

    @RequestMapping(method = RequestMethod.GET)
    List<Candidate> getElectionCandidates(@RequestParam(value = "election_id", required = false) UUID electionUUID) {
        if (electionUUID != null) {
            return electionListRepository.findById(electionUUID).orElseThrow(() -> new ObjectNotFoundException(electionUUID, "election")).getCandidateList(); }
        else
            return candidateListRepository.findAll();

    }

    @RequestMapping(method = RequestMethod.POST)
    Candidate createCandidate(@RequestBody Candidate newCandidate) {


        return candidateListRepository.save(newCandidate);
    }

}
