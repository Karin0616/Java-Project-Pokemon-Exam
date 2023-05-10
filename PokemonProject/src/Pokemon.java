
public class Pokemon extends UserData {
	protected Pokemon_Name name;
	protected float HP;
	protected int ATK;
	protected int DEF;
	protected int SPD;
	protected Type type;
	protected int Lv;
	protected Skill_Name skillname;
	public int add = 0;
	 
	public void Pikachu(int add) {
		this.name = Pokemon_Name.피카츄;
		HPCalculrator(35,add);
		ATKDEFSPDCalculrator(55, 40, 90, add);
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
