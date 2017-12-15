package com.bw.fit.common.log.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static com.bw.fit.common.util.PubFun.*;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.bw.fit.common.dao.DaoTemplete;

/*****
 * 消息队列产品发送数据/消费数据的日志切面
 * 
 * @author yangh
 *
 */
@Component
public class MqLogAspect implements Ordered {
	@Autowired
	private DaoTemplete daoTemplete;

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 2;
	}

	public Object aroundMethod(ProceedingJoinPoint pjd) {
		// String name = pjd.getSignature().getName();
//		Signature sig = pjd.getSignature();
//		MethodSignature msig = null;
//		if (!(sig instanceof MethodSignature)) {
//			throw new IllegalArgumentException("该注解只能用于方法");
//		}
//		msig = (MethodSignature) sig;
//		Object target = pjd.getTarget();
//		Method currentMethod = target.getClass().getMethod(msig.getName(),
//				msig.getParameterTypes());
//		currentMethod.getName()
		List<Object> list = Arrays.asList(pjd.getArgs());
		Object obj = null;
		try {
			obj = pjd.proceed(); // 执行
			// if("CommonModel".equalsIgnoreCase(((pjd.getArgs()[0]).getClass().getSimpleName()))){
			//
			// for(Object o:list){
			// CommonModel c = (CommonModel)o;
			// c.setLogId(getUUID());
			// ObjectMapper maper = new ObjectMapper();
			// String jsonlist = maper.writeValueAsString(c);
			// c.setLogContent(jsonlist);
			// c.setReturnInfo(obj!=null?obj.toString():"-9"); // 返回json传入
			// commonDao.insert("systemSql.logOperation", c);
			// }
			// }
		} catch (Throwable e) {
			System.out.println("mq LogAspect:异常通知 ... , exception = " + e);
			e.printStackTrace();
		}
		return obj;
	}
}
