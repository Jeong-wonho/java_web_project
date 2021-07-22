package com.day.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;


public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest mr;
//		String saveDirectory = "C:\\Users\\kallz\\OneDrive\\바탕 화면\\java_web_develope\\myweb\\upload";
		String saveDirectory = getServletContext().getRealPath("upload");
		
		int maxPostSize = 100*1024; //바이트
		String encoding = "utf-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		mr.getParameter("t");
		File file = mr.getFile("f");
		System.out.println(file.getName()); //파일이름
		System.out.println(file.length()); //파일크기
		
		File oldF = new File(saveDirectory, file.getName());
		File newF = new File(saveDirectory, "id_1" + file.getName());
		if(oldF.renameTo(newF)) {
			System.out.println(file.getName()+"->"+newF.getName());
		}
		
//		InputStream is = request.getInputStream();
//		Scanner sc = new Scanner(is);
//		String line = null;
//		while(sc.hasNext()) {
//			line = sc.nextLine();
//			System.out.println(line);
//		}
//		sc.close();
	}

}
