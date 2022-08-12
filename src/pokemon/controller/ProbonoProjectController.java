package pokemon.controller;

import java.sql.SQLException;

import pokemon.model.PokemonDAO;
import pokemon.model.ProbonoDAO;
import pokemon.model.PokemonBookDAO;
import pokemon.model.dto.PokemonBookDTO;
import poketmon.view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class ProbonoProjectController {

	// 모든 프로젝트 검색 로직
	public static void getAllProbonoProjects() {
		try {
			RunningEndView.projectListView(PokemonBookDAO.getAllProbonoProjects());
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 검색시 에러 발생");
		}
	}

	// 새로운 프로젝트 저장 로직
	public static boolean addProbonoProject(PokemonBookDTO probonoProject) {
		boolean result = false;

		try {
			result = PokemonBookDAO.addProbonoProject(probonoProject);
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 저장시 에러 발생");
		}
		return result;
	}

	// 모든 프로젝트 검색 로직
	public static void getAllActivists() {
		try {
			RunningEndView.activistListView(PokemonDAO.getAllActivists());
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("모든 재능 기부자 검색시 에러 발생");
		}
	}

	// 프로보노 아이디로 프로보노 목적 수정
	public static boolean updateProbono(String probonoId, String probonoPurpose) {
		boolean result = false;
		try {
			result = ProbonoDAO.updateProbono(probonoId, probonoPurpose);
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("프로보노 id로 프로보노 목적 변경 오류");
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
