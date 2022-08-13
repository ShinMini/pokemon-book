/*
CREATE TABLE probono_project (
	   probono_project_id     		NUMBER(5) PRIMARY KEY,
	   probono_project_name 		VARCHAR2(50) NOT NULL,
       probono_id           			VARCHAR2(50) NOT NULL,       
       activist_id          				VARCHAR2(20) NOT NULL,
       receive_id           				VARCHAR2(20) NOT NULL, 
       project_content      			VARCHAR2(100) NOT NULL
);   */

package pokemon.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PokemonBookDTO {
	// 1. 포켓몬 도감에 등록된 포켓몬 정보들 모두 출력, 
	// 2. 포켓몬 도감에서 owner아이디, poketmon 아이디로 특정 검색
	
	private int pokemonBookId;

	private PokemonDTO pokemon;
	private OwnerDTO owner; 

	@Builder(builderMethodName = "pokemonBookDTOBuilder")
	public PokemonBookDTO(int pokemonBookId, PokemonDTO pokemon, OwnerDTO owner) {
		this.pokemonBookId = pokemonBookId;
		this.pokemon = pokemon;
		this.owner = owner;
	}

	// builder.append()	// build().setString()이런식으로 호출해야 toString이 반환되는지, 
	// 혹은 기존 처럼 toString()이 바로 호출가능한지 여부 확인 필요 
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" === 포켓몬 도감 === \n 도감 index : ");
		builder.append(pokemonBookId);

		// 포켓몬의 정보 출력
		builder.append(pokemon.toString());
		// PokemonDTO.pokemonDTOBuilder().toString();	빌더와 동시에 toString()을 할 필요가 없음. 
		
		// 오너의 정보
		builder.append(owner);
		// OwnerDTO.ownerDTOBuilder().toString();

		return builder.toString();
	}



}
