package pokemon.model;

import java.sql.SQLException;
import java.util.ArrayList;

import pokemon.exception.NotExistException;
import pokemon.model.dto.PokemonBookDTO;
import pokemon.model.dto.PokemonDTO;

//서버 내부에서 하나의 객체수를 보장하면서 서비스하게 되는 singleton design 구조

public class PokemonService {

	private static PokemonService instance = new PokemonService();

	private PokemonService() {}

	public static PokemonService getInstance() {
		return instance;
	}


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

	// 기존 pokemon 수정
	public static boolean updatePokemon(String pokemonId, String probonoPurpose)
			throws SQLException, NotExistException {
		notExistPokemon(pokemonId);
		return PokemonDAO.updatePokemon(pokemonId, probonoPurpose);
	}

	// pokemon 삭제
	public boolean deletePokemon(String pokemonId) throws SQLException, NotExistException {
		notExistPokemon(pokemonId);
		return PokemonDAO.deletePokemon(pokemonId);
	}



	public static boolean addActivist(PoketmonTypeDTO activist) throws SQLException {
		return PokemonDAO.addActivist(activist);
	}

	public static boolean updateActivist(String activistId, String major) throws SQLException, NotExistException {
		notExistActivist(activistId);
		return PokemonDAO.updateActivist(activistId, major);
	}

	public boolean deleteActivist(String activistId) throws SQLException, NotExistException {
		notExistActivist(activistId);
		return PokemonDAO.deleteActivist(activistId);
	}

	public static PoketmonTypeDTO getActivist(String activistId) throws SQLException, NotExistException {
		PoketmonTypeDTO activist = PokemonDAO.getActivist(activistId);
		if (activist == null) {
			throw new NotExistException("검색하는 재능 기부자가 미 존재합니다.");
		}
		return activist;
	}

	public static ArrayList<PoketmonTypeDTO> getAllActivists() throws SQLException {
		return PokemonDAO.getAllActivists();
	}



	public static boolean addRecipient(RecipientDTO recipient) throws SQLException {
		return RecipientDAO.addRecipient(recipient);
	}

	public static boolean updateRecipient(String recipientId, String receiveHopeContent)
			throws SQLException, NotExistException {
		notExistRecipient(recipientId);
		return RecipientDAO.updateRecipient(recipientId, receiveHopeContent);
	}

	public boolean deleteRecipient(String recipientId) throws SQLException, NotExistException {
		notExistRecipient(recipientId);
		return RecipientDAO.deleteRecipient(recipientId);
	}

	public static RecipientDTO getRecipient(String recipientId) throws SQLException {
		return RecipientDAO.getRecipient(recipientId);
	}

	public static ArrayList<RecipientDTO> getAllRecipients() throws SQLException {
		return RecipientDAO.getAllRecipients();
	}

	


	public static boolean addProbonoUser(PokemonBookDTO pokemonUser) throws SQLException {
		return PokemonBookDAO.addProbonoProject(pokemonUser);
	}

	public static boolean updatePokemonBookOwner(int PokemonOwner_id, String activistId)
			throws SQLException, NotExistException {
		notExistPokemonOwner(PokemonOwner_id);
		return PokemonBookDAO.updateProbonoProjectActivist(PokemonOwner_id, activistId);
	}

	public static boolean updateProbonoUserReceive(int pokemonUserId, String receiveId)
			throws SQLException, NotExistException {
		notExistProbonoUser(pokemonUserId);
		return PokemonBookDAO.updateProbonoProjectReceive(pokemonUserId, receiveId);
	}

	public static boolean deleteProbonoUser(int pokemonUserId) throws SQLException, NotExistException {
		notExistProbonoUser(pokemonUserId);
		return PokemonBookDAO.deleteProbonoProject(pokemonUserId);
	}

	// 프로보노프로젝트 id로 존재 유무 검색하는 메소드
	public static PokemonBookDTO getProbonoUser(int pokemonUserId) 
			throws SQLException, NotExistException {
		PokemonBookDTO pokemonUser = PokemonBookDAO.getProbonoProject(probonoUserId);
		
		if (pokemonUser == null) {
			throw new NotExistException("검색하는 재능기부 프로젝트가 미 존재합니다.");
		}
		return pokemonUser;
		
	}

	public static ArrayList<PokemonBookDTO> getAllProbonoUsers() throws SQLException {
		return PokemonBookDAO.getAllProbonoProjects();
	}


	/***************** exist 검사 METHOD ************************/

	// Poketmon - CRUD	// 이름으로 포켓몬 검색
	public static void notExistPokemon(String pokemonName) throws NotExistException, SQLException {
		PokemonDTO poketmon = PokemonDAO.getPokemonName(pokemonName);	// parameter로 유입된 id에 일치하는 poketmon 객체가 존재하지 않는 경우, 
		if (poketmon == null) {
			throw new NotExistException("검색하신 포켓몬 정보가 없습니다.");	// throw notExitstException 호출
		}
	}
	
	// ProjectUserDAO - CRUD	// 포켓몬 도감 index 번호로 도감 번호 조회
	public static void notExistPokemon(int pokemonBookId) throws NotExistException, SQLException {
		PokemonBookDTO pokemonBook = PokemonBookDAO.getPokemonBookId(pokemonBookId);
		if (pokemonUser == null) {
			throw new NotExistException("검색하는 재능기부 프로젝트가 미 존재합니다.");
		}
	}
	
	// Recipient - CRUD
	public static void notExistRecipient(String recipientId) throws NotExistException, SQLException {
		RecipientDTO recipient = RecipientDAO.getRecipient(recipientId);
		if (recipient == null) {
			throw new NotExistException("검색하는 재능 수해자가 미 존재합니다.");
		}
	}
		// Activist - CRUD
	public static void notExistActivist(String activistId) throws NotExistException, SQLException {
		PoketmonTypeDTO activist = PokemonDAO.getActivist(activistId);
		if (activist == null) {
			throw new NotExistException("검색하는 재능 기부자가 미 존재합니다.");
		}
	}
}
