package pokemon.controller;

import java.sql.SQLException;

import pokemon.model.PokemonDAO;
import pokemon.model.dto.PokemonDTO;
import poketmon.view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class PokemonController {

	// 모든 포켓몬 검색
	public static void getAllPokemons() {
		try {
			RunningEndView.pokemonListView(PokemonDAO.getAllPokemons());
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 검색시 에러 발생");
		}
	}

	// 새로운 포켓몬 저장
	// 리턴값을 돌려줄 필요가 없다고 생각해서 void 추가
	public static void addPokemon(PokemonDTO pokemon) {
		boolean result = false;

		try {
			result = PokemonDAO.addPokemon(pokemon);
			if (result == true) {
				RunningEndView.showMsg("새로운 포켓몬을 등록했습니다");
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("새로운 포켓몬 등록중 오류 발생");
		}
	}

	// 포켓몬 삭제 로직
	public static void deletePokemon(String pokemonName) {
		try {
			boolean result = PokemonDAO.deletePokemon(pokemonName);
			if (result == true) {
				RunningEndView.showMsg("삭제에 성공했습니다");
			} else {
				RunningEndView.showError("삭제에 실패하였습니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			RunningEndView.showError("오류발생 다시 시도해 주세요");
		}
	}

	// pokemonNam으로 pokemonPoewr수정
	public static void updataPokemon(String pokemonName, int pokemonPower) {
		boolean result = false;
		try {
			result = PokemonDAO.updatePokemon(pokemonName, pokemonPower);
			if (result == true) {
				RunningEndView.showMsg(pokemonName + "의 능력치를 " + pokemonPower + "로 변경하였습니다");
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("오류발생 다시 시도해주세요");
		}
	}

	// 포켓몬 정보 검색
	public static void getPokemon(String pokemonName) {
		try {
			if (PokemonDAO.getPokemon(pokemonName) != null) {
				RunningEndView.allView(PokemonDAO.getPokemon(pokemonName));
			} else {
				RunningEndView.showError("검색 대상이 존재하지 않습니다");
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
			RunningEndView.showError("검색중 오류 발생 ");
		}
	}
	// PokemonAge PokemonType PokemonPower

	// 포켓몬 나이로 검색
	public static void pokemonAge(int pokemonAge) {
		try {
			if (PokemonDAO.pokemonAge(pokemonAge) != null) {
				RunningEndView.allView(PokemonDAO.pokemonAge(pokemonAge));
			} else {
				RunningEndView.showError("검색 대상이 존재하지 않습니다");
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
			RunningEndView.showError("검색중 오류 발생 ");
		}
	}
	
	// 포캣몬 타입으로 검색
	public static void pokemonType(String pokemonType) {
		try {
			if (PokemonDAO.pokemonType(pokemonType) != null) {
				RunningEndView.allView(PokemonDAO.pokemonType(pokemonType));
			} else {
				RunningEndView.showError("검색 대상이 존재하지 않습니다");
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
			RunningEndView.showError("검색중 오류 발생 ");
		}
	}
	
	// 포켓몬 파워로 검색
	public static void pokemonPower(int pokemonPower) {
		try {
			if (PokemonDAO.pokemonPower(pokemonPower) != null) {
				RunningEndView.allView(PokemonDAO.pokemonPower(pokemonPower));
			} else {
				RunningEndView.showError("검색 대상이 존재하지 않습니다");
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
			RunningEndView.showError("검색중 오류 발생 ");
		}
	}
	
}
