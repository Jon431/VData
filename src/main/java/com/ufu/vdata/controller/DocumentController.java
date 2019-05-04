package com.ufu.vdata.controller;

import com.ufu.vdata.entity.document.Document;
import com.ufu.vdata.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
                                @RequestParam(value = "date-from", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateFrom,
                                @RequestParam(value = "date-to", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateTo) {

    if (type==null&&status==null&&dateFrom==null&&dateTo==null)
    return documentService.getAll();

    else if (type!=null&&status==null&&dateFrom==null&&dateTo==null)
        return documentService.getAllByType(type);

    else if (type==null&&status!=null&&dateFrom==null&&dateTo==null)
        return documentService.getAllByStatus(status);

    else if (type==null&&status==null&&dateFrom!=null&&dateTo!=null)
        return documentService.getAllByDateCreated(dateFrom,dateTo);

    else if (type!=null&&status!=null&&dateFrom==null&&dateTo==null)
        return documentService.getAllByTypeAndStatus(type, status);
    else return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    void createDocuments(@RequestParam(value = "type") Byte type, @RequestBody ArrayList<String> candidateIds) {
        switch(type) {
            case 1: {
                documentService.createDocIncComs(candidateIds);
            }
            case 2: {
                documentService.createDocINNs(candidateIds);
            }
            default: {
                throw new IllegalArgumentException("Type " + type + " not found. Please use valid document type.");
            }
        }
    }
    @RequestMapping(method = RequestMethod.PUT)
    void operateDocuments(@RequestParam(value = "do") String doo, @RequestBody ArrayList<String> documentIds) {
        if (doo.equals("send")) {
            documentService.sendDocuments(documentIds);
        }
        else throw new IllegalArgumentException("Action " + doo + " not found. Please use valid action type.");
     }

}
