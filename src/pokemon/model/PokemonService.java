package pokemon.model;

import java.sql.SQLException;
import java.util.ArrayList;

import pokemon.exception.NotExistException;
import pokemon.model.dto.OwnerDTO;
import pokemon.model.dto.PokemonBookDTO;
import pokemon.model.dto.PokemonDTO;

//서버 내부에서 하나의 객체수를 보장하면서 서비스하게 되는 singleton design 구조

public class PokemonService {

	private static PokemonService instance = new PokemonService();

	private PokemonService() {}

	public static PokemonService getInstance() {
		return instance;
	}

	/*------------- === 	포켓몬 관련 service   === -------------------------*/ 

	// 모든 Pokemon 정보 반환
	public static ArrayList<PokemonDTO> getAllPokemons() throws SQLException {	// PoketmonDTO type을 갖는 ArrayList 반환
		return PokemonDAO.getAllPokemons();
	}
	// Pokemon 이름으로 검색
	public static PokemonDTO getPokemon(String pokemonName) throws SQLException, NotExistException {
		PokemonDTO pokemon = PokemonDAO.getPokemonName(pokemonName);
		if (pokemon == null) {
			throw new NotExistException("검색하신 포켓몬 정보가 없습니다.");
		}
		return pokemon;
	}
	// 새로운 pokemon 저장
	public static boolean addPokemon(PokemonDTO pokemon) throws SQLException {
		return PokemonDAO.addPokemon(pokemon);
	}


	// 기존 pokemon 수정 (이름으로 검색해서 수정 * 이때 id값은 건드리지 말기)
	public static boolean updatePokemon(String pokemonName, PokemonDTO pokemon) throws SQLException, NotExistException {
		notExistPokemon(pokemonName);
		return PokemonDAO.updatePokemon(pokemonName, pokemon);
	}

	// pokemon 삭제
	public boolean deletePokemon(String pokemonId) throws SQLException, NotExistException {
		notExistPokemon(pokemonId);
		return PokemonDAO.deletePokemon(pokemonId);
	}



	/*------------- === Owner 관련 Service === -----------------------------------*/

	// 정상적으로 작동할 경우 true, 실패시 false 반환
	public static boolean addOwner(OwnerDTO owner) throws SQLException {
		return OwnerDAO.addOwner(owner);
	}

	public static boolean updateOwner(int ownerId, String ownerTier) throws SQLException, NotExistException {
		notExistOwner(ownerId);	// owner 하위 function들 exception 구문 체크 필요
		return OwnerDAO.updateOwner(ownerId, ownerTier);
	}

	public boolean deleteOwner(int ownerId) throws SQLException, NotExistException {
		notExistOwner(ownerId);
		return OwnerDAO.deleteOwner(ownerId);
	}

	public static OwnerDTO getOwner(int ownerId) throws SQLException, NotExistException {
		OwnerDTO owner = OwnerDAO.getOwnerId(ownerId);
		if (owner == null) {
			throw new NotExistException("검색하는 포켓몬 마스터가 존재하지 않습니다.");
		}
		return owner;
	}

	public static ArrayList<OwnerDTO> getAllOwners() throws SQLException {
		return OwnerDAO.getAllOwners();
	}


	/*------------- === Pokemon Book 관련 Service === -----------------------------------*/

	// 아래도 마찬가지로 PokemonDAO와 유사하게 PokemonBookDAO 수정 필요
	public static boolean addPokemonBook(PokemonBookDTO pokemonBook) throws SQLException {
		return PokemonBooDAO.addPokemonBook(pokemonBook);
	}

	// pokemonBook update 도감 index로 검색해 해당 포켓몬 및 주인정보 수정
	public static boolean updatePokemonBook(int pokemonBookId, int pokemonId, int ownerId)
			throws SQLException, NotExistException {
		notExistPokemonBook(pokemonBookId);
		return PokemonBookDAO.updatePokemonBook(pokemonBookId, pokemonId, ownerId);
	}

	//pokemonBook delete // 도감 index로 검색해 해당 도감 삭제
	public boolean deletePokemonBook(int pokemonBookId) throws SQLException, NotExistException {
		notExistPokemonBook(pokemonBookId);
		return PokemonBookDAO.deletePokemonBook(pokemonBookId);
	}

	// 해당 도감 기록 검색
	public static PokemonBookDTO getPokemonBook(int pokemonBookId) throws SQLException {
		return PokemonBookDAO.getPokemonBookId(pokemonBookId);
	}

	public static ArrayList<PokemonBookDTO> getAllPokemonBooks() throws SQLException {
		return PokemonBookDAO.getAllPokemonBook();
	}



	/***************** exist 검사 METHOD ************************/

	// Pokemon - CRUD	// 이름으로 포켓몬 검색
	public static void notExistPokemon(String pokemonName) throws NotExistException, SQLException {
		PokemonDTO pokemon;
		try {
			pokemon = PokemonDAO.getPokemonName(pokemonName);	// parameter로 유입된 id에 일치하는 poketmon 객체가 존재하지 않는 경우, 
			if (pokemon == null) {
				throw new NotExistException("검색하신 포켓몬 정보가 없습니다.");	// throw notExitstException 호출
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// ProjectUserDAO - CRUD	// 포켓몬 도감 index 번호로 도감 번호 조회
	public static void notExistPokemonBook(int pokemonBookId) throws NotExistException, SQLException {
		PokemonBookDTO pokemonBook;
		try {
			pokemonBook = PokemonBookDAO.getPokemonBookId(pokemonBookId);
			if (pokemonBook == null) {
				throw new NotExistException("검색하신 도감 정보가 존재하지 않습니다.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	// PokemonBook - CRUD 포켓몬 오너 조회
	public static void notExistOwner(int ownerId) throws NotExistException, SQLException {
		OwnerDTO owner;
		try {
			owner = OwnerDAO.getOwnerId(ownerId);
			if (owner == null) {
				throw new NotExistException("검색하신 포켓몬 마스터가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
