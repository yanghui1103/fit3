package com.bw.fit.log.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import static com.bw.fit.common.util.PubFun.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.session.Session;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.util.PubFun;
import com.bw.fit.log.enmu.LogType;
import com.bw.fit.log.entity.TLogInfo;
import com.bw.fit.log.service.ILogService;
import com.bw.fit.system.model.LogUser;
import com.bw.fit.system.model.User;

/****
 * 拦截control请求的日志切面
 * 
 * @author yangh
 *
 */
@Component(value="controlLogAspect")
public class ControlLogAspect implements Ordered {
	public static Log log = LogFactory.getLog(ControlLogAspect.class);
	@Autowired  
	HttpServletRequest request; 
	@Autowired
	private ILogService iLogService;

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	public Object aroundMethod(ProceedingJoinPoint pjd) {
		// 通过分析aop监听参数分析出request等信息
		List<Object> list = Arrays.asList(pjd.getArgs());
		Object obj = null;
		try {
			Signature sig = pjd.getSignature();
			MethodSignature msig = null;
			if (!(sig instanceof MethodSignature)) {
				throw new IllegalArgumentException("该注解只能用于方法");
			}
			msig = (MethodSignature) sig;
			Object target = pjd.getTarget();
			Method currentMethod = target.getClass().getMethod(msig.getName(),
					msig.getParameterTypes());

			obj = pjd.proceed(); // 执行
			TLogInfo t = new TLogInfo();
			t.setLog_type_id("58");		 // 主系统操作日志，选自数据字典
			Session session = PubFun.getCurrentSession(); 
			User user = ((User)session.getAttribute("CurrentUser"));
			t.setOperator_id(user==null?"":user.getFdid());
			t.setFdid(getUUID());
			t.setIp(getIpAddr(request));
			t.setOperate_function(currentMethod.getName()); 
			if ((obj instanceof JSONObject)) {
				JSONObject j = (JSONObject)obj;
				t.setRes(j.get("res").toString());
				t.setMsg(j.get("msg").toString());
			}
			String url =  request.getRequestURL().toString();//  http://localhost:8080/dufyun_SpringMVC/testName 
			t.setUrl(url);
			iLogService.notice(t);
		} catch (Throwable e) {
			log.error("controller LogAspect:异常通知 ... , exception = " + e);
			e.printStackTrace();
		}
		return obj;
	}
}
