package controller;

import javafx.scene.input.KeyCode;
import model.Model;

public class SimpleHeroControl implements HeroControl {
    
    private Model myModel;
    private int myID;
    
    public SimpleHeroControl(Model model, int id) {
        myModel = model;
        myID = id;
    }
    
    @Override
    public void onKeyPressed(KeyCode code) {
        myModel.getGame().handleKeyPressed(myID, code);
    }
    
    @Override
    public void onKeyReleased(KeyCode code) {
        myModel.getGame().handleKeyReleased(myID, code);
    }
    
    public void setID(int id) {
        myID = id;
    }

}
