package model;

import java.util.HashMap;
import java.util.Map;

import util.Constants;
import util.ObservableBase;
import util.Replicate;
import util.ResourceParser;

public class Environment extends ObservableBase<Environment> implements Replicate<Environment> {

    public static final String CONSTANT_FILE = "constants";

    private Map<Constant, Double> myConstants;
    
    public enum Constant {
        G,
        CHAR_FRICTION_COEFF;
    }

    public Environment() {
        myConstants = initConstants();
    }

    public void setConstant(Constant name, double value) {
        myConstants.put(name, value);
        notifyObservers(this);
    }

    public double getConstant(Constant name) {
        if (myConstants.containsKey(name)) {
            return myConstants.get(name);
        }
        return 0;
    }

    @Override
    public void copyFrom(Environment other) {
        for(Constant c: Constant.values()) {
            myConstants.put(c, other.getConstant(c));
        }
        notifyObservers(this);
    }

    private Map<Constant, Double> initConstants() {
        Map<Constant, Double> myConstants = new HashMap<>();
        ResourceParser parser = new ResourceParser(Constants.RESOURCE_PACKAGE + CONSTANT_FILE);
        parser.parseAll((key, value) -> myConstants.put(Constant.valueOf(key), Double.parseDouble(value)));
        return myConstants;
    }
    
}
