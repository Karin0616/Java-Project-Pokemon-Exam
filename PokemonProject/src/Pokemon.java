
public class Pokemon extends UserData {
	protected int index=0;
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
	public int exp =0;
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
	public Pokemon(int dex, int add,int Lv) {
		this.index=dex;
		this.add=add;
		this.name = pdex[dex].getNameA();
		this.type= pdex[dex].getType();
		this.Lv = pdex[dex].getLv()+Lv;
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
	public void lvCalculrator(int[] Candy) {
		for(int i=0;i<5;i++) {
			if(Candy[i]==0) {
				continue;
			}else {
				switch(i) {
				case 0:
					this.exp+=100;
				case 1:
					this.exp+=200;
				case 2:
					this.exp+=300;
				case 3:
					this.exp+=400;
				case 4:
					this.exp+=500;
				}
				
			}
		}
		if(exp>=100&&this.Lv<10) {
			this.Lv+=exp/100;
		}else if (exp>=250&&this.Lv<20) {
			this.Lv+=exp/250;
		}else if (exp>=350&&this.Lv<30) {
			this.Lv+=exp/350;
		}else if (exp>=450&&this.Lv<40) {
			this.Lv+=exp/450;
		}else if (exp>=600&&this.Lv<50) {
			this.Lv+=exp/600;
		}else if (exp>=800&&this.Lv<60) {
			this.Lv+=exp/800;
		}else if (exp>=1000&&this.Lv<70) {
			this.Lv+=exp/1000;
		}else if (exp>=1200&&this.Lv<80) {
			this.Lv+=exp/1200;
		}else if (exp>=1500&&this.Lv<100) {
			if(this.Lv+exp/1500>100) {
				
			}else {
				this.Lv+=exp/1500;
			}
		}
	}
}
