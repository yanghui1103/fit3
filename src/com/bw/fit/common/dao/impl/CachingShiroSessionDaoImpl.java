package com.bw.fit.common.dao.impl;

import java.io.*;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;

import com.bw.fit.common.dao.DaoTemplete;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.common.util.PubFun;
import com.bw.fit.system.model.User;
import com.mchange.v2.ser.SerializableUtils;
/*****
 * redis存储会话
 * @author yangh
 *
 */
@Repository(value = "redisSessionDAO")
public class CachingShiroSessionDaoImpl extends EnterpriseCacheSessionDAO {
	
	@Autowired
	private DaoTemplete daoTemplete ;
	@Override
	protected void doDelete(Session session) {
        super.doDelete(session);
        try {
			daoTemplete.del(session.getId()+"");
		} catch (RbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doUpdate(Session session) {
        super.doUpdate(session);
        try {
			daoTemplete.set(session.getId().toString(), sessionToByte(session));
		} catch (RbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = super.doCreate(session);
		try {
			daoTemplete.set(sessionId.toString(), sessionToByte(session));
		} catch (RbackException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		// 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId); 
        if(session == null){
            byte[] bytes = null ;
            try {
				bytes = daoTemplete.get(sessionId.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null ;
			}
            if(bytes != null && bytes.length > 0){
                session = byteToSession(bytes);    
            }
        }
        return session;
	}

	/****
	 * 把session对象转化为byte保存到redis中
	 * @param session
	 * @return
	 */
	public byte[] sessionToByte(Session session) {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] bytes = null;
		try {
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(session);
			bytes = bo.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/*****
	 * 把byte还原为session
	 * @param bytes
	 * @return
	 */
	public Session byteToSession(byte[] bytes) {
		ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
		ObjectInputStream in;
		SimpleSession session = null;
		try {
			in = new ObjectInputStream(bi);
			session = (SimpleSession) in.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return session;
	}
}
