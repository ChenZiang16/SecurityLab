package com.security.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.security.dao.DocumentDao;
import com.security.entry.Document;
import com.security.service.DocumentService;
@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentDao documentdao;

	@Override
	public List<Document> getDocList(String userId) {
		return documentdao.getDocList(userId);
	}

	@Override
	public int insertDoc(Document document) {
		return documentdao.insertDoc(document);
	}

	@Override
	public Document getDoc(Document doc) {
		return documentdao.getDoc(doc);
	}

	@Override
	public Integer countDoc() {
		return documentdao.countDoc();
	}

}
