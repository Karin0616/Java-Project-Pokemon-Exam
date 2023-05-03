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
				System.out.println(towerLevel+"에 해당하는 층은 없습니다.");
				System.out.println("다시 입력해주세요.(층은 30층까지 있습니다.)");
			}else if(towerLevel>recordTowerLevel) {
				//입력값이 최고기록보다 높을 때
				System.out.println("입력하신 층은 현재 도전하실 수 없습니다.");
				System.out.println("이전 층을 먼저 클리어해주세요.");
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
