package com.slur.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ProcessHandle.Info;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.slur.dto.Student;
import com.slur.dto.Teacher;
import com.slur.dto.User;
import com.slur.service.ApplicantsService;

import net.coobird.thumbnailator.Thumbnailator;

@RestController
public class UploadController {
	
	@Autowired
	private ServletContext servletContext;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicantsService service;
	
	@PostMapping("/uploadteacher")
	public Map<String, Object> uploadTeacher(@RequestPart MultipartFile photoFile,@RequestPart MultipartFile docFile, Teacher teacher, HttpSession session) {
		User u = (User)session.getAttribute("loginInfo");
		teacher.setTeacher_user_id(u);
		Map<String, Object> result = new HashMap<>();
		
		log.info("요청전달데이터 user_id=" + u.getUser_id() + ", name=" + u.getUser_name());
		log.info("photoFiles=" + photoFile);
		log.info("docFiles=" + docFile);
		log.info("요청전달데이터");
		
		String uploadPath = servletContext.getRealPath("upload");
//		String uploadPath = servletContext.getRealPath(null);
		log.info("업로드 실제경로:"+uploadPath);
		
		//경로생성
		if(!new File(uploadPath).exists()) {
			log.info("업로드 실제경로생성");
			new File(uploadPath).mkdirs();
		}
		
		//ToDo : drinkFile 실제경로에 저장하기
		if(photoFile != null) {
			log.error(photoFile.getOriginalFilename());
			String photoFileName = photoFile.getOriginalFilename();
//			String[] phtName = photoFileName.split(".");
//			String endfileName = phtName[1];
//			log.error("마지막 파일형식:"+endfileName);
			
			if (!"".equals(photoFileName) && photoFile.getSize() != 0)
			System.out.println("1. 사진파일크기:"+photoFile.getSize()+", 파일이름:" + photoFileName);
			//String fileName = "id_1"+drinkFileName;
			String fileName = "profile"+"-" + teacher.getTeacher_user_id().getUser_id(); 
			File file = new File(uploadPath, fileName);//파일 생성	
			System.out.println("1. 업로드 파일이름:" + file.getName() + ", 존재여부:" + file.exists());
			try {
				FileCopyUtils.copy(photoFile.getBytes(), file);
				log.info("파일 생성");
				
				//이미지 파일인 경우 섬네일 파일을 만듦
				String contentType = photoFile.getContentType(); //파일형식				
				log.info("DRINK 파일형식:" + contentType);
				
				if(contentType.startsWith("image")) { //이미지파일인 경우
					String thumbnailName =  "s_"+fileName+".jpg"; //s_파일이름
					File thumbnailFile = new File(uploadPath,thumbnailName); 
					FileOutputStream thumbnail = new FileOutputStream(thumbnailFile);
					InputStream photoFileIS = photoFile.getInputStream();
					int width = 150;
					int height = 150;
					
					//섬네일파일 만들기
					Thumbnailator.createThumbnail(photoFileIS, thumbnail, width, height);
					
					result.put("photoFileName", thumbnailName);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block	
				e.printStackTrace();
			}
		}
	
	//ToDo : drinkFile 실제경로에 저장하기
			if(docFile != null) {
				log.error(docFile.getOriginalFilename());
				String docFileName = docFile.getOriginalFilename();
				
				if (!"".equals(docFileName) && docFile.getSize() != 0)
				System.out.println("사진파일크기:"+docFile.getSize()+", 파일이름:" + docFileName);
				//String fileName = "id_1"+drinkFileName;
				String fileName = UUID.randomUUID()+"_"+teacher.getTeacher_user_id().getUser_id(); 
				File file = new File(uploadPath, fileName);//파일 생성	
				try {
					FileCopyUtils.copy(docFile.getBytes(),file);
				} catch (IOException e) {
					// TODO Auto-generated catch block	
					e.printStackTrace();
				}
			}
		
	

	try {
		log.error(u.getUser_id());
		teacher.setTeacher_user_id(u);
		log.error(teacher.toString());
		service.teacher_application(teacher);
		result.put("status", 1);
		
	}catch (Exception e){
		e.printStackTrace();
		result.put("status", 0);
		result.put("msg", e.getMessage());
		
	}
	return result;
	}
	
	@PostMapping("/uploadstudent")
	public Map<String, Object> uploadStudent(@RequestPart MultipartFile photoFile,@RequestPart MultipartFile docFile, Student student, HttpSession session) {
		User u = (User)session.getAttribute("loginInfo");
		student.setStudent_user_id(u);
		Map<String, Object> result = new HashMap<>();
		
		log.info("요청전달데이터 user_id=" + u.getUser_id() + ", name=" + u.getUser_name());
		log.info("photoFiles=" + photoFile);
		log.info("docFiles=" + docFile);
		log.info("요청전달데이터");
		
		String uploadPath = servletContext.getRealPath("upload");
		log.info("업로드 실제경로:"+uploadPath);
		
		//경로생성
		if(!new File(uploadPath).exists()) {
			log.info("업로드 실제경로생성");
			new File(uploadPath).mkdirs();
		}
		
		//ToDo : drinkFile 실제경로에 저장하기
		if(photoFile != null) {
			log.error(photoFile.getOriginalFilename());
			String photoFileName = photoFile.getOriginalFilename();
//			String[] phtName = photoFileName.split(".");
//			String endfileName = phtName[1];
//			log.error("마지막 파일형식:"+endfileName);
			
			if (!"".equals(photoFileName) && photoFile.getSize() != 0)
			System.out.println("1. 사진파일크기:"+photoFile.getSize()+", 파일이름:" + photoFileName);
			//String fileName = "id_1"+drinkFileName;
			String fileName = "profile"+"-" + student.getStudent_user_id().getUser_id(); 
			File file = new File(uploadPath, fileName);//파일 생성	
			System.out.println("1. 업로드 파일이름:" + file.getName() + ", 존재여부:" + file.exists());
			try {
				FileCopyUtils.copy(photoFile.getBytes(), file);
				log.info("파일 생성");
				
				//이미지 파일인 경우 섬네일 파일을 만듦
				String contentType = photoFile.getContentType(); //파일형식				
				log.info("DRINK 파일형식:" + contentType);
				
				if(contentType.startsWith("image")) { //이미지파일인 경우
					String thumbnailName =  "s_"+fileName+".jpg"; //s_파일이름
					File thumbnailFile = new File(uploadPath,thumbnailName); 
					FileOutputStream thumbnail = new FileOutputStream(thumbnailFile);
					InputStream photoFileIS = photoFile.getInputStream();
					int width = 150;
					int height = 150;
					
					//섬네일파일 만들기
					Thumbnailator.createThumbnail(photoFileIS, thumbnail, width, height);
					
					result.put("photoFileName", thumbnailName);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block	
				e.printStackTrace();
			}
		}
	
	//ToDo : drinkFile 실제경로에 저장하기
			if(docFile != null) {
				log.error(docFile.getOriginalFilename());
				String docFileName = docFile.getOriginalFilename();
				
				if (!"".equals(docFileName) && docFile.getSize() != 0)
				System.out.println("사진파일크기:"+docFile.getSize()+", 파일이름:" + docFileName);
				//String fileName = "id_1"+drinkFileName;
				String fileName = UUID.randomUUID()+"_"+student.getStudent_user_id().getUser_id(); 
				File file = new File(uploadPath, fileName);//파일 생성	
				try {
					FileCopyUtils.copy(docFile.getBytes(),file);
				} catch (IOException e) {
					// TODO Auto-generated catch block	
					e.printStackTrace();
				}
			}
		
	

	try {
		log.error(u.getUser_id());
		student.setStudent_user_id(u);
		log.error(student.toString());
		service.student_application(student);
		result.put("status", 1);
		
	}catch (Exception e){
		e.printStackTrace();
		result.put("status", 0);
		result.put("msg", e.getMessage());
		
	}
	return result;
	}
	
	@PostMapping("/studentmod")
	public Map<String, Object> modStudent(@RequestPart MultipartFile photoFile,@RequestPart MultipartFile docFile, Student student, HttpSession session) {
		User u = (User)session.getAttribute("loginInfo");
		log.error(u.getUser_id());
		student.setStudent_user_id(u);
		Map<String, Object> result = new HashMap<>();
		
		log.info("요청전달데이터 user_id=" + u.getUser_id() + ", name=" + u.getUser_name());
		log.info("photoFiles=" + photoFile);
		log.info("docFiles=" + docFile);
		log.info("요청전달데이터");
		
		String uploadPath = servletContext.getRealPath("upload");
		log.info("업로드 실제경로:"+uploadPath);
		
		//경로생성
		if(!new File(uploadPath).exists()) {
			log.info("업로드 실제경로생성");
			new File(uploadPath).mkdirs();
		}
		
		//ToDo : drinkFile 실제경로에 저장하기
		if(photoFile != null) {
			log.error(photoFile.getOriginalFilename());
			String photoFileName = photoFile.getOriginalFilename();
//			String[] phtName = photoFileName.split(".");
//			String endfileName = phtName[1];
//			log.error("마지막 파일형식:"+endfileName);
			
			if (!"".equals(photoFileName) && photoFile.getSize() != 0)
			System.out.println("1. 사진파일크기:"+photoFile.getSize()+", 파일이름:" + photoFileName);
			//String fileName = "id_1"+drinkFileName;
			String fileName = "profile"+"-" + student.getStudent_user_id().getUser_id()+"_" + photoFileName; 
			File file = new File(uploadPath, fileName);//파일 생성	
			System.out.println("1. 업로드 파일이름:" + file.getName() + ", 존재여부:" + file.exists());
			try {
				FileCopyUtils.copy(photoFile.getBytes(), file);
				log.info("파일 생성");
				
				//이미지 파일인 경우 섬네일 파일을 만듦
				String contentType = photoFile.getContentType(); //파일형식				
				log.info("DRINK 파일형식:" + contentType);
				
				if(contentType.startsWith("image")) { //이미지파일인 경우
					String thumbnailName =  "s_"+fileName+".jpg"; //s_파일이름
					File thumbnailFile = new File(uploadPath,thumbnailName); 
					FileOutputStream thumbnail = new FileOutputStream(thumbnailFile);
					InputStream photoFileIS = photoFile.getInputStream();
					int width = 150;
					int height = 150;
					
					//섬네일파일 만들기
					Thumbnailator.createThumbnail(photoFileIS, thumbnail, width, height);
					
					result.put("photoFileName", thumbnailName);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block	
				e.printStackTrace();
			}
		}
	
	//ToDo : drinkFile 실제경로에 저장하기
			if(docFile != null) {
				log.error(docFile.getOriginalFilename());
				String docFileName = docFile.getOriginalFilename();
				
				if (!"".equals(docFileName) && docFile.getSize() != 0)
				System.out.println("사진파일크기:"+docFile.getSize()+", 파일이름:" + docFileName);
				//String fileName = "id_1"+drinkFileName;
				String fileName = UUID.randomUUID()+"_"+student.getStudent_user_id().getUser_id(); 
				File file = new File(uploadPath, fileName);//파일 생성	
				try {
					FileCopyUtils.copy(docFile.getBytes(),file);
				} catch (IOException e) {
					// TODO Auto-generated catch block	
					e.printStackTrace();
				}
			}
		
	

	try {

		log.error(student.toString());
		log.error(student.toString());
		service.student_modify(student);
		result.put("status", 1);

	}catch (Exception e){
		e.printStackTrace();
		result.put("status", 0);
		result.put("msg", e.getMessage());
		
	}
	return result;
	}
	
	@PostMapping("teachermod")
	public Map<String, Object> modTeacher(@RequestPart MultipartFile photoFile,@RequestPart MultipartFile docFile, Teacher teacher, HttpSession session) {
		User u = (User)session.getAttribute("loginInfo");
		log.error(u.getUser_id());
		teacher.setTeacher_user_id(u);
		Map<String, Object> result = new HashMap<>();
		
		log.info("요청전달데이터 user_id=" + u.getUser_id() + ", name=" + u.getUser_name());
		log.info("photoFiles=" + photoFile);
		log.info("docFiles=" + docFile);
		log.info("요청전달데이터");
		
		String uploadPath = servletContext.getRealPath("upload");
		log.info("업로드 실제경로:"+uploadPath);
		
		//경로생성
		if(!new File(uploadPath).exists()) {
			log.info("업로드 실제경로생성");
			new File(uploadPath).mkdirs();
		}
		
		//ToDo : drinkFile 실제경로에 저장하기
		if(photoFile != null) {
			log.error(photoFile.getOriginalFilename());
			String photoFileName = photoFile.getOriginalFilename();
//			String[] phtName = photoFileName.split(".");
//			String endfileName = phtName[1];
//			log.error("마지막 파일형식:"+endfileName);
			
			if (!"".equals(photoFileName) && photoFile.getSize() != 0)
			System.out.println("1. 사진파일크기:"+photoFile.getSize()+", 파일이름:" + photoFileName);
			//String fileName = "id_1"+drinkFileName;
			String fileName = "profile"+"-" + teacher.getTeacher_user_id().getUser_id(); 
			File file = new File(uploadPath, fileName);//파일 생성	
			System.out.println("1. 업로드 파일이름:" + file.getName() + ", 존재여부:" + file.exists());
			try {
				FileCopyUtils.copy(photoFile.getBytes(), file);
				log.info("파일 생성");
				
				//이미지 파일인 경우 섬네일 파일을 만듦
				String contentType = photoFile.getContentType(); //파일형식				
				log.info("DRINK 파일형식:" + contentType);
				
				if(contentType.startsWith("image")) { //이미지파일인 경우
					String thumbnailName =  "s_"+fileName+".jpg"; //s_파일이름
					log.error(thumbnailName);
					File thumbnailFile = new File(uploadPath,thumbnailName); 
					FileOutputStream thumbnail = new FileOutputStream(thumbnailFile);
					InputStream photoFileIS = photoFile.getInputStream();
					int width = 150;
					int height = 150;
					
					//섬네일파일 만들기
					Thumbnailator.createThumbnail(photoFileIS, thumbnail, width, height);
					
					result.put("photoFileName", thumbnailName);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block	
				e.printStackTrace();
			}
		}
	
	//ToDo : drinkFile 실제경로에 저장하기
			if(docFile != null) {
				log.error(docFile.getOriginalFilename());
				String docFileName = docFile.getOriginalFilename();
				
				if (!"".equals(docFileName) && docFile.getSize() != 0)
				System.out.println("사진파일크기:"+docFile.getSize()+", 파일이름:" + docFileName);
				//String fileName = "id_1"+drinkFileName;
				String fileName = UUID.randomUUID()+"_"+teacher.getTeacher_user_id().getUser_id()+"_" + docFileName; 
				File file = new File(uploadPath, fileName);//파일 생성	
				try {
					FileCopyUtils.copy(docFile.getBytes(),file);
				} catch (IOException e) {
					// TODO Auto-generated catch block	
					e.printStackTrace();
				}
			}
		
	

	try {

		log.error(teacher.toString());
		log.error(teacher.toString());
		service.teacher_modify(teacher);
		result.put("status", 1);

	}catch (Exception e){
		e.printStackTrace();
		result.put("status", 0);
		result.put("msg", e.getMessage());
		
	}
	return result;
	}
}
