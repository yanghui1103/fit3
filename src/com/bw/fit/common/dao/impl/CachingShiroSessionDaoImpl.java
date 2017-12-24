package com.bw.fit.common.dao.impl;

import java.io.Serializable;
 







import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.bw.fit.common.dao.DaoTemplete;
@Repository
public class CachingShiroSessionDaoImpl extends CachingSessionDAO {

	@Override
	protected void doDelete(Session arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doUpdate(Session arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Serializable doCreate(Session arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Session doReadSession(Serializable arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
