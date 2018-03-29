package com.bw.fit.common.util;

import org.springframework.stereotype.Component;
 
@Component(value="beanFactory")
public class BeanFactory {

	/*****
	 * 根据类名[含包名]，工厂模式创建一个该类的实例对象
	 * @param ClassFullName
	 * @return
	 * @throws Exception
	 */
	public Object getNewClassInstance(String ClassFullName) throws Exception{
		Class<?> clazz = Class.forName(ClassFullName);		
		return clazz.newInstance() ;
	} 
}
