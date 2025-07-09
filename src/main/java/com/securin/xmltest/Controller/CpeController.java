package com.securin.xmltest.Controller;

import com.securin.xmltest.Service.CpeService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
