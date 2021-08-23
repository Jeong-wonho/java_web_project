package com.slur.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.slur.dto.Student;
import com.slur.dto.Teacher;
import com.slur.dto.User;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;

@Repository("applicationDAO")
public class ApplicationDAOOracle implements ApplicationDAO {
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	@Override
	public void insertTeacher(Teacher t) throws AddException {
		// TODO Auto-generated method stub
		//DB연결
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("con.slur.dto.ApplicationMapper.insertTeacher", t);
			session.commit();
		}catch(Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		}finally {
			if(session != null) {
				session.close();
			}
		}
		
	}

	@Override
	public void insertStudent(Student s) throws AddException {
		// TODO Auto-generated method stub
		//DB연결
				SqlSession session = null;
				try {
					session = sessionFactory.openSession();
					session.insert("con.slur.dto.ApplicationMapper.insertStudent", s);
					session.commit();
				}catch(Exception e) {
					e.printStackTrace();
					throw new AddException(e.getMessage());
				}finally {
					if(session != null) {
						session.close();
					}
				}
	}

	@Override
	public void updateTeacher(Teacher t) throws ModifyException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.update("con.slur.dto.ApplicationMapper.updateTeacher", t);
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	@Override
	public void updateStudent(Student s) throws ModifyException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.update("con.slur.dto.ApplicationMapper.updateStudent", s);
		}catch(Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	@Override
	public Teacher selectByTeacherApplication(User u) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			Teacher t = session.selectOne("con.slur.dto.ApplicationMapper.selectByTeacherApplication", u.getUser_id());
			
			return t;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	@Override
	public Student selectByStudentApplication(User u) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			Student s = session.selectOne("con.slur.dto.ApplicationMapper.selectByStudentApplication", u.getUser_id());
			
			return s;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) {
				session.close();
			}
		}
	}

}
