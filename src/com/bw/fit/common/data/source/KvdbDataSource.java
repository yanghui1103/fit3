package com.bw.fit.common.data.source;

import com.bw.fit.common.model.RbackException;

public interface KvdbDataSource {

	public void set(String key ,Object value) throws RbackException;
	
}
