import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.day.dao.CustomerDAO;
import com.day.dto.Customer;
import com.day.dto.Product;
import com.day.exception.FindException;
import com.day.service.ProductService;


public abstract class Test {

	public static void main(String[] args) {
		// 1.스프링 컨테이너 (엔진)을 시작시킨다. 
		// 사용 api 종류: ApplicationContext
		// 2.설정파일(config.xml)의 bean태그에 설정한 클래스타입의 객체를 생성한다.
		
		//Spring Controller 생성 코드
		ApplicationContext ctx;
		String configLocation = "config.xml";
		ctx = new ClassPathXmlApplicationContext(configLocation);
		
//		ctx = new AnnotationConfigApplicationContext(com.day.config.Factory.class);
		
		Customer c1 = ctx.getBean("c", com.day.dto.Customer.class);
		System.out.println(c1);
		Customer c2 = ctx.getBean("c", com.day.dto.Customer.class);
		System.out.println(c2);
		// c라는 객체는 하나뿐이다.!
		System.out.println("c1==c2:" + (c1==c2)); //true : 스프링컨테이너에서 관리되는 c객체는 1개이다.
		
		CustomerDAO cDAO = ctx.getBean("customerDAO", com.day.dao.CustomerDAO.class);
		System.out.println(cDAO);
//		
//		try {
//			Customer c = cDAO.selectById("id1");
//			System.out.println(c);
//		} catch (FindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Product p1 = ctx.getBean("p", com.day.dto.Product.class);
		System.out.println(p1);
		
		ProductService productservice = ctx.getBean("productService", com.day.service.ProductService.class);
		try {
			System.out.println(productservice.findByNo("G0001"));
		} catch (FindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
