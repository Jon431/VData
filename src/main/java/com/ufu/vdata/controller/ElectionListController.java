package com.ufu.vdata.controller;

import com.ufu.vdata.entity.Election;
import com.ufu.vdata.repository.ElectionListRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value= "/api/elections")
class ElectionListController {

    @Autowired
    ElectionListRepository electionListRepository;


    @RequestMapping(method = RequestMethod.GET)
    List<Election> findElections() {
        return electionListRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    Election createElection(@RequestBody Election newElection) {
        return electionListRepository.save(newElection);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{electionId}")
    Election getElection(@PathVariable Long electionId) {
        return electionListRepository.findById(electionId).orElseThrow(() -> new ObjectNotFoundException(electionId, "election"));
    }

    @RequestMapping(method = RequestMethod.PUT, value="/{electionId}")
    Election editElection(@PathVariable Long electionId, @RequestBody Election newElection) {
        return electionListRepository.findById(electionId)
                .map(election -> {
                        election.setName(newElection.getName());
                        return electionListRepository.save(election);}).orElseGet(() -> {
                        newElection.setId(electionId);
                        return electionListRepository.save(newElection);});
    }

    @RequestMapping(method = RequestMethod.DELETE, value="/{electionId}")
    void deleteElection(@PathVariable Long electionId) {
        electionListRepository.deleteById(electionId);
    }

}
