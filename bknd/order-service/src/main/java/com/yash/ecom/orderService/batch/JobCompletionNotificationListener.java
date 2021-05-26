package com.yash.ecom.orderService.batch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("Job started.");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("Job ended.");
		
	}

}
