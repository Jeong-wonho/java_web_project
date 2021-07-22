package com.day.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import com.day.dao.BookmarkDAO;
import com.day.dto.Bookmark;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.RemoveException;

public class BookmarkService {
	private BookmarkDAO dao;
	private static BookmarkService service;
	public static String envProp;	
	private BookmarkService() {
		Properties env = new Properties();
		try {	
			//env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("BookmarkDAO");
			Class c = Class.forName(className);	 // JVM에 로드
			dao = (BookmarkDAO) c.newInstance(); // 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static BookmarkService getInstance() {
		if(service==null) {
			service = new BookmarkService();
		}
		return service;
	}
	/**
	 * 관심상품 버튼 클릭시 디비에 등록
	 * @param bm
	 * @throws AddException
	 */
	public void insert(Bookmark bm) throws AddException{
		dao.insert(bm);
	}
	/**
	 * 체크된 관심상품을 다시 클릭시 해당 북마크 넘버 등록글을 삭제한다
	 * 
	 * @param bm_num
	 * @throws RemoveException
	 */
	public void delete(int bm_num) throws RemoveException{
		dao.delete(bm_num);
	}
	/**
	 * 해당 아이디의 관심상품등록 전체를 리스트로 가져온다
	 * @param bm_id
	 * @return
	 * @throws FindException
	 */
	public List<Bookmark> findAll(String bm_id) throws FindException{
		return dao.selectAll(bm_id);
		
	}
	
	
	
}
