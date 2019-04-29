package com.ufu.vdata.repository;

import com.ufu.vdata.entity.DocMonCom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DocMonComListRepository extends JpaRepository<DocMonCom, UUID> {
    @Query("select d from DocMonCom d where d.type = :type")
    List<DocMonCom> getAllByType(@Param("type") Byte type);
}
