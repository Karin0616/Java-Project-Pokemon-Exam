import java.util.Scanner;
import java.util.Random;
public class Gatcha {
	public static String place="입구";
	static Pokemon p;
	private static int randomAdd;
	private static int randLv;
	private static Random random = new Random();
	static PokemonDex pdex[] = PokemonDex.getInstance();
	public Gatcha() {
		
	}
	
	public static void gatchamode() {
		//포획모드 상황출력
		printer(1);
		if(Pokemon_Main.user.getBoxCount()==20) {
			printer(98);
		}
		int selecter=0;
		while(true) {
			try {
				selecter= Game_Display.GameScan.nextInt();
			}catch(NumberFormatException e) {
				System.out.println("0에서 7 사이의 숫자를 입력해주세요.");
			}
			
			//예외처리
			if(selecter>=0&&selecter<=7) {
				break;
			}else {
				System.out.println("0에서 7 사이의 값을 입력해주세요.");
			}
		}	
		switch(selecter) {
		case 0:
			Game_Display.MainScreen();
			break;
		default:
		
			map(selecter);
			
		}
		
	}
	public static void printer(int num) {
		/*
		 * 텍스트ui부분
		 */
		switch (num) {
		case 1:
			System.out.println("포획모드");
			System.out.println("현재 위치"+place);
			System.out.println("현재 포켓몬 소유갯수"+Pokemon_Main.user.getBoxCount()+"/20");
			System.out.println("탐색할 에리어를 선택하세요.");
			System.out.println("1.초원 2.바다 3.화산 4.바위산 5.호수 6. 사막 7. 동굴 0. 돌아가기");//help
			break;
		case 2:
		case 11:
			System.out.println("현재 위치"+place);
			System.out.println("어디를 조사할까요?");
			System.out.println("1. 나무위를 조사한다");
			System.out.println("2. 수풀을 헤집는다");
			break;
		case 12:
			System.out.println("현재 위치"+place);
			System.out.println("어디를 조사할까요?");
			System.out.println("야생의"+p.name+p.Lv+"이 나타났다.");
			System.out.println("무엇을 할까?");
			System.out.println("1. 잡는다 2. 도망간다");
			break;
		case 97:
			System.out.println(p.name+p.Lv+"을 잡았다!");
			System.out.println("메인으로 돌아갑니다.");
			Game_Display.MainScreen();
			break;
		
		case 98:
			//박스초과
			System.out.println("박스가 꽉 찼습니다. 박스를 정리해주세요.");
			System.out.println("엔터키를 누르시면 메인으로 넘어갑니다.");
			String ani= Game_Display.GameScan.nextLine();
			Game_Display.MainScreen();
			break;
		case 99:
			//돌아가기 기능
			gatchamode();
			break;
		
		}
		
		
	}
	public static void map(int num) {
		//여기서 이것저것 다함
		/*
		 * 여기서 printer()부분이 이제 실행되는 것이니까 잘 찾아넣고
		 * 예외처리 trycatch에 catch부분 프린트도 알아서 잘 바꿔도됨.
		 * 
		 */
		//help
		switch(num) {
		case 1:
			place="미정"; //테스트용으로 일단 미정으로 바꿈 원래 초원임
			printer(11);
			int selecter=0;
			while(true) {
				try {
					selecter= Game_Display.GameScan.nextInt();
				}catch(NumberFormatException e) {
					System.out.println("1에서 2 사이의 숫자를 입력해주세요.");
				}
				
				//예외처리
				if(selecter==1 ||selecter==2) {
					break;
				}else {
					System.out.println("1 혹은 2만 입력 가능합니다.");
				}
			}			
			
			//System.out.println("prograss1");
			pokemonGenerator();
			//System.out.println("prograss2");
			printer(12);
			while(true) {
				try {
					selecter= Game_Display.GameScan.nextInt();
				}catch(NumberFormatException e) {
					System.out.println("1에서 2 사이의 숫자를 입력해주세요.");
				}
				
				//예외처리
				if(selecter==1 ||selecter==2) {
					break;
				}else {
					System.out.println("1 혹은 2만 입력 가능합니다.");
				}
			}	
			if(selecter==1) {
				
				Pokemon tmP[]=Pokemon_Main.user.getPokemon();
				
				tmP[Pokemon_Main.user.getBoxCount()]= new Pokemon(p.index,p.add,p.Lv);
				
				Pokemon_Main.user.setBoxCount(Pokemon_Main.user.getBoxCount()+1);
				//박스카운트 변경					
				Pokemon_Main.user.setGotPokemon(tmP);
				printer(97);
				
			}else if(selecter==2) {
				printer(99);
			}
			
			
		}
	}

	public static void setRandomAdd() {
		randomAdd = (int)(Math.random()*10);
	}
	public static void setLv(int tmpPindex) {
		int Tlv = Pokemon_Main.user.getTrainerLV();
		//System.out.println("트레이너레벨"+Tlv);//임시
		if(Tlv<10) {
			randLv=random.nextInt(1,Tlv);
			//System.out.println("야생"+randLv);//임시
		}else if(Tlv<20) {
			//최소값 설정
			randLv=random.nextInt(7,Tlv);
			//System.out.println("야생"+randLv);//임시
			if(randLv<pdex[tmpPindex].getLv()) {
				//만약 랜덤레벨이 본인 최소 레벨보다 낮을 때
				randLv=pdex[tmpPindex].getLv();
			}
			
		}else if(Tlv<30) {
			//최소값 설정
			randLv=random.nextInt(16, Tlv);
			//System.out.println("야생"+randLv);//임시
			if(randLv<pdex[tmpPindex].getLv()) {
				//만약 랜덤레벨이 본인 최소 레벨보다 낮을 때
				randLv=pdex[tmpPindex].getLv();
			}
			
		}else if(Tlv<40) {
			//최소값 설정
			randLv=random.nextInt(27, Tlv);
		//	System.out.println("야생"+randLv);//임시
			if(randLv<pdex[tmpPindex].getLv()) {
				//만약 랜덤레벨이 본인 최소 레벨보다 낮을 때
				randLv=pdex[tmpPindex].getLv();
			}
			
		}else if(Tlv<50) {
			//최소값 설정
			randLv=random.nextInt(35, Tlv);
			//System.out.println("야생"+randLv);//임시
			if(randLv<pdex[tmpPindex].getLv()) {
				//만약 랜덤레벨이 본인 최소 레벨보다 낮을 때
				randLv=pdex[tmpPindex].getLv();
			}
			
		}else if(Tlv<60) {
			//최소값 설정
			randLv=random.nextInt(45, Tlv);
		//	System.out.println("야생"+randLv);//임시
			if(randLv<pdex[tmpPindex].getLv()) {
				//만약 랜덤레벨이 본인 최소 레벨보다 낮을 때
				randLv=pdex[tmpPindex].getLv();
			}
			
		}else if(Tlv<101) {
			//최소값 설정
			randLv=random.nextInt(55, Tlv);
		//	System.out.println("야생"+randLv);//임시
			if(randLv<pdex[tmpPindex].getLv()) {
				//만약 랜덤레벨이 본인 최소 레벨보다 낮을 때
				randLv=pdex[tmpPindex].getLv();
			}
		}
		
	}
	public static void pokemonGenerator() {
		//System.out.println("prograss3");
		int tmpPindex;
		while(true) {
			//System.out.println("prograss5");
			tmpPindex=random.nextInt(1,54);
			//System.out.println(tmpPindex);
			//Game_Display.GameScan.next();
			if(pdex[tmpPindex].getPlace().equals(place)&&pdex[tmpPindex].getLv()<=Pokemon_Main.user.getTrainerLV()) {
				break;
			}
			//Game_Display.GameScan.next();
			//System.out.println("prograss6");
		}
		setRandomAdd();
		setLv(tmpPindex);
		p=new Pokemon(tmpPindex,randomAdd,randLv);
		//System.out.println("prograss4");
		
	}
	
}
