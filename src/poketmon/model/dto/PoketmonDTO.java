/*
CREATE TABLE Poketmon (
       poketmon_id          	VARCHAR2(50) PRIMARY KEY,
       poketmon_name      VARCHAR2(20) NOT NOT NULL,
       poketmon_feature  	VARCHAR2(200) NULL
);  */
package poketmon.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class PoketmonDTO {
	private String poketmonId;
	private String poketmonName;
	private String poketmonFeature;
	private String poketmonType;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" 포켓몬 정보 \n 1. 포켓몬 번호 : ");
		builder.append(poketmonId);
		builder.append(" 2. 포켓몬 이름 : ");
		builder.append(poketmonName);
		builder.append(" 3. 포켓몬 특징 : ");
		builder.append(poketmonFeature);
		builder.append(" 4. 포켓몬 타입 : ");
		builder.append(poketmonType);
		return builder.toString();
	}
}
