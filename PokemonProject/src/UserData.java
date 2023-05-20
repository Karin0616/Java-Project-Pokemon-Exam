
public class UserData {
	private int boxCount=0;
	private Pokemon gotPokemon[];
	private int trainerLV;
	private int Candy[]=new int[5];
	
	public UserData() {
		
	}
	public UserData(int boxCount,Pokemon gotPokemon[],int trainerLV,int[] Candy) {
		this.setBoxCount(boxCount);
		this.gotPokemon=gotPokemon;
		this.trainerLV=trainerLV;
		this.Candy=Candy;
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
