package com.bw.fit.system.controller;

import static com.bw.fit.common.util.PubFun.copyProperties;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bw.fit.common.controller.BaseController;
import com.bw.fit.common.model.BaseModel;
import com.bw.fit.common.model.RbackException;
import com.bw.fit.common.util.BeanFactory;
import com.bw.fit.common.util.PropertiesUtil;
import com.bw.fit.common.util.PubFun;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.entity.TAttachment;
import com.bw.fit.system.entity.Tcompany;
import com.bw.fit.system.entity.TdataDict;
import com.bw.fit.system.entity.TtoDo;
import com.bw.fit.system.entity.TtoRead;
import com.bw.fit.system.entity.Tuser;
import com.bw.fit.system.model.Attachment;
import com.bw.fit.system.model.Company;
import com.bw.fit.system.model.ToDo;
import com.bw.fit.system.model.ToRead;
import com.bw.fit.system.model.User;
import com.bw.fit.system.service.SystemService;
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
	@Autowired
	private SystemService systemService ;
	@Resource(name="beanFactory") 
	private BeanFactory beanFactory;
	
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
	
	/******
	 * 根据外键ID,查询其附件列表，并可以约束附件类型
	 * @param foreignId
	 * @param fileType 文件类型，以逗号分割
	 * @param auth 2 读写兼有，1，只有读权限
	 * @param model
	 * @return
	 */
	@RequestMapping("openAttachmentPage/{foreignId}/{fileType}/{auth}")
	public String openAttachmentPage(@PathVariable(value="foreignId") String foreignId,
			@PathVariable(value="auth") String auth,
				@PathVariable(value="fileType") String fileType,Model model	){
		model.addAttribute("foreignId", foreignId);
		Session session = PubFun.getCurrentSession();
		String user_id = ((User) session.getAttribute("CurrentUser")).getFdid();
		model.addAttribute("user_id", user_id);
		if("-9".equals(fileType)){
			// 文件格式不限
			model.addAttribute("fileType", "*");
		}else{
			StringBuffer sb = new StringBuffer();
			String[] fts = fileType.split(",");
			Map fileTypeMaps = new LinkedHashMap<>();
			fileTypeMaps.put("jpg", "image/jpeg");
			fileTypeMaps.put("doc", "application/msword");
			fileTypeMaps.put("jpeg", "image/jpeg");
			fileTypeMaps.put("xls", "application/vnd.ms-excel");
			fileTypeMaps.put("ppt", "application/vnd.ms-powerpoint");
			for(String s :fts){
				if(fileTypeMaps.containsKey(s)){
					sb.append(fileTypeMaps.get(s));
					sb.append(",");
				}
			}
			if(sb == null){
				model.addAttribute("fileType", "*");
			}else{
				String patten = sb.toString();
				model.addAttribute("fileType",patten.substring(0, patten.length()-1));
			}
		}
		/****
		 * auth
		 */
		model.addAttribute("auth", auth);
		
		return "system/attachment/attachmentListPage";
	}
	
	@RequestMapping(value="upLoadAttachmentFile",produces = "application/json; charset=utf-8")
	@ResponseBody
	public JSONObject upLoadAttachmentFile(@RequestParam(value="file", required=false) MultipartFile file,
			@RequestParam(value="foreignId") String foreignId)throws Exception  {
		JSONObject json = new JSONObject(); 
		Session session = PubFun.getCurrentSession();
		User us_first = (User)session.getAttribute("CurrentUser");
		if(file == null){
			json = new JSONObject();
			json.put("msg", "导入异常,excel文件缺失");
			json.put("res", "1");
			return (json); 
		}else{ 
			try {
				String realPath =PropertiesUtil.getValueByKey("system.attachment_path");
				String fileOriginalName = file.getOriginalFilename();
                String suffix = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1);
                String newFileName = PubFun.getUUID()+"."+suffix ;
				//这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, newFileName));
                Attachment a = (Attachment)beanFactory.getNewClassInstance("com.bw.fit.system.model.Attachment");
                PubFun.fillCommonProptities(a, true);
                a.setFile_name(newFileName);
                a.setBefore_name(fileOriginalName);
                a.setPath(realPath);
                a.setForeign_id(foreignId);
                a.setFile_size(String.valueOf(file.getSize()/1024/1024)+"MB");
                json = systemService.createNewAttachment(a);
			} catch (RbackException ex) {
				ex.printStackTrace();
				json = new JSONObject();
				json.put("msg", "导入异常,"+ex.getMsg());
				json.put("res", "1"); 
			} finally{
				return (json);
			}
		} 
	}
	
	
	@RequestMapping("attachmentList/{foreignid}")
	@ResponseBody
	public JSONObject attachmentList(@PathVariable("foreignid") String foreign_id,
			Model model,   HttpServletRequest request) {
		JSONObject json = new JSONObject(); 
		TAttachment c = new TAttachment();
		c.setForeign_id(foreign_id);
		List<TAttachment> listTotal = systemDao.getAttachmentList(c);
		if (listTotal != null && listTotal.size() > 0) {
			json.put("total", listTotal.size());
		} else {
			json.put("total", 0);
		}
		json.put("rows", JSONObject.toJSON(listTotal));
		return json;
	}
	
	
	@RequestMapping("deleteAttachment/{fdid}")
	@ResponseBody
	public JSONObject deleteAttachment(@PathVariable("fdid") String fdid,
			   HttpServletRequest request) {
		Session session = PubFun.getCurrentSession();
		User user = (User)session.getAttribute("CurrentUser");
		JSONObject json = new JSONObject(); 
		Attachment c = new Attachment();
		c.setCreator(user.getFdid());
		c.setFdid(fdid);
		try{
			json = systemService.deleteAttachment(c);
		}catch(RbackException ex){
			json = new JSONObject();
			json.put("res", "1");
			json.put("msg", ex.getMsg());			
		}finally{
			return json;
		}
	}
	/****
	 * 下载附件
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("download/{fdid}")    
    public ResponseEntity<byte[]> download(@PathVariable String fdid,HttpServletRequest request) throws IOException {    
    	TAttachment ta = systemDao.getAttachment(fdid);

        String path="";
    	if(ta!=null){ 
    		path =   request.getServletContext().getRealPath("") +"/upLoadFiles/"+ta.getFile_name();
    	}else{
    		return null ;
    	}
        File file=new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        String fileName=new String(ta.getBefore_name());
        //String fileName=new String(ta.getBefore_name().getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);    
    }    
}
