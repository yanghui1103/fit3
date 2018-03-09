package com.bw.fit.warning.service.impl;

import java.util.List;
import java.util.Optional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.common.util.MailTool;
import com.bw.fit.common.util.SmsSender;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.entity.TdataDict;
import com.bw.fit.warning.service.IWarningService;

@Service
public class IWarningServiceImpl implements IWarningService {

	@Autowired
	private SystemDao systemDao ;
	
	@Override
	public void sendWarning(String warningLevel ,String warningType ,String target_number,String subject,String message) throws RbackException {
		List<TdataDict> lis = systemDao.getDataDictOfPId("1111111151");//取出预警级别
		Optional<TdataDict> t = lis.stream().filter(x->x.getDict_value().equalsIgnoreCase(warningLevel)).findFirst();
		
		message = "[" + t.get().getDict_name()+"]" +message ;		
		if("1".equals(warningType)){
			try {
				SmsSender.SendSMSString(target_number, message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RbackException("1","预警短信发送异常,地址:"+target_number); 
			}
		}else if("3".equals(warningType)){ 
			StringBuilder sb = new StringBuilder(message);
			try {
				MailTool.send(subject, sb, new InternetAddress[] { new InternetAddress(target_number) });
			} catch (AddressException e) {
				// TODO Auto-generated catch block
			}
		}else if("2".equals(warningType)){
			/****
			 * 发送即时消息
			 */
		} else{
			throw new RbackException("1","预警方式错误"); 
		}
	}

}
