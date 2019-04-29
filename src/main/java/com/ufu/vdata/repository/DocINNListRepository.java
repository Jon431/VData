package com.ufu.vdata.repository;

import com.ufu.vdata.entity.DocINN;
import com.ufu.vdata.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DocINNListRepository extends JpaRepository<DocINN, UUID> {
    @Query("select d from DocINN d where d.status = :status")
    List<Document> getAllByStatus(@Param("status") Byte status);
    @Query("select d from DocINN d")
    List<Document> getAll();
}
