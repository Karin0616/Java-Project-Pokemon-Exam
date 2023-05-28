import java.util.Scanner;
public class BattleTower {
	protected static int towerLevel;
	protected static int recordTowerLevel;
	static Scanner battleScan = new Scanner(System.in);
	
	public BattleTower(){
		
	}
	public static void BattleTowerScene() {
		while(true) {
			towerLevel = battleScan.nextInt();
			if(towerLevel<0||towerLevel>30) {
				//현재설정 타워레벨 최대 30
				System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
				System.out.println("　　　　　　　　　　대전모드：배틀타워　　　　　　　　　　");
				System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 현재 최고층"+BattleTower.recordTowerLevel+"/30");
				System.out.println("　　　　　　　―――――――◈―――――――　　　　　　　");
				System.out.println("> "+towerLevel+"층은 존재하지 않습니다.");
				System.out.println("> 1에서 30 사이의 정수를 입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("○―――――――――――――――――○　○―――――――○");
				System.out.println("｜Ｎ：　Ｎ층으로　이동　　　　　　　｜　｜０：　　타이틀｜");
				System.out.println("○―――――――――――――――――○　○―――――――○");
			}else if(towerLevel>recordTowerLevel) {
				//입력값이 최고기록보다 높을 때
				System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
				System.out.println("　　　　　　　　　　대전모드：배틀타워　　　　　　　　　　");
				System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 현재 최고층"+BattleTower.recordTowerLevel+"/30");
				System.out.println("　　　　　　　―――――――◈―――――――　　　　　　　");
				System.out.println("> 입력하신 층은 현재 도전하실 수 없습니다.");
				System.out.println("> 이전 층을 먼저 클리어해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("○―――――――――――――――――○　○―――――――○");
				System.out.println("｜Ｎ：　Ｎ층으로　이동　　　　　　　｜　｜０：　　타이틀｜");
				System.out.println("○―――――――――――――――――○　○―――――――○");
			}else {
				switch(towerLevel) {
				case 0:
					Game_Display.MainScreen();
					break;
				case 1:
					
				}
			}
			
		}
		
		
	}
	
	
}
