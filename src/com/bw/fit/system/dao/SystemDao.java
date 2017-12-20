package com.bw.fit.system.dao;

import com.bw.fit.system.entity.TdataDict;

/****
 * 系统基础持久层
 * @author yangh
 *
 */
public interface SystemDao {

	/****
	 * 根据值获取数据字典对象
	 * @param dict_value
	 * @return
	 */
	public TdataDict getDictByValue(String dict_value);
	
}
