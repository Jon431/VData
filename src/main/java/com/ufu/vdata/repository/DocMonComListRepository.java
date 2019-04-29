package com.ufu.vdata.repository;

import com.ufu.vdata.entity.DocMonCom;
import com.ufu.vdata.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface DocMonComListRepository extends JpaRepository<DocMonCom, UUID> {
    @Query("select d from DocMonCom d where d.status = :status")
    List<Document> getAllByStatus(@Param("status") Byte status);
    @Query("select d from DocMonCom d")
    List<Document>getAll();
    //@Query("select d from DocMonCom d ")
    List<Document>getAllByDateCreatedBetween(Date date1, Date date2);
}
