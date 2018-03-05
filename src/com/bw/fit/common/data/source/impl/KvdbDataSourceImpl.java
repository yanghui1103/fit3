package com.bw.fit.common.data.source.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.bw.fit.common.data.source.KvdbDataSource;
import com.bw.fit.common.model.RbackException;

/****
 * k-v 数据库 单机服务模式（组件）
 * @author yangh
 *
 */
@Component
public class KvdbDataSourceImpl  implements KvdbDataSource{
	 @Autowired
	 private RedisTemplate redisTemplate ;

	@Override
	public void set(String key, Object value) throws RbackException {
		try {
			redisTemplate.opsForValue().set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RbackException("1",e.getMessage());
		}
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return  (String)(redisTemplate.opsForValue().get(key)) ; 
	}

	@Override
	public void del(String key) throws RbackException {
		try {
			redisTemplate.delete(key);
		} catch (Exception e) {
			throw new RbackException("1",e.getMessage());
		}
	}


	 
}
