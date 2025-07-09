package com.securin.xmltest.Batch;

import com.securin.xmltest.DataModel.CpeItem;
import com.securin.xmltest.Entity.CpeEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Config {

    @Bean
    public Job fetchJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("importCpe",jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository,
                     PlatformTransactionManager transactionManage,
                     ItemProcessor<CpeItem,CpeEntity> itemProcessor,
                     ItemReader<CpeItem> itemReader,
                     ItemWriter<CpeEntity> itemWriter) {
        return new StepBuilder("importCpe",jobRepository)
                .<CpeItem,CpeEntity>chunk(1500,transactionManage)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(10);
        return taskExecutor;
    }
}
