
public class Pokemon extends UserData {
	protected int index;
	protected String name;
	protected float HP;
	protected float ATK;
	protected float DEF;
	protected float SPD;
	protected String type;
	protected int Lv;
	protected int eveLv;
	protected int[] skillIndexNum = new int[4];
	public int add = 0;
	PokemonDex pdex[] = PokemonDex.getInstance();
	public Pokemon() {
		
	}
	public Pokemon(int dex, int add) {
		this.index=dex;
		this.add=add;
		this.name = pdex[dex].getNameA();
		this.type= pdex[dex].getType();
		this.Lv = pdex[dex].getLv();
		this.eveLv = pdex[dex].getEveLv();
		this.skillIndexNum=pdex[dex].getSkillIndexNum();
		HPCalculrator(pdex[dex].getBaseStatus(0),add);
		ATKDEFSPDCalculrator(pdex[dex].getBaseStatus(1),pdex[dex].getBaseStatus(2),pdex[dex].getBaseStatus(3),add);
	}
	
	public void HPCalculrator(float hp,int add) {
		this.HP=((2*hp+add+100)*this.Lv/100)+10;
	}
	public void ATKDEFSPDCalculrator(float atk,float def,float spd,float add) {
		this.ATK=((2*atk)+add)*this.Lv/100;
		this.DEF=((2*def)+add)*this.Lv/100;
		this.SPD=((2*spd)+add)*this.Lv/100;
		
	}
}
