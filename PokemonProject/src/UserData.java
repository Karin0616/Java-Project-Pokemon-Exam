
public class UserData {
	private int boxCount=0;
	private Pokemon gotPokemon[]= new Pokemon[20];
	private int trainerLV=50; //테스트때문에 바꿈 디폴트값 1
	private int Candy[]=new int[5];
	
	public UserData() {
		
	}
	public UserData(int boxCount,Pokemon gotPokemon[],int trainerLV,int[] Candy) {
		this.setBoxCount(boxCount);
		this.gotPokemon=gotPokemon;
		this.trainerLV=trainerLV;
		this.Candy=Candy;
	}
	public void setupStart() {
		this.gotPokemon[0] =  new Pokemon(1,5,5);
		this.boxCount =1;
		this.trainerLV=50;//임시
		for(int i : Candy) {
			i=0;
		}
	}
	public Pokemon[] getPokemon() {
		return this.gotPokemon;
	}
	public void setGotPokemon(Pokemon[] Poke) {
		this.gotPokemon=Poke;
	}
	public int getTrainerLV() {
		return this.trainerLV;
	}
	public void setTrainerLv(int add) {
		this.trainerLV+=add;
	}
	
	public int[] getEXPCandyCount() {
		return this.Candy;
	}
	public void setEXPCandyCount(int[] Candy) {
		for(int i=0;i<Candy.length;i++) {
			this.Candy[i]= Candy[i];
		}
	}
	
	public void changeBox(int first, int second) {
		
	}
	
	
	
	public int getBoxCount() {
		return boxCount;
	}
	public void setBoxCount(int boxCount) {
		this.boxCount = boxCount;
	}
	
	
}
