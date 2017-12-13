package com.bw.fit.warning.service.impl;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.common.util.MailTool;
import com.bw.fit.common.util.SmsSender;
import com.bw.fit.warning.service.WarningService;

@Service
public class WarningServiceImpl implements WarningService {

	@Override
	public void sendWarning(String warningLevel ,String target_number,String subject,String message) throws RbackException {
		if("1".equals(warningLevel)){
			try {
				SmsSender.SendSMSString(target_number, message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RbackException("1","预警短信发送异常,地址:"+target_number); 
			}
		}else if("3".equals(target_number)){ 
			StringBuilder sb = new StringBuilder(message);
			try {
				MailTool.send(subject, sb, new InternetAddress[] { new InternetAddress(target_number) });
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				throw new RbackException("1","预警邮件发送异常,地址:"+target_number); 
			}
		}else if("2".equals(warningLevel)){
			/****
			 * 发送即时消息
			 */
		} 
	}

}
