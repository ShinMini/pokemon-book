//POKEMON DAO
package pokemon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pokemon.model.dto.PokemonDTO;
import pokemon.model.util.DBUtil;


public class PokemonDAO {
	// 희돈님이 보내주신 파일대로 교체 완료
	
	/*	수정 사항_(완)
	*  Feature 일괄적으로 Age로 변경
	* sql문 변경된것과 일치하게 변경
	모든 포켓몬 검색해서 반환
	*/ 
	public static ArrayList<PokemonDTO> getAllPokemons() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PokemonDTO> all = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon");
			rset = pstmt.executeQuery();

			all = new ArrayList<PokemonDTO>();
			while (rset.next()) {
				all.add(PokemonDTO.pokemonDTOBuilder()
									.pokemonId(rset.getInt(1))
									.pokemonName(rset.getString(2))
									.pokemonAge(rset.getInt(3))
									.pokemonType(rset.getString(4))
									.pokemonPower(rset.getInt(5))
									.pokemonLegend(rset.getBoolean(6)).build());
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return all;
	}
	
	// 포켓몬 이름으로 검색
	public static PokemonDTO getPokemon(String pokemonName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonDTO pokemon1 = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon where pokemon_name=?");
			pstmt.setString(1, pokemonName);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemon1 = PokemonDTO.pokemonDTOBuilder()
							.pokemonId(rset.getInt(1))
							.pokemonName(rset.getString(2))
							.pokemonAge(rset.getInt(3))
							.pokemonType(rset.getString(4))
							.pokemonPower(rset.getInt(5))
							.pokemonLegend(rset.getBoolean(6)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemon1;
	}

	
	// 포켓몬 특징으로 검색
	// 나이가 같은 포켓몬 한명만 검색할것인지? 아니면 전체 다 검색할것인지?
	public static PokemonDTO pokemonAge(int pokemonAge) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonDTO pokemon2 = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon where pokemon_age=?");
			pstmt.setInt(1, pokemonAge);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemon2 = PokemonDTO.pokemonDTOBuilder()
							.pokemonId(rset.getInt(1))
							.pokemonName(rset.getString(2))
							.pokemonAge(rset.getInt(3))
							.pokemonType(rset.getString(4))
							.pokemonPower(rset.getInt(5))
							.pokemonLegend(rset.getBoolean(6)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemon2;
	}

	
	// 포켓몬 타입으로 검색
	// 타입이 같은 포켓몬 모두 검색할려면 while문으로 리스트에 넣어서 출력해야 할듯
	public static PokemonDTO pokemonType(String pokemonType) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonDTO pokemon2 = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon where pokemon_type=?");
			pstmt.setString(1, pokemonType);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemon2 = PokemonDTO.pokemonDTOBuilder()
							.pokemonId(rset.getInt(1))
							.pokemonName(rset.getString(2))
							.pokemonAge(rset.getInt(3))
							.pokemonType(rset.getString(4))
							.pokemonPower(rset.getInt(5))
							.pokemonLegend(rset.getBoolean(6)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemon2;
	}
	
	// 능력치 검색
	// 이상인 포켓몬 모두 검색할려면 while문으로 리스트에 넣어서 출력해야 할듯
	public static PokemonDTO pokemonPower(int pokemonPower) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonDTO pokemon2 = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon where pokemon_power >=?");
			pstmt.setInt(1, pokemonPower);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemon2 = PokemonDTO.pokemonDTOBuilder()
							.pokemonId(rset.getInt(1))
							.pokemonName(rset.getString(2))
							.pokemonAge(rset.getInt(3))
							.pokemonType(rset.getString(4))
							.pokemonPower(rset.getInt(5))
							.pokemonLegend(rset.getBoolean(6)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemon2;
	}
	
	// 포켓몬 레전드 유무로 검색
	
	// 포켓몬 추가
	public static boolean addPokemon(PokemonDTO pokemon) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into pokemon values(?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pokemon.getPokemonId());
			pstmt.setString(2, pokemon.getPokemonName());
			pstmt.setInt(3, pokemon.getPokemonAge());
			pstmt.setString(4, pokemon.getPokemonType());
			pstmt.setInt(5, pokemon.getPokemonPower());
			pstmt.setBoolean(6, pokemon.isPokemonLegend());

			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// CREATE Required -> 주경님  * 포켓몬 id는 update X 
	// 포켓몬 수정 1.  : 포켓몬 이름으로 검색해서 /
	// 이름 외 다른 정보들 수정 (받아온 pokemon 객체에 있는 정보로 업데이트
	// public static boolean updatePokemon(int PokemonId, PokemonDTO pokemon){ ... }


	// 포켓몬 수정
	public static boolean updatePokemon(String pokemonName, int pokemonPower) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement("update pokemon set pokemon_power=? where pokemon_name=?");
			pstmt.setInt(1, pokemonPower);
			pstmt.setString(2, pokemonName);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 포켓몬 삭제
	public static boolean deletePokemon(String pokemonName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from pokemon where pokemon_name=?");

			pstmt.setString(1, pokemonName);

			int result = pstmt.executeUpdate();
			
			if (result != 0) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
}