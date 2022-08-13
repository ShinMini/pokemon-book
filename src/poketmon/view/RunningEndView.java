package poketmon.view;

import java.util.ArrayList;

import pokemon.model.dto.OwnerDTO;
import pokemon.model.dto.PokemonBookDTO;
import pokemon.model.dto.PokemonDTO;

public class RunningEndView {
	
	//모든 포켓몬 출력
	public static void pokemonListView(ArrayList<PokemonDTO> pokemonList){
		
		int listSize = pokemonList.size();
		
		if(listSize != 0) {
			int count = 1;
			
			for(PokemonDTO p : pokemonList) {
				// ; 정상 종료시 count 변수값 1 증가
				System.out.println("검색 정보 " + (count++) + " - " + p); 
			} 
			
		}else {
			System.out.println("검색 정보 0");
		}
	}

	// owner 전체 출력을 위한 메소드 추가
	public static void ownerListView(ArrayList<OwnerDTO> ownerList){
		
		int listSize = ownerList.size();
		
		if(listSize != 0) {
			int count = 1;
			
			for(OwnerDTO p : ownerList) {
				// ; 정상 종료시 count 변수값 1 증가
				System.out.println("검색 정보 " + (count++) + " - " + p); 
			} 
			
		}else {
			System.out.println("검색 정보 0");
		}
	}
	// 별도의 activist view method를 구현해서 제네릭 적용
	public static void activistListView(ArrayList<PoketmonTypeDTO> activistList) {
		int listSize = activistList.size();
		
		if(listSize != 0) {
			int count = 1;
			for(PoketmonTypeDTO a : activistList) {
				System.out.println("검색 정보 " + (count++) + " - " + a);
			}
		} else {
			System.out.println("검색 정보 0");
		}
	}
		
	//특정 프로젝트 출력 
	public static void projectView(PokemonBookDTO project){
		System.out.println(project);		
	}
	
	//?? 모든 DTO 정보 출력하는 메소드
	// upcasting 활용
	public static void allView(Object object){
		System.out.println(object);
	}
	
	//예외 상황 출력
	public static void showError(String message){
		System.out.println(message);		
	}
	
	public static void showMsg(String message) {
		System.out.println(message);
	}
		
}
