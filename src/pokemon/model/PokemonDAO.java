//POKEMON DAO
package pokemon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lombok.Builder;
import pokemon.model.dto.PokemonDTO;
import pokemon.model.util.DBUtil;


public class PokemonDAO {
	
	// 모든 포켓몬 검색해서 반환
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
									.pokemonFeature(rset.getString(3))
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
	public static PokemonDTO getPokemonName(String pokemonName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonDTO pokemon = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon where pokemonname=?");
			pstmt.setString(1, pokemonName);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				pokemon = PokemonDTO.pokemonDTOBuilder()
							.pokemonId(rset.getInt(1))
							.pokemonName(rset.getString(2))
							.pokemonFeature(rset.getString(3))
							.pokemonType(rset.getString(4))
							.pokemonPower(rset.getInt(5))
							.pokemonLegend(rset.getBoolean(6)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemon;
	}

	
	// 포켓몬 특징으로 검색
	public static PokemonDTO PokemonFeature(String pokemonFeature) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonDTO pokemon = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon where pokemonfeature=?");
			pstmt.setString(1, pokemonFeature);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemon = PokemonDTO.pokemonDTOBuilder()
							.pokemonId(rset.getInt(1))
							.pokemonName(rset.getString(2))
							.pokemonFeature(rset.getString(3))
							.pokemonType(rset.getString(4))
							.pokemonPower(rset.getInt(5))
							.pokemonLegend(rset.getBoolean(6)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemon;
	}

	
	// 포켓몬 타입으로 검색
	public static PokemonDTO PokemonType(String pokemonType) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonDTO pokemon = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon where pokemontype=?");
			pstmt.setString(1, pokemonType);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemon = PokemonDTO.pokemonDTOBuilder()
							.pokemonId(rset.getInt(1))
							.pokemonName(rset.getString(2))
							.pokemonFeature(rset.getString(3))
							.pokemonType(rset.getString(4))
							.pokemonPower(rset.getInt(5))
							.pokemonLegend(rset.getBoolean(6)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemon;
	}
	
	// 능력치 검색
	public static PokemonDTO PokemonPower(String pokemonPower) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonDTO pokemon = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon where pokemonpower >=");
			pstmt.setString(1, pokemonPower);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemon = PokemonDTO.pokemonDTOBuilder()
							.pokemonId(rset.getInt(1))
							.pokemonName(rset.getString(2))
							.pokemonFeature(rset.getString(3))
							.pokemonType(rset.getString(4))
							.pokemonPower(rset.getInt(5))
							.pokemonLegend(rset.getBoolean(6)).build();
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return pokemon;
	}
	
	// 포켓몬 레전드 유무로 검색
	
	// 포켓몬 추가
	public static boolean addPokemon(PokemonDTO pokemon) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into activist values(?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pokemon.getPokemonId());
			pstmt.setString(2, pokemon.getPokemonName());
			pstmt.setString(3, pokemon.getPokemonFeature());
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

			pstmt = con.prepareStatement("update pokemon set pokmonpower=? where pokemonname=?");
			pstmt.setString(1, pokemonName);
			pstmt.setInt(2, pokemonPower);

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
			pstmt = con.prepareStatement("delete from pokemon where pokemonname=?");

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

	public static PokemonDTO getPokemon(int int1) {
		// TODO Auto-generated method stub
		return null;
	}
	
}