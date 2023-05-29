import java.util.Scanner;

public class Game_Display {
	static Scanner GameScan = new Scanner(System.in);
	private static int num;
	
	public Game_Display(){
		
	}
	
	public static void StartScreen() {
		/*
		 * 텍스트 ui
		 */
		//타이틀화면
		System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
		System.out.println("　　　　　　　　　　　Ｐｏｋｅｍｏｎ　　　　　　　　　　　");
		System.out.println("　　　　　　　　　　　　포켓몬스터　　　　　　　　　　　　");
		System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> 원하는 메뉴를 선택해주세요.");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("　　　　　　　　　　　             ◈――――――――◈");
		System.out.println("　　　　　　　　　　　             ｜［１］　처음부터｜");
		System.out.println("　　　　　　　　　　　             ｜［２］　불러오기｜");
		System.out.println("　　　　　　　　　　　             ｜［３］　　끝내기｜");
		System.out.println("　　　　　　　　　　　             ◈――――――――◈");
		while(true) {
			try {
				num= Game_Display.GameScan.nextInt();
			}catch(NumberFormatException e) {
				System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
				System.out.println("　　　　　　　　　　　Ｐｏｋｅｍｏｎ　　　　　　　　　　　");
				System.out.println("　　　　　　　　　　　　포켓몬스터　　　　　　　　　　　　");
				System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 정수를 입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("　　　　　　　　　　　             ◈――――――――◈");
				System.out.println("　　　　　　　　　　　             ｜［１］　처음부터｜");
				System.out.println("　　　　　　　　　　　             ｜［２］　불러오기｜");
				System.out.println("　　　　　　　　　　　             ｜［３］　　끝내기｜");
				System.out.println("　　　　　　　　　　　             ◈――――――――◈");
			}
			
			if(num>0&&num<4) {
				switch(num) {
				case 1://처음부터
					Game_Display.MainScreen();
					break;
				case 2://불러오기
					load();
					break;
				case 3://끝내기
					GameScan.close();
					System.exit(0);
					break;
				
				}
				break;
			}else {
				//다른 숫자 입력시
				System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
				System.out.println("　　　　　　　　　　　Ｐｏｋｅｍｏｎ　　　　　　　　　　　");
				System.out.println("　　　　　　　　　　　　포켓몬스터　　　　　　　　　　　　");
				System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 유효하지 않은 숫자입니다.");
				System.out.println("> 다시 입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("　　　　　　　　　　　             ◈――――――――◈");
				System.out.println("　　　　　　　　　　　             ｜［１］　처음부터｜");
				System.out.println("　　　　　　　　　　　             ｜［２］　불러오기｜");
				System.out.println("　　　　　　　　　　　             ｜［３］　　끝내기｜");
				System.out.println("　　　　　　　　　　　             ◈――――――――◈");
			}
			
		}
	}
	public static void MainScreen() {
		//메인화면
		/*
		 * 텍스트 ui
		 */
		System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
		System.out.println("　　　　　　　　　　　Ｐｏｋｅｍｏｎ　　　　　　　　　　　");
		System.out.println("　　　　　　　　　　　　포켓몬스터　　　　　　　　　　　　");
		System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> 원하는 메뉴를 선택해주세요.");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("○―――――――○　○―――――――○　○―――――――○");
		System.out.println("｜１：　대전모드｜　｜２：　포획모드｜　｜３：포켓몬박스｜");
		System.out.println("○―――――――○　○―――――――○　○―――――――○");
		System.out.println("　　　　　　　　　　○―――――――○　○―――――――○");
		System.out.println("　　　　　　　　　　｜４：　저장한다｜　｜５：　타이틀로｜");
		System.out.println("　　　　　　　　　　○―――――――○　○―――――――○");
		while(true) {
			try {
				num= Game_Display.GameScan.nextInt();
			}catch(NumberFormatException e) {
				System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
				System.out.println("　　　　　　　　　　　Ｐｏｋｅｍｏｎ　　　　　　　　　　　");
				System.out.println("　　　　　　　　　　　　포켓몬스터　　　　　　　　　　　　");
				System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 정수를 입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("○―――――――○　○―――――――○　○―――――――○");
				System.out.println("｜１：　대전모드｜　｜２：　포획모드｜　｜３：포켓몬박스｜");
				System.out.println("○―――――――○　○―――――――○　○―――――――○");
				System.out.println("　　　　　　　　　　○―――――――○　○―――――――○");
				System.out.println("　　　　　　　　　　｜４：　저장한다｜　｜５：　타이틀로｜");
				System.out.println("　　　　　　　　　　○―――――――○　○―――――――○");
			}
			if(num>0&&num<6) {
				switch(num) {
				case 1://대전모드
					BattleMode();
					break;
				case 2://포획모드
					GotchaMode();
					break;
				case 3://포켓몬박스
					pokemonBox();
					break;
				case 4://저장
					save();
					break;
				case 5://타이틀로
					StartScreen();
					break;
				}
				break;
			}else {
				//다른 숫자 입력시
				System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
				System.out.println("　　　　　　　　　　　Ｐｏｋｅｍｏｎ　　　　　　　　　　　");
				System.out.println("　　　　　　　　　　　　포켓몬스터　　　　　　　　　　　　");
				System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 유효하지 않은 숫자입니다.");
				System.out.println("> 다시　입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("○―――――――○　○―――――――○　○―――――――○");
				System.out.println("｜１：　대전모드｜　｜２：　포획모드｜　｜３：포켓몬박스｜");
				System.out.println("○―――――――○　○―――――――○　○―――――――○");
				System.out.println("　　　　　　　　　　○―――――――○　○―――――――○");
				System.out.println("　　　　　　　　　　｜４：　저장한다｜　｜５：　타이틀로｜");
				System.out.println("　　　　　　　　　　○―――――――○　○―――――――○");
			}
		}
			
		
	}
	public static void BattleMode() {
		//배틀모드 화면
		System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
		System.out.println("　　　　　　　　　　대전모드：배틀타워　　　　　　　　　　");
		System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> 현재 최고층"+BattleTower.recordTowerLevel+"/30");
		System.out.println("　　　　　　　―――――――◈―――――――　　　　　　　");
		System.out.println("> 도전하실 층을 입력해주세요.");
		System.out.println("> 도전을 취소하시고 싶으시다면 0을 눌러주세요.");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("○―――――――――――――――――○　○―――――――○");
		System.out.println("｜Ｎ：　Ｎ층으로　이동　　　　　　　｜　｜０：　　타이틀｜");
		System.out.println("○―――――――――――――――――○　○―――――――○");
		BattleTower.BattleTowerScene();
		
	}
	public static void GotchaMode() {
		//포획모드
		//System.out.println("포획모드");
		Gatcha.gatchamode();
	}
	public static void pokemonBox() {
		//포켓몬 박스확인
		PokemonBox.pokemonBoxScene();
	}
	public static void save() {
		SaveLoadManager.saveData(Pokemon_Main.user);
		
	}
	public static void load() {
		SaveLoadManager.loadData(Pokemon_Main.user);
		
	}
	
}
