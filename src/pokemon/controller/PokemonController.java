package pokemon.controller;

import java.util.Scanner;

import pokemon.model.PokemonService;

//현 로직 : view.RunningStrartView에서 호출 
public class PokemonController {
	public static Scanner sc = new Scanner(System.in);
	public static PokemonService PS = new PokemonService();


	// 
	public static void control(String msg) {
		System.out.println(msg + "함수 호출");

		String input = sc.nextLine();
		callServiceClass(msg, input);


	}

	public static void callServiceClass(String msg, String input) {

		if(msg.equals("pokemonBook")) {

		System.out.println("\n =========== 포켓몬 도감 조회 ============== \n");
		System.out.println(" 1. 포켓몬 도감 전체 조회 \n 2. 포켓몬 도감 인덱스로 조회하기 \n");

			switch (input) {
			case"1" ://  모든 테이블 검색
				PokemonService.getAllPokemonBooks();
				break;
			case"2" ://  포켓몬 도감 인덱스로 테이블 검색
				PokemonService.getPokemonBook();
				break;
			default:
				break;

			}
		}		
		if(msg.equals("pokemon")) {

		System.out.println("\n =========== 포켓몬 조회 ============== \n");
		System.out.println(" 1. 포켓몬 전체 조회 \n 2. 포켓몬 id로 조회하기 \n 3. 포켓몬 id로 update \n 4. 포켓몬 id로 조회하기 \n");

			switch (input) {
			case"1" ://  모든 테이블 검색
				PokemonService.getAllPokemonBooks();
				break;
			case"2" ://  포켓몬 도감 인덱스로 테이블 검색
				PokemonService.getPokemonBook();
				break;
			case"3" ://  포켓몬 도감 포켓몬 테이블 검색
		System.out.println("\n =========== 포켓몬 업데이트 ============== \n");
		System.out.println(" 1. 포켓몬 name update \n 2. 포켓몬 id로 조회하기 \n 3. 포켓몬 id로 update \n 4. 포켓몬 id로 조회하기 \n");
		input = sc.nextLine();
		System.out.println("먼저 변경하실 포켓몬 아이디를 입력해주세요")
		input_id = sc.nextLine();

		System.out.println("변경하실 포켓몬 정보를 선택해주세요");
		System.out.println(" 1. type \n 2. 포켓몬 age\n 3. 포켓몬 id로 update \n 4. 포켓몬 id로 조회하기 \n");
		input_column = sc.nextLine();
		String changeColumn = "";
		if(input_column.equals("1")) {
			changeColumn = "type";
		}
		if(input_column.equals("2")) {
			changeColumn = "age";
		}

		System.out.println("어케 바꿀까요>");
		String set_update = sc.nextLine();
		
			pokemonUpdate(input_id, changeColumn, set_update);
			
				PokemonService.pokemonUpdate();
				break;
			default:
				break;

			}
		}
	}
}
