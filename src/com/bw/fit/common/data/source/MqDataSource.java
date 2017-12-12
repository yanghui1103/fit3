package com.bw.fit.common.data.source;

import com.bw.fit.common.entity.RbackException;

public interface MqDataSource {

	public void sendData(Object content) throws  RbackException;
	
	public void consumeData() throws  RbackException;
}
