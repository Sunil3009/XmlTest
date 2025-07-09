package com.securin.xmltest.Batch;

import com.securin.xmltest.DataModel.CpeItem;
import com.securin.xmltest.Entity.CpeEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Processor implements ItemProcessor<CpeItem, CpeEntity> {
    @Override
    public CpeEntity process(CpeItem item) throws Exception {
        CpeEntity cpeEntity = new CpeEntity();
        cpeEntity.setCpeTitle(item.getTitle().getText());
        cpeEntity.setCpe22Uri(item.getCpe22Uri());
        cpeEntity.setCpe23Uri(item.getCpe23Item().getCpe23Uri());
        cpeEntity.setCpe22Date(getCpe22Date(item.getDeprecationDate()));
        if(item.getCpe23Item() != null
                && item.getCpe23Item().getDeprecation() != null
                && item.getCpe23Item().getDeprecation().getDate() != null){
            cpeEntity.setCpe23Date(getCpeDate(item.getCpe23Item().getDeprecation().getDate()));
        }

        return cpeEntity;

    }

    private LocalDateTime getCpeDate(String deprecatedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        OffsetDateTime date = OffsetDateTime.parse(deprecatedDate, formatter);
        return date.toLocalDateTime();
    }

    public LocalDateTime getCpe22Date(String deprecatedDate) {
        if (deprecatedDate == null || deprecatedDate.isBlank()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        OffsetDateTime date = OffsetDateTime.parse(deprecatedDate, formatter);
        return date.toLocalDateTime();
    }
}
