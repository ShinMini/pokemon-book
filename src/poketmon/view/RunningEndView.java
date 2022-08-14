package poketmon.view;

import java.util.ArrayList;

import pokemon.model.dto.OwnerDTO;
import pokemon.model.dto.PokemonBookDTO;
import pokemon.model.dto.PokemonDTO;

public class RunningEndView {

	/* ---------- === 포켓몬 정보 출력 === -----------------------------*/

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

	//특정 포켓몬 정보 출력 
	public static void pokemon(PokemonDTO pokemon){
		System.out.println(pokemon);		
	}


	/* ---------- === 오너(Owner) 정보 출력 === -----------------------------*/

	// owner 전체 출력을 위한 메소드 추가
	public static void ownerListView(ArrayList<OwnerDTO> ownerList){

		int listSize = ownerList.size();

		if(listSize != 0) {
			int count = 1;

			for(OwnerDTO p : ownerList) {
				// ; 정상 종료시 count 변수값 1 증가
				System.out.println("검색 정보 " + (count++) + " - " + p); 
			} 
		} else {
			System.out.println("검색 정보 0");
		}
	}


	//특정 포켓몬 오너의 정보 출력 
	public static void ownerView(OwnerDTO owner){
		System.out.println(owner);		
	}

		/* ---------- === 포켓몬 도감 정보 출력 === -----------------------------*/

		// 모든 포켓몬 도감 정보 출력
		public static void pokemonBookListView(ArrayList<PokemonBookDTO> pokemonBookList) {
			int listSize = pokemonBookList.size();

			if(listSize != 0) {
				int count = 1;
				for(PokemonBookDTO p : pokemonBookList) {
					System.out.println("검색 정보 " + (count++) + " - " + p);
				}
			} else {
				System.out.println("검색 정보 0");
			}
		}

		//특정 포켓몬 도감 정	보 출력 
		public static void pokemonBookView(PokemonBookDTO pokemonBook){
			System.out.println(pokemonBook);		
		}

		//?? 모든 DTO 정보 출력하는 메소드	요거는 잘 모르겠음. 
		/* 호출할 때 allView(objectType objectInstance); 이런식으로 호출하면 모든 메서드가 적용되는경우,
		 *  pokemonView, ownerView 메서드는 필요 없어짐 
		 *  => 차후 view에서 호출 실험 후 정상 작동 되는 경우, 위 메서드를 아래 코드로 변경 예정 */

		public static void allView(Object object){ // upcasting 활용
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