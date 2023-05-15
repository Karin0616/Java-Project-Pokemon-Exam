
public class UserData {
	private int boxCount;
	private Pokemon gotPokemon[];
	private int trainerLV;
	private int Candy[]=new int[5];
	
	public UserData() {
		
	}
	public UserData(int boxCount,Pokemon gotPokemon[],int trainerLV,int[] Candy) {
		this.boxCount=boxcount;
		this.gotPokemon=gotPokemon;
		this.trainerLV=trainerLV;
		this.Candy=Candy;
	}
	public Pokemon[] getPokemon() {
		return this.gotPokemon;
	}
	public int getTrainnerLv() {
		return this.trainerLV;
	}
	
	public int[] getEXPCandyCount() {
		return this.Candy;
	}
	public void setNewPokemonBox(Pokemon poke) {
		
	}
	public void changeBox(int first, int second) {
		
	}
	public void setTrainnerLv(int add) {
		this.trainerLV+=add;
	}
	
	public void setEXPCandyCount(int Xs,int S,int M,int L,int XL) {
		this.Candy[0]=Xs;
		this.Candy[1]=S;
		this.Candy[2]=M;
		this.Candy[3]=L;
		this.Candy[4]=XL;
	}
	
	
}
