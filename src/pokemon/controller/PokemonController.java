package pokemon.controller;

import java.sql.SQLException;
import java.util.Scanner;

import pokemon.exception.NotExistException;
import pokemon.model.PokemonService;
import pokemon.view.EndView;

//현 로직 : view.RunningStrartView에서 호출 
public class PokemonController {
	public static Scanner sc = new Scanner(System.in);
	public static PokemonService PS = new PokemonService();


	public static void control(int mainOption) throws SQLException, NotExistException {
		int subOption = 0;

		subOption = getOption(mainOption);
		callServiceClass(mainOption, subOption);
		
	}

	public static int getOption(int mainOption) {
		if(mainOption == 1) {
			System.out.println("\n =========== 포켓몬 도감 조회 ============== \n");
			System.out.println(" 1. 포켓몬 도감 전체 조회 \n 2. 포켓몬 도감 인덱스로 조회하기 \n 3. 포켓몬 도감 인덱스로 업데이트하기 \n 4. 포켓몬 도감 인덱스로 삭제하기 \n");
		}
		if(mainOption == 2) {
			System.out.println("\n =========== 포켓몬 조회 ============== \n");
			System.out.println(" 1. 포켓몬 전체 조회 \n 2. 특정 포켓몬 조회하기 \n 3. 포켓몬 id로 update \n 4. 포켓몬 id로 delete \n 5. 포켓몬 id로 삭제하기 \n");
		}
		if(mainOption == 3) {
			System.out.println("\n =========== owner 조회 ============== \n");
			System.out.println(" 1. owner 전체 조회 \n 2. owner 조회하기 \n 3. owner id로 업데이트하기 \n 4. owner 삭제하기");
		}			
		int subOption = sc.nextInt();

		return subOption;
	}



	// 1번 -> pokemonBook, 2번 -> pokemon, 3번 -> owner
	public static void callServiceClass(int mainOption, int subOption) throws SQLException, NotExistException {
		if(mainOption == 1) {
			pokemonBookSelected(subOption);
		}		
		if(mainOption == 2) {
			pokemonSelected(subOption);
		}
		if(mainOption == 3) {
			ownerSelected(subOption);
		}
	}

	public static void pokemonBookSelected(int subOption) throws SQLException, NotExistException {
		int inputIndex = 0;
		switch (subOption) {
		case 1 ://  모든 테이블 검색
			EndView.pokemonBookListView(PokemonService.getAllPokemonBooks());
			break;
		case 2 ://  포켓몬 도감 인덱스로 테이블 검색
			inputIndex = sc.nextInt();
			EndView.pokemonBookView(PokemonService.getPokemonBook(inputIndex));
			break;
		case 3 ://  포켓몬 도감 해당 인덱스로 업데이트
			inputIndex = sc.nextInt();
			PokemonService.deletePokemonBook(inputIndex);
			System.out.println("업데이트에 성공했습니다.");
			break;
		case 4 ://  포켓몬 도감 해당 인덱스로 삭제
			inputIndex = sc.nextInt();
			PokemonService.deletePokemonBook(inputIndex);
			System.out.println("삭제에 성공했습니다.");
			break;
		default:
			break;
		}
	}
	public static void pokemonSelected(int subOption) throws SQLException, NotExistException {
		String pokemonId;
		String getColumn ="";
		String getRead ="";
		int inputColumn =0;
		String[] changeColumns = {"name", "age", "type", "power", "legend"};

		switch (subOption) {
		case 1 ://  모든 테이블 검색
			EndView.pokemonListView(PokemonService.getAllPokemons());
			break;
		case 2 ://  특정 포켓몬 검색
			System.out.println("\n =========== 포켓몬 정보 검색하기 ============== \n");
			System.out.println(" 1. name \n 2. age \n 3. type \n 4. power \n 5. legend \n");
			System.out.println("검색 하실 포켓몬 column를 선택해주세요: ");
			getColumn = sc.nextLine();
			System.out.println("검색 하실 포켓몬 "+ getColumn + "값을 입력해주세요: ");
			getRead = sc.nextLine();

			System.out.println("\n =========== 포켓몬 검색 결과 ============== \n");
			EndView.pokemonView(PokemonService.getPokemon(getColumn, getRead));
			break;
		case 3 ://  포켓몬 id로 포켓몬 정보수정하기
			System.out.println("\n =========== 포켓몬 id로 포켓몬 정보 수정하기 ============== \n");
			System.out.println("변경하실 포켓몬 id를 입력해주세요.");
			pokemonId = sc.nextLine();

			System.out.println(" 1. name \n 2. age \n 3. type \n 4. power \n 5. legend \n");
			System.out.println("변경하실 포켓몬 정보를 선택해주세요");
			inputColumn = sc.nextInt();

			System.out.println("변경할 값을 입력해주세요.");
			String set_update = sc.nextLine();

			PokemonService.updatePokemon(pokemonId, changeColumns[inputColumn -1], set_update);
			System.out.println("업데이트에 성공했습니다.");
		case 4 ://  포켓몬 id로 삭제하기
			System.out.println("삭제하실 포켓몬 아이디를 입력해주세요.");
			pokemonId = sc.nextLine();

			PokemonService.deletePokemon(pokemonId);
			System.out.println("삭제에 성공했습니다.");
			break;
		default:
			break;
		}
	}
	
	public static void ownerSelected(int subOption) throws SQLException, NotExistException {
		String ownerId;
		int inputColumn =0;
		String[] changeColumns = {"name", "age", "type", "power", "legend"};

		switch (subOption) {
		case 1 ://  모든 테이블 검색
			EndView.ownerListView(PokemonService.getAllOwners());
			break;
		case 2 ://  특정 오너 검색
			System.out.println("검색하실 owner id 값을 입력해주세요: ");
			ownerId = sc.nextLine();
			EndView.ownerView(PokemonService.getOwner(ownerId));
			break;
		case 3 ://  오너 id로 owner 정보수정하기
			System.out.println("\n =========== owner id로 owner 정보 수정하기 ============== \n");
			System.out.println("변경하실 owner id를 입력해주세요.");
			ownerId = sc.nextLine();

			System.out.println(" 1. name \n 2. age \n 3. type \n 4. power \n 5. legend \n");
			System.out.println("변경하실 owner 정보를 선택해주세요");
			inputColumn = sc.nextInt();

			System.out.println("변경할 값을 입력해주세요.");
			String set_update = sc.nextLine();

			PokemonService.updateOwner(ownerId, changeColumns[inputColumn -1], set_update);
			System.out.println("업데이트에 성공했습니다.");
		case 4 ://  포켓몬 id로 삭제하기
			System.out.println("삭제하실 owner 아이디를 입력해주세요.");
			ownerId = sc.nextLine();
			PokemonService.deleteOwner(ownerId);
			System.out.println("삭제에 성공했습니다.");
			break;

		default:
			break;
		}
	}
}
