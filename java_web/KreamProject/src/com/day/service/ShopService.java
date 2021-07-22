package com.day.service;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import com.day.dao.ShopDAO;
import com.day.dto.Shop;
import com.day.exception.FindException;

public class ShopService {
	private ShopDAO dao;
	private static ShopService service;
	public static String envProp;	
	private ShopService() {
		Properties env = new Properties();
		try {	
//			env.load(new FileInputStream("classes.prop"));
			env.load(new FileInputStream(envProp));
			String className = env.getProperty("ShopDAO");
			Class c = Class.forName(className);	 // JVM에 로드
			dao = (ShopDAO) c.newInstance(); // 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static ShopService getInstance() {
		if(service == null) {
			service = new ShopService();
		}
		return service;
	}
	
	/**
	 * 
	 * @param id 북마크 표시를 위한 id , id가 없을 경우 빈객체 또는 null이라도 넘겨줘야 정상 작동함!
	 * 		 새로운 메서드를 만들지 않기 위해서 selectAll method안에서 if문으로 처리 하였습니다.
	 * @return 전체 상품정보를 반환합니다.
	 * @throws FindException
	 */
	public List<Shop> findAll(String id) throws FindException{
		return dao.selectAll(id);
		
	}
	
	/**
	 * 
	 * @param id 북마크 표시를 위한 id , id가 없을 경우 빈객체 또는 null이라도 넘겨줘야 정상 작동함!
	 * 		 새로운 메서드를 만들지 않기 위해서 selectAll method안에서 if문으로 처리 하였습니다.
	 * @param prod_name 상품 브랜드와 컬렉션으로 검색합니다. sql like함수를 사용해서 이름으로 검색하게 처리
	 * @return 해당 브랜드나 컬렉션 이름을 가진 데이터를 불러옵니다.
	 * @throws FindException
	 */
	public List<Shop> findByName(String id, String prod_name) throws FindException{
		return dao.selectByName(id, prod_name);
	}
	
	/**
	 * 
	 * @param id 북마크 표시를 위한 id , id가 없을 경우 빈객체 또는 null이라도 넘겨줘야 정상 작동함!
	 * 		 새로운 메서드를 만들지 않기 위해서 selectAll method안에서 if문으로 처리 하였습니다.
	 * @param gender 성별에 따라 상품을 구분합니다.
	 * @return
	 * @throws FindException
	 */
//	public List<Shop> findByGender(String id, String[] gender) throws FindException{
//		return dao.selectByGender(id, gender);
//	}
	
	/**
	 * 
	 * @param id를넘겨주어 가장 최근 발매일 상위 4개를 불러오는 메서드 메인페이지에서 사용!
	 * 	
	 * @return
	 * @throws FindException
	 */
	public List<Shop> findByReleaseddt(String id) throws FindException{
		return dao.selectByReleaseddt(id);
		
	}
	
	public List<Shop> findByList(String id,String[] brand) throws FindException{
		return dao.selectByList(id, brand);
	}
	
}
