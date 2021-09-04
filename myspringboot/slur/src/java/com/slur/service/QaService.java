package com.slur.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slur.dao.QaDAO;
import com.slur.dto.Notice;
import com.slur.dto.Qa;
import com.slur.dto.Review;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;
import com.slur.exception.RemoveException;

@Service
public class QaService {
	@Autowired
	private QaDAO dao;
	
	public void qaWrite(Qa q) throws AddException{
		dao.insertQA(q);
	}
	
	public void qaMoidfy(Qa q) throws ModifyException{
		dao.updateQa(q);
	}
	
	public void qaDelete(int qa_num) throws RemoveException{
		dao.deleteQa(qa_num);
	}
	
	public List<Qa> qaSelectAll(int pageNo, int amount) throws FindException{
		return dao.selectAll(pageNo, amount);
	}
	
	public Qa qaSelectInfo(int qa_num) throws FindException{
		return dao.selectByInfo(qa_num);
	}
	
	public List<Qa> qaAll() throws FindException{
		return dao.selectAll();
	}

	
}
