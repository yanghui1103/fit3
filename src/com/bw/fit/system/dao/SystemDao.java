package com.bw.fit.system.dao;

import java.util.List;

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
	/****
	 * 
	 * @param c
	 * @return
	 */
	public List<TdataDict> getDataDictList(String parent_id);
	
}
