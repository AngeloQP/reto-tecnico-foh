package com.reto.backend.config;

import com.reto.backend.entities.Cliente;
import com.reto.backend.steps.ClienteItemProcessor;
import com.reto.backend.steps.ClienteItemReader;
import com.reto.backend.steps.ClienteItemWritter;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public ClienteItemReader clienteItemReader(){
        return new ClienteItemReader();
    }

    @Bean
    public ClienteItemProcessor clienteItemProcessor() {
        return new ClienteItemProcessor();
    }

    @Bean
    public ClienteItemWritter clienteItemWriter() {
        return new ClienteItemWritter();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setQueueCapacity(5);
        return taskExecutor;
    }

    @Bean
    public Step readExcel() {
        return stepBuilderFactory.get("readExcel")
                .<Row, Cliente>chunk(100)
                .reader(clienteItemReader())
                .processor(clienteItemProcessor())
                .writer(clienteItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(readExcel())
                .build();
    }

}
