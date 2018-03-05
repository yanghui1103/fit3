package com.bw.fit.common.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.common.data.source.KvdbDataSource;
import com.bw.fit.common.data.source.MqDataSource;
import com.bw.fit.common.data.source.NoSQLDataSource;
import com.bw.fit.common.data.source.RmdbDataSource;
import com.bw.fit.common.model.RbackException;

@Repository
public class DaoTempleteImpl  implements DaoTemplete{

	@Autowired
	private MqDataSource mqDataSource ;
	@Autowired
	private KvdbDataSource kvdbDataSource; 
	@Autowired
	private RmdbDataSource rmdbDataSource ;
	
	@Override
	public void sendDataToExchange(String message) throws RbackException {
		// TODO Auto-generated method stub
		mqDataSource.sendDataToExchange(message);
	}

	@Override
	public void sendDataToQueue(String queueName, String message)
			throws RbackException {
		// TODO Auto-generated method stub
		mqDataSource.sendDataToQueue(queueName, message);
	}

	@Override
	public String consumeDataFormQueue(String queueName) throws RbackException {
		// TODO Auto-generated method stub
		return mqDataSource.consumeDataFormQueue(queueName);
	}

//	@Override
//	public void del_list(String listName) {
//		// TODO Auto-generated method stub
//		noSQLDataSource.del_list(listName);
//	}
//
//	@Override
//	public void lPush_list(String listName, String str) {
//		// TODO Auto-generated method stub
//		noSQLDataSource.lPush_list(listName, str);
//	}
//
//	@Override
//	public void lrange(String listName, long start, long end) {
//		// TODO Auto-generated method stub
//		 noSQLDataSource.lrange(listName, start, end);
//	}
//
//	@Override
//	public void rPush(String listName, String str)  throws RbackException  {
//		// TODO Auto-generated method stub
//		try {
//			noSQLDataSource.rPush(listName, str);
//		} catch (RbackException e) {
//			 throw new RbackException("1",e.getMessage())  ;
//		}
//	}
//
//	@Override
//	public void lRemove(String listName, long index) {
//		// TODO Auto-generated method stub 
//	}
//
//	@Override
//	public String lIndex(String listName, long index) {
//		// TODO Auto-generated method stub
//		return noSQLDataSource.lIndex(listName, index);
//	}
//
//	@Override
//	public String lPop(String listName) {
//		// TODO Auto-generated method stub
//		return noSQLDataSource.lPop(listName);
//	}
//
//	@Override
//	public String rPop(String listName) {
//		// TODO Auto-generated method stub
//		return noSQLDataSource.rPop(listName);
//	}
//
//	@Override
//	public void sAdd(String setName, String object) {
//		// TODO Auto-generated method stub
//		noSQLDataSource.sAdd(setName, object);
//	}
//
//	@Override
//	public void sRemove(String setName, String object) {
//		// TODO Auto-generated method stub
//		noSQLDataSource.sRemove(setName, object);
//	}
//
//	@Override
//	public long sCard(String setName) {
//		// TODO Auto-generated method stub
//		return noSQLDataSource.sCard(setName);
//	}
//
//	@Override
//	public boolean sisMember(String setName, String object) {
//		// TODO Auto-generated method stub
//		return noSQLDataSource.sisMember(setName, object);
//	}

	@Override
	public void insert(String sql, Object object) throws RbackException {
		// TODO Auto-generated method stub
		rmdbDataSource.insert(sql, object);
	}

	@Override
	public void update(String sql, Object object) throws RbackException {
		// TODO Auto-generated method stub
		rmdbDataSource.update(sql, object);
	}

	@Override
	public void delete(String sql, Object object) throws RbackException {
		// TODO Auto-generated method stub
		rmdbDataSource.delete(sql, object);
	}

	@Override
	public List getListData(String sql, Object object) {
		// TODO Auto-generated method stub
		return rmdbDataSource.getListData(sql, object);
	}

	@Override
	public Object getOneData(String sql, Object object) {
		// TODO Auto-generated method stub
		return rmdbDataSource.getOneData(sql, object);
	}

	@Override
	public void join() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(String key, Object value) throws RbackException {
		kvdbDataSource.set(key, value);
	}

	@Override
	public String get(String key)  {
		return kvdbDataSource.get(key)  ;
	}

	@Override
	public void del(String key) throws RbackException {
		kvdbDataSource.del(key);
	}

}
