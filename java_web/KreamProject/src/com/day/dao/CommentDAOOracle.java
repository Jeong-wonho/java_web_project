package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.day.dto.Comment;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
import com.day.exception.RemoveException;
import com.day.sql.Myconnection;

public class CommentDAOOracle implements CommentDAO {


	public List<Comment> selectAll(int board_num) throws FindException {
		Connection con = null;
		try {
			con = Myconnection.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());// 사용자에게 떠넘긴다. => 사용자 = service.java
		}
		String selectALLSQL =  
				"SELECT cm_number,cm_date,cm_maintext,kr_comment.user_id \r\n"+
				"FROM kr_board \r\n"+
				"LEFT JOIN kr_comment ON (kr_board.board_num = kr_comment.board_num) \r\n"+
				"LEFT JOIN kr_product ON (kr_board.prod_num = kr_product.prod_num) \r\n"+
				"WHERE kr_board.board_num = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList<Comment>();		
		try {
			pstmt = con.prepareStatement(selectALLSQL);
			pstmt.setInt(1,board_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				
				int cm_number = rs.getInt("cm_number");
				Date cm_date = rs.getDate("cm_date");
				String cm_maintext = rs.getString("cm_maintext");
				String cm_user_id= rs.getString("user_id");
				
				Comment cm = new Comment(board_num,cm_number,cm_date,cm_maintext,cm_user_id );
				//System.out.println(cm.toString());//test
				list.add(cm);
				
			}
				if (list.size() == 0) {
				throw new FindException("댓글이 없습니다"); //
				}
				return list;
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage()); // 검색하다가 발생한 예외라는 것임을 알려주기 위해 SQLException을 가공해서 예외를 던짐.
		} finally {

			Myconnection.close(con, pstmt, rs);
		}
	}


	public void insert(Comment c) throws AddException {
		//DB연결
				Connection con = null;
				try {
					con = Myconnection.getConnection();

				} catch (SQLException e) {
					e.printStackTrace();
					throw new AddException(e.getMessage());
				}
				String insertSQL = "insert into kr_comment values (?,?,sysdate,?,?)";
				// 게시판번호,댓글번호 ,댓글작성날짜,본문,회원아디
				PreparedStatement pstmt = null;
				try {
					pstmt = con.prepareStatement(insertSQL);	          
					pstmt.setInt(1, c.getBoard_num()); //게시판번호,시퀀스 넣으면 빼
					pstmt.setInt(2, c.getCm_number()); //댓글번호
					pstmt.setString(3, c.getCm_maintext());	//본문
					pstmt.setString(4, c.getcm_user_id());  //회원아디
				
					int rowcnt = pstmt.executeUpdate();
					System.out.println("총 " + rowcnt + "건의 댓글이 추가되었습니다.");
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new AddException(e.getMessage());
				}finally {
					Myconnection.close(con, pstmt, null);
				}
				
	}
				
				
	


	public void update(Comment c) throws ModifyException {
		//DB연결
				Connection con = null;
				try {
					con = Myconnection.getConnection();

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					throw new ModifyException(e.getMessage());
				}
				String updateSQL = "UPDATE KR_comment SET cm_maintext =?, cm_date= sysdate WHERE board_num=? and cm_number=?";
				PreparedStatement pstmt =null;

				try {
					pstmt=con.prepareStatement(updateSQL);
					pstmt.setString(1, c.getCm_maintext());
					pstmt.setInt(2,c.getBoard_num()); //게시판번호 나중에 dto에서 getparam으로 받은값 가져오기
					pstmt.setInt(3,c.getCm_number()); //코멘트 번호도 마찬가지
					int rowcnt = pstmt.executeUpdate();
					System.out.println(rowcnt);
					if(rowcnt == 1) {
						System.out.println("총 " + rowcnt + "건의 댓글이 수정되었습니다.");		
					}else{
						throw new ModifyException("댓글을 변경 할 수 없습니다");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					throw new ModifyException(e.getMessage());
				}finally {
					Myconnection.close(con, pstmt, null);
				}
	}

	
	public void delete(int board_num ,int comment_num) throws RemoveException {
		//DB연결
				Connection con = null;
				try {
					con = Myconnection.getConnection();

				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					throw new RemoveException(e.getMessage());
				}
				String deleteSQL = "DELETE FROM kr_comment WHERE board_num=? and cm_number=?";
				
				PreparedStatement pstmt = null;
				try {
					pstmt = con.prepareStatement(deleteSQL);
					pstmt.setInt(1,board_num);
					pstmt.setInt(2,comment_num);
					
					int rowcnt = pstmt.executeUpdate();
					if(rowcnt == 1) {
						System.out.println("총 " + rowcnt + "건의 행이 삭제되었습니다.");		
					}else{
						throw new RemoveException("게시물을 삭제 할 수 없습니다");
					}
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					throw new RemoveException(e.getMessage());
				}finally {
					Myconnection.close(con, pstmt, null);
				}
		
	}
}
