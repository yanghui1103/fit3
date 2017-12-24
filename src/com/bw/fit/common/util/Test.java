package com.bw.fit.common.util;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.util.treeHandler.JsonTreeHandler;
import com.bw.fit.system.model.DataDict;

public class Test { 
	public static void main(String[] args) throws Exception, Exception, Throwable{ 
		
		String s = "[{fdid : '1111111111', dict_value : 'DATADD', dict_remark : 'null', can_add : '1', can_edit : '0', can_del	 : '0', num	 : '0', parent_id	 : '', dict_name :'数据字典', children : [{fdid : '100022', dict_value : 'ORGTYPE', dict_remark : 'null', can_add : '1', can_edit : '0', can_del	 : '0', num	 : '1', parent_id	 : '1111111111', dict_name :'组织类型', children : [{fdid : 'sds', dict_value : 'ORGTYPE01', dict_remark : 'null', can_add : '0', can_edit : '1', can_del	 : '0', num	 : '2', parent_id	 : '100022', dict_name :'营业组织', leaf : true},{fdid : '2', dict_value : 'ORGTYPE02', dict_remark : 'null', can_add : '0', can_edit : '0', can_del	 : '0', num	 : '3', parent_id	 : '100022', dict_name :'管理组织', leaf : true}]},{fdid : '123123d3', dict_value : 'DATALEVEL', dict_remark : '0', can_add : '0', can_edit : '0', can_del	 : '0', num	 : '4', parent_id	 : '1111111111', dict_name :'数据权限', children : [{fdid : '13344', dict_value : 'currents', dict_remark : '0', can_add : '0', can_edit : '0', can_del	 : '0', num	 : '5', parent_id	 : '123123d3', dict_name :'本级可见', leaf : true},{fdid : '13344e', dict_value : 'thiss', dict_remark : 'null', can_add : '0', can_edit : '1', can_del	 : '0', num	 : '6', parent_id	 : '123123d3', dict_name :'本级及子组织可见', leaf : true},{fdid : '133444', dict_value : 'alls', dict_remark : 'null', can_add : '0', can_edit : '0', can_del	 : '0', num	 : '7', parent_id	 : '123123d3', dict_name :'全组织可见', leaf : true}]},{fdid : '3223r', dict_value : 'OPPTYPE', dict_remark : 'null', can_add : '1', can_edit : '1', can_del	 : '0', num	 : '8', parent_id	 : '1111111111', dict_name :'相对方类型', children : [{fdid : '0497ecd9d95c4cdabb044298bf5dff02', dict_value : 'supplyer', dict_remark : 'null', can_add : '1', can_edit : '1', can_del	 : '1', num	 : '1', parent_id	 : '3223r', dict_name :'供应商', leaf : true},{fdid : '5a9bbedb6067439390dea6d0575570f9', dict_value : 'customer', dict_remark : 'null', can_add : '1', can_edit : '0', can_del	 : '1', num	 : '2', parent_id	 : '3223r', dict_name :'客户', leaf : true}]},{fdid : '12312d', dict_value : 'OppSource', dict_remark : '0', can_add : '0', can_edit : '0', can_del	 : '0', num	 : '9', parent_id	 : '1111111111', dict_name :'相对方数据来源', children : [{fdid : '4b740d9e7a624ce09114982a5c45833c', dict_value : 'hts', dict_remark : 'null', can_add : '1', can_edit : '1', can_del	 : '0', num	 : '1', parent_id	 : '12312d', dict_name :'合同管理系统', leaf : true},{fdid : '6ccc1567ff994b3fbf0561a54a310483', dict_value : 'its', dict_remark : 'null', can_add : '1', can_edit : '0', can_del	 : '0', num	 : '2', parent_id	 : '12312d', dict_name :'IT项目管理系统手录', leaf : true}]},{fdid : 'pm12312d', dict_value : 'pm_type', dict_remark : '0', can_add : '0', can_edit : '0', can_del	 : '0', num	 : '10', parent_id	 : '1111111111', dict_name :'项目类型', children : [{fdid : '09e0971a7d344b78838ad70b2bd2ba31', dict_value : 'xtjc', dict_remark : 'null', can_add : '1', can_edit : '0', can_del	 : '0', num	 : '1', parent_id	 : 'pm12312d', dict_name :'系统集成', leaf : true},{fdid : '9fbf5e808480408fb4325a69718a2294', dict_value : 'rjkf', dict_remark : 'null', can_add : '1', can_edit : '0', can_del	 : '0', num	 : '2', parent_id	 : 'pm12312d', dict_name :'软件开发', leaf : true},{fdid : 'c2f34329004f44bc839ab2633271fc48', dict_value : 'zxfw', dict_remark : 'null', can_add : '1', can_edit : '1', can_del	 : '0', num	 : '3', parent_id	 : 'pm12312d', dict_name :'咨询实施', leaf : true},{fdid : '2b2d27d4ad714d89b1c825fdbd0aef3b', dict_value : 'ywfw', dict_remark : 'null', can_add : '1', can_edit : '0', can_del	 : '0', num	 : '4', parent_id	 : 'pm12312d', dict_name :'运维服务', leaf : true}]},{fdid : 'ct12312d', dict_value : 'Complete_result_type', dict_remark : '0', can_add : '0', can_edit : '0', can_del	 : '0', num	 : '21', parent_id	 : '1111111111', dict_name :'项目结束结论类型', children : [{fdid : '0a723cb67da54bc985da56471dc845e4', dict_value : '2', dict_remark : 'null', can_add : '1', can_edit : '1', can_del	 : '0', num	 : '1', parent_id	 : 'ct12312d', dict_name :'符合最终验收', leaf : true}]}]}]";
		
		System.out.println(s.replace("fdid", "id"));
	}
	 
}
