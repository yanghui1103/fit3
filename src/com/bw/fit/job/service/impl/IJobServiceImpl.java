package com.bw.fit.job.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.bw.fit.job.service.IJobService;

@Service
public class IJobServiceImpl implements IJobService {

	public static Log log = LogFactory.getLog(IJobServiceImpl.class);
	@Override
	public void test() {
		// TODO Auto-generated method stub
		log.info("test job");
	}
	@Override
	public void job_clear() {
		// TODO Auto-generated method stub
		log.info("test job_clear");
	}

}
