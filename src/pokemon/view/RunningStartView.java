package pokemon.view;

import java.util.Scanner;

public class RunningStartView {
	static Scanner sc = new Scanner(System.in);
	// 희돈님이 보내주신 파일로 수정 완료

	public static void main(String[] args) {
		mainMenu();

	}


	// 임시 function 입니다. 파일 분리 예정.
	public static void mainMenu() {
		int select = 0;
		while (true) {
			try {
				System.out.println("\n =========== 포켓몬 도감 ============== \n");
				System.out.println(" 1. 포켓몬 도감 조회 \n 2. 포켓몬 조회 \n 3. 포켓몬 마스터 조회 \n");

				System.out.print(" 원하시는 옵션을 선택하세요: ");
				select = sc.nextInt();
				System.out.print("");
				switch (select) {
				case 1:
					System.out.println("\n [포켓몬 도감 조회 함수 호출]");
					// controller.callPokemonBook();
					break;
				case 2:
					System.out.println("\n [포켓몬 조회 함수 호출]");
					// controller.callPokemon();
					break;
				case 3:
					System.out.println("\n [포켓몬 마스터 조회 함수 호출]");
					// controller.callOwner();
					break;
				case 4:
					System.out.println("프로그램 종료");
					RunningEndView.showError("Exception 발생 프로그램을 종료합니다");
					System.exit(0);
					break;
				default:
					break;
				}
			} catch(Exception e) {
				System.err.print(e);
			}

		}
	}

	public static void mainMenuPokemonBook() {}
}