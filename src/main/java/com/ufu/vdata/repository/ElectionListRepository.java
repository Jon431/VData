package com.ufu.vdata.repository;

import com.ufu.vdata.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectionListRepository extends JpaRepository<Election, Long> {

}
