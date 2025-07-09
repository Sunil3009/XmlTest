package com.securin.xmltest.Batch;

import com.securin.xmltest.Entity.CpeEntity;
import com.securin.xmltest.Repository.CpeRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


@Component
public class Writer implements ItemWriter<CpeEntity> {

    private CpeRepository cpeRepository;

    public Writer(CpeRepository cpeRepository) {
        this.cpeRepository=cpeRepository;
    }

    @Override
    public void write(Chunk<? extends CpeEntity> chunk) throws Exception {
        cpeRepository.saveAll(chunk.getItems());
    }
}
