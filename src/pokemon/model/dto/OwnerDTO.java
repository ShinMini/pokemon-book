package pokemon.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDTO {
	private int ownerId;
	private String ownerName;
	private int ownerAge;
	private String ownerTier;
	
	@Builder(builderMethodName = "ownerDTOBuilder")
	public OwnerDTO(int ownerId, String ownerName, int ownerAge, String ownerTier) {
		this.ownerId = ownerId;
		this.ownerName = ownerName;
		this.ownerAge = ownerAge;
		this.ownerTier = ownerTier;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" 소유자 정보 \n 1. 소유자 아이디 : ");
		builder.append(ownerId);
		builder.append(" 2. 소유자 이름 ");
		builder.append(ownerName);
		builder.append(" 3. 소유자 나이 : ");
		builder.append(ownerAge);
		builder.append(" 4. 소유자 티어 : ");
		builder.append(ownerTier);
		return builder.toString();
	}
}