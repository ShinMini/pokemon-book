package pokemon.controller;

import java.sql.SQLException;

import pokemon.model.PokemonBookDAO;
import pokemon.model.PokemonDAO;
import pokemon.model.dto.OwnerDTO;
import pokemon.model.dto.PokemonBookDTO;
import pokemon.model.dto.PokemonDTO;
import poketmon.view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class PokemonController {

	// 모든 포켓몬 도감 검색 로직
	public static void getAllPokemonBooks() {
		try {
			RunningEndView.projectListView(PokemonBookDAO.getAllPokemonBook());
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 검색시 에러 발생");
		}
	}

	// 새로운 포켓몬 도감에 추가(저장)하는 로직
	public static boolean addPokemonBook(PokemonBookDTO pokemonBook) {
		boolean result = false;

		try {
			result = PokemonBookDAO.addPokemonBook(pokemonBook);
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 저장시 에러 발생");
		}
		return result;
	}

	// 모든 포켓몬 검색 로직
	public static void getPoketmon() {
		try {
			RunningEndView.activistListView(PokemonDAO.getAllActivists());
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("모든 재능 기부자 검색시 에러 발생");
		}
	}

	// pokemon 도감 index에 기록된 포켓몬(Pokemon) 정보 수정
	public static boolean updatePokemonBookPokemon(int pokemonBookId, PokemonDTO pokemon) {
		boolean result = false;
		try {
			result = PokemonBookDAO.updatePokemonBookPokemon(pokemonBookId, pokemon);	// 포켓몬 도감 index정보를 받아 해당 도감에 기록된 포켓몬 정보 수정
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("해당 [" + Integer.toString(pokemonBookId) + "]index의 포켓몬 정보 수정 실패");
		}
		return result;
	}

	// pokemon 도감 index에 기록된 포켓몬 주인(Owner) 정보 수정
	public static boolean updatePokemonBookOwner(int pokemonBookId, OwnerDTO owner) {
		boolean result = false;
		try {
			result = PokemonBookDAO.updatePokemonBookOwner(pokemonBookId, owner);	// 포켓몬 도감 index정보를 받아 해당 도감에 기록된 포켓몬 주인(Owner)정보 수정
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("해당 [" + Integer.toString(pokemonBookId) + "]index의 포켓몬 주인(Owner) 정보 수정 실패");
		}
		return result;
	}

	// 프로보노 정보 검색
	public static void getProbono(String probonoId) {
		try {
			RunningEndView.allView(ProbonoDAO.getProbono(probonoId));
		} catch (SQLException e) {
			e.printStackTrace();
			RunningEndView.showError("프로보노 id로 해당 프로보노 검색 오류 ");
		}
	}
	
	
	
}
