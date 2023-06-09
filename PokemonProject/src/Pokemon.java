import java.lang.reflect.Array;
import java.util.Arrays;

public class Pokemon implements Cloneable {
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
		//System.out.println(this.index+"인덱스");
		this.add=add;
		//System.out.println(this.add+"add");
		this.name = pdex[dex-1].getNameA();
		//System.out.println(this.name);
		this.type= pdex[dex-1].getType();
		//System.out.println(this.type);
		this.Lv = pdex[dex-1].getLv();
		//System.out.println(this.Lv+"레벨");
		this.eveLv = pdex[dex-1].getEveLv();
		//System.out.println(this.eveLv+"진화레벨");
		this.skillIndexNum=pdex[dex-1].getSkillIndexNum();
		//System.out.println(this.skillIndexNum[0]);
		HPCalculrator(pdex[dex-1].getBaseStatus(0),add);
		ATKDEFSPDCalculrator(pdex[dex-1].getBaseStatus(1),pdex[dex-1].getBaseStatus(2),pdex[dex-1].getBaseStatus(3),add);
	}
	public Pokemon(int dex, int add,int Lv) {
		this.index=dex;
		this.add=add;
		this.name = pdex[dex-1].getNameA();
		this.type= pdex[dex-1].getType();
		this.Lv = Lv;
		this.eveLv = pdex[dex-1].getEveLv();
		this.skillIndexNum=pdex[dex-1].getSkillIndexNum();
		HPCalculrator(pdex[dex-1].getBaseStatus(0),add);
		ATKDEFSPDCalculrator(pdex[dex-1].getBaseStatus(1),pdex[dex-1].getBaseStatus(2),pdex[dex-1].getBaseStatus(3),add);
	}
	
	public void HPCalculrator(float hp,int add) {
		this.HP=((2*hp+add+100)*this.Lv/100)+10;
	}
	public float HPCalReturner(float hp,int add) {
		float HP=((2*hp+add+100)*this.Lv/100)+10;
		return HP;
	}
	public void ATKDEFSPDCalculrator(float atk,float def,float spd,float add) {
		this.ATK=((2*atk)+add)*this.Lv/100;
		this.DEF=((2*def)+add)*this.Lv/100;
		this.SPD=((2*spd)+add)*this.Lv/100;
		
	}
	public void lvCalculrator(int num) {
			switch(num) {
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
		//진화 관련 트리거
		if(this.Lv>=this.eveLv&&this.pdex[this.index].getLv()==this.eveLv) {
			evolve();
		}
		HPCalculrator(pdex[this.index-1].getBaseStatus(0),add);
		ATKDEFSPDCalculrator(pdex[this.index-1].getBaseStatus(1),pdex[this.index-1].getBaseStatus(2),pdex[this.index-1].getBaseStatus(3),add);
	
		
	}
	public void evolve() {
	//진화메소드
		//String temp = this.name;

		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
		System.out.println("> …… 오잉!?");
		System.out.println("> "+this.name+"의 상태가……!");

		this.name = pdex[this.index].getNameA();
		this.type= pdex[this.index].getType();
		this.eveLv = pdex[this.index].getEveLv();
		this.skillIndexNum=pdex[this.index].getSkillIndexNum();
		HPCalculrator(pdex[this.index].getBaseStatus(0),add);
		ATKDEFSPDCalculrator(pdex[this.index].getBaseStatus(1),pdex[this.index].getBaseStatus(2),pdex[this.index].getBaseStatus(3),add);
		this.index+=1;
		System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

		System.out.println("> 축하합니다! "+PokemonDex.getName(this.index - 1)+"은(는) "+this.name+"으로 진화했습니다!");
	}
	
	protected Object clone() throws CloneNotSupportedException{//deep copy
		Pokemon cloned = (Pokemon)super.clone();
		cloned.skillIndexNum = Arrays.copyOf(this.skillIndexNum, this.skillIndexNum.length);
		
		return cloned;
	}
	public Pokemon getPokemon() {//deep copy
		Pokemon cloned = null;
		try {
			cloned = (Pokemon)clone();
		} catch (CloneNotSupportedException e) {
			// TODO: handle exception
		}
		return cloned;
	}
	
	
}
