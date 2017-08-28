package gameobject.component;

import java.util.function.BiPredicate;

import gameobject.IGameObject;
import gameobject.Template;
import gameobject.basics.GeneratorStrategy.GeneratorStrategy;
import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;
import util.SerializableBiPredicate;

public class Generator implements Component {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private boolean generate;
    private double generatePosX;
    private double generatePosY;
    private double generateDiffX;
    private double generateDiffY;
    private boolean check;
    private int generateCount;

    private Template generator;
    private double deathTime;
    private double rebornTime;
    private SerializableBiPredicate<IGameObject, Double> rebornCheck;

    public Generator() {
        this((IGameObject) null);
    }

    public Generator(IGameObject obj) {
        this(obj, false);
    }

    public Generator(IGameObject obj, boolean generate) {
//    	System.out.println("used third constructor");
//        setTemplate(obj);
        this.generate = generate;
        this.check = false;
        this.generateCount = 1;
        this.deathTime = Double.MAX_VALUE;
        this.rebornTime = 5;
        this.rebornCheck = (I, time) -> (time - I.getComponent(ComponentTypes.GENERATOR).deathTime) > 100;
    }

    public Generator(Generator g) {
        this.generator = g.generator;
        this.rebornCheck = g.rebornCheck;
        this.deathTime = g.deathTime;
        this.generatePosX = g.generatePosX;
        this.generatePosY = g.generatePosY;
        this.generateDiffX = g.generateDiffX;
        this.generateDiffY = g.generateDiffY;
        this.generateCount = g.generateCount;
        this.rebornTime = g.rebornTime;
        this.check = g.check;
    }

    public boolean check() {
        return check;
    }

    public void setGenerateCheck(boolean value) {
        this.check = value;
    }

    public void setGenerateDiffX(double generateDiffX) {
        this.generateDiffX = generateDiffX;
    }

    public void setGenerateDiffY(double generateDiffY) {
        this.generateDiffY = generateDiffY;
    }

    /**
     * Signifies the need to generate something if the <code>status</code> is
     * set to <code>true</code>.
     * 
     * @param status
     */
    public void setGenerate(boolean status) {
        this.generate = status;
    }

    public boolean getGenerate() {
        return this.generate;
    }

    public void dieAt(double time) {
        this.deathTime = time;
    }

    public double getDeathTime() {
        return this.deathTime;
    }

    public void changeRebornCheck(SerializableBiPredicate<IGameObject, Double> criteria) {
        this.rebornCheck = criteria;
    }
    
    public void changeRebornCheck(GeneratorStrategy gs) {
    	this.rebornCheck=gs.getJudge().get(0);
    }

    public BiPredicate<IGameObject, Double> getRebornCheck() {
        return rebornCheck;
    }

    public Template generate() {
    	//if (this.getGenerateCnt()<0) return;
    	Vector2D pos = generator.getPosition();
        pos.setX(generatePosX + generateDiffX);
        pos.setY(generatePosY + generateDiffY);
        this.setGenerateCnt(this.getGenerateCnt() - 1);
        deathTime = Double.MAX_VALUE;
        if(generator.has(ComponentTypes.AI)) {
        	AI ai = generator.getComponent(ComponentTypes.AI);
        	ai.setInitX(generatePosX + generateDiffX);
        	ai.setInitY(generatePosY + generateDiffY);
        }
        return generator;
    }
    
    public void setTemplate(IGameObject obj) {
        generator = new Template(obj);
        
    }

    @Override
    public ComponentType<? extends Component> getType() {
        return ComponentTypes.GENERATOR;
    }

    @Override
    public Generator copy() {
        return new Generator(this);
    }

    public double getRebornTime() {
        return rebornTime;
    }

    public void setRebornTime(double rebornTime) {
        this.rebornTime = rebornTime;
    }

    public double getGeneratePosX() {
        return generatePosX;
    }

    public void setGeneratePosX(double generatePosX) {
        this.generatePosX = generatePosX;
    }

    public double getGeneratePosY() {
        return generatePosY;
    }

    public void setGeneratePosY(double generatePosY) {
        this.generatePosY = generatePosY;
    }

    public int getGenerateCnt() {
        return generateCount;
    }

    public void setGenerateCnt(int generateCnt) {
        this.generateCount = generateCnt;
    }

}
