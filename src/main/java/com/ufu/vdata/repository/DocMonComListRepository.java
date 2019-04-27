package com.ufu.vdata.repository;

import com.ufu.vdata.entity.DocMonCom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DocMonComListRepository extends JpaRepository<DocMonCom, UUID> {
}
