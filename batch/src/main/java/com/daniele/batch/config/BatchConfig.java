package com.daniele.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.daniele.batch.model.Nota;
import com.daniele.batch.processor.NotaProcessor;
import com.daniele.batch.reader.NotaReader;
import com.daniele.batch.writer.NotaWriter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private NotaProcessor notaProcessor;
	
	@Autowired
	private NotaReader notaReader;	
	
	@Autowired 
	NotaWriter notaWriter;
	
	@Bean
	public Job exportNotaJob(){
		return jobBuilderFactory.get("importXmlJob")
				.incrementer(new RunIdIncrementer())
				.flow(step())
				.end()
				.build();
	}
	
	@Bean
	public Step step(){
		return stepBuilderFactory.get("step1")
				.<String,Nota>chunk(1)
				.reader(notaReader)
				.processor(notaProcessor)
				.writer(notaWriter)
				.build();
	}
		


	
	@Bean
	public ItemReader<String> reader(){ 		
		return new NotaReader();	
	}
	
	
	@Bean
	public NotaProcessor notaProcessor(){
		return new NotaProcessor();
	}	
	

}
