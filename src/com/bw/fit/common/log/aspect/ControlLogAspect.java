package com.bw.fit.common.log.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import static com.bw.fit.common.util.PubFun.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.log.entity.TLogInfo;
import com.bw.fit.log.service.ILogService;
import com.bw.fit.system.model.LogUser;

/****
 * 拦截control请求的日志切面
 * 
 * @author yangh
 *
 */
@Component
public class ControlLogAspect implements Ordered {
	public static Log log = LogFactory.getLog(ControlLogAspect.class);

	@Autowired
	private ILogService iLogService;

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

	public Object aroundMethod(ProceedingJoinPoint pjd) {
		// String name = pjd.getSignature().getName();
		HttpServletRequest request = null;  
		// 通过分析aop监听参数分析出request等信息
		List<Object> list = Arrays.asList(pjd.getArgs());
		for(Object obj :list){
			if (obj instanceof HttpServletRequest) {  
                request = (HttpServletRequest) obj;  
            }
		}
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
			t.setUser_id(((LogUser)request.getSession(false).getAttribute("LogUser")).getFdid());
			t.setFdid(getUUID());
			t.setActionName(currentMethod.getName());
			t.setHappen_time(null);
			t.setMessage((JSONObject) (JSONObject.toJSON((pjd.getArgs()[0]))));
			t.setMsg(obj.toString());
			iLogService.notice(t);
		} catch (Throwable e) {
			log.error("controller LogAspect:异常通知 ... , exception = " + e);
			e.printStackTrace();
		}
		return obj;
	}
}
