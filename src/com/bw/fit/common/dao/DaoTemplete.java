package com.bw.fit.common.dao;

import com.bw.fit.common.data.source.MqDataSource;
import com.bw.fit.common.data.source.NoSQLDataSource;
import com.bw.fit.common.data.source.RmdbDataSource;

public interface DaoTemplete extends MqDataSource, NoSQLDataSource,
		RmdbDataSource {

	public void join();
}
