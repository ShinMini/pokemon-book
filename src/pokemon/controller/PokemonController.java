package pokemon.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import pokemon.model.PokemonService;

//현 로직 : view.RunningStrartView에서 호출 
public class PokemonController {
	public static Scanner sc = new Scanner(System.in);
	public static PokemonService PS = new PokemonService();


	public static void control(int mainOption) {
		int subOption = 0;

		subOption = getOption(mainOption);
		callServiceClass(mainOption, subOption);
		
	}

	public static int getOption(int mainOption) {
		if(mainOption == 1) {
			System.out.println("\n =========== 포켓몬 도감 조회 ============== \n");
			System.out.println(" 1. 포켓몬 도감 전체 조회 \n 2. 포켓몬 도감 인덱스로 조회하기 \n");
		}
		if(mainOption == 2) {
			System.out.println("\n =========== 포켓몬 조회 ============== \n");
			System.out.println(" 1. 포켓몬 전체 조회 \n 2. 포켓몬 id로 조회하기 \n 3. 포켓몬 id로 추가 \n 4. 포켓몬 id로 수정하기 \n 5. 포켓몬 id로 삭제하기 \n");
		}
		if(mainOption == 3) {
			System.out.println("\n =========== 포켓몬 도감 조회 ============== \n");
			System.out.println(" 1. 포켓몬 도감 전체 조회 \n 2. 포켓몬 도감 인덱스로 조회하기 \n");
		}			
		int subOption = sc.nextInt();

		return subOption;
	}



	// 1번 -> pokemonBook, 2번 -> pokemon, 3번 -> owner
	public static void callServiceClass(int mainOption, int subOption) throws SQLException {
		if(mainOption == 1) {
			// pokemonBook 사용할 거임.
			pokemonBookSelected(subOption);
		}		
		if(mainOption == 2) {
			pokemonSelected(subOption);
		}
	}

	public static void pokemonBookSelected(int subOption) throws SQLException {
		switch (subOption) {
		case 1 ://  모든 테이블 검색
			PokemonService.getAllPokemonBooks();
			break;
		case 2 ://  포켓몬 도감 인덱스로 테이블 검색
			System.out.println("조회하실 포켓몬 도감 인덱스를 입력해주세요");
			int inputIndex = sc.nextInt();
			PokemonService.getPokemonBook(inputIndex);
			break;
		default:
			break;
		}
	}
	public static void pokemonSelected(int subOption) throws SQLException {
		String pokemonId;
		int inputColumn =0;
		String changeColumn = "";
		String[] changeColumns = {"name", "age", "type", "power", "legend"};

		switch (subOption) {
		case 1 ://  모든 테이블 검색
			PokemonService.getAllPokemons();
			break;
		case 2 ://  포켓몬 id로 포켓몬 정보수정하기
			System.out.println("\n =========== 포켓몬 id로 포켓몬 정보 수정하기 ============== \n");
			System.out.println("변경하실 포켓몬 id를 입력해주세요.");
			pokemonId = sc.nextLine();

			System.out.println(" 1. name \n 2. age \n 3. type \n 4. power \n 5. legend \n");
			System.out.println("변경하실 포켓몬 정보를 선택해주세요");
			inputColumn = sc.nextInt();

			System.out.println("변경할 값을 입력해주세요.");
			String set_update = sc.nextLine();

			// 포켓몬 업데이트
			PokemonService.updatePokemon(pokemonId, changeColumns[inputColumn -1], set_update);

		case 3 ://  포켓몬 id로 삭제하기
			System.out.println("삭제하실 포켓몬 아이디를 입력해주세요.");
			pokemonId = sc.nextLine();
			PokemonService.deletePokemon(pokemonId);
			break;

		default:
			break;
		}
	}
}
