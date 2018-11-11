package com.security.utils;

import java.security.MessageDigest;

public class MD5Utils {
	
	//加密
	public static String encodeByMd5(String str){
		String md5 = "";  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  // 创建一个md5算法对象  
            byte[] messageByte = str.getBytes("UTF-8");  
            byte[] md5Byte = md.digest(messageByte);              // 获得MD5字节数组,16*8=128位  
            md5 = bytesToHex(md5Byte);                            // 转换为16进制字符串  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return md5;  
	}
	
	//无法解密,只能将输入值加密与之前加密值比较
	public boolean varifyPwd(String newStr,String oldStr){
		if(encodeByMd5(newStr).equals(oldStr)){
			return true;
		}
		return false;
	}
	
    // 二进制转十六进制  
    public static String bytesToHex(byte[] bytes) {  
        StringBuffer hexStr = new StringBuffer();  
        int num;  
        for (int i = 0; i < bytes.length; i++) {  
            num = bytes[i];  
             if(num < 0) {  
                 num += 256;  
            }  
            if(num < 16){  
                hexStr.append("0");  
            }  
            hexStr.append(Integer.toHexString(num));  
        }  
        return hexStr.toString().toUpperCase();  
    }  

}
