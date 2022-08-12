/*
CREATE TABLE probono_project (
	   probono_project_id     		NUMBER(5) PRIMARY KEY,
	   probono_project_name 		VARCHAR2(50) NOT NULL,
       probono_id           			VARCHAR2(50) NOT NULL,       
       activist_id          				VARCHAR2(20) NOT NULL,
       receive_id           				VARCHAR2(20) NOT NULL, 
       project_content      			VARCHAR2(100) NOT NULL
);   */

package poketmon.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PoketmonBookDTO {
	// 1. 포켓몬 도감에 등록된 포켓몬 정보들 모두 출력, 
	// 2. 포켓몬 도감에서 owner아이디, poketmon 아이디로 특정 검색
	
	private int poketmonBookId;

	private String poketmonId;
	private String ownerId; 

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" 포켓몬 도감 \n 도감 index : ");

		// 포켓몬의 정보
		builder.append(poketmonId);
		PoketmonDTO.builder().toString();
		
		// 오너의 정보
		builder.append(ownerId);
		OwnerDTO.builder().toString();

		return builder.toString();
	}
}
