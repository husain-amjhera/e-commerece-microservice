package com.yash.ecom.orderService.scheduler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Scheduler {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Scheduled(fixedDelay = 120000, initialDelay = 300000)
	public void updateStatus() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		log.info("Scheduled update");
		Map<String, JobParameter> confMap = new HashMap<String, JobParameter>();
		confMap.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(confMap);
		jobLauncher.run(job, jobParameters);
		log.info("Completed scheduled updating");
	}
}
