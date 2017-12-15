package com.bw.fit.log.dao;

import java.util.List;

import com.bw.fit.log.entity.TLogInfo;

/*****
 * 日志组件DAO层
 * @author yangh
 *
 */
public interface DLogDao {
	/******
	 * 录入日志消息
	 */
	public void notice(TLogInfo l);
	/*****
	 * 根据id查询日志信息
	 * @param fdid
	 * @return
	 */
	public TLogInfo getLogInfoById(String fdid);
	/******
	 * 	根据日志的  level/user_id/menu_id/res数据
	 *  查询出记录fdid的list
	 * @param l
	 * @return 返回 日志记录fdid的list
	 */
	public List<String> getFdidByInfo(TLogInfo l);


}
