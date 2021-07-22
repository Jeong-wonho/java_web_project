package com.day.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.day.dto.Board;
import com.day.dto.Comment;
import com.day.exception.AddException;
import com.day.exception.FindException;
import com.day.exception.ModifyException;
import com.day.exception.RemoveException;
import com.day.sql.Myconnection;

public class BoardDAOOracle implements BoardDAO {

	public List<Board> selectAll() throws FindException { // 전체 보드 리스트 가져오기 (완료)
		// DB연결
		Connection con = null;
		try {
			con = Myconnection.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage());// 사용자에게 떠넘긴다. => 사용자 = service.java
		}
		String selectALLSQL = "SELECT kr_board.board_num,\r\n" + "prod_name,\r\n" + "kr_product.PROD_RELEASEPRICE product_price,\r\n"
				+ "kr_board.user_id,\r\n" + "board_recommened,\r\n" + "board_date,\r\n" + "board_title,\r\n"
				+ "board_maintext,\r\n" + "(select count(cm_number)\r\n" + "from kr_comment\r\n"
				+ "where board_num = kr_board.board_num\r\n" + "group by board_num\r\n" + ") cm_count\r\n"
				+ "FROM kr_board \r\n" + "LEFT JOIN  kr_comment ON (kr_board.board_num = kr_comment.board_num)\r\n"
				+ "LEFT JOIN kr_product ON (kr_board.prod_num = kr_product.prod_num)"
				+ "order by kr_board.board_num asc";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Board> list = new ArrayList<Board>();
		try {
			pstmt = con.prepareStatement(selectALLSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				
				int board_num = rs.getInt("board_num");
				String prod_name = rs.getString("prod_name");
				String user_id = rs.getString("user_id");
				int board_recomnened = rs.getInt("board_recommened");
				String board_maintext = rs.getString("board_maintext");
				Date board_date = rs.getDate("board_date");
				String board_title = rs.getString("board_title");
				int cm_count = rs.getInt("cm_count");
				int prod_releaseprice = rs.getInt("product_price");

				Board b = new Board(board_num,user_id, prod_name,prod_releaseprice,board_recomnened,
						board_maintext,board_date,board_title,null,cm_count);
				System.out.println(b.toString());// test
				list.add(b);

			}
			if (list.size() == 0) {
				throw new FindException("게시물이 없습니다"); //
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new FindException(e.getMessage()); // 검색하다가 발생한 예외라는 것임을 알려주기 위해 SQLException을 가공해서 예외를 던짐.
		} finally {

			Myconnection.close(con, pstmt, rs);
		}

	}

//게시글작성 (성공)
	public void insert(Board b) throws AddException {
		// DB연결
		Connection con = null;
		try {
			con = Myconnection.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());// 사용자에게 떠넘긴다. => 사용자 = service.java
		}
		String insertSQL = "INSERT INTO kr_board \r\n" + "VALUES\r\n"
				+ "(BOARD_NUM_SEQ.NEXTVAL,?,(select prod_num from kr_product where prod_name = ?),0,?,sysdate,?)";
		// 순서대로 게시글번호,작성자 id,상품이름에 대한 상품번호,추천수,본문,작성시간,제목
//				게시판번호 : request.get으로가져옴
//				회원아이디 : 세션으로 가져옴
//				댓글번호 : 시퀀스이용
//				댓글작성날짜 : sysdate
//				댓글본문 : 사용자입력
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setString(1, b.getUser_id()); // 작성자id
			pstmt.setString(2, b.getprod_name()); // 상품이름
			pstmt.setString(3, b.getBoard_maintext()); // 본문
			pstmt.setString(4, b.getBoard_title()); // 제목

			int rowcnt = pstmt.executeUpdate();
			System.out.println("총 " + rowcnt + "건의 행이 추가되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			throw new AddException(e.getMessage());
		} finally {
			Myconnection.close(con, pstmt, null);
		}

	}

	public void update(Board b) throws ModifyException {
		// DB연결
		Connection con = null;
		try {
			con = Myconnection.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		}
		String updateSQL = "UPDATE kr_board SET board_maintext=?, board_title=? WHERE board_num =?";
		// 본문,제목,게시판번호
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(updateSQL);
			pstmt.setString(1, b.getBoard_maintext()); // 본문
			pstmt.setString(2, b.getBoard_title()); // 제목
			pstmt.setInt(3, b.getBoard_num()); // 게시판번호

			int rowcnt = pstmt.executeUpdate();
			if (rowcnt == 1) {
				System.out.println("총 " + rowcnt + "건의 행이 변경되었습니다.");
			} else {
				throw new ModifyException("게시물을 변경 할 수 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ModifyException(e.getMessage());
		} finally {
			Myconnection.close(con, pstmt, null);
		}

	}

	public void delete(int board_num) throws RemoveException {
		// DB연결
		Connection con = null;
		try {
			con = Myconnection.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());// 사용자에게 떠넘긴다. => 사용자 = service.java
		}
		String deleteSQL1 = "DELETE FROM kr_comment WHERE board_num=?";

		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(deleteSQL1);
			pstmt.setInt(1, board_num);
			int rowcnt = pstmt.executeUpdate();
			if (rowcnt == 1) {
				System.out.println("총 " + rowcnt + "건의 행이 삭제되었습니다.");
			} else {
				throw new RemoveException("게시물을 삭제 할 수 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		}

		String deleteSQL = "DELETE FROM kr_board WHERE board_num=?";

		try {
			pstmt = con.prepareStatement(deleteSQL);
			pstmt.setInt(1, board_num);
			int rowcnt = pstmt.executeUpdate();
			if (rowcnt == 1) {
				System.out.println("총 " + rowcnt + "건의 행이 삭제되었습니다.");
			} else {
				throw new RemoveException("게시물을 삭제 할 수 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RemoveException(e.getMessage());
		} finally {
			Myconnection.close(con, pstmt, null);
		}

	}


	public Board selectByNum(int board_num) throws FindException {
		List<Comment> list= new ArrayList<Comment>();
		try {
			CommentDAOOracle cm = new CommentDAOOracle();
			list = cm.selectAll(board_num);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		Connection con = null;
		try {
			con = Myconnection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FindException(e.getMessage());
		}
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectByNumSQL = "SELECT *\r\n"
				+ "FROM kr_board\r\n"
				+ "LEFT JOIN kr_product ON (kr_board.prod_num = kr_product.prod_num)\r\n"
				+ "WHERE kr_board.board_num = ?";
		
		try {
			pstmt = con.prepareStatement(selectByNumSQL);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Board b = new Board();
				b.setBoard_num(board_num);
				b.setUser_id(rs.getString("user_id"));
				b.setBoard_recomnened(rs.getInt("board_recommened"));
				b.setBoard_maintext(rs.getString("board_maintext"));
				b.setBoard_date(rs.getDate("board_date"));
				b.setBoard_title("board_title");
				b.setCm_lines(list);
				b.setCm_count(list.size());
				b.setprod_name(rs.getString("prod_name"));
				b.setProd_releaseprice(rs.getInt("prod_releaseprice"));
				return b;
			}
			throw new FindException("게시글이 존재하지 않습니다"); //바꾸
		} catch (SQLException e) {
			throw new FindException(e.getMessage());
		}finally {
			Myconnection.close(con, pstmt, rs);
		}

	}
}

