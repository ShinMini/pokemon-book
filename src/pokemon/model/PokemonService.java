package pokemon.model;

import java.sql.SQLException;
import java.util.ArrayList;

import pokemon.exception.NotExistException;
import pokemon.model.dto.OwnerDTO;
import pokemon.model.dto.PokemonBookDTO;
import pokemon.model.dto.PokemonDTO;

public class PokemonService {
	private static PokemonService instance = new PokemonService();
	public PokemonService() {}
	public static PokemonService getInstance() {
		return instance;
	}

	/*------------- === [POKEMON CRUD] === -----------------------------------*/

	//[CREATE] 포켓몬(pokemon) 정보 추가(INSERT)
	public static boolean addPokemon(PokemonDTO pokemon) throws SQLException {
		return PokemonDAO.addPokemon(pokemon);
	}
	// [UPDATE] 받아온 pokemon id값의 포켓몬 이름 수정.  // toUpdate => 업데이트할 column value ex) 피카츄 updateColumn => 업데이트할 column ex) name, power...
	public static boolean updatePokemon(String pokemonId, String toUpdate, String updateCoulmn) throws SQLException, NotExistException {
		notExistPokemon("id", pokemonId);
		return PokemonDAO.updatePokemon(pokemonId, toUpdate, updateCoulmn);
	}
	//[DELETE] pokemon id로 검색해 해당 pokemon 정보 삭제
	public static boolean deletePokemon(String pokemonId) throws SQLException, NotExistException {
		notExistPokemon("id", pokemonId);
		return PokemonDAO.deletePokemon(pokemonId);
	}
	// [READ] pokemon id와 일치하는 pokemon 정보 검색
	public static PokemonDTO getPokemon(String getColumn, String getRead) throws SQLException, NotExistException {
		return PokemonDAO.getPokemon(getColumn, getRead);
	}
	// [READ] 모든 pokemon 정보 검색
	public static ArrayList<PokemonDTO> getAllPokemons() throws SQLException {	// PoketmonDTO type을 갖는 ArrayList 반환
		return PokemonDAO.getAllPokemons();
	}

	/*------------- === [OWNER CRUD] === -----------------------------------*/

	//[CREATE] 포켓몬 주인(OWNER) 정보 추가(INSERT)
	public static boolean addOwner(OwnerDTO owner) throws SQLException {
		return OwnerDAO.addOwner(owner);
	}
	// [UPDATE] owner id에 해당하는 포켓몬 마스터(owner) 정보 수정. 
	public static boolean updateOwner(String ownerId, String toUpdate, String updateCoulmn) throws SQLException, NotExistException {
		notExistOwner("id", ownerId);
		return OwnerDAO.updateOwner(ownerId, toUpdate, updateCoulmn);
	}
	//[DELETE] owner id로 검색해 해당 owner 정보 삭제
	public static boolean deleteOwner(String ownerId) throws SQLException, NotExistException {
		notExistOwner("id", ownerId);
		return OwnerDAO.deleteOwner(ownerId);
	}
	// [READ] owner id와 일치하는 owner 정보 검색
	public static OwnerDTO getOwner(String ownerId) throws SQLException, NotExistException {
		return OwnerDAO.getOwner("id", ownerId);
	}
	// [READ] 모든 owner 정보 검색
	public static ArrayList<OwnerDTO> getAllOwners() throws SQLException {
		return OwnerDAO.getAllOwners();
	}


	/*------------- === [POKEMON BOOK CRUD] === -----------------------------------*/

	//[CREATE] 포켓몬 도감 정보 추가(INSERT)
	public static boolean addPokemonBook(PokemonBookDTO pokemonBook) throws SQLException {
		return PokemonBookDAO.addPokemonBook(pokemonBook);
	}

	/* pokemonBook update는 두가지 method로 구분함. 
	 * 1. 해당 도감 index에 포함된 포켓몬 정보 수정. 
	 * 2. 해당 도감 index에 포함된 주인 정보 수정
	 * 두가지 모두 수정하는 경우, delete 문과 add문으로 깔끔하게 처리되기 때문에 추가 안함. */

	// [UPDATE] 1. 해당 도감 index에 포함된 포켓몬 정보 수정. 
	public static boolean updatePokemonBook(int pokemonBookId, PokemonDTO pokemon)
			throws SQLException, NotExistException {
		notExistPokemonBook(pokemonBookId);
		return PokemonBookDAO.updatePokemonBook(pokemonBookId, pokemon);
	}
	// [UPDATE] 2. 해당 도감 index에 포함된 주인 정보 수정
	public static boolean updatePokemonBook(int pokemonBookId, OwnerDTO owner)
			throws SQLException, NotExistException {
		notExistPokemonBook(pokemonBookId);
		return PokemonBookDAO.updatePokemonBook(pokemonBookId, owner);
	}
	//[DELETE] 도감 index로 검색해 해당 도감 정보 삭제
	public boolean deletePokemonBook(int pokemonBookId) throws SQLException, NotExistException {
		notExistPokemonBook(pokemonBookId);
		return PokemonBookDAO.deletePokemonBook(pokemonBookId);
	}
	// [READ] pokemon book index와 일치하는 pokemon book 정보 검색
	public static PokemonBookDTO getPokemonBook(int pokemonBookId) throws SQLException {
		return PokemonBookDAO.getPokemonBookId(pokemonBookId);
	}
	// [READ] 모든 포켓몬 도감 정보 검색
	public static ArrayList<PokemonBookDTO> getAllPokemonBooks() throws SQLException {
		return PokemonBookDAO.getAllPokemonBook();
	}

	/***************** [CHECK EXIST CORRECTED VAILD] ************************/

	// notExistPokemon("id", pokemonId)
	// [POKEMON - CRUD]	// 포켓몬 column 유효 검사
	public static void notExistPokemon(String checkColumn, String getCheckValue) throws NotExistException, SQLException {
		PokemonDTO pokemon;
		try {
			pokemon = PokemonDAO.getPokemon(checkColumn, getCheckValue);	// parameter로 유입된 id에 일치하는 poketmon 객체가 존재하지 않는 경우, 
			if (pokemon == null) {
				throw new NotExistException("검색하신 포켓몬 정보가 없습니다.");	// throw notExitstException 호출
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// [OWNER - CRUD] 포켓몬 오너 column 유효 검사 
	public static void notExistOwner(String checkColumn, String getCheckValue) throws NotExistException, SQLException {
		OwnerDTO owner;
		try {
			owner = OwnerDAO.getOwner(checkColumn, getCheckValue);
			if (owner == null) {
				throw new NotExistException("검색하신 포켓몬 마스터가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// [POKEMON BOOK - CRUD] 포켓몬 도감 index 유효 검사
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
}