package com.slur.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.slur.dto.Criteria;
import com.slur.dto.Program;
import com.slur.dto.Review;
import com.slur.exception.AddException;
import com.slur.exception.FindException;
import com.slur.exception.ModifyException;
import com.slur.exception.RemoveException;

@Repository("ProgramDAO")
public class ProgramDAOOracle implements ProgramDAO {
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public void insertReview(Review review) throws AddException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.slur.dto.ProgramMapper.insertReview", review);
		}catch (Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session != null)
				session.close();
		}
		
	}

	@Override
	public List<Review> selectAll() throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Review> list = session.selectList("com.slur.dto.ProgramMapper.selectAll");
			return list;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public Review selectByInfo(int review_num) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session=sessionFactory.openSession();
			Review story = session.selectOne("com.slur.dto.ProgramMapper.selectByInfo", review_num);
			return story;
		} catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public void updateReview(Review review) throws ModifyException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.update("com.slur.dto.ProgramMapper.updateReview", review);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ModifyException(e.getMessage());
		}finally {
			session.close();
		}

	}

	@Override
	public void deleteReview(Review review) throws RemoveException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			session.delete("com.slur.dto.ProgramMapper.deleteReview", review);
		}catch(Exception e) {
			throw new RemoveException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public List<Program> selectByTimes() throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Program> times = session.selectList("com.slur.dto.ProgramMapper.selectByTimes");
			return times;
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public List<Program> selectByReviews(String program_times) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Program> reviewLine = session.selectList("com.slur.dto.ProgramMapper.selectByReviews", program_times);
			return reviewLine;
		}catch(Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Review> selectByWord(String word) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Review> review = session.selectList("com.slur.dto.ProgramMapper.selectByWord", word);
			return review;
		} catch (Exception e) {
			// TODO: handle exception
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
		
	}

	@Override
	public List<Review> getListWithPaging(Criteria cri) throws FindException {
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			List<Review> review = session.selectList("com.slur.dto.ProgramMapper.getListWithPaging", cri);
			return review;
		} catch (Exception e) {
			// TODO: handle exception
			throw new FindException(e.getMessage());
		}finally {
			session.close();
		}
	}

	@Override
	public List<Review> getListWithPaging(int pageNo, int amount) throws FindException{
		// TODO Auto-generated method stub
		SqlSession session = null;
		try {
			session = sessionFactory.openSession();
			HashMap<String, Integer> map = new HashMap<>();
			map.put("pageNum", pageNo);
			map.put("amount", amount);
			List<Review> review = session.selectList("com.slur.dto.ProgramMapper.getListWithPaging1", map);
			return review;
		} catch (Exception e) {
			// TODO: handle exception
			throw new FindException(e.getMessage());
		}finally {
			if(session != null)session.close();
		}
	}

}
