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
	/** INSERT */
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
	/** 검색 SELECT */
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

	// [READ] 포켓몬 검색 ex) getPokemon("name", "파이리");
	public static PokemonDTO getPokemon(String getColumn, String getRead) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonDTO pokemon = null;
		try {
			con = DBUtil.getConnection();

			pstmt = setReadColumn(getColumn, getRead, con, pstmt);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemon = PokemonDTO.pokemonDTOBuilder()
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
		return pokemon;
	}

	public static PreparedStatement setReadColumn(String getColumn, String getRead, Connection con, PreparedStatement pstmt) {	// update할 대상을 정해줌. 
		try {
			pstmt = con.prepareStatement("select * from pokemon where pokemon_" + getColumn +"=?");
			pstmt.setString(1, getRead);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}


	/** 수정 UPDATE */
	// [UPDATE]	포켓몬 id를 받아와, 유저가 원하는 포켓몬 column의 value 수정 // updateColmn = 유저가 업데이트를 원하는 column 값 
	public static boolean updatePokemon(int pokemonId, String toUpdate, String updateColumn) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();	// connection 확인

			pstmt = setUpdateColumn(pokemonId, updateColumn, toUpdate, con, pstmt);	// connection 정상 실행시 update 실행
			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}

	// update할 column에 따라 sql query문 다르게 실행
	public static PreparedStatement setUpdateColumn(int pokemonId, String updateColumn, String toUpdate,Connection con, PreparedStatement pstmt) {	// update할 대상을 정해줌. 
		try {
			pstmt = con.prepareStatement("update pokemon set pokemon_"+ updateColumn + "=? where pokemon_id=?");
			pstmt.setString(1, toUpdate);
			pstmt.setInt(2, pokemonId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	/** 삭제 DELETE */
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