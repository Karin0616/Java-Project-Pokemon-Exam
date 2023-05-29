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
	
	protected static int playerDflag=0;
	protected static int enemyDflag=0;
	SkillDex[] sDex = SkillDex.getInstance();
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
		case 3://배틀조우
			System.out.println(towerLevel+"층의 수호자"+enemy.get(currentEnemy).name+"가 나타났다!");
			
			break;
		case 4:
			System.out.println("가라!"+player.get(currentPlayer).name);
			break;
		case 5://전투 상태화면
			
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			System.out.println("							"+enemy.get(currentEnemy).name+"HP:"+enemy.get(currentEnemy).HP+"/"+enemyFullHp);
			System.out.println();
			System.err.println(player.get(currentPlayer).name+"HP : "+player.get(currentPlayer).HP+"/"+playerFullHp);
			System.out.println("무엇을 할까?");
			System.out.println("1. 싸운다 2. 포켓몬을 교체한다. 3. 포기한다.");
			break;
		
		
		case 9://적 몹 쓰러짐
			System.out.println(towerLevel+"층의 수호자"+enemy.get(currentEnemy).name+"가 쓰러졌다!");
			currentEnemy+=1;
			enemyDieflag=1;
			enemyFullHp=enemy.get(currentEnemy).HP;
			System.out.println(towerLevel+"층의 수호자"+enemy.get(currentEnemy).name+"가 나타났다!");
			break;
		case 10://플레이어승리
			System.out.println(towerLevel+"층의 수호자"+enemy.get(currentEnemy).name+"에게 승리했다!");
			reward();//보상
			Pokemon_Main.user.setRecordTowerLevel(recordTowerLevel);
			System.out.println("엔터키 누르시면 메인으로 돌아갑니다.");
			String tmp=battleScan.nextLine();
			Game_Display.MainScreen();
			break;
		case 11://플레이어몹 사망
			System.out.println(player.get(currentPlayer).name+"가 쓰러졌다!");
			currentPlayer+=1;
			playerDieflag=1;
			playerFullHp=player.get(currentPlayer).HP;
			System.out.println("가라!"+player.get(currentPlayer).name);
			break;
		case 12://플레이어 패배
			System.out.println(towerLevel+"층의 수호자"+enemy.get(currentEnemy).name+"에게 패배했다!");
			System.out.println("나는 눈앞이 깜깜해졌다..");
			System.out.println("좀 더 성장해서 도전해보시길 바랍니다.");
			System.out.println("엔터키 누르시면 메인으로 돌아갑니다.");
			battleScan.nextLine();
			Game_Display.MainScreen();
			break;
		case 61://자속기 플레이어공격
			System.out.println(player.get(currentPlayer).name+"의 "+Skill.getSkillNameByIndex(player.get(currentPlayer).skillIndexNum[0])+"공격!");
			System.out.println(enemy.get(currentEnemy).name+"는 "+pDMG+"의 피해를 입었다!.");
			break;
		case 62://비자속기 플레이어공격
			System.out.println(player.get(currentPlayer).name+"의 "+Skill.getSkillNameByIndex(player.get(currentPlayer).skillIndexNum[1])+"공격!");
			System.out.println(enemy.get(currentEnemy).name+"는 "+pDMG+"의 피해를 입었다!.");
			break;
		case 63://디버프
			System.out.println(player.get(currentPlayer).name+"의 "+Skill.getSkillNameByIndex(player.get(currentPlayer).skillIndexNum[2])+"공격!");
			if(pAdebuff!=0) {
				System.out.println(enemy.get(currentEnemy).name+"의 방어가 낮아졌다.");
			}else if(pDdebuff!=0) {
				System.out.println(enemy.get(currentEnemy).name+"의 공격이 낮아졌다.");
			}
			break;
		case 71://적공격 자속
			System.out.println(enemy.get(currentEnemy).name+"의 "+Skill.getSkillNameByIndex(enemy.get(currentEnemy).skillIndexNum[0])+"공격!");
			System.out.println(player.get(currentPlayer).name+"는 "+eDMG+"의 피해를 입었다!.");
			break;
		case 72:
			System.out.println(enemy.get(currentEnemy).name+"의 "+Skill.getSkillNameByIndex(enemy.get(currentEnemy).skillIndexNum[1])+"공격!");
			System.out.println(player.get(currentPlayer).name+"는 "+eDMG+"의 피해를 입었다!.");
			break;
		case 73:
			System.out.println(enemy.get(currentEnemy).name+"의 "+Skill.getSkillNameByIndex(enemy.get(currentEnemy).skillIndexNum[2])+"공격!");
			if(eAdebuff!=0) {
				System.out.println(player.get(currentPlayer).name+"의 방어가 낮아졌다.");
			}else if(eDdebuff!=0) {
				System.out.println(player.get(currentPlayer).name+"의 공격이 낮아졌다.");
			}
			break;
		case 74:
			System.out.println(enemy.get(currentEnemy).name+"은/는 공격을 방어했다!");
			break;
		case 75:
			System.out.println(enemy.get(currentEnemy).name+"은/는 방어에 실패했다!");
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
						System.out.println("1,2,3사이의 수를 입력해주세요. ");
						continue;
					}
				}catch (NumberFormatException e) {
					// TODO: handle exception
					System.out.println("1,2,3사이의 수를 입력해주세요. ");
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
				whatEnemyDoing();
				break;
			case 3://포기한다.
				System.out.println("정말로 포기하겠습니까? 포기하시려면 -1을 입력해주세요. 다른 입력시 배틀로 돌아갑니다.");
					try {
						select = battleScan.nextInt();
						if(select==-1) {
							System.out.println("배틀을 종료하고 메인화면으로 돌아갑니다.");
							Game_Display.MainScreen();
						}else {
							System.out.println("배틀을 재개합니다.");
						}
					}catch (NullPointerException e) {
						// TODO: handle exception
						System.out.println("배틀을 재개합니다.");
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
		
		System.out.println(player.get(currentPlayer).name+"은 무슨 일을 할까?");
		for(int i=0;i<4;i++) {
			pSkillName[i]=Skill.getSkillNameByIndex(player.get(currentPlayer).skillIndexNum[i]);
			eSkillName[i]=Skill.getSkillNameByIndex(enemy.get(currentEnemy).skillIndexNum[i]);
		}
		System.out.println("1."+pSkillName[0]+"2. "+pSkillName[1]+"3. "+pSkillName[2]+"4. "+pSkillName[3]);
		int select =0;
		while(true) {//스킬입력
			try {
				select  = battleScan.nextInt();
				if(select<1||select>4) {
					System.out.println("1,2,3,4사이의 수를 입력해주세요. ");
					continue;
				}
			}catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("1,2,3사이의 수를 입력해주세요. ");
				continue;
			}
			break;
		}//선택지
		int fs=0;//0은 플레이어 1은 적
		if(player.get(currentPlayer).SPD>enemy.get(currentEnemy).SPD) {
			fs=0;
		}else if(player.get(currentPlayer).SPD<enemy.get(currentEnemy).SPD) {
			fs=1;
		}else {
			fs=(int) (Math.random())/2;
		}
		if(fs==0) {//플레이어 선턴
			System.out.println("나의 선공!");
			enemyDflag=0;
			//System.out.println("방어계산기 "+playerDflag+" "+enemyDflag);
			switch (select) {//스킬사용
				case 1: //자속기
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
				playerDflag=1;
				System.out.println(player.get(currentPlayer).name+"는 방어 태세에 들어갔다.");
				whatEnemyDoing();
				if(enemyDflag==1) {
					battlePrinter(75);
					break;
				}
				playerDflag=0;
				break;
			}
		}else if(fs==1) {//후공
			System.out.println("적의 선공!");
			enemyDflag=0;
			switch (select) {//스킬사용
			case 1: //자속기
				//적 행동
				if(enemyDflag==1) {
					battlePrinter(74);
					break;
				}
				whatEnemyDoing();
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
				if(enemyDflag==1) {
					battlePrinter(74);
					break;
				}
				whatEnemyDoing();
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
				playerDflag=1;
				System.out.println(player.get(currentPlayer).name+"는 방어 태세에 들어갔다.");
				if(enemyDflag==1) {
					battlePrinter(75);
					break;
				}
				whatEnemyDoing();
				playerDflag=0;
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
		if(enemyDieflag==1) {
			enemyDieflag=0;
		
		}else {
			if(enemyPatternCnt==1) {
				enemyDebuff();
				battlePrinter(73);
			}
			else if(enemyPatternCnt==2) {
				enemyAttack(1);
				battlePrinter(71);
			}
			else if(enemyPatternCnt==3) {//비자속기
				enemyAttack(2);
				battlePrinter(72);
			}
			else if(enemyPatternCnt%5!=0) {
				if(enemy.get(currentEnemy).HP<=enemyFullHp/2) {//반피 이하면 공격위주로 하도록
					enemyAttack((int)Math.random()/2+1);
				}else {
					if(enemyPatternCnt%3<3) {
						enemyAttack((int)Math.random()/2+1);
					}else {
						enemyDebuff();
					}
				}
			}
			
			
			//방어태세는 5턴마다 하는걸로
			else if(enemyPatternCnt%5==0) {
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
		enemyPatternCnt++;
		if(playerDflag==1) {
			System.out.println(player.get(currentPlayer).name+"은 방어에 실패했다!");
		}
		eAdebuff=0;
		eDdebuff=0;
	}
	public static void enemyAttack(int num) {
		if(num==1) {//자속기
			if(playerDflag==1) {//플레이어 방어시
				System.out.println(player.get(currentPlayer).name+"은 방어에 성공했다!");
			}else {
				eDMG=Skill.dmgCal(enemy.get(currentEnemy), player.get(currentPlayer), enemy.get(currentEnemy).skillIndexNum[0]);
				player.get(currentPlayer).HP=player.get(currentPlayer).HP-eDMG;
				if(player.get(currentPlayer).HP<=0) {//플레이어 다운시
					if(currentPlayer==3) {//패배
						battlePrinter(12);
					}else {
						battlePrinter(11);
					}
				}//외곽if문
			}
			enemyPatternCnt++;
		}else if(num==2) {//비자속
			if(playerDflag==1) {//플레이어 방어시
				System.out.println(player.get(currentPlayer).name+"은 방어에 성공했다!");
			}else {
				eDMG=Skill.dmgCal(enemy.get(currentEnemy), player.get(currentPlayer), enemy.get(currentEnemy).skillIndexNum[1]);
				player.get(currentPlayer).HP=player.get(currentPlayer).HP-eDMG;
				if(player.get(currentPlayer).HP<=0) {//플레이어 다운시
					if(currentPlayer==3) {//패배
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
			p2 = new Pokemon(25,1,5);//dex,add,lv탄동
			p3 = new Pokemon(28,1,5);//dex,add,lv꼬링크
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 2:
			p1 = new Pokemon(31,2,5);//dex,add,lv 찌르꼬
			p2 = new Pokemon(37,2,5);//dex,add,lv잉어킹
			p3 = new Pokemon(10,2,5);//dex,add,lv맛보돈
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 3:
			p1 = new Pokemon(20,3,7);//dex,add,lv 고오스
			p2 = new Pokemon(39,3,7);//dex,add,lv동미러
			p3 = new Pokemon(50,3,7);//dex,add,lv깜눈크
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 4:
			p1 = new Pokemon(47,3,8);//dex,add,lv 고디탱
			p2 = new Pokemon(55,3,8);//dex,add,lv눈꼬마
			p3 = new Pokemon(43,3,8);//dex,add,lv스컹뿡
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 5://5단위로 좀 쌔짐
			p1 = new Pokemon(12,3,10);//dex,add,lv 화살꼬빈
			p2 = new Pokemon(15,3,11);//dex,add,lv델빌
			p3 = new Pokemon(34,3,12);//dex,add,lv딥상어동
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 6:
			p1 = new Pokemon(1,3,12);//dex,add,lv 나오하
			p2 = new Pokemon(17,3,12);//dex,add,lv랄토스
			p3 = new Pokemon(22,3,12);//dex,add,lv미니브
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 7:
			p1 = new Pokemon(41,3,14);//dex,add,lv 요가랑
			p2 = new Pokemon(45,3,14);//dex,add,lv조로아
			p3 = new Pokemon(53,3,14);//dex,add,lv활화르바
			enemy.put(1, p1);
			enemy.put(2, p2);
			enemy.put(3, p3);
			break;
		case 8:
			p1 = new Pokemon(25,4,16);//dex,add,lv 탄동
			p2 = new Pokemon(45,3,16);//dex,add,lv조로아
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
			p1 = new Pokemon(58,3,22);//dex,add,lv 딥상어동
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
		}
		
		
		
	}
	public static void change() {
		System.out.println("바꿀 포켓몬을 선택해주세요.");
		System.out.println("현재 포켓몬: "+player.get(currentPlayer).name);
		System.out.println("바꿀 포켓몬 번호: 1."+player.get(1).name+"2. "+player.get(2).name+"3. "+player.get(3).name);
		int select=0;
		while(true) {//스킬입력
			try {
				select  = battleScan.nextInt();
				if(select<1||select>3) {
					System.out.println("1,2,3사이의 수를 입력해주세요. ");
					continue;
				}
				if(player.get(select).HP<=0) {
					System.out.println("해당 포켓몬은 전투 불능입니다. 다시선택해주세요");//전투불능 체크
					continue;
				}
			}catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("1,2,3사이의 수를 입력해주세요. ");
				continue;
			}
			break;
		}//선택지
		
		currentPlayer=select;
		playerFullHp=player.get(currentPlayer).HP;
		System.out.println("가라!"+player.get(currentPlayer).name);
		
	}
	public static void reward() {
		int[] candy= new int[5];//xs s m l xl
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
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 2:
			candy[0]=5;
			candy[1]=1;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 3:
			candy[0]=10;
			candy[1]=1;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 4:
			candy[0]=10;
			candy[1]=2;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 5:
			candy[0]=5;
			candy[1]=3;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 6:
			candy[0]=5;
			candy[1]=4;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 7:
			candy[0]=10;
			candy[1]=4;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 8:
			candy[0]=5;
			candy[1]=5;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 9:
			candy[0]=10;
			candy[1]=5;
			candy[2]=0;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 10:
			candy[0]=5;
			candy[1]=1;
			candy[2]=1;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		case 11:
			candy[0]=10;
			candy[1]=1;
			candy[2]=1;
			candy[3]=0;
			candy[4]=0;
			Pokemon_Main.user.setEXPCandyUP(candy);
			System.out.println("보상으로 경험사탕XS "+candy[0]+"개, S "+candy[1]+"개, M "+candy[2]+"개, L "+candy[3]+"개, XL "+candy[4]+"개를 획득했다!");
			Pokemon_Main.user.setTrainerLvUP(1);
			System.out.println("트레이너 레벨이 "+Pokemon_Main.user.getTrainerLV()+"이 되었다!");
			break;
		}
	}
}
