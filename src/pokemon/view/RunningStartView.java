package pokemon.view;

// import pokemon.controller.OwnerController;
// import pokemon.controller.PokemonController;
import pokemon.model.dto.OwnerDTO;
import pokemon.model.dto.PokemonDTO;
import pokemon.controller.PokemonController;

public class RunningStartView {
	// 희돈님이 보내주신 파일로 수정 완료

	public static void main(String[] args) {
		printQuery();
	}

	public static void printQuery() {
//		System.out.println("**** 모든 포켓몬 검색 ****");
//		PokemonController.getAllPokemons();
		
//		System.out.println("\\n**** 특정 포켓몬 검색 ****");
//		PokemonController.getPokemon("Squirtle");
		
//		System.out.println("\n**** 특정 포켓몬 추가 ****");
//		PokemonController.addPokemon(new PokemonDTO(16, "홍길동", 50, "물", 400, true));
//		PokemonController.getPokemon("홍길동");
		
//		System.out.println("\n**** 특정 나이로 포켓몬 검색 ****");
//		PokemonController.pokemonAge(50);
		
//		System.out.println("\n**** 특정 파워로 포켓몬 검색");
//		PokemonController.pokemonPower(400);
//		
//		System.out.println("\n**** 특정 타입으로 포켓몬 검색");
//		PokemonController.pokemonType("물");
		
//		System.out.println("\n**** 포켓몬 이름으로 파워 수정 ****");
//		PokemonController.getPokemon("홍길동");
//		PokemonController.updataPokemon("홍길동", 999);
//		PokemonController.getPokemon("홍길동");

		
		System.out.println("\\n**** 특정 포켓몬 삭제 ****");
		PokemonController.deletePokemon("홍길동");
		PokemonController.getPokemon("홍길동");

		
		
		
//		여기까지가 Owner 테스트 ************************************************************************
//		System.out.println("**** 모든 소유자 검색 ****");
//		OwnerController.getAllOwners();
//		
//		System.out.println("\n**** 특정 소유자 검색 ****");
//		OwnerController.getOwner(10);
		
//		System.out.println("\n**** 소유자 신규 등록 ****");
//		OwnerController.addOwner(new OwnerDTO(16, "홍길동", 99, "챌린저"));
//		OwnerController.getOwner(16);
		
		
//		System.out.println("\n**** 소유자 티어 수정 ****");
//		OwnerController.updateOwner(16, "gold");
//		OwnerController.getOwner(16);
		
//		System.out.println("\n**** 소유자 삭제 ****");
//		OwnerController.deleteOwner(16);;
//		OwnerController.getOwner(16);

	}
}