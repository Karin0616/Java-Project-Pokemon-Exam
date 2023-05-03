
public class PokemonDex {
	private static PokemonDex PDex[] = new PokemonDex[20];
	private int indexNum;
	private String place;
	private String PokemonName;
	private float baseStatus[]= new float[4];// 종족치,hp 공격 방어 스피드 순
	private int Lv;
	private int EveLv;
	private SkillDex skillIndexNum[] = new SkillDex[4];
	
	
	private PokemonDex() {
		//1번
		for(int i = 0; i<20;i++) {
			switch(i) {
			case 0:
				PDex[i].PokemonName = "나오하";
				PDex[i].indexNum = 1;
				PDex[i].place = "미정";// 차후 정함
				PDex[i].baseStatus[0]= 40;// hp
				PDex[i].baseStatus[1]= 61;//공격
				PDex[i].baseStatus[2]= 54;//방어
				PDex[i].baseStatus[3]= 65;//스피드
				PDex[i].Lv =1;
				PDex[i].EveLv =16;
				//PDex[i].skillIndexNum[0].index= 1;
				
			}
		}
	}
	public void getDex(int dex) {
		switch(dex-1){
		case 0:
			
		}
	}
	
}
