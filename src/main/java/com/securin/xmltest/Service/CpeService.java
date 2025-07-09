package com.securin.xmltest.Service;

import com.securin.xmltest.Entity.CpeEntity;
import com.securin.xmltest.Repository.CpeRepository;
import com.securin.xmltest.Specification.ReqSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class CpeService {

    @Autowired
    private CpeRepository cpeRepository;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job fetchJob;


    public String fetchAndLoad() throws IOException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(fetchJob,jobParameters);
        return "fetched";
    }

    public Page<CpeEntity> findAll(PageRequest page) {
        return cpeRepository.findAll(page);
    }

    public Page<CpeEntity> findAll(String title, String cpe22Uri, String cpe23Uri, String date) {
        Specification<CpeEntity> spec = Specification.where(ReqSpecification.hasAtLeastOneDate());

        if (title != null && !title.isEmpty()) {
            spec = spec.and(ReqSpecification.hasTitle(title));
        }

        if (cpe22Uri != null && !cpe22Uri.isEmpty()) {
            spec = spec.and(ReqSpecification.withCpe22uri(cpe22Uri));
        }

        if (cpe23Uri != null && !cpe23Uri.isEmpty()) {
            spec = spec.and(ReqSpecification.withCpe23uri(cpe23Uri));
        }

        if (date != null && !date.isEmpty()) {
            LocalDateTime dateValue = convertDate(date);
            spec = spec.and(ReqSpecification.deprecatedBefore(dateValue));
        }

        return cpeRepository.findAll(spec, PageRequest.of(0, 10, Sort.by("id")));
    }


    public LocalDateTime convertDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        OffsetDateTime offDate = OffsetDateTime.parse(date, formatter);
        return offDate.toLocalDateTime();
    }
}
