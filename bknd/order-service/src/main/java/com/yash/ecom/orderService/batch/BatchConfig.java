package com.yash.ecom.orderService.batch;

import java.sql.SQLException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yash.ecom.orderService.domain.Order;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public OrderItemReader reader() {
		return new OrderItemReader();
	}

	@Bean
	public OrderItemProcessor processor() {
		return new OrderItemProcessor();
	}

	@Bean
	public OrderItemWriter write() {
		return new OrderItemWriter();
	}

	@Bean
	public OrderItemWriterListner writeListner() {
		return new OrderItemWriterListner();
	}

	@Bean
	public OrderItemProcessorListner processListner() {
		return new OrderItemProcessorListner();
	}

	@Bean
	public OrderItemReaderListner readListner() {
		return new OrderItemReaderListner();
	}

	@Bean
	public Step step1(OrderItemReader reader, OrderItemProcessor processor, OrderItemWriter writer,
			OrderItemReaderListner readListner, OrderItemProcessorListner processListner,
			OrderItemWriterListner writeListner) {
		return stepBuilderFactory.get("step1")
				.<Order, Order>chunk(2)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.listener(readListner)
				.listener(processListner)
				.listener(writeListner)
				.faultTolerant()
				.retryLimit(2)
				.retry(SQLException.class)
				.build();
	}

	@Bean
	public Job updateOrderJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory.get("updateOrderJob")
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}

}
