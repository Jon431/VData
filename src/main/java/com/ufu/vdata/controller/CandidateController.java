package com.ufu.vdata.controller;

import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.Election;
import com.ufu.vdata.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidates")
class CandidateController {

    private final CandidateService candidateService;


    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService; }

    @RequestMapping(method = RequestMethod.GET)
    List<Candidate> getCandidates(@RequestParam(value = "election-id", required = false) String electionId) {
        if (electionId != null) {
            return candidateService.getCandidates(new Election(electionId)); }

        else
            return candidateService.getCandidates();
    }

    @RequestMapping(method = RequestMethod.POST)
    Candidate createCandidate(@RequestBody Candidate newCandidate) {
        return candidateService.createCandidate(newCandidate);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{candidateId}")
    Candidate getCandidate(@PathVariable UUID candidateId) {
        return candidateService.getCandidate(candidateId);
    }

    //TODO edit candidates

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{candidateId}")
    void deleteCandidate(@PathVariable UUID candidateId) {
        candidateService.deleteCandidate(candidateId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload-file")
    Candidate uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(value = "election-id", required = false) String electionId) {

            Candidate cnd = candidateService.uploadFile(file);
            if (electionId != null) {
            cnd.setElection(new Election(electionId)); }
            return cnd;
    }
}
