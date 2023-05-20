
public class Pokemon extends UserData {
	protected String name;
	protected float HP;
	protected int ATK;
	protected int DEF;
	protected int SPD;
	protected String type;
	protected int Lv;
	protected String skillname;
	public int add = 0;
	 
	public Pokemon() {
		
	}
	public Pokemon(int add) {
		this.add=add;
		
	}
	
	public void HPCalculrator(float hp,int add) {
		this.HP=((2*hp+add+100)*this.Lv/100)+10;
	}
	public void ATKDEFSPDCalculrator(int atk,int def,int spd,int add) {
		this.ATK=((2*atk)+add)*this.Lv/100;
		this.DEF=((2*def)+add)*this.Lv/100;
		this.SPD=((2*spd)+add)*this.Lv/100;
		
	}
}
