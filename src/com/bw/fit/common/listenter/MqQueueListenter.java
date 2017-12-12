package com.bw.fit.common.listenter;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class MqQueueListenter implements MessageListener {

	@Override
	public void onMessage(Message msg, byte[] arg1) {
		try {
			System.out.print(msg.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}