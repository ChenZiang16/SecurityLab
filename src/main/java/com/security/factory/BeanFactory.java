package com.security.factory;

import java.util.ArrayList;
import java.util.List;
import com.security.entry.CodeAndMsg;
import com.security.entry.Files;

public class BeanFactory {
	
	public static Files getFilesBean(){
		return new Files();
	}
	
	public static List<Files> getFilesListBean(){
		return new ArrayList<Files>();
	}
	
	public static CodeAndMsg getCodeAndMsgBean(){
		return new CodeAndMsg();
	}

}
