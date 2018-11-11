package com.security.service;

import java.util.List;
import com.security.entry.Document;

public interface DocumentService {
	List<Document> getDocList(String userId);
	Document getDoc(Document doc);
	int insertDoc(Document document);
	Integer countDoc();
	
}
