package com.bw.fit.common.util.service;

import java.util.Date;

/****
 * 系统级工具组件 
 * @version v3.0
 * @author yangh
 *
 */
public interface PubFun {
	/****
	 * 根据shrio加密方式
	 * 得到的密文
	 * @param userName
	 * @param passwd
	 * @param hashalgorithmName 加密方式
	 * @param iterations 加密次数
	 * @return
	 */
	public String getValueByKey(String key);
	/*****
	 * 获取应用过程级
	 * 根据K获取Value
	 */
	public String getUserPasswordShiro(String userName,String passwd,String hashalgorithmName,int iterations);
	/****
	 * 到秒
	 */
	public String getSysDate();
	/*****
	 * 到分钟
	 */
	public String getSysDateM() ;
	/****
	 * 到日期
	 */
	public String getTruncSysDate();
	public String getUUID();
	public String getUUID(boolean isContainMLine);
	/**
	 * date2比date1多的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public int differentDays(Date date1, Date date2) ;

	/**
	 * base64编码
	 * 转文件
	 * 
	 * @param base64 编码
	 * @param path
	 * @return bool
	 */
	public boolean base64ToFile(String base64, String path);
	/**
	 * 文件
	 * 转base64
	 * 
	 * @param path
	 * @return base64
	 */
	public String fileToBase64(String path) ;

	/*****
	 * 将一个数据扩充长度 只能比原来的长度更长
	 * 
	 * @param oldArray
	 * @param newSize
	 * @return
	 */
	public Object resizeArray(Object oldArray, int newSize) ;
	/*****
	 * 判断ImageFromBase64
	 * @param base64Str
	 * @return
	 */
	public boolean isImageFromBase64(String base64Str);
	/******
	 * 把目标文件缩/扩成一个新文件，JPG格式
	 * @param sourcePath 目标文件的路径
	 * @param width  宽
	 * @param length 高
	 */
	public void targetZoomOut(String sourcePath,int width,int length) ;
	
}
