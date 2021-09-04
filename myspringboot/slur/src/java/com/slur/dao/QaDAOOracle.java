package com.slur.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.slur.dto.Notice;
import com.slur.dto.Qa;
import com.slur.dto.Review;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;
import com.slur.exception.RemoveException;

@Repository("QaDAO")
public class QaDAOOracle implements QaDAO {
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public void insertQA(Qa qa) throws AddException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.slur.dto.QaMapper.insertQa", qa);
		}catch (Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session != null)
				session.close();
		}

	}

	@Override
	public void updateQa(Qa qa) throws ModifyException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.update("com.slur.dto.QaMapper.updateQa", qa);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ModifyException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteQa(int qa_num) throws RemoveException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.delete("com.slur.dto.QaMapper.deleteQa", qa_num);
		}catch(Exception e) {
			throw new RemoveException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public List<Qa> selectAll(int pageNo, int amount) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			HashMap<String, Integer> map = new HashMap<>();
			map.put("pageNum", pageNo);
			map.put("amount", amount);
			List<Qa> qa = session.selectList("com.slur.dto.QaMapper.qaSelectAll", map);
			return qa;
		} catch (Exception e) {
			// TODO: handle exception
			throw new FindException(e.getMessage());
		}finally {
			if(session != null)session.close();
		}
	}
	

	@Override
	public Qa selectByInfo(int qa_num) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session=sessionFactory.openSession();
			Qa qa = session.selectOne("com.slur.dto.QaMapper.qaSelectInfo", qa_num);
			return qa;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}

 }

	@Override
	public List<Qa> selectAll() throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Qa> list = session.selectList("com.slur.dto.QaMapper.selectCount");
			return list;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}
	
}
