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

	// Poketmon - CRUD
	public static void notExistProbono(String poketmonId) throws NotExistException, SQLException {
		PokemonDTO poketmon = PokemonDAO.getPokemon(poketmonId);	// parameter로 유입된 id에 일치하는 poketmon 객체가 존재하지 않는 경우, 
		if (poketmon == null) {
			throw new NotExistException("검색하진 재능기부 정보가 없습니다.");	// throw notExitstException 호출
		}
	}

	// 모든 Poketmon 정보 반환
	public static ArrayList<PokemonDTO> getAllPoketmons() throws SQLException {	// PoketmonDTO type을 갖는 ArrayList 반환
		return PokemonDAO.getAllPoketmons();
	}

	// probono id로 검색
	public static PokemonDTO getPokemon(String probonoId) throws SQLException, NotExistException {
		PokemonDTO probono = PokemonDAO.getPokemon(probonoId);
		if (probono == null) {
			throw new NotExistException("검색하신 재능기부 정보가 없습니다.");
		}
		return probono;
	}

	// 새로운 probono 저장
	public static boolean addPokemon(PokemonDTO probono) throws SQLException {
		return PokemonDAO.addPokemon(probono);
	}

	// 기존 probono 수정
	public static boolean updatePokemon(String probonoId, String probonoPurpose)
			throws SQLException, NotExistException {
		notExistPokemon(probonoId);
		return PokemonDAO.updatePokemon(probonoId, probonoPurpose);
	}

	// probono 삭제
	public boolean deletePokemon(String probonoId) throws SQLException, NotExistException {
		notExistPokemon(probonoId);
		return PokemonDAO.deletePokemon(probonoId);
	}

	// Activist - CRUD
	public static void notExistActivist(String activistId) throws NotExistException, SQLException {
		PoketmonTypeDTO activist = PokemonDAO.getActivist(activistId);
		if (activist == null) {
			throw new NotExistException("검색하는 재능 기부자가 미 존재합니다.");
		}
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

	// Recipient - CRUD
	public static void notExistRecipient(String recipientId) throws NotExistException, SQLException {
		RecipientDTO recipient = RecipientDAO.getRecipient(recipientId);
		if (recipient == null) {
			throw new NotExistException("검색하는 재능 수해자가 미 존재합니다.");
		}
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

	
	// ProjectUserDAO - CRUD
	public static void notExistPokemon(int pokemonId) throws NotExistException, SQLException {
		PokemonBookDTO probonoUser = PokemonBookDAO.getProbonoProject(pokemonId);
		if (probonoUser == null) {
			throw new NotExistException("검색하는 재능기부 프로젝트가 미 존재합니다.");
		}
	}

	public static boolean addProbonoUser(PokemonBookDTO probonoUser) throws SQLException {
		return PokemonBookDAO.addProbonoProject(probonoUser);
	}

	public static boolean updatePokemonBookOwner(int PokemonOwner_id, String activistId)
			throws SQLException, NotExistException {
		notExistPokemonOwner(PokemonOwner_id);
		return PokemonBookDAO.updateProbonoProjectActivist(PokemonOwner_id, activistId);
	}

	public static boolean updateProbonoUserReceive(int probonoUserId, String receiveId)
			throws SQLException, NotExistException {
		notExistProbonoUser(probonoUserId);
		return PokemonBookDAO.updateProbonoProjectReceive(probonoUserId, receiveId);
	}

	public static boolean deleteProbonoUser(int probonoUserId) throws SQLException, NotExistException {
		notExistProbonoUser(probonoUserId);
		return PokemonBookDAO.deleteProbonoProject(probonoUserId);
	}

	// 프로보노프로젝트 id로 존재 유무 검색하는 메소드
	public static PokemonBookDTO getProbonoUser(int probonoUserId) 
			throws SQLException, NotExistException {
		PokemonBookDTO probonoUser = PokemonBookDAO.getProbonoProject(probonoUserId);
		
		if (probonoUser == null) {
			throw new NotExistException("검색하는 재능기부 프로젝트가 미 존재합니다.");
		}
		return probonoUser;
		
	}

	public static ArrayList<PokemonBookDTO> getAllProbonoUsers() throws SQLException {
		return PokemonBookDAO.getAllProbonoProjects();
	}

}
