package com.ufu.vdata.controller;

import com.ufu.vdata.entity.Election;
import com.ufu.vdata.repository.ElectionListRepository;
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


    @RequestMapping(method= RequestMethod.GET)
    List<Election> findAll() {
        return electionListRepository.findAll();
    }

    @RequestMapping(method= RequestMethod.POST)
    Election createElection(@RequestBody Election newElection) {

        return electionListRepository.save(newElection);
    }

}
