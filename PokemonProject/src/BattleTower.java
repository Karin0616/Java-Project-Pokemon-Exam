import java.util.HashMap;
import java.util.Scanner;
public class BattleTower {
	protected static int towerLevel;
	protected static int recordTowerLevel=1;
	protected static HashMap<Integer, Pokemon> player;
	protected static HashMap<Integer, Pokemon> enemy;
	protected static float enemyFullHp;
	protected static float playerFullHp;
	protected static int currentPlayer;
	protected static int currentEnemy;
	protected static String[] pSkillName = new String[4];
	protected static String[] eSkillName = new String[4];
	
	protected static float pDMG=0;
	protected static float eDMG=0;
	
	protected static float pAdebuff=0;
	protected static float pDdebuff=0;
	protected static float eAdebuff=0;
	protected static float eDdebuff=0;
	
	protected static int enemyPatternCnt=1;
	
	protected static int enemyDieflag=0;
	protected static int playerDieflag=0;
	protected static int deathCnt=0;
	
	protected static int playerDflag=0;
	protected static int enemyDflag=0;
	SkillDex[] sDex = SkillDex.getInstance();
	PokemonDex[] pDex= PokemonDex.getInstance();
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
				battlePrinter(1);
				System.out.println("> "+towerLevel+"층은 존재하지 않습니다.");
				System.out.println("> 1에서 30 사이의 정수를 입력해주세요.");
				battlePrinter(1);
				System.out.println("○－－－－－－－－－－－－－－－－－○　○－－－－－－－○");
				System.out.println("｜Ｎ：　Ｎ층으로　이동　　　　　　　｜　｜０：　　타이틀｜");
				System.out.println("○－－－－－－－－－－－－－－－－－○　○－－－－－－－○");
				
			}else if(towerLevel>recordTowerLevel) {
				//입력값이 최고기록보다 높을 때
				System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
				System.out.println("　　　　　　　　　　대전모드：배틀타워　　　　　　　　　　");
				System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
				battlePrinter(1);
				System.out.println("> 입력하신 층은 현재 도전하실 수 없습니다.");
				System.out.println("> 이전 층을 먼저 클리어해주세요.");
				battlePrinter(1);
				System.out.println("○－－－－－－－－－－－－－－－－－○　○－－－－－－－○");
				System.out.println("｜Ｎ：　Ｎ층으로　이동　　　　　　　｜　｜０：　　타이틀｜");
				System.out.println("○－－－－－－－－－－－－－－－－－○　○－－－－－－－○");
			}else {
				switch(towerLevel) {
				case 0:
					Game_Display.MainScreen();
					break;
				default:
					battleManeger();
					break;
					
				}
			}
			
		}
		
		
	}//battlescene end
	public static void battlePrinter(int num) {
		switch(num) {
		case 1:
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			break;
		case 2:
			break;
		case 3://배틀조우
			battlePrinter(1);
			System.out.println("> "+towerLevel+"층의 수호자 "+enemy.get(currentEnemy).name+"이(가) 나타났다!");
			break;
		case 4:
			System.out.println("가라! "+player.get(currentPlayer).name+"!");
			break;
		case 5://전투 상태화면
			battlePrinter(1);
			for (int i = 0; i < 25 - enemy.get(currentEnemy).name.length() - String.valueOf(enemy.get(currentEnemy).Lv).length(); i++) System.out.print("　");
			System.out.println(enemy.get(currentEnemy).name + "　Ｌｖ．" + Game_Display.toFull(String.valueOf(enemy.get(currentEnemy).Lv)));
			for (int i = 0; i < 25 - String.format("%.2f", enemy.get(currentEnemy).HP).length() - String.valueOf(enemyFullHp).length(); i++) System.out.print("　");
			System.out.println("ＨＰ："+Game_Display.toFull(String.format("%.2f", enemy.get(currentEnemy).HP))+"／"+Game_Display.toFull(String.valueOf(enemyFullHp)));
			System.out.println("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
			System.out.println("　　　　　　　　－－－－　Ｖ　Ｓ　－－－－　　　　　　　　");
			System.out.println("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　");
			System.out.println(player.get(currentPlayer).name + "　Ｌｖ．" + Game_Display.toFull(String.valueOf(player.get(currentPlayer).Lv)));
			System.out.println("ＨＰ："+Game_Display.toFull(String.format("%.2f", player.get(currentPlayer).HP))+"／"+Game_Display.toFull(String.valueOf(playerFullHp)));
			battlePrinter(1);
			System.out.println("> 무엇을 할까?");
			battlePrinter(1);
			System.out.println("○－－－－－－－○　○－－－－－－－○　○－－－－－－－○");
			System.out.println("｜１：　싸운다　｜　｜２：　교체한다｜　｜３：　포기한다｜");
			System.out.println("○－－－－－－－○　○－－－－－－－○　○－－－－－－－○");
			break;
		
		
		case 9://적 몹 쓰러짐
			System.out.println("> "+towerLevel+"층의 수호자 "+enemy.get(currentEnemy).name+"이(가) 쓰러졌다!");
			currentEnemy+=1;
			enemyDieflag=1;
			enemyFullHp=enemy.get(currentEnemy).HP;
			System.out.println("> "+towerLevel+"층의 수호자 "+enemy.get(currentEnemy).name+"이(가) 나타났다!");
			break;
		case 10://플레이어승리
			System.out.println("> "+towerLevel+"층의 수호자 "+enemy.get(currentEnemy).name+"에게 승리했다!");
			reward();//보상
			Pokemon_Main.user.setRecordTowerLevel(recordTowerLevel);
			System.out.println("> 엔터를 누르시면 메인으로 돌아갑니다.");
			battlePrinter(1);
			String tmp=battleScan.nextLine();
			//System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			Game_Display.MainScreen();
			break;
		case 11://플레이어몹 사망
			System.out.println("> "+player.get(currentPlayer).name+"이(가) 쓰러졌다!");
			if(currentPlayer!=3) {
				currentPlayer+=1;
			}else if(currentPlayer==3&&deathCnt<3) {
				for(int i=1;i<3;i++) {
					if(player.get(i).HP>=0) {
						currentPlayer=i;
						break;
					}
				}
			}
			playerDieflag=1;
			playerFullHp=player.get(currentPlayer).HPCalReturner(player.get(currentPlayer).pdex[player.get(currentPlayer).index-1].getBaseStatus(0), player.get(currentPlayer).add);  
			System.out.println("> 가라! "+player.get(currentPlayer).name+"!");
			break;
		case 12://플레이어 패배
			System.out.println("> " + towerLevel+"층의 수호자 "+enemy.get(currentEnemy).name+"에게 패배했다!");
			System.out.println("> 나는 눈앞이 깜깜해졌다..");
			System.out.println("> 좀 더 성장해서 도전해보시길 바랍니다.");
			System.out.println("> 엔터를 누르시면 메인으로 돌아갑니다.");
			battleScan.nextLine();
			Game_Display.MainScreen();
			break;
		case 61://자속기 플레이어공격
			System.out.println("> "+player.get(currentPlayer).name+"의 "+Skill.getSkillNameByIndex(player.get(currentPlayer).skillIndexNum[0])+" 공격!");
			System.out.println("> "+enemy.get(currentEnemy).name+"은(는) "+(int)pDMG+"의 피해를 입었다!");
			break;
		case 62://비자속기 플레이어공격
			System.out.println("> "+player.get(currentPlayer).name+"의 "+Skill.getSkillNameByIndex(player.get(currentPlayer).skillIndexNum[1])+" 공격!");
			System.out.println("> "+enemy.get(currentEnemy).name+"은(는) "+(int)pDMG+"의 피해를 입었다!");
			break;
		case 63://디버프
			System.out.println("> "+player.get(currentPlayer).name+"의 "+Skill.getSkillNameByIndex(player.get(currentPlayer).skillIndexNum[2])+" 공격!");
			if(pAdebuff!=0) {
				System.out.println("> "+enemy.get(currentEnemy).name+"의 방어가 낮아졌다.");
			}else if(pDdebuff!=0) {
				System.out.println("> "+enemy.get(currentEnemy).name+"의 공격이 낮아졌다.");
			}
			break;
		case 71://적공격 자속
			System.out.println("> "+enemy.get(currentEnemy).name+"의 "+Skill.getSkillNameByIndex(enemy.get(currentEnemy).skillIndexNum[0])+" 공격!");
			System.out.println("> "+player.get(currentPlayer).name+"은(는) "+(int)eDMG+"의 피해를 입었다!");
			break;
		case 72:
			System.out.println("> "+enemy.get(currentEnemy).name+"의 "+Skill.getSkillNameByIndex(enemy.get(currentEnemy).skillIndexNum[1])+" 공격!");
			System.out.println("> "+player.get(currentPlayer).name+"은(는) "+(int)eDMG+"의 피해를 입었다!");
			break;
		case 73:
			System.out.println("> "+enemy.get(currentEnemy).name+"의 "+Skill.getSkillNameByIndex(enemy.get(currentEnemy).skillIndexNum[2])+" 공격!");
			
			if(eAdebuff!=0) {
				System.out.println("> "+player.get(currentPlayer).name+"의 방어가 낮아졌다.");
			}else if(eDdebuff!=0) {
				System.out.println("> "+player.get(currentPlayer).name+"의 공격이 낮아졌다.");
			}else {
				System.out.println(player.get(currentPlayer).name+"디버프 미적용");
			}
			break;
		case 74:
			System.out.println("> "+enemy.get(currentEnemy).name+"은(는) 공격을 방어했다!");
			break;
		case 75:
			System.out.println("> "+enemy.get(currentEnemy).name+"은(는) 방어에 실패했다!");
			break;
		}
	}
	public static void battleManeger() {
		//세팅
		player= new HashMap<>();
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
		currentEnemy=1;
		currentPlayer=1;
		enemyGenerator();//몸생성 enemy해쉬맵 생성
		
		enemyFullHp=enemy.get(currentEnemy).HP;
		playerFullHp=player.get(currentPlayer).HP;
		pDMG=0;
		eDMG=0;
		
		pAdebuff=0;
		pDdebuff=0;
		eAdebuff=0;
		eDdebuff=0;
		
		enemyPatternCnt=1;
		
		playerDflag=0;
		enemyDflag=0;
		battlePrinter(3);//조우 대사
		
		
		
		
		boolean flag = true;
		int select=0;
		String strSel;
		//선택지
		while(flag) {
			battlePrinter(5);
			while(true) {
				try {
					select  = battleScan.nextInt();
					if(select<1||select>3) {
						System.out.println("> 1, 2, 3사이의 수를 입력해주세요.");
						continue;
					}
				}catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("> 1, 2, 3사이의 수를 입력해주세요.");
					continue;
				}
				break;
			}//선택지
			switch(select) {
			case 1://싸운다
				battle();
				break;
			case 2://교체한다
				change();
				playerDflag=0;
				enemyDieflag=0;
				whatEnemyDoing();
				break;
			case 3://포기한다.
				battlePrinter(1);
				System.out.println("> 정말로 포기하겠습니까? 포기하시려면 -1을 입력해주세요. 다른 입력시 배틀로 돌아갑니다.");
				battlePrinter(1);
					try {
						select = battleScan.nextInt();
						if(select==-1) {
							battlePrinter(1);
							System.out.println("> 배틀을 종료하고 메인화면으로 돌아갑니다.");
							battlePrinter(1);
							Game_Display.MainScreen();
						}else {
							battlePrinter(1);
							System.out.println("> 배틀을 재개합니다.");
						}
					}catch (NullPointerException e) {
						// TODO: handle exception
						battlePrinter(1);
						System.out.println("> 배틀을 재개합니다.");
						break;
					}
					
				break;
			
			}
			
			
		}
		
		
	}
	
	public static void battle() {
		/*
		 *  배틀 선택지
		 *  1공격기 2공격기(비자속) 3 변화기 4 방어
		 *  키입력
		 *  스피드 계산(선후공 판단)
		 *  선공자 선 행동 계산 후 적용
		 *  후공자 행동 계산 후 적용
		 *  체력 확인
		 *  배틀 재개 혹은 종료
		 */
		pDMG=0;
		eDMG=0;
		
		pAdebuff=0;
		pDdebuff=0;
		eAdebuff=0;
		eDdebuff=0;

		enemyDieflag=0;

		battlePrinter(1);
		System.out.println("> "+player.get(currentPlayer).name+"은(는) 무슨 일을 할까?");
		battlePrinter(1);
		for(int i=0;i<4;i++) {
			pSkillName[i]=Skill.getSkillNameByIndex(player.get(currentPlayer).skillIndexNum[i]);
			eSkillName[i]=Skill.getSkillNameByIndex(enemy.get(currentEnemy).skillIndexNum[i]);
		}
		System.out.println("○－－－－－－－－－－－－○　○－－－－－－－－－－－－○");
		System.out.print("｜１：　"+pSkillName[0]);
		for (int i = 0; i < 9 - pSkillName[0].length(); i++) System.out.print("　");
		System.out.print("｜　｜２：　"+pSkillName[1]);
		for (int i = 0; i < 9 - pSkillName[1].length(); i++) System.out.print("　");
		System.out.println("｜");
		System.out.println("○－－－－－－－－－－－－○　○－－－－－－－－－－－－○");
		System.out.println("○－－－－－－－－－－－－○　○－－－－－－－－－－－－○");
		System.out.print("｜３：　"+pSkillName[2]);
		for (int i = 0; i < 9 - pSkillName[2].length(); i++) System.out.print("　");
		System.out.print("｜　｜４：　"+pSkillName[3]);
		for (int i = 0; i < 9 - pSkillName[3].length(); i++) System.out.print("　");
		System.out.println("｜");
		System.out.println("○－－－－－－－－－－－－○　○－－－－－－－－－－－－○");
		int select =0;
		while(true) {//스킬입력
			try {
				select  = battleScan.nextInt();
				if(select<1||select>4) {
					battlePrinter(1);
					System.out.println("> 1에서 4 사이의 수를 입력해주세요.");
					battlePrinter(1);
					continue;
				}
			}catch (NumberFormatException e) {
				// TODO: handle exception
				battlePrinter(1);
				System.out.println("> 1에서 4 사이의 수를 입력해주세요.");
				battlePrinter(1);
				continue;
			}
			break;
		}//선택지
		int fs=0;//0은 플레이어 1은 적
		//System.out.println("테스트용 스피드 출력"+player.get(currentPlayer).SPD+"또 "+enemy.get(currentEnemy).SPD);
		if(player.get(currentPlayer).SPD>enemy.get(currentEnemy).SPD) {
			
			fs=0;
		}else if(player.get(currentPlayer).SPD<enemy.get(currentEnemy).SPD) {
			fs=1;
		}else {
			fs=(int) (Math.random())/2;
		}
		
		battlePrinter(1);
		if(fs==0) {//플레이어 선턴
			System.out.println("> 나의 선공!");
			enemyDflag=0;
			//System.out.println("방어계산기 "+playerDflag+" "+enemyDflag);
			switch (select) {//스킬사용
				case 1: //자속기
				playerDflag=0;
				if(enemyDflag==1) {
					battlePrinter(74);
					break;
				}
				pDMG=Skill.dmgCal(player.get(currentPlayer), enemy.get(currentEnemy), player.get(currentPlayer).skillIndexNum[0]);
				enemy.get(currentEnemy).HP=enemy.get(currentEnemy).HP-pDMG;
				
				battlePrinter(61);
				if(enemy.get(currentEnemy).HP<=0) {//적 다운시
					if(currentEnemy==3) {
						battlePrinter(10);
					}else {
						battlePrinter(9);
					}
				}//외곽if문
				//적 행동
				whatEnemyDoing();
				
					break;
				case 2://비자속기
				playerDflag=0;
				if(enemyDflag==1) {
					battlePrinter(74);
					break;
				}
				pDMG=Skill.dmgCal(player.get(currentPlayer), enemy.get(currentEnemy), player.get(currentPlayer).skillIndexNum[1]);
				enemy.get(currentEnemy).HP=enemy.get(currentEnemy).HP-pDMG;
				battlePrinter(62);
				if(enemy.get(currentEnemy).HP<=0) {//적 다운시
					if(currentEnemy==3) {
						battlePrinter(10);
					}else {
						battlePrinter(9);
					}
				}//외곽if문
				//적 행동
				whatEnemyDoing();
				if(enemyDflag==1) {
					battlePrinter(74);
					break;
				}
				break;
			case 3://디버프
				playerDflag=0;
				pAdebuff=Skill.ATKdebuffCal(enemy.get(currentEnemy),player.get(currentPlayer).skillIndexNum[2]);
				pDdebuff=Skill.DEFdebuffCal(enemy.get(currentEnemy),player.get(currentPlayer).skillIndexNum[2]);
				if(pAdebuff==0||pDdebuff!=0) {//방디벞
					enemy.get(currentEnemy).DEF=enemy.get(currentEnemy).DEF*pDdebuff;					
				}else if(pAdebuff!=0||pDdebuff==0) {//공디벞
					enemy.get(currentEnemy).DEF=enemy.get(currentEnemy).ATK*pAdebuff;
				}else {//에러체크
					System.out.println("오류남 스킬 이상함");
				}
				battlePrinter(63);
				pAdebuff=0;
				pDdebuff=0;
				//적행동
				whatEnemyDoing();
				if(enemyDflag==1) {
					battlePrinter(75);
					break;
				}
				break;
			case 4:
				//방어, 적행동
				if(playerDflag==1) {
					System.out.println("> "+player.get(currentPlayer).name+"는 방어에 실패했다!");
					playerDflag=0;
				}else {
					playerDflag=1;
					System.out.println("> "+player.get(currentPlayer).name+"는 방어 태세에 들어갔다.");
				}
				
				whatEnemyDoing();
				if(enemyDflag==1) {
					battlePrinter(75);
					break;
				}
				
				break;
			}
		}else if(fs==1) {//후공
			System.out.println("> 적의 선공!");
			enemyDflag=0;
			switch (select) {//스킬사용
			case 1: //자속기
				//적 행동
				playerDflag=0;
				whatEnemyDoing();
				if(enemyDflag==1) {
					battlePrinter(74);
					break;
				}
				if(playerDieflag==1) {
					playerDieflag=0;
					break;
				}
				pDMG=Skill.dmgCal(player.get(currentPlayer), enemy.get(currentEnemy), player.get(currentPlayer).skillIndexNum[0]);
				enemy.get(currentEnemy).HP=enemy.get(currentEnemy).HP-pDMG;
				battlePrinter(61);
				if(enemy.get(currentEnemy).HP<=0) {//적 다운시
					if(currentEnemy==3) {
						battlePrinter(10);
					}else {
						battlePrinter(9);
					}
				}//외곽if문
				
				break;
			case 2://비자속기
				//적 행동
				playerDflag=0;
				whatEnemyDoing();
				if(enemyDflag==1) {
					battlePrinter(74);
					break;
				}
				if(playerDieflag==1) {
					playerDieflag=0;
					break;
				}
				pDMG=Skill.dmgCal(player.get(currentPlayer), enemy.get(currentEnemy), player.get(currentPlayer).skillIndexNum[1]);
				enemy.get(currentEnemy).HP=enemy.get(currentEnemy).HP-pDMG;
				battlePrinter(62);
				if(enemy.get(currentEnemy).HP<=0) {//적 다운시
					if(currentEnemy==3) {
						battlePrinter(10);
					}else {
						battlePrinter(9);
					}
				}//외곽if문
				
				break;
			case 3://디버프
				//적행동
				playerDflag=0;
				whatEnemyDoing();
				pAdebuff=Skill.ATKdebuffCal(enemy.get(currentEnemy),player.get(currentPlayer).skillIndexNum[2]);
				pDdebuff=Skill.DEFdebuffCal(enemy.get(currentEnemy),player.get(currentPlayer).skillIndexNum[2]);
				if(pAdebuff==0||pDdebuff!=0) {//방디벞
					enemy.get(currentEnemy).DEF=enemy.get(currentEnemy).DEF*pDdebuff;					
				}else if(pAdebuff!=0||pDdebuff==0) {//공디벞
					enemy.get(currentEnemy).DEF=enemy.get(currentEnemy).ATK*pAdebuff;
				}else {//에러체크
					System.out.println("오류남 스킬 이상함");
				}
				battlePrinter(63);
				if(enemyDflag==1) {
					battlePrinter(75);
					break;
				}
				
				pAdebuff=0;
				pDdebuff=0;
				break;
			case 4:
				//방어, 적행동
				if(playerDflag==1) {
					System.out.println("> "+player.get(currentPlayer).name+"는 방어에 실패했다!");
					playerDflag=0;
				}else {
					playerDflag=1;
					System.out.println("> "+player.get(currentPlayer).name+"는 방어 태세에 들어갔다.");
				}
				whatEnemyDoing();
				if(enemyDflag==1) {
					battlePrinter(75);
					break;
				}
				
				break;
			}
		}//후공끝
		
		
	}//battle끝
	public static void whatEnemyDoing() {
		/*
		 * 1,2,3패턴 고정 5턴마다 방어
		 * 나머지 생각
		 */
		//적 행동패턴 
		enemyDflag=0;
		//System.out.println("[DEBUG] 카운트 : " + enemyPatternCnt);
		if(enemyDieflag==0) {
			if(enemyPatternCnt==1) {
				//System.out.println("[DEBUG] 기본 디버프 패턴");
				enemyDebuff();
			}
			else if(enemyPatternCnt==2) {
				//System.out.println("[DEBUG] 기본 자속 패턴");
				enemyAttack(1);
			}
			else if(enemyPatternCnt==3) {//비자속기
				//System.out.println("[DEBUG] 기본 디버프 패턴");
				enemyAttack(2);
			}
			else if(enemyPatternCnt%5!=0) {
				//System.out.println("[DEBUG] 랜덤 패턴 진입");
				int rand = (int) Math.random() / 2 + 1;
				//System.out.println("[DEBUG] 랜덤 값 : " + rand);
				if (enemy.get(currentEnemy).HP <= enemyFullHp / 2) {//반피 이하면 공격위주로 하도록
					//System.out.println("[DEBUG] 반피 이하 공격패턴");
					enemyAttack(rand);
				} else if (enemyPatternCnt % 3 != 0) {
					//System.out.println("[DEBUG] 랜덤 공격 패턴");
					enemyAttack(rand);
				} else {
					//System.out.println("[DEBUG] 랜덤 디버프 패턴");
					enemyDebuff();
				}
			}//방어태세는 5턴마다 하는걸로
			else if(enemyPatternCnt%5==0) {
				//System.out.println("[DEBUG] 기본 방어 패턴");
				enemyDflag=1;
				enemyPatternCnt++;
			}
		}



	}
	public static void enemyDebuff() {
		//System.out.println(Skill.getSkillNameByIndex(enemy.get(currentEnemy).skillIndexNum[2]));
		eAdebuff=Skill.ATKdebuffCal(player.get(currentPlayer),enemy.get(currentEnemy).skillIndexNum[2]);
		eDdebuff=Skill.DEFdebuffCal(player.get(currentPlayer),enemy.get(currentEnemy).skillIndexNum[2]);
		if(eAdebuff==0||eDdebuff!=0) {//방디벞
			player.get(currentPlayer).DEF=player.get(currentPlayer).DEF*eDdebuff;					
		}else if(pAdebuff!=0||pDdebuff==0) {//공디벞
			player.get(currentPlayer).DEF=player.get(currentPlayer).ATK*eAdebuff;
		}else {//에러체크
			System.out.println("오류남 스킬 이상함");
		}
		battlePrinter(73);
		enemyPatternCnt++;
		if(playerDflag==1) {
			System.out.println("> "+player.get(currentPlayer).name+"은(는) 방어에 실패했다!");
		}
		
	}
	public static void enemyAttack(int num) {
		if(num==1) {//자속기
			if(playerDflag==1) {//플레이어 방어시
				System.out.println("> "+player.get(currentPlayer).name+"은(는) 방어에 성공했다!");
			}else {
				eDMG=Skill.dmgCal(enemy.get(currentEnemy), player.get(currentPlayer), enemy.get(currentEnemy).skillIndexNum[0]);
				player.get(currentPlayer).HP=player.get(currentPlayer).HP-eDMG;
				battlePrinter(71);
				if(player.get(currentPlayer).HP<=0) {//플레이어 다운시
					deathCnt+=1;
					if(deathCnt==3) {//패배
						battlePrinter(12);
					}else {
						battlePrinter(11);
					}
				}//외곽if문
			}
			enemyPatternCnt++;
		}else if(num==2) {//비자속
			if(playerDflag==1) {//플레이어 방어시
				System.out.println("> "+player.get(currentPlayer).name+"은(는) 방어에 성공했다!");
			}else {
				eDMG=Skill.dmgCal(enemy.get(currentEnemy), player.get(currentPlayer), enemy.get(currentEnemy).skillIndexNum[1]);
				player.get(currentPlayer).HP=player.get(currentPlayer).HP-eDMG;
				battlePrinter(72);
				if(player.get(currentPlayer).HP<=0) {//플레이어 다운시
					deathCnt+=1;
					if(deathCnt==3) {//패배
						battlePrinter(12);
					}else {
						battlePrinter(11);
					}
				}//외곽if문
			}
			enemyPatternCnt++;
		}
		
	}
	public static void enemyGenerator() {
		enemy= new HashMap<>();
		Pokemon p1;
		Pokemon p2;
		Pokemon p3;
		switch(towerLevel) {
		//노가다파트
		case 1:
			p1 = new Pokemon(22,1,5);//dex,add,lv 미니브
			p2 = new Pokemon(25,1,5);//dex,add,lv 탄동
			p3 = new Pokemon(28,1,5);//dex,add,lv 꼬링크
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 2:
			p1 = new Pokemon(31,2,5);//dex,add,lv 찌르꼬
			p2 = new Pokemon(37,2,5);//dex,add,lv 잉어킹
			p3 = new Pokemon(10,2,5);//dex,add,lv 맛보돈
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 3:
			p1 = new Pokemon(20,3,7);//dex,add,lv 고오스
			p2 = new Pokemon(39,3,7);//dex,add,lv 동미러
			p3 = new Pokemon(50,3,7);//dex,add,lv 깜눈크
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 4:
			p1 = new Pokemon(47,3,8);//dex,add,lv 고디탱
			p2 = new Pokemon(55,3,8);//dex,add,lv 눈꼬마
			p3 = new Pokemon(43,3,8);//dex,add,lv 스컹뿡
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 5://5단위로 좀 쌔짐
			p1 = new Pokemon(12,3,10);//dex,add,lv 화살꼬빈
			p2 = new Pokemon(15,3,11);//dex,add,lv 델빌
			p3 = new Pokemon(34,3,12);//dex,add,lv 딥상어동
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 6:
			p1 = new Pokemon(1,3,12);//dex,add,lv 나오하
			p2 = new Pokemon(17,3,12);//dex,add,lv 랄토스
			p3 = new Pokemon(22,3,12);//dex,add,lv 미니브
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 7:
			p1 = new Pokemon(41,3,14);//dex,add,lv 요가랑
			p2 = new Pokemon(45,3,14);//dex,add,lv 조로아
			p3 = new Pokemon(53,3,14);//dex,add,lv 활화르바
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 8:
			p1 = new Pokemon(25,4,16);//dex,add,lv 탄동
			p2 = new Pokemon(45,3,16);//dex,add,lv 조로아
			p3 = new Pokemon(32,4,16);//dex,add,lv 찌르버드
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 9:
			p1 = new Pokemon(15,1,17);//dex,add,lv 델빌
			p2 = new Pokemon(20,1,17);//dex,add,lv 고오스
			p3 = new Pokemon(13,1,18);//dex,add,lv 불화살빈
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 10:
			p1 = new Pokemon(18,2,20);//dex,add,lv 킬리아
			p2 = new Pokemon(29,2,20);//dex,add,lv 럭시오
			p3 = new Pokemon(11,4,20);//dex,add,lv 퍼퓨돈
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 11:
			p1 = new Pokemon(34,3,22);//dex,add,lv 딥상어동
			p2 = new Pokemon(38,3,22);//dex,add,lv 갸라도스
			p3 = new Pokemon(2,4,23);//dex,add,lv 나로테
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 12:
			p1 = new Pokemon(5,3,24);//dex,add,lv 악뜨거
			p2 = new Pokemon(13,3,24);//dex,add,lv 불화살빈
			p3 = new Pokemon(16,4,25);//dex,add,lv 헬가
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 13:
			p1 = new Pokemon(41,3,26);//dex,add,lv 요가랑
			p2 = new Pokemon(50,4,26);//dex,add,lv 깜눈크
			p3 = new Pokemon(45,4,26);//dex,add,lv 조로아
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 14:
			p1 = new Pokemon(18,4,28);//dex,add,lv 킬리아
			p2 = new Pokemon(47,4,28);//dex,add,lv 고디탱
			p3 = new Pokemon(39,4,29);//dex,add,lv 동미러
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 15:
			p1 = new Pokemon(43,5,30);//dex,add,lv 스컹뿡
			p2 = new Pokemon(32,5,30);//dex,add,lv 찌르버드
			p3 = new Pokemon(26,6,30);//dex,add,lv 탄자곤
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 16:
			p1 = new Pokemon(13,2,32);//dex,add,lv 불화살빈
			p2 = new Pokemon(23,2,32);//dex,add,lv 올리뇨
			p3 = new Pokemon(57,3,34);//dex,add,lv 꽁어름
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 17:
			p1 = new Pokemon(2,3,34);//dex,add,lv 나로테
			p2 = new Pokemon(5,3,34);//dex,add,lv 악뜨거
			p3 = new Pokemon(8,3,34);//dex,add,lv 아꾸왁
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 18:
			p1 = new Pokemon(51,3,36);//dex,add,lv 악비르
			p2 = new Pokemon(48,3,36);//dex,add,lv 고디보미
			p3 = new Pokemon(55,4,38);//dex,add,lv 눈꼬마
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 19:
			p1 = new Pokemon(35,3,40);//dex,add,lv 한바이트
			p2 = new Pokemon(53,4,40);//dex,add,lv 활화르바
			p3 = new Pokemon(11,4,42);//dex,add,lv 퍼퓨돈
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 20:
			p1 = new Pokemon(38,5,45);//dex,add,lv 갸라도스
			p2 = new Pokemon(16,6,45);//dex,add,lv 헬가
			p3 = new Pokemon(21,6,45);//dex,add,lv 고우스트
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 21:
			p1 = new Pokemon(42,3,46);//dex,add,lv 요가램
			p2 = new Pokemon(19,3,46);//dex,add,lv 가디안
			p3 = new Pokemon(30,3,48);//dex,add,lv 렌트라
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 22:
			p1 = new Pokemon(46,3,50);//dex,add,lv 조로아크
			p2 = new Pokemon(40,3,50);//dex,add,lv 동탁군
			p3 = new Pokemon(27,4,50);//dex,add,lv 석탄산
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 23:
			p1 = new Pokemon(33,4,52);//dex,add,lv 찌르호크
			p2 = new Pokemon(44,4,53);//dex,add,lv 스컹탱크
			p3 = new Pokemon(14,4,54);//dex,add,lv 파이어로
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 24:
			p1 = new Pokemon(24,4,56);//dex,add,lv 올리르바
			p2 = new Pokemon(58,6,56);//dex,add,lv 크레베이스
			p3 = new Pokemon(52,4,58);//dex,add,lv 악비아르
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 25:
			p1 = new Pokemon(3,6,60);//dex,add,lv 마스카나
			p2 = new Pokemon(6,6,60);//dex,add,lv 라우드본
			p3 = new Pokemon(9,6,60);//dex,add,lv 웨이니발
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 26:
			p1 = new Pokemon(11,4,63);//dex,add,lv 퍼퓨돈
			p2 = new Pokemon(16,3,63);//dex,add,lv 요가램
			p3 = new Pokemon(42,4,63);//dex,add,lv 가디안
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 27:
			p1 = new Pokemon(19,4,66);//dex,add,lv 가디안
			p2 = new Pokemon(3,5,67);//dex,add,lv 마스카나
			p3 = new Pokemon(38,6,68);//dex,add,lv 갸라도스
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 28:
			p1 = new Pokemon(14,5,70);//dex,add,lv 파이어로
			p2 = new Pokemon(9,5,73);//dex,add,lv 웨이니발
			p3 = new Pokemon(54,6,73);//dex,add,lv 불카모스
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 29:
			p1 = new Pokemon(58,6,75);//dex,add,lv 크레베이스
			p2 = new Pokemon(6,6,75);//dex,add,lv 라우드본
			p3 = new Pokemon(33,8,78);//dex,add,lv 찌르호크
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 30:
			p1 = new Pokemon(56,7,80);//dex,add,lv 얼음귀신
			p2 = new Pokemon(54,7,80);//dex,add,lv 불카모스
			p3 = new Pokemon(36,9,80);//dex,add,lv 한카리아스
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		}
		
		
		
	}
	public static void change() {
		battlePrinter(1);
		System.out.println("> 바꿀 포켓몬을 선택해주세요.");
		battlePrinter(1);
		System.out.println("○－－－현재－포켓몬－－－○　○－－－바꿀－포켓몬－－－○");
		System.out.print("｜　　　" + player.get(currentPlayer).name);
		for (int i = 0; i < 9 - player.get(currentPlayer).name.length(); i++) System.out.print("　");
		System.out.print("｜　｜１：　" + player.get(1).name);
		for (int i = 0; i < 9 - player.get(1).name.length(); i++) System.out.print("　");
		System.out.println("｜");
		System.out.println("○－－－－－－－－－－－－○　○－－－－－－－－－－－－○");
		System.out.println("　　　　　　　　　　　　　　　○－－－－－－－－－－－－○");
		System.out.print("　　　　　　　　　　　　　　　｜２：　" + player.get(2).name);
		for (int i = 0; i < 9 - player.get(2).name.length(); i++) System.out.print("　");
		System.out.println("｜");
		System.out.println("　　　　　　　　　　　　　　　○－－－－－－－－－－－－○");
		System.out.println("　　　　　　　　　　　　　　　○－－－－－－－－－－－－○");
		System.out.print("　　　　　　　　　　　　　　　｜３：　" + player.get(3).name);
		for (int i = 0; i < 9 - player.get(3).name.length(); i++) System.out.print("　");
		System.out.println("｜");
		System.out.println("　　　　　　　　　　　　　　　○－－－－－－－－－－－－○");
		int select=0;
		while(true) {//스킬입력
			try {
				select  = battleScan.nextInt();
				if(select<1||select>3) {
					battlePrinter(1);
					System.out.println("> 1에서 3 사이의 수를 입력해주세요. ");
					battlePrinter(1);
					continue;
				}
				if(player.get(select).HP<=0) {
					battlePrinter(1);
					System.out.println("> 해당 포켓몬은 전투 불능입니다. 다시 선택해주세요");//전투불능 체크
					battlePrinter(1);
					continue;
				}
			}catch (NumberFormatException e) {
				// TODO: handle exception
				battlePrinter(1);
				System.out.println("> 1에서 3 사이의 수를 입력해주세요. ");
				battlePrinter(1);
				continue;
			}
			break;
		}//선택지
		
		currentPlayer=select;
		playerFullHp=player.get(currentPlayer).HP;
		battlePrinter(1);
		System.out.println("> 가라! "+player.get(currentPlayer).name+"!");
		
	}
	public static void reward() {
		int[] candy= new int[5];//xs s m l xl
		int monsterBall=0;
		if(towerLevel==recordTowerLevel) {
			recordTowerLevel+=1;
		}
		switch(towerLevel) {
		case 1:
			candy[0]=5;
			candy[1]=0;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 2:
			candy[0]=5;
			candy[1]=1;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 3:
			candy[0]=10;
			candy[1]=1;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 4:
			candy[0]=10;
			candy[1]=2;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 5:
			candy[0]=5;
			candy[1]=3;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 6:
			candy[0]=5;
			candy[1]=4;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 7:
			candy[0]=10;
			candy[1]=4;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 8:
			candy[0]=5;
			candy[1]=5;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 9:
			candy[0]=10;
			candy[1]=5;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 10:
			candy[0]=5;
			candy[1]=1;
			candy[2]=1;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 11:
			candy[0]=10;
			candy[1]=1;
			candy[2]=1;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 12:
			candy[0]=5;
			candy[1]=2;
			candy[2]=1;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 13:
			candy[0]=10;
			candy[1]=2;
			candy[2]=1;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 14:
			candy[0]=5;
			candy[1]=3;
			candy[2]=1;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 15:
			candy[0]=5;
			candy[1]=3;
			candy[2]=2;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 16:
			candy[0]=10;
			candy[1]=3;
			candy[2]=2;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 17:
			candy[0]=5;
			candy[1]=4;
			candy[2]=2;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 18:
			candy[0]=10;
			candy[1]=4;
			candy[2]=2;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 19:
			candy[0]=5;
			candy[1]=5;
			candy[2]=2;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 20:
			candy[0]=10;
			candy[1]=3;
			candy[2]=2;
			candy[3]=1;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 21:
			candy[0]=5;
			candy[1]=4;
			candy[2]=3;
			candy[3]=1;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 22:
			candy[0]=10;
			candy[1]=5;
			candy[2]=3;
			candy[3]=1;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 23:
			candy[0]=10;
			candy[1]=5;
			candy[2]=4;
			candy[3]=1;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 24:
			candy[0]=10;
			candy[1]=5;
			candy[2]=5;
			candy[3]=2;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 25:
			candy[0]=10;
			candy[1]=5;
			candy[2]=3;
			candy[3]=2;
			candy[4]=1;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 26:
			candy[0]=10;
			candy[1]=5;
			candy[2]=5;
			candy[3]=2;
			candy[4]=1;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 27:
			candy[0]=10;
			candy[1]=5;
			candy[2]=5;
			candy[3]=3;
			candy[4]=1;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 28:
			candy[0]=10;
			candy[1]=5;
			candy[2]=5;
			candy[3]=5;
			candy[4]=1;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 29:
			candy[0]=10;
			candy[1]=5;
			candy[2]=5;
			candy[3]=5;
			candy[4]=2;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 30:
			candy[0]=10;
			candy[1]=5;
			candy[2]=5;
			candy[3]=5;
			candy[4]=3;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("> 보상으로 경험사탕 XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			monsterBall=3;
			Pokemon_Main.user.setMonsterBallUp(monsterBall);
			System.out.println("> 보상으로 몬스터볼을 "+monsterBall+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("> 트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		}
	}
}
