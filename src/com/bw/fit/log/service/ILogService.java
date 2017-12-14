package com.bw.fit.log.service;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.log.entity.TLogInfo;

/*****
 * 日志组件
 * @author yangh
 *
 */
public interface ILogService {
	/******
	 * 录入日志
	 * @param level  
	 */
	public void notice(TLogInfo l);
	/*****
	 * 根据id查询日志信息
	 * @param fdid
	 * @return
	 */
	public TLogInfo getLogInfoById(String fdid);
}
