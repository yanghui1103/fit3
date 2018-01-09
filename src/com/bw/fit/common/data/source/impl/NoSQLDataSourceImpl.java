package com.bw.fit.common.data.source.impl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisCluster;

import com.bw.fit.common.data.source.NoSQLDataSource;
import com.bw.fit.common.model.RbackException;
/****
 * K-V 数据库集群模式下使用(组件)
 * @author yangh
 *
 */

public class NoSQLDataSourceImpl implements NoSQLDataSource {

	@Autowired
	private JedisCluster jedisCluster;  
	@Override
	public void del_list(String listName) {
		// TODO Auto-generated method stub
		jedisCluster.del(listName);
	}

	@Override
	public void lPush_list(String listName, String str) {
		// TODO Auto-generated method stub
		jedisCluster.lpush(listName, str);
	}

	@Override
	public void lrange(String listName, long start, long end) {
		// TODO Auto-generated method stub
		jedisCluster.lrange(listName, start, end);
	}

	@Override
	public void rPush(String listName,String str) throws RbackException {
		// TODO Auto-generated method stub
		try {
			jedisCluster.rpush(listName, str);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RbackException("1",e.getMessage());
		}
	}

	@Override
	public void lRemove(String listName, long index) {
		// TODO Auto-generated method stub  
	}

	@Override
	public String lIndex(String listName, long index) {
		// TODO Auto-generated method stub
		return jedisCluster.lindex(listName, index);
	}

	@Override
	public String lPop(String listName) {
		// TODO Auto-generated method stub
		return jedisCluster.lpop(listName);
	}

	@Override
	public String rPop(String listName) {
		// TODO Auto-generated method stub
		return jedisCluster.rpop(listName);
	}

	@Override
	public void sAdd(String setName, String object) {
		// TODO Auto-generated method stub
		jedisCluster.sadd(setName, object);
	}

	@Override
	public void sRemove(String setName, String object) {
		// TODO Auto-generated method stub
		jedisCluster.srem(setName, object);
	}

	@Override
	public long sCard(String setName) {
		// TODO Auto-generated method stub
		return jedisCluster.scard(setName);
	}

	@Override
	public boolean sisMember(String setName, String object) {
		// TODO Auto-generated method stub 
		return jedisCluster.sismember(setName, object);
	}

}
