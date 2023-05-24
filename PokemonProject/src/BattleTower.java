import java.util.HashMap;
import java.util.Scanner;
public class BattleTower {
	protected static int towerLevel;
	protected static int recordTowerLevel=1;
	static Scanner battleScan = new Scanner(System.in);
	
	public BattleTower(){
		
	}
	public static void BattleTowerScene() {
		while(true) {
			towerLevel = battleScan.nextInt();
			if(towerLevel<0||towerLevel>30) {
				//현재설정 타워레벨 최대 30
				battlePrinter(1);
				System.out.println("> "+towerLevel+"층은 존재하지 않습니다.");
				System.out.println("> 1에서 30 사이의 정수를 입력해주세요.");
				battlePrinter(2);
				
			}else if(towerLevel>recordTowerLevel) {
				//입력값이 최고기록보다 높을 때
				battlePrinter(1);
				System.out.println("> 입력하신 층은 현재 도전하실 수 없습니다.");
				System.out.println("> 이전 층을 먼저 클리어해주세요.");
				battlePrinter(2);
			}else {
				switch(towerLevel) {
				case 0:
					Game_Display.MainScreen();
					break;
				case 1:
					
				}
			}
			
		}
		
		
	}//battlescene end
	public static void battlePrinter(int num) {
		switch(num) {
		case 1:
			System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
			System.out.println("　　　　　　　　　　대전모드：배틀타워　　　　　　　　　　");
			System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			break;
		case 2:
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("○―――――――――――――――――○　○―――――――○");
			System.out.println("｜Ｎ：　Ｎ층으로　이동　　　　　　　｜　｜０：　　타이틀｜");
			System.out.println("○―――――――――――――――――○　○―――――――○");
			break;
		}
	}
	public static void battleManeger() {
		HashMap<Integer, Pokemon> player= new HashMap<>();
		try {
			player.put(1, (Pokemon) Pokemon_Main.user.getPokemonEach(0).clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			player.put(2, (Pokemon) Pokemon_Main.user.getPokemonEach(1).clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			player.put(3, (Pokemon) Pokemon_Main.user.getPokemonEach(2).clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HashMap<Integer, Pokemon> enemy= enemyGenerator();
		
		
	}
	public static HashMap<Integer, Pokemon> enemyGenerator() {
		HashMap<Integer, Pokemon> enemy= new HashMap<>();
		Pokemon p1;
		Pokemon p2;
		Pokemon p3;
		switch(towerLevel) {
		//노가다파트
		case 1:
			p1 = new Pokemon(22,1,5);//dex,add,lv 미니브
			p2 = new Pokemon(25,1,5);//dex,add,lv탄동
			p3 = new Pokemon(28,1,5);//dex,add,lv꼬링크
			enemy.put(1, p1);
			enemy.put(1, p2);
			enemy.put(1, p3);
			break;
		case 2:
			p1 = new Pokemon(31,2,5);//dex,add,lv 찌르꼬
			p2 = new Pokemon(37,2,5);//dex,add,lv잉어킹
			p3 = new Pokemon(10,2,5);//dex,add,lv맛보돈
			enemy.put(1, p1);
			enemy.put(1, p2);
			enemy.put(1, p3);
			break;
		}
		
		
		return enemy;
	}
	
	
}
