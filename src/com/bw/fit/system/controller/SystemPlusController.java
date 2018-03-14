package com.bw.fit.system.controller;

import static com.bw.fit.common.util.PubFun.copyProperties;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.controller.BaseController;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.entity.TtoDo;
import com.bw.fit.system.entity.TtoRead;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.ToDo;
import com.bw.fit.system.model.ToRead;
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
	
	/*****
	 * 待办列表
	 * @param toDo
	 * @param params
	 * @return
	 */
	@RequestMapping("todolist/{status}")
	@ResponseBody
	public JSONObject todolist(@ModelAttribute ToDo toDo,@PathVariable String status){
		JSONObject json = new JSONObject();
		TtoDo td = new TtoDo();
		copyProperties(td, toDo);
		toDo.setStatus(status);
		td.setPaginationEnable("1");
		List<TtoDo> list = systemDao.getToDoList(td);
		for(TtoDo tt:list){
			tt.setStatus(systemDao.getDictByValue(tt.getStatus()).getDict_name());
		}
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
	/*****
	 * 待阅列表
	 * @param toRead
	 * @param params
	 * @return
	 */
	@RequestMapping("toreadlist/{status}")
	@ResponseBody
	public JSONObject toreadlist(@ModelAttribute ToRead toRead,@PathVariable String status){
		JSONObject json = new JSONObject();
		TtoRead td = new TtoRead();
		toRead.setStatus(status);
		copyProperties(td, toRead);
		td.setPaginationEnable("1");
		List<TtoRead> list = systemDao.getToReadList(td);
		for(TtoRead tt:list){
			tt.setStatus(systemDao.getDictByValue(tt.getStatus()).getDict_name());
		}
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
	
	/******
	 * 打开待办页面,如果当前用户为当前办理人，则可以办理
	 * @param fdid 记录ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toDo/{fdid}",method=RequestMethod.GET)
	public String getTododetail(@PathVariable String fdid,Model model){
		TtoDo dog = new TtoDo();
		dog.setFdid(fdid);
		model.addAttribute("toDo", systemDao.getToDoDetail(dog));
		return "system/todo/toDoDealPage";
	}

	/******
	 * 打开待阅页面,如果当前用户为当前待阅的接收人，则可以办理
	 * @param fdid 记录ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value="toRead/{fdid}",method=RequestMethod.GET)
	public String gettoReaddetail(@PathVariable String fdid,Model model){
		TtoRead dog = new TtoRead();
		dog.setFdid(fdid);
		model.addAttribute("toRead", systemDao.getToReadDetail(dog));
		
		return "system/toread/toReadDealPage";
	}
	
}
