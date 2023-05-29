
/*
 *클래스 설명
 *소지 포켓몬 확인 (이름 레벨 능력치)개체치는 일부로 안보여줄거임
 *트레이너 레벨 확인
 *소지 사탕 갯수 확인
 *소지포켓몬 사탕맥이기
 *포켓몬 순서 변경(배열 0,1,2번이 전투몹임)
 *포켓몬 놓아주기
 *할까말까 고민중인거
 *트레이너의 최고기록 확인
 */
public class PokemonBox {
	static Pokemon p=null;
	public static int selecter=0;
	public static int index=0;
	public static void pokemonBoxScene() {
		/* 텍스트UI부분
		 * candy[]는 0번부터 4번까지 총5개, xs s m L XL 순
		 * 
		 */
		int candy[]= Pokemon_Main.user.getEXPCandyCount();
		System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
		System.out.println("　　　　　　　　　　포　켓　몬　박　스　　　　　　　　　　");
		System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> 트레이너 레벨 : "+Pokemon_Main.user.getTrainerLV());
		System.out.println("　　　　　　　―――――――◈―――――――　　　　　　　");
		System.out.println("> 원하는 메뉴를 선택해주세요.");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("○――――――――――○　○――――――――――――――○");
		System.out.println("｜１：　소지　포켓몬　｜　｜　　　　　경험사탕　　　　　｜");
		System.out.println("○――――――――――○　｜　ＸＳ　"+Game_Display.toFull(String.format("%03d", candy[0]))+"　Ｓ　"+Game_Display.toFull(String.format("%03d", candy[1])+"　｜"));
		System.out.println("○――――――――――○　｜　Ｍ　　"+Game_Display.toFull(String.format("%03d", candy[2]))+"　Ｌ　"+Game_Display.toFull(String.format("%03d", candy[3])+"　｜"));
		System.out.println("｜２：　뒤로가기　　　｜　｜　ＸＬ　"+Game_Display.toFull(String.format("%03d", candy[4]))+"　　　　　　　｜");
		System.out.println("○――――――――――○　○――――――――――――――○");
		while(true) {
			try {
				selecter = Game_Display.GameScan.nextInt();
				if(selecter == 1) {
					pokemonChecker();
				}else if(selecter == 2) {
					Game_Display.MainScreen();
				}else {
					System.out.println("◈＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼◈");
					System.out.println("　　　　　　　　　　포　켓　몬　박　스　　　　　　　　　　");
					System.out.println("◈／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／＼／◈");
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("> 트레이너 레벨 : "+Pokemon_Main.user.getTrainerLV());
					System.out.println("　　　　　　　―――――――◈―――――――　　　　　　　");
					System.out.println("> 다시 입력해주세요.");
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("○――――――――――○　○――――――――――――――○");
					System.out.println("｜１：　소지　포켓몬　｜　｜　　　　　경험사탕　　　　　｜");
					System.out.println("○――――――――――○　｜　ＸＳ　"+Game_Display.toFull(String.format("%03d", candy[0]))+"　Ｓ　"+Game_Display.toFull(String.format("%03d", candy[1])+"　｜"));
					System.out.println("○――――――――――○　｜　Ｍ　　"+Game_Display.toFull(String.format("%03d", candy[2]))+"　Ｌ　"+Game_Display.toFull(String.format("%03d", candy[3])+"　｜"));
					System.out.println("｜２：　뒤로가기　　　｜　｜　ＸＬ　"+Game_Display.toFull(String.format("%03d", candy[4]))+"　　　　　　　｜");
					System.out.println("○――――――――――○　○――――――――――――――○");
				}
			}catch (NumberFormatException e) {
				// TODO: handle exception
			}
			break;
		}
		
		
	}
	public static void pokemonChecker() {
		/*
		 * 텍스트 ui부분
		 */

		//포켓몬 각각 스텟 출력부분. 통째로 바꿔도 됨
		//포켓몬 인덱스, 이름,타입,레벨,체력,공 방 체 스피드 순서는 알아서 잘 배열해서 구성해주면 됨
		try {
			System.out.println("○―――――――――――소지포켓몬―――――――――――○");
			for(int i=0; i<Pokemon_Main.user.getBoxCount();i++) {
				Pokemon p = Pokemon_Main.user.getPokemonEach(i).getPokemon();//이거는 잘 활용해보는게 편함
				System.out.print("　"+Game_Display.toFull(String.valueOf(i+1))+"　"+p.name+"　Ｌｖ．" + Game_Display.toFull(String.valueOf(p.Lv)));
				for (int j = 0; j < 19 - p.name.length() - p.type.length() - String.valueOf(p.Lv).length(); j++) System.out.print("　");
				System.out.println(p.type+"타입");
				System.out.println("　　　체력："+(int)p.HP+"　공격력："+(int)p.ATK+"　방어력："+(int)p.DEF+"　스피드： "+(int)p.SPD);
				if (i != Pokemon_Main.user.getBoxCount() - 1)System.out.println("　　　　　　　　　　　　　―――　　　　　　　　　　　　　");
			}
			System.out.println("○―――――――――――――――――――――――――――○");
		}catch (NullPointerException e) {
			// TODO: handle exception
			
		}
		
		//System.out.println("");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> 포켓몬을 지정하시려면 포켓몬의 인덱스를 입력해주세요.");
		System.out.println("> 메인으로 돌아가시려면 -1을 입력해주세요.");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		while(true) {
			try {
				selecter = Game_Display.GameScan.nextInt();
				
			}catch (NumberFormatException e) {
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 다시 입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			}
			if(selecter==-1) {
				Game_Display.MainScreen();
				break;
			}else if(selecter<-1||selecter>Pokemon_Main.user.getBoxCount()) {
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 다시 입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			}else {
				break;
			}
			
		}
		index=selecter-1;//포켓몬 인덱스값 저장
		p = Pokemon_Main.user.getPokemonEach(index).getPokemon();//이거는 잘 활용해보는게 편함
		System.out.print("　"+Game_Display.toFull(String.valueOf(selecter))+"　"+p.name+"　Ｌｖ．" + Game_Display.toFull(String.valueOf(p.Lv)));
		for (int j = 0; j < 19 - p.name.length() - p.type.length() - String.valueOf(p.Lv).length(); j++) System.out.print("　");
		System.out.println(p.type+"타입");
		System.out.println("　　　체력："+(int)p.HP+"　공격력："+(int)p.ATK+"　방어력："+(int)p.DEF+"　스피드： "+(int)p.SPD);
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> 무엇을 하시겠습니까?");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("○――――――――――――○　○――――――――――――○");
		System.out.println("｜１：경험사탕을　먹인다　｜　｜２：　순서를　바꾼다　　｜");
		System.out.println("○――――――――――――○　○――――――――――――○");
		System.out.println("○――――――――――――○　○――――――――――――○");
		System.out.println("｜３：　놓아준다　　　　　｜　｜４：　돌아가기　　　　　｜");
		System.out.println("○――――――――――――○　○――――――――――――○");
		int tmp=0;
		while(true) {
			try {
				tmp = Game_Display.GameScan.nextInt();
				
			}catch (NumberFormatException e) {
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 다시 입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			}
				
			switch(tmp) {
			case 1:
				useCandy();
				break;
			case 2:
				change(index);
				break;
			case 3:
				buybuy(index);
				break;
			case 4:
				pokemonBoxScene();
				break;
			default:
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 다시 입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			}
		}
		
		
	}//포켓몬 체커 끝
	
	public static void useCandy() {
		/*
		 * 텍스트 ui 
		 */
		int candy[]= Pokemon_Main.user.getEXPCandyCount();
		System.out.println("○―――――――――――경험　사탕―――――――――――○");
		System.out.println("｜　　ＸＳ　"+Game_Display.toFull(String.format("%03d", candy[0]))+"　　Ｓ　　"+Game_Display.toFull(String.format("%03d", candy[1])+"　　Ｍ　　"+Game_Display.toFull(String.format("%03d", candy[2]))+"　　　｜"));
		System.out.println("｜　　Ｌ　　"+Game_Display.toFull(String.format("%03d", candy[3]))+"　　ＸＬ　"+Game_Display.toFull(String.format("%03d", candy[4]))+"　　　　　　　　　　　｜");
		System.out.println("○―――――――――――――――――――――――――――○");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> 어떤 사탕을 쓰실 건가요?");
		System.out.println("> [0]XS [1]S [2]M [3]L [4]XL");
		System.out.println("> 돌아가시려면 9번을 입력해주세요.");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("○―――――――○　○―――――――○　○―――――――○");
		System.out.println("｜１：　Ｘ　Ｓ　｜　｜１：　　Ｓ　　｜　｜２：　　Ｍ　　｜");
		System.out.println("○―――――――○　○―――――――○　○―――――――○");
		System.out.println("○―――――――○　○―――――――○　○―――――――○");
		System.out.println("｜３：　　Ｌ　　｜　｜４：　Ｘ　Ｌ　｜　｜９：　돌아가기｜");
		System.out.println("○―――――――○　○―――――――○　○―――――――○");
		boolean ch=true;
		while(ch) {
			try {
				selecter = Game_Display.GameScan.nextInt();
				
			}catch (NumberFormatException e) {
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 다시 입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			}
			switch(selecter) {
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
					Pokemon_Main.user.setCandyDown(selecter);
					p.lvCalculrator(selecter);
					Pokemon_Main.user.setPokemonReplace(index, p);
					pokemonChecker();
					ch=false;
					break;
				case 9:
					Game_Display.pokemonBox();
					ch=false;
					break;
			}
			

		}//while
	}
	public static void change(int sel) {
		/*
		 * 텍스트 ui
		 */
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> 해당 포켓몬을 몇번으로 바꾸실건가요? ");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		int a=0;
		boolean ch = true;
		while(ch) {
			System.out.print("입력 : ");
			try {
				selecter = Game_Display.GameScan.nextInt();
				if(selecter<0||selecter>Pokemon_Main.user.getBoxCount()) {
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.println("> 다시 입력해주세요.");
					System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
					System.out.print("입력 :");
				}else {
					a=selecter;
					break;
				}
			}catch (NumberFormatException e) {
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.println("> 다시 입력해주세요.");
				System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
				System.out.print("입력 : ");
			}
		}
		
		
		Pokemon_Main.user.changeBox(a,sel);
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> 변경되었습니다!");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		pokemonChecker();
	}
	public static void buybuy(int num) {
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> "+p.name+"을(를) 밖에 놓아주었다. 바이바이 "+p.name+"!");
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		Pokemon_Main.user.removePokemon(num);
		pokemonChecker();
	}
	
}//클래스끝
