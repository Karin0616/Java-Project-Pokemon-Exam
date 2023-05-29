import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SkillDex {
    private static SkillDex[] SDex = new SkillDex[217];//스킬 총 개수
    private String name;
    private float power;
    private float ATKnurf;
    private float DEFnurf;
    private String type;
    private int indexNum;

    private SkillDex() {
    }

    // Singleton pattern to ensure only one instance of SkillDex is created


    private SkillDex(int indexNum,String name, float power, float ATKnurf, float DEFnurf, String type) {
		// TODO Auto-generated constructor stub
    	this.indexNum=indexNum;
    	this.name=name;
    	this.power=power;
    	this.ATKnurf=ATKnurf;
    	this.DEFnurf=DEFnurf;
    	this.type=type;
    	
	}

	public static SkillDex[] getInstance() {
    	try {
            BufferedReader reader = new BufferedReader(new FileReader("skillInfo.csv"));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < 217) {//스킬총수
                String[] values = line.split(",");
                int indexNum = Integer.parseInt(values[0]);
                String name= values[1];
                float power = Float.parseFloat(values[2]);
                float ATKnurf = Float.parseFloat(values[3]);
                float DEFnurf = Float.parseFloat(values[4]);
                String type = values[5];
                
                SDex[i] = new SkillDex(indexNum,name,power,ATKnurf,DEFnurf,type );
                i++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
		
		return SDex;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public float getATKnurf() {
        return ATKnurf;
    }

    public void setATKnurf(float ATKnurf) {
        this.ATKnurf = ATKnurf;
    }

    public float getDEFnurf() {
        return DEFnurf;
    }

    public void setDEFnurf(float DEFnurf) {
        this.DEFnurf = DEFnurf;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(int indexNum) {
        this.indexNum = indexNum;
    }

   
}