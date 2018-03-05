package com.bw.fit.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;

import redis.clients.jedis.Jedis;

public class Student implements Serializable {
	public static byte[] objectToByte(Student st) {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] bytes = null;
		try {
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(st);
			bytes = bo.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	public Student() {
		name = "xiaoming";
	}
	public static void  copyFile(File fromFile,File toFile) throws IOException{
        FileInputStream ins = new FileInputStream(fromFile);
        FileOutputStream out = new FileOutputStream(toFile);
        byte[] b = new byte[1024];
        int n=0;
        while((n=ins.read(b))!=-1){
            out.write(b, 0, n);
        }
        
        ins.close();
        out.close();
    }
	public static void main(String[] args) throws Exception {

		Student st = new Student();
//		File file = new File("d://person.txt");  
//
//        //序列化持久化对象
//        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));   
//        out.writeObject(st);  
//        out.close();  


		File file2 = new File("d://ppppp.txt");
//		copyFile(file,file2);
		
        //反序列化，并得到对象
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file2));  
        Object newPerson = in.readObject(); // 没有强制转换到Person类型  
        in.close();  
        System.out.println(newPerson);  
        System.out.println(((Student)newPerson).name);
        

	}

}
