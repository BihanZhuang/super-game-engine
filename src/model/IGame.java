package model;

import java.io.File;
import java.io.IOException;

import gameobject.ObjectInfo;
import gameobject.Template;
import javafx.scene.input.KeyCode;

public interface IGame extends GameInfo {

    /**
     * Adds a <code>Template</code> of champion to the game and returns the ID
     * of the corresponding user control.
     * 
     * @param template
     * @return
     */
    int connect(Template template);

    /**
     * Returns the <code>ObjectInfo</code> of a champion of <code>id</code>.
     * @param controlID
     * @return
     */
    ObjectInfo getChampion(int controlID);

    void addLevel();
    
    void saveRecord(File file, int controlID);
    
    int loadRecord(File file);
    
    void saveGame();
    
    /**
     * Serializes the game to an XML String.
     * Champions in the game are not preserved.
     * For the use of game editing.
     * @return
     */
    void saveGame(File file);
    
    /**
     * Reconstructs a game from an XML String.
     * Any champion saved in the XML String will not be
     * connected to user control.
     * @param file
     * @throws IOException 
     */
    void loadGame(File file);

    void handleKeyReleased(int controlId, KeyCode code);

    void handleKeyPressed(int controlId, KeyCode code);

}
