package com.ufu.vdata.repository;

import com.ufu.vdata.entity.Candidate;
import com.ufu.vdata.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CandidateListRepository extends JpaRepository<Candidate, UUID> {
    @Query("select c from Candidate c where c.election = :election")
    List<Candidate> findAllByElection(@Param("election")Election election);
}
