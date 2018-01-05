package com.bw.fit.system.controller;

import static com.bw.fit.common.util.PubFun.copyProperties;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.controller.BaseController;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.entity.TtoDo;
import com.bw.fit.system.entity.TtoRead;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.ToDo;
/*******
 * 系统管理Plus
 * Controller
 * @author yangh
 *
 */
@RequestMapping("systemPlus")
@Controller
public class SystemPlusController extends BaseController {

	@Autowired
	private SystemDao systemDao ;
	
	@RequestMapping("todolist/{params}")
	@ResponseBody
	public JSONObject todolist(@ModelAttribute ToDo toDo,@PathVariable String params){
		JSONObject json = new JSONObject();
		TtoDo td = new TtoDo();
		copyProperties(td, toDo);
		td.setPaginationEnable("1");
		List<TtoDo> list = systemDao.getToDoList(td);
		td.setPaginationEnable("0");
		List<TtoDo> listTotal = systemDao.getToDoList(td);
		if (listTotal != null && listTotal.size() > 0) {
			json.put("total", listTotal.size());
		} else {
			json.put("total", 0);
		}
		json.put("rows", JSONObject.toJSON(list));
		return json;
	
	}
	@RequestMapping("toreadlist/{params}")
	@ResponseBody
	public JSONObject toreadlist(@ModelAttribute TtoRead toRead,@PathVariable String params){
		JSONObject json = new JSONObject();
		TtoRead td = new TtoRead();
		copyProperties(td, toRead);
		td.setPaginationEnable("1");
		List<TtoRead> list = systemDao.getToReadList(td);
		td.setPaginationEnable("0");
		List<TtoRead> listTotal = systemDao.getToReadList(td);
		if (listTotal != null && listTotal.size() > 0) {
			json.put("total", listTotal.size());
		} else {
			json.put("total", 0);
		}
		json.put("rows", JSONObject.toJSON(list));
		return json;
	
	}
	
	
}
