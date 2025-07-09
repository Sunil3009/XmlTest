package com.securin.xmltest.Controller;

import com.securin.xmltest.Entity.CpeEntity;
import com.securin.xmltest.Service.CpeService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CpeController {

    @Autowired
    CpeService cpeService;

    @GetMapping("/fetch")
    public String fetch() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, IOException, JobParametersInvalidException, JobRestartException {
        cpeService.fetchAndLoad();
        return "Fetch completed";
    }

    @GetMapping
    public Page<CpeEntity> getAllCpes(
            @RequestParam(defaultValue = "0") int page,       // default page number = 0
            @RequestParam(defaultValue = "10") int limit      // default page size = 10
    ) {
        return cpeService.findAll(
                PageRequest.of(page, limit, Sort.by("id"))
        );
    }

    @GetMapping("/filter")
    public Page<CpeEntity> getAllCpesFilter(@RequestParam(required = false) String title,
                                            @RequestParam(required = false) String cpe22Uri,
                                            @RequestParam(required = false) String Date,
                                            @RequestParam(required = false) String cpe23Uri){

        return cpeService.findAll(title,cpe22Uri,cpe23Uri,Date);
    }
}
