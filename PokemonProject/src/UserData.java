
public class UserData {
	private int boxCount=0;
	private Pokemon gotPokemon[]= new Pokemon[20];
	private int trainerLV=10; //테스트때문에 바꿈 디폴트값 1
	private int Candy[]=new int[5];
	private int recordTowerLevel=1;
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
		this.gotPokemon[1]= new Pokemon(4,5,5);
		this.gotPokemon[2]= new Pokemon(7,5,5);
		this.boxCount =3;
		this.trainerLV=1;//임시
		for(int i=0;i<Candy.length;i++) {
			Candy[i]=0;//임시
		}
		
	}
	public Pokemon[] getPokemon() {
		return this.gotPokemon;
	}
	public Pokemon getPokemonEach(int num) {
		return this.gotPokemon[num];
	}
	public void setGotPokemon(Pokemon[] Poke) {
		this.gotPokemon=Poke;
		//System.out.println(gotPokemon[boxCount-1].name);
	}
	public void setPokemonReplace(int index,Pokemon P) {
		this.gotPokemon[index]=P;
		//System.out.println(gotPokemon[boxCount-1].name);
	}
	public void removePokemon(int num) {
		for(int i=num-1;i<this.boxCount;i++) {
			this.gotPokemon[i]=this.gotPokemon[i+1];
		}

	}
	public int getTrainerLV() {
		return this.trainerLV;
	}
	public void setTrainerLvUP(int add) {
		this.trainerLV+=add;
	}
	public void setTrainerLv(int add) {
		this.trainerLV=add;
	}
	
	public int[] getEXPCandyCount() {
		return this.Candy;
	}
	public void setEXPCandyCount(int[] Candy) {
		for(int i=0;i<Candy.length;i++) {
			this.Candy[i]= Candy[i];
		}
	}
	public void setEXPCandyUP(int[] Candy) {
		for(int i=0;i<Candy.length;i++) {
			this.Candy[i]+= Candy[i];
		}
	}
	public void setCandyDown(int num) {
		this.Candy[num]-=1;
	}
	public void changeBox(int first, int second) {
		Pokemon p = this.gotPokemon[first-1].getPokemon();
		this.gotPokemon[first-1]= this.gotPokemon[second].getPokemon();
		this.gotPokemon[second]=p.getPokemon();
	}
	
	
	
	public int getBoxCount() {
		return boxCount;
	}
	public void setBoxCount(int boxCount) {
		this.boxCount = boxCount;
	}
	public int getRecordTowerLevel() {
		return recordTowerLevel;
	}
	public void setRecordTowerLevel(int recordTowerLevel) {
		this.recordTowerLevel = recordTowerLevel;
	}
	
	
}
