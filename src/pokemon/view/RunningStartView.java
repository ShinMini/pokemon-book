package pokemon.view;

import java.lang.ModuleLayer.Controller;
import java.util.Scanner;

import pokemon.controller.PokemonController;

public class RunningStartView {
	static Scanner sc = new Scanner(System.in);
	// 희돈님이 보내주신 파일로 수정 완료

	public static void main(String[] args) {
		mainMenu();
	
	}
	
	
	// 임시 function 입니다. 파일 분리 예정.
	public static void mainMenu() {
		PokemonController cont; 
		int select = 0;
		while (true) {
			try {
				System.out.println(" =========== 포켓몬 도감 ==============");
				System.out.println(" 0. 종료 \n 1. 포켓몬 도감 조회 \n 2. 포켓몬 조회 \n 3. 포켓몬 마스터 조회");
				select = sc.nextInt();
				switch (select) {
				case 1:
					System.out.println("포켓몬 도감 조회 함수 호출");
					break;
				case 2:
					System.out.println("포켓몬 도감 조회 함수 호출");
					break;
				case 3:
					System.out.println(");
					break;
				case 4:
					System.out.println("프로그램 종료하기");
					break;
				}
				if (select == 0) {
					RunningEndView.showMsg("프로그램을 종료합니다");
					System.exit(0);
				} else if (select > 3 || select < 0) {
					RunningEndView.showMsg("정해진 범위 안에서 선택하세요");
				} else if (select == 1) {
					System.out.println("1.조회 2.추가 3.삭제 ...");
				} else if (select == 2) {
					System.out.println("1.조회 2.추가 3.삭제 ...");
				} else if (select == 3) {
					System.out.println("1.조회 2.추가 3.삭제 ...");
				}
			} catch (Exception e) {
				e.printStackTrace();
				RunningEndView.showError("Exception 발생 프로그램을 종료합니다");
				System.exit(0);
			}
		}
	}
}