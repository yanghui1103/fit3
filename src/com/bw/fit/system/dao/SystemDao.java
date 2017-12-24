package com.bw.fit.system.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.model.RbackException;
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
	 * 获取父节点下所有子孙节点
	 * @param c
	 * @return
	 */
	public List<TdataDict> getDataDictList(String parent_id);
	/****
	 * 此节点信息
	 * @param fdid
	 * @return
	 */
	public TdataDict getThisDataDictInfo(String fdid);
	
	public void deleteDict(String fdid) throws RbackException;
	
	
}
