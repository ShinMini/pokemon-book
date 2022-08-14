package pokemon.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pokemon.model.dto.PokemonBookDTO;
import pokemon.model.util.DBUtil;
public class PokemonBookDAO {
	// 전체적인 구조 pokemonDAO와 유사하게 수정 필요
	
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
	// 포켓몬 ID로 소유자 ID 수정
	/* 보류
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
	*/
	// 소유자 ID로 포켓몬 ID 수정
	// 포켓몬 도감 id를 검색해 pokemon id값을 찾아 해당 도감 수정하도록 update 필요
	public static boolean updatePokemonBook(int pokemonBookId, int ownerId, int pokemonId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update pokemon_book set pokemon_book_id=? where pokemonId=?");
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

	// pokemon Book id 가져오는 함수 생성 필요
	public static PokemonBookDTO getPokemonBookId(int pokemonBookId) {
		// TODO Auto-generated method stub
		return null;
	}

	// 도감 index번호로 검색해 도감 정보 삭제 하는 function 생성 필요
	public static boolean deletePokemonBook(int pokemonBookId) {
		// TODO Auto-generated method stub
		return false;
	}
}
	
	
	
	
	
	/*
	// 희돈님이 추가하신 파일 
	public class JoinClass {
		private int pokemonBookId;
		private int ownerId;
		private String ownerName;
		private int ownerAge;
		private String ownerTier;
		private int pokemonId;
		private String pokemonName;
		private int pokemonAge;
		private String pokemonType;
		private int pokemonPower;
		private int pokemonLegend;
	public static JoinClass getPokemonBookOwner(int ownerId) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			JoinClass joinClass = null;
			
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(“select * from pokemonbook pb inner join pokemon p on pb.pokemonId = p.pokemonId inner join owner o on pb.ownerId = o.ownerId where p.pokemonId=?“);
				pstmt.setInt(1, ownerId);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					joinClass = JoinClass.builder()
							.pokemonBookId(rset.getInt(1))
							.pokemonId(rset.getInt(2))
							.pokemonName(rset.getString(5))
							.pokemonAge(rset.getInt(6))
							.pokemonType(rset.getString(7))
							.pokemonPower(rset.getInt(8))
							.pokemonLegend(rset.getInt(9))
							.ownerId(rset.getInt(10))
							.ownerName(rset.getString(11))
							.ownerAge(rset.getInt(12))
							.ownerTier(rset.getString(13))
							.build();
				}
			} finally {
				DBUtil.close(con, pstmt, rset);
			}
			return joinClass;
		}
	public static void printQuery() {
			
			try {
				System.out.println(PokemonBookDAO.getPokemonBookOwner(10));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
}
	*/