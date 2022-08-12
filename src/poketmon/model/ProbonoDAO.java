/*
 * CREATE TABLE probono (
       probono_id           	VARCHAR2(50) PRIMARY KEY,
       probono_name     	VARCHAR2(50) NOT NULL,
       probono_purpose  VARCHAR2(200) NOT NULL
);  */
package poketmon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import poketmon.model.dto.PoketmonDTO;
import poketmon.model.util.DBUtil;

public class ProbonoDAO {

	// 저장
	public static boolean addProbono(PoketmonDTO probono) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into probono values(?, ?, ?)");
			pstmt.setString(1, probono.getProbonoId());
			pstmt.setString(2, probono.getProbonoName());
			pstmt.setString(3, probono.getProbonoPurpose());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}

		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 수정
	// 프로보노 id로 프로보노 목적 수정하기
	public static boolean updateProbono(String probonoId, String probonoPurpose) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update probono set probono_purpose=? where probono_id=?");
			pstmt.setString(1, probonoPurpose);
			pstmt.setString(2, probonoId);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 삭제
	public static boolean deleteProbono(String probonoId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from probono where probono_id=?");
			pstmt.setString(1, probonoId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// 프로포노 아이디로 해당 프로보노 모든 정보 검색
	public static PoketmonDTO getProbono(String probonoId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PoketmonDTO probono = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from probono where probono_id=?");
			pstmt.setString(1, probonoId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				probono = PoketmonDTO.builder()
						.probonoId(rset.getString(1))
						.probonoName(rset.getString(2))
						.probonoPurpose(rset.getString(3)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return probono;
	}

	// 모든 프로보노 검색
	public static ArrayList<PoketmonDTO> getAllProbonos() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PoketmonDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from probono");
			rset = pstmt.executeQuery();

			list = new ArrayList<PoketmonDTO>();
			while (rset.next()) {
				list.add(PoketmonDTO.builder()
						.probonoId(rset.getString(1))
						.probonoName(rset.getString(2))
						.probonoPurpose(rset.getString(3)).build());
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}
