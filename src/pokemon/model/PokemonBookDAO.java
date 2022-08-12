package pokemon.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pokemon.model.dto.PokemonBookDTO;
import poketmon.model.util.DBUtil;
public class PokemonBookDAO {
	
	/** 저장 INSERT */
	// 새로운 도감 저장
	public static boolean addPokemonBook(PokemonBookDTO poketmonBook) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into pokemon_book values(?,?)");
			pstmt.setInt(2, poketmonBook.getPokemonId());
			pstmt.setInt(3, poketmonBook.getOwnerId());
			
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	/** 수정 UPDATE */
	// 포켓몬 ID로 소유자 ID 수정
	public static boolean updatePokemonBookOwner(int pokemonId, int ownerId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update pokemon_book set owner_id=? where pokemon_id=?");
			pstmt.setInt(1, pokemonId);
			pstmt.setInt(2, ownerId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	// 소유자 ID로 포켓몬 ID 수정
	public static boolean updatePokemonBookPokemon(int ownerId, int pokemonId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update pokemon_book set pokemon_id=? where owner_id=?");
			pstmt.setInt(1, ownerId);
			pstmt.setInt(2, pokemonId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	/** 삭제 DELETE */
	// 포켓몬 Id로 해당 도감 삭제
	public static boolean deletePokemonBookPokemon(int pokemonId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from pokemon_book where pokemon_id=?");
			pstmt.setInt(1, pokemonId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	// 소유자 Id로 해당 도감 삭제
	public static boolean deletePokemonBookOwner(int ownerId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from pokemon_book where owner_id=?");
			pstmt.setInt(1, ownerId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	/** 검색 SELECT */
	// 포켓몬 Id로 해당 도감 검색
	public static PokemonBookDTO getPokemonBookPokemon(int pokemonId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonBookDTO pokemonBook = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon_book where pokemon_id=?");
			pstmt.setInt(1, pokemonId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemonBook = PokemonBookDTO.builder()
							.pokemonId(rset.getInt(1))
							.ownerId(rset.getInt(2)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemonBook;
	}
	// 소유자 Id로 해당 도감 검색
	public static PokemonBookDTO getPokemonBookOwner(int ownerId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonBookDTO pokemonBook = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon_book where pokemon_id=?");
			pstmt.setInt(1, ownerId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemonBook = PokemonBookDTO.builder()
						.pokemonId(rset.getInt(1))
						.ownerId(rset.getInt(2)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemonBook;
	}
	// 모든 도감 검색
	public static ArrayList<PokemonBookDTO> getAllPokemonBook() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PokemonBookDTO> list = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon_book");
			rset = pstmt.executeQuery();
			list = new ArrayList<PokemonBookDTO>();
			while (rset.next()) {
				list.add(PokemonBookDTO.builder()
						.pokemonId(rset.getInt(1))
						.ownerId(rset.getInt(2)).build());
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
}