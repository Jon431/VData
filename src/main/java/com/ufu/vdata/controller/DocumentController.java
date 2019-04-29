package com.ufu.vdata.controller;

import com.ufu.vdata.entity.Document;
import com.ufu.vdata.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Document> getDocuments(@RequestParam(value = "type", required = false) Byte type,
                                @RequestParam(value = "status", required = false) Byte status,
                                @RequestParam(value = "date-from", required = false) Date dateFrom,
                                @RequestParam(value = "date-to", required = false) Date dateTo) {
    documentService.populate();
    if (type!=null)
    return documentService.getAllByType(type);
    else return documentService.getAllByStatus(status);
    }

}
