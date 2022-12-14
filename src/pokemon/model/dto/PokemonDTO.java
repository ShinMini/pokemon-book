// POKEMON DTO
package pokemon.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

// Feature를 Age로 변경
public class PokemonDTO {

	private int pokemonId;
	private String pokemonName;
	private int pokemonAge;
	private String pokemonType;
	private int pokemonPower;
	private boolean pokemonLegend; //출력 고려해서 True, False가 편리
	
	@Builder(builderMethodName = "pokemonDTOBuilder")
	public PokemonDTO(int pokemonId, String pokemonName, int pokemonAge, String pokemonType, int pokemonPower, boolean pokemonLegend) {
		this.pokemonId = pokemonId;
		this.pokemonName = pokemonName;
		this.pokemonAge = pokemonAge;
		this.pokemonType =  pokemonType;
		this.pokemonPower = pokemonPower;
		this.pokemonLegend = pokemonLegend;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" 포켓몬 정보 \n 1. 포켓몬 번호 : ");
		builder.append(pokemonId);
		builder.append(" 2. 포켓몬 이름 : ");
		builder.append(pokemonName);
		builder.append(" 3. 포켓몬 나이 : ");
		builder.append(pokemonAge);
		builder.append(" 4. 포켓몬 타입 : ");
		builder.append(pokemonType);
		builder.append(" 5. 포켓몬 능력치 : ");
		builder.append(pokemonPower);
		builder.append(" 6. 레전드 포켓몬 : ");
		builder.append(pokemonLegend);
		return builder.toString();
	}

}