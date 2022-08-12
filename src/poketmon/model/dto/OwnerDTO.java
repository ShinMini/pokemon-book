package poketmon.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OwnerDTO {
	private String ownerId;
	private String ownerName;
	private int ownerAge;
	private String ownerTier;

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
