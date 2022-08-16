package pokemon.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pokemon.model.dto.OwnerDTO;
import pokemon.model.dto.PokemonBookDTO;
import pokemon.model.dto.PokemonDTO;
import pokemon.model.util.DBUtil;
public class PokemonBookDAO {
	// 전체적인 구조 pokemonDAO와 유사하게 수정
	
	/** 검색 SELECT */
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
				list.add(PokemonBookDTO.pokemonBookDTOBuilder()
						.pokemonBookId(rset.getInt(1))
						.pokemon(PokemonDAO.getPokemon("id", rset.getString(2)))
						.owner(OwnerDAO.getOwner("id", rset.getString(3))).build());
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	
	// pokemon Book Id로 해당 도감 검색
	public static PokemonBookDTO getPokemonBookId(int pokemonBookId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonBookDTO pokemonBook = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon_book where pokemon_book_id=?");
			pstmt.setInt(1, pokemonBookId);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemonBook = PokemonBookDTO.pokemonBookDTOBuilder()
						.pokemonBookId(rset.getInt(1))
						.pokemon(PokemonDAO.getPokemon("id", rset.getString(2)))
						.owner(OwnerDAO.getOwner("id", rset.getString(3))).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemonBook;
	}
	
	
	// 포켓몬 Id로 해당 도감 검색
	public static PokemonBookDTO getPokemonBookPokemon(PokemonDTO pokemon) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonBookDTO pokemonBook = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon_book where pokemon_id=?");
			pstmt.setInt(1, pokemon.getPokemonId());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemonBook = PokemonBookDTO.pokemonBookDTOBuilder()
						.pokemonBookId(rset.getInt(1))
						.pokemon(PokemonDAO.getPokemon("id", rset.getString(2)))
						.owner(OwnerDAO.getOwner("id", rset.getString(3))).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemonBook;
	}
	
	
	// 소유자 Id로 해당 도감 검색
	public static PokemonBookDTO getPokemonBookOwner(OwnerDTO owner) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonBookDTO pokemonBook = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon_book where owner_id=?");
			pstmt.setInt(1, owner.getOwnerId());
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemonBook = PokemonBookDTO.pokemonBookDTOBuilder()
						.pokemonBookId(rset.getInt(1))
						.pokemon(PokemonDAO.getPokemon("id", rset.getString(2)))
						.owner(OwnerDAO.getOwner("id", rset.getString(3))).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemonBook;
	}
	
	/** 저장 INSERT */
	// 새로운 도감 저장
	public static boolean addPokemonBook(PokemonBookDTO pokemonBook) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			// INSERT INTO pokemon_book (pokemon_book_id, pokemonId, ownerId) VALUES (5, 4, 12);
			pstmt = con.prepareStatement("insert into pokemon_book values(?, ?, ?)");
			pstmt.setInt(1, pokemonBook.getPokemonBookId());
			pstmt.setInt(2, pokemonBook.getPokemon().getPokemonId());
			pstmt.setInt(3, pokemonBook.getOwner().getOwnerId());
			
			int result = pstmt.executeUpdate();	// 제대로 실행이 되었을 경우.
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	/** 수정 UPDATE */
	// 포켓몬북 ID로 소유자 ID 수정
	public static boolean updatePokemonBook(int pokemonBookId, OwnerDTO owner) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update pokemon_book set owner_id=? where pokemonBook_id=?");
			pstmt.setInt(1, owner.getOwnerId());
			pstmt.setInt(2, pokemonBookId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 포켓몬북 ID로 포켓몬 ID 수정
	public static boolean updatePokemonBook(int pokemonBookId, PokemonDTO pokemon) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update pokemon_book set pokemon_id=? where pokemonBook_id=?");
			pstmt.setInt(1, pokemon.getPokemonId());
			pstmt.setInt(2, pokemonBookId);
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
	// 포켓몬북 Id로 해당 도감 삭제
	public static boolean deletePokemonBook(int pokemonBookId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from pokemon_book where pokemon_book_id=?");
			pstmt.setInt(1, pokemonBookId);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	


	
}