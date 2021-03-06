import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.day.dto.Customer;
import com.day.dto.OrderInfo;
import com.day.dto.OrderLine;
import com.day.dto.Product;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession(); //Connection과 Session 의 기능이 같다.
			// ---------------한값받아오기-------------------
			Product p = session.selectOne("com.day.dto.ProductMapper.selectByNo", "C0001"); //한가지만 호출될 경우
			System.out.println(p);
			
			//--------------여러값 받아오기-----------------------
			//List<Product> list = session.selectList("com.day.dto.ProductMapper.selectAll"); //여러행이 표출될경우!
			//System.out.println(list);
			
			//----------------여러파라미터값 받아오기---------------
//			HashMap<String, Integer> map = new HashMap<>();
//			map.put("currentPage", 2); //보여줄페이지는 2페이지
//			map.put("cnt_per_page", 4); //페이지별 보여줄 목록수 4건
//			List<Product> listCurrentPage = session.selectList("com.day.dto.ProductMapper.selectAllPage", map);	
//			//System.out.println(listCurrentPage);
//			for(Product p1: listCurrentPage) {
//				System.out.println(p1);
//			}
			//-------------상품이름으로 부르기----------------------
//			List<Product> listName = session.selectList("com.day.dto.ProductMapper.selectByName", "아");
//			HashMap<String, String> map1 = new HashMap<>();
//			map1.put("word",  "리");
//			map1.put("ordermethod",  "prod_name DESC");
//			List<Product> listName = session.selectList("com.day.dto.ProductMapper.selectByName", map1);
//			System.out.println(listName);
			
			//-------------insert문 실험--------------------
//			System.out.println("고객 추가");
//			HashMap<String, Object> mapCustomer = new HashMap<>();
//			mapCustomer.put("id", "id11");
//			mapCustomer.put("pwd", "pwd11");
//			mapCustomer.put("name", "이름11");
//			mapCustomer.put("buildingno", "1");
//			session.insert("com.day.dto.CustomerMapper.insert", mapCustomer);
			
//			Customer c = new Customer();
//			c.setId("id11");
//			c.setPwd("p111");
//			c.setName("이름11");
//			c.setBuildingno("1");
//			session.insert("com.day.dto.CustomerMapper.insert", c);
//			session.commit(); // mybatis 는  auto commit이 안된다.
			
			//-----------------------고객정보수정--------------
//			System.out.println("고객 정보 수정");
//			Customer c1 = new Customer();
//			c1.setId("id10");
//			c1.setEnabled(0); //탈퇴처리
//			c1.setPwd("pupd100"); //비밀번호변경
//			c1.setName("nupd100");
//			int rowcnt = session.update("com.day.dto.CustomerMapper.update", c1);

//			if(rowcnt == 1) {
//				System.out.println("id추가");
//			}else if(rowcnt < 1){
//			System.out.println("id추가안됨");
//		}
//			session.commit();			
			//--------고객정보삭제
//			System.out.println("고객 정보 삭제");
//			String id = "id11";
//			int rowcnt = session.delete("com.day.dto.CustomerMapper.delete", id);
//			if(rowcnt==1) {
//				System.out.println(id+"가 삭제됨");
//			}else if(rowcnt < 1) {
//				System.out.println(id+"가 없어서 삭제 안됨");
//			}
//			Customer c = session.selectOne("com.day.dto.CustomerMapper.selectById", "id1");
//			System.out.println(c);
			
			OrderInfo info1 = new OrderInfo();
			Customer order_c = new Customer();
			order_c.setId("id1");
			info1.setOrder_c(order_c);
			
			List<OrderLine> lines = new ArrayList<>();
			
			OrderLine line = new OrderLine();
			Product order_p = new Product();
			order_p.setProd_no("G0001");
			line.setOrder_quantity(10);
			line.setOrder_p(order_p);
			lines.add(line);
			
			
//			OrderLine line2 = new OrderLine(); //객체를 만들려면 새로운 변수 새로운 객체를 만드는게 좋다.
//			Product order_p2 = new Product();
//			order_p2.setProd_no("G0002");
//			line2.setOrder_quantity(20);
//			line2.setOrder_p(order_p);
//			lines.add(line2);
			
			line = new OrderLine(); //객체를 만들려면 새로운 변수 새로운 객체를 만드는게 좋다.
			Product order_p2 = new Product();
			order_p2.setProd_no("G0002");
			line.setOrder_quantity(20);
			line.setOrder_p(order_p2);
			lines.add(line);
			
			
			info1.setLines(lines);
			session.insert("com.day.dto.OrderMapper.insertInfo", info1);
			
			for(OrderLine line2: info1.getLines()) {
				session.insert("com.day.dto.OrderMapper.insertLine", line2);
			}
			session.commit();
			
			List<OrderInfo> list = session.selectList("com.day.dto.OrderMapper.selectById", "id1");
			System.out.println("id1고객의 주문목록");
			for(OrderInfo info:list) {
				int order_no = info.getOrder_no();
				Date order_dt = info.getOrder_dt();
				System.out.println("주문번호:" + order_no + ", 주문일자:" + order_dt +"---------");
				List<OrderLine> lines1 = info.getLines();
				for(OrderLine line1: lines1) {
					Product p1 = line1.getOrder_p();
					int quantity = line1.getOrder_quantity();
					System.out.println("상품번호:"+p1.getProd_no()+
									   ",상품명:"+p1.getProd_name()+
									   ",상품가격:"+p1.getProd_price()+
							  	   	   ",주문수량:+quantity");
				}
				System.out.println("-----------------------------------------------------");
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

