package com.ufu.vdata.repository;

import com.ufu.vdata.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateListRepository extends JpaRepository<Candidate, UUID> {
}
