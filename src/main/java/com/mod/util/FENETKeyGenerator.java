package com.mod.util;

import java.text.SimpleDateFormat;
import java.util.Date;



public final class FENETKeyGenerator {

	/**
	 * @return 20 number char
	 */
	public synchronized final static String getKey(){
		long l = System.currentTimeMillis();
		double d = Math.random();
		StringBuffer sb = new StringBuffer();
		sb.append(d);
		sb.delete(0,sb.length()-7);
		sb.insert(0,l);
		return sb.toString();
	}

	public synchronized final static String getKey(String prefix, String suffix){
		StringBuffer sb = new StringBuffer();
		sb.append(prefix);
		sb.append(getKey());
		sb.append(suffix);
		return sb.toString();
	}

	public synchronized final static String getCustomKey(int i){
		long l = System.currentTimeMillis();
		
		StringBuffer sb = new StringBuffer();
		sb.append(i);
		while ( sb.length()<7 ){
			sb.insert(0, "0");
		}
		sb.insert(0,l);
		return sb.toString();
	}

	public synchronized final static String getZoneString(int i, int size){
		StringBuffer sb = new StringBuffer();
		sb.append(i);
		
		return null;
	}


	
	public synchronized final static String getTzKey(){
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");   
		Date date = new Date();   
		String str = sd.format(date);
		double d = Math.random();
		StringBuffer sb = new StringBuffer();
		sb.append(d);
		sb.delete(0,sb.length()-5);
		sb.insert(0,str);
		return "TZ" + sb.toString();
	}
}
