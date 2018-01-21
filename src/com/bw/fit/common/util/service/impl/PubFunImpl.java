package com.bw.fit.common.util.service.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Decoder.BASE64Decoder;

import com.bw.fit.common.model.RbackException;
import com.bw.fit.common.util.SmsSender;
import com.bw.fit.common.util.service.PubFun;
import com.bw.fit.system.dao.SystemDao;
import com.bw.fit.system.entity.TdataDict;

@Service
public class PubFunImpl implements PubFun {
	@Autowired
	private SystemDao systemDao;
	//应用过程中使用到的K-V配置,而非实施级别的配置。
	private static String fileName ="com/bw/fit/common/conf/keyValuePropConf" ;
	private static final double PI = 3.1415926535898;
	private static double EARTH_RADIUS = 6378.137;
	private static Log log = LogFactory.getLog(PubFun.class);
	@Override
	public String getUserPasswordShiro(String userName,String passwd,String hashalgorithmName,int iterations){
		String credentials = passwd;
		Object salt = ByteSource.Util.bytes(getValueByKey("user.pw.slogmm") + userName);
		Object result = new SimpleHash(hashalgorithmName,credentials,salt,iterations);
		return result.toString();
	}

	@Override
	public String getValueByKey(String key) {
		ResourceBundle rb=null;
		try {
			rb = ResourceBundle.getBundle(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return rb.getString(key);
	}


	@Override
	public String getSysDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	@Override
	public String getSysDateM() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return sdf.format(date);
	}

	@Override
	public String getTruncSysDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	


	@Override
	public String getUUID(boolean isContainMLine) {
		String s = UUID.randomUUID().toString();
		if (isContainMLine) {
			return s;
		}
		return s.replace("-", "");
	}

	@Override
	public String getUUID() {
		return getUUID(false);
	}
	

	@Override
	public int differentDays(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		int day1 = cal1.get(Calendar.DAY_OF_YEAR);
		int day2 = cal2.get(Calendar.DAY_OF_YEAR);

		int year1 = cal1.get(Calendar.YEAR);
		int year2 = cal2.get(Calendar.YEAR);
		if (year1 != year2) // 同一年
		{
			int timeDistance = 0;
			for (int i = year1; i < year2; i++) {
				if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
				{
					timeDistance += 366;
				} else // 不是闰年
				{
					timeDistance += 365;
				}
			}

			return timeDistance + (day2 - day1);
		} else // 不同年
		{
			log.info("判断day2 - day1 : " + (day2 - day1));
			return day2 - day1;
		}
	}

	@Override
	public boolean base64ToFile(String base64, String path) {
		byte[] buffer;
		try {
			buffer = Base64.getDecoder().decode(base64);
			FileOutputStream out = new FileOutputStream(path);
			out.write(buffer);
			out.close();
			return true;
		} catch (Exception e) {
			throw new RuntimeException("base64字符串异常或地址异常\n" + e.getMessage());
		}
	}

	@Override
	public String fileToBase64(String path) {
		File file = new File(path);
		FileInputStream inputFile;
		try {
			inputFile = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			inputFile.read(buffer);
			inputFile.close();
			return Base64.getEncoder().encodeToString(buffer);
		} catch (Exception e) {
			throw new RuntimeException("文件路径无效\n" + e.getMessage());
		}
	}
	
	
	@Override
	public Object resizeArray(Object oldArray, int newSize) {
		int oldSize = java.lang.reflect.Array.getLength(oldArray);
		Class elementType = oldArray.getClass().getComponentType();
		Object newArray = java.lang.reflect.Array.newInstance(elementType,
				newSize);
		int preserveLength = Math.min(oldSize, newSize);
		if (preserveLength > 0)
			System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
		return newArray;
	}
	@Override
	public boolean isImageFromBase64(String base64Str) {
		boolean flag = false;
		try {
			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(
					new BASE64Decoder().decodeBuffer(base64Str)));
			if (null == bufImg) {
				return flag;
			}
			flag = true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return flag;
	}
	
	@Override
	public void targetZoomOut(String sourcePath,int width,int length) { // 将目标图片缩小成256*256并保存
		File file1 = new File(sourcePath); // 用file1取得图片名字
		String name = file1.getName();
		try {
			BufferedImage input = ImageIO.read(file1);
			Image big = input.getScaledInstance(256, 256, Image.SCALE_DEFAULT);
			BufferedImage inputbig = new BufferedImage(256, 256,
					BufferedImage.TYPE_INT_BGR);
			inputbig.getGraphics().drawImage(input, 0, 0, width, length, null); // 画图

			ImageIO.write(inputbig, "jpg", new File(getValueByKey("system.attachment_path")
					+ name)); // 将其保存在C:/imageSort/targetPIC/下
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public boolean getEmailIsOpen(String email_dict_fdid) {
		List<TdataDict> list = systemDao.getDataDictList(email_dict_fdid);
		if(list==null){
			return false;
		}
		if(list.size()>1){
			return false;
		}
		if(list.get(0).getDict_name().equalsIgnoreCase("OPEN")){
			return true;
		}else{
			return false ;
		}
	}

	@Override
	public boolean getSMSIsOpen(String sms_dict_fdid) {
		List<TdataDict> list = systemDao.getDataDictList(sms_dict_fdid);
		if(list==null){
			return false;
		}
		if(list.size()>1){
			return false;
		}
		if(list.get(0).getDict_name().equalsIgnoreCase("OPEN")){
			return true;
		}else{
			return false ;
		}
	}

	@Override
	public void sendSMSString(String phone, String content)
			throws RbackException {
		if(getSMSIsOpen("fe6261a8c88c4d54ba97302b182742ee")){
			SmsSender.SendSMSString(phone, content);
		}else{
			return ;
		}
	}
	
}
