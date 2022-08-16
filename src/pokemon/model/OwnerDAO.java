//OWNER DAO
package pokemon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pokemon.model.dto.OwnerDTO;
import pokemon.model.util.DBUtil;

public class OwnerDAO { //포켓몬 소유자 DAO 

	/** 추가 INSERT */
	public static boolean addOwner(OwnerDTO owner) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("insert into owner values (?, ?, ?, ?)");
			pstmt.setInt(1, owner.getOwnerId());
			pstmt.setString(2, owner.getOwnerName());
			pstmt.setInt(3, owner.getOwnerAge());
			pstmt.setString(4, owner.getOwnerTier());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return false;
	}

	/** 검색 SELECT */
	public static ArrayList<OwnerDTO> getAllOwners() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OwnerDTO> list = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from owner");
			rset = pstmt.executeQuery();

			list = new ArrayList<OwnerDTO>();
			while (rset.next()) {
				list.add(OwnerDTO.ownerDTOBuilder().ownerId(rset.getInt(1)).ownerName(rset.getString(2))
						.ownerAge(rset.getInt(3)).ownerTier(rset.getString(4)).build());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}



	/* *8 수정필요
	public static OwnerDTO getOwnerId(int ownerId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OwnerDTO result = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from owner where owner_id=?");
			pstmt.setInt(1, ownerId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				result = OwnerDTO.ownerDTOBuilder().ownerId(rset.getInt(1)).ownerName(rset.getString(2))
						.ownerAge(rset.getInt(3)).ownerTier(rset.getString(4)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return result;
	}
	*/

	/** 수정 UPDATE */
	public static boolean updateOwner(String ownerId, String toUpdate, String updateColumn) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();	// connection 확인

			pstmt = setUpdateColumn(ownerId, updateColumn, toUpdate, con, pstmt);	// connection 정상 실행시 update 실행
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	public static PreparedStatement setUpdateColumn(String ownerId, String updateColumn, String toUpdate,Connection con, PreparedStatement pstmt) {	// update할 대상을 정해줌. 
		try {
			pstmt = con.prepareStatement("update owner set owner_"+ updateColumn + "=? where owner=?");
			pstmt.setString(1, toUpdate);
			pstmt.setInt(2, Integer.parseInt(ownerId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	/** 삭제 DELETE */
	public static boolean deleteOwner(String ownerId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from owner where owner_id=?");
			pstmt.setInt(1, Integer.parseInt(ownerId));
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn, pstmt);
		}
		return false;
	}
}