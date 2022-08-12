/* 	private String pokemonId;
	private String pokemonName;
	private String pokemonFeature;
	private String pokemonType;
	private int pokemonPower;
	private String pokemonLegend;
);  */
package pokemon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import lombok.Builder;
import pokemon.model.dto.PokemonDTO;
import pokemon.model.util.DBUtil;

//포켓몬 DAO 클래스
public class PokemonDAO {
	
	// 모든 포켓몬 검색해서 반환
	public static ArrayList<PokemonDTO> getAllActivists() throws SQLException {
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
		PokemonDTO pokemon1 = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon where pokemonName=?");
			pstmt.setString(1, pokemonName);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemon1 = PokemonDTO.pokemonDTOBuilder()
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
		return pokemon1;
	}

	
	// 포켓몬 특징으로 검색
	public static PokemonDTO PokemonFeature(String pokemonFeature) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PokemonDTO pokemon2 = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from pokemon where pokemonFeature=?");
			pstmt.setString(1, pokemonFeature);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pokemon2 = PokemonDTO.pokemonDTOBuilder()
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
		return pokemon2;
	}

	
	// 포켓몬 타입으로 검색
	
	// 능력치 검색
	
	// 포켓몬 레전드 유무로 검색
	
	// 포켓몬 추가
	public static boolean addPokemon(PokemonDTO pokemon) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into activist values(?, ?, ?, ?)");
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
	
	// 포켓몬 수정
	public static boolean updatePokemon(String pokemonName, int pokemonPower) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();

			pstmt = con.prepareStatement("update pokemon set pokmonPower=? where pokemonName=?");
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
			pstmt = con.prepareStatement("delete from pokemon where pokemonName=?");

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