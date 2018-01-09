package com.bw.fit.common.dao;

import com.bw.fit.common.data.source.KvdbDataSource;
import com.bw.fit.common.data.source.MqDataSource;
import com.bw.fit.common.data.source.NoSQLDataSource;
import com.bw.fit.common.data.source.RmdbDataSource;

public interface DaoTemplete extends MqDataSource,  KvdbDataSource,
		RmdbDataSource {

	public void join();
}
