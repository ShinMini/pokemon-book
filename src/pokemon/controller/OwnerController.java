package pokemon.controller;

import java.sql.SQLException;

import pokemon.model.OwnerDAO;
import pokemon.model.dto.OwnerDTO;
import poketmon.view.RunningEndView;

//현 로직 : view.RunningStrartView에서 호출 
public class OwnerController {
	
	// 모든 소유자 검색
	public static void getAllOwners() {
		try {
			RunningEndView.ownerListView(OwnerDAO.getAllOwners());
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("모든 프로젝트 검색시 에러 발생");
		}
	}

	// 새로운 소유자 저장
	// 리턴값을 돌려줄 필요가 없다고 생각해서 void 추가
	public static void addOwner(OwnerDTO owner) {
		boolean result = false;

		try {
			result = OwnerDAO.addOwner(owner);
			if (result == true) {
				RunningEndView.showMsg("새로운 소유자를 등록했습니다");
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("새로운 소유자 등록중 오류 발생");
		}
	}

	// 소유자 삭제 로직
	public static void deleteOwner(int ownerId) {
		try {
			boolean result = OwnerDAO.deleteOwner(ownerId);
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

	// ownerId로 티어 수정
	// 리턴값을 돌려줄 필요가 없다고 생각해서 void 추가
	public static void updateOwner(int ownerId, String tier) {
		boolean result = false;
		try {
			result = OwnerDAO.updateOwner(ownerId, tier);
			if (result == true) {
				RunningEndView.showMsg(ownerId + "번의 티어를 " + tier + "로 변경하였습니다");
			}
		} catch (SQLException s) {
			s.printStackTrace();
			RunningEndView.showError("오류발생 다시 시도해주세요");
		}
	}

	// 소유자 정보 검색
	public static void getOwner(int ownerId) {
		try {
			if(OwnerDAO.getOwnerId(ownerId) != null) {
				RunningEndView.allView(OwnerDAO.getOwnerId(ownerId));
			}else{
				RunningEndView.showError("검색 대상이 존재하지 않습니다");
			};
		} catch (SQLException e) {
			e.printStackTrace();
			RunningEndView.showError("검색중 오류 발생 ");
		}
	}
	
	
	
}
