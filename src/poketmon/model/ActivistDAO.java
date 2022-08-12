/* CREATE TABLE activist (
       activist_id          VARCHAR2(20)  PRIMARY KEY,
       name                 VARCHAR2(20) NULL,
       password             VARCHAR2(20) NULL,
       major                VARCHAR2(50) NULL
);  */
package poketmon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lombok.Builder;
import poketmon.model.dto.PoketmonTypeDTO;
import poketmon.model.util.DBUtil;

//기부자 table과 관계된 DAO 클래스 
public class ActivistDAO {
	private static boolean add;

	//TO DO
	// 기부자 등록(insert)
	public static boolean addActivist(PoketmonTypeDTO activist) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("insert into activist values(?, ?, ?, ?)");

			pstmt.setString(1, activist.getId());
			pstmt.setString(2, activist.getName());
			pstmt.setString(3, activist.getPassword());
			pstmt.setString(4, activist.getMajor());

			int i = pstmt.executeUpdate();
			if(i ==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {	// 자원 반환
			DBUtil.close(conn, pstmt);
		}
		return false;
	}

	//TO DO
	// sql - delete from activist where activist_id=?
	public static boolean deleteActivist(String activistId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from activist where activist_id=?");
			pstmt.setString(1, activistId);

			int i = pstmt.executeUpdate();
			if(i ==1) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {	// 자원 반환
			DBUtil.close(conn, pstmt);	// DBUtil에서 이미 생성해둔 close를 활용해 코드 재사용. 

		}
		return false;
	}

	//TO DO
	//	// id로 해당 기부자의 모든 정보 반환
	public static PoketmonTypeDTO getActivist(String activistId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PoketmonTypeDTO activist = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from activist where activistId = ?");
			pstmt.setString(1, activistId);
			rset = pstmt.executeQuery();

			if(rset.next()) {
				activist = new PoketmonTypeDTO(rset.getString("actvisit_id"), rset.getString("name"), rset.getString("password"), rset.getString("major"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				DBUtil.close(con, pstmt, rset);
			}	
		return activist;
		}

		// 수정
		// 기부자 id로 주요 기부 내용 수정하기
		public static boolean updateActivist(String activistId, String major) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();

				pstmt = con.prepareStatement("update activist set major=? where activist_id=?");
				pstmt.setString(1, major);
				pstmt.setString(2, activistId);

				int result = pstmt.executeUpdate();
				if (result == 1) {
					return true;
				}
			} finally {
				DBUtil.close(con, pstmt);
			}
			return false;
		}



		// ???모든 기부자 검색해서 반환
		// sql - select * from activist
		public static ArrayList<PoketmonTypeDTO> getAllActivists() throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<PoketmonTypeDTO> all = null;

			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("select * from activist");
				rset = pstmt.executeQuery();

				all = new ArrayList<PoketmonTypeDTO>();
				while (rset.next()) {
					add = all.add(PoketmonTypeDTO.activistDTOBuilder()
							.id(rset.getString(1))
							.name(rset.getString(2))
							.password(rset.getString(3))
							.major(rset.getString(4)).build());
				}
			} finally {
				DBUtil.close(con, pstmt, rset);
			}
			return all;
		}

	}