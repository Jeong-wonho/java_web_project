package com.day.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.day.dto.Customer;

@WebListener
public class MySessionAttributeListener implements HttpSessionAttributeListener {
	private int loginedCnt; // 로그인된 고객수
    public MySessionAttributeListener() {
        
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
         String attrName = se.getName();
         //String attrValue = se.getValue();
         if("loginInfo".equals(attrName)) {
        	 Customer c = (Customer)se.getValue();
        	 loginedCnt++; //로그인 된 고객 수 1 증가.
        	 System.out.println(c.getId()+ "님이 로그인 했습니다. 현재 로그인된 총 고객 수 : " + loginedCnt);
         }
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	if("loginInfo".equals(se.getName())) {
    		Customer c = (Customer)se.getValue();
    		loginedCnt--; // 로그인된 고객 수 1감소
    		System.out.println(c.getId()+ "님이 로그인 했습니다. 현재 로그인된 총 고객 수 : " + loginedCnt);
    	}
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         
    }
	
}
