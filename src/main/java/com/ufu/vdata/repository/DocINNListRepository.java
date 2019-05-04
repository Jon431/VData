package com.ufu.vdata.repository;

import com.ufu.vdata.entity.document.DocINN;
import com.ufu.vdata.entity.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface DocINNListRepository extends JpaRepository<DocINN, UUID> {
    List<Document> getAllByStatus(Byte status);
    @Query("select d from DocINN d")
    List<Document> getAll();
    List<Document>getAllByDateCreatedBetween(Date date1, Date date2);
    List<Document>getAllByTypeAndStatus(Byte type, Byte status);
    Document getById(UUID id);
}
