package com.security.dao;

import java.util.List;
import com.security.entry.Document;

public interface DocumentDao {
	List<Document> getDocList(String userId);
	Document getDoc(Document doc);
	int insertDoc(Document document);
	Integer countDoc();
}
