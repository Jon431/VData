package com.ufu.vdata.repository;

import com.ufu.vdata.entity.document.DocIncCom;
import com.ufu.vdata.entity.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface DocIncComRepository extends JpaRepository<DocIncCom, UUID> {
    @Query("select d from DocIncCom d where d.status = :status")
    List<Document> getAllByStatus(@Param("status") Byte status);
    @Query("select d from DocIncCom d")
    List<Document>getAll();
    //@Query("select d from DocIncCom d ")
    List<Document>getAllByDateCreatedBetween(Date date1, Date date2);
}
