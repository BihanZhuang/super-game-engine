package model;

import java.io.File;

import gameobject.ObjectInfo;
import gameobject.Template;
import javafx.scene.input.KeyCode;
import system.ControlBus;
import util.Observable;
import util.ObservableBase;
import util.Observer;
import util.Serializer;
import util.TextIO;
import util.VoogaException;

public class Game implements IGame {
    
    private static final String LEVEL_FILE = "level%d.xml";
    private static final String CHAMPION_FILE = "champions.xml";
    private static final String METADATA_FILE = "metadata.xml";

    private ChampionPool myPool;
    private IWorld myWorld;
    private GameStats myStats;
    private ControlBus controlBus;
    private Serializer mySerializer;
    private File myFolder;
    private TextIO myIO;
    private int currentLevel, maxLevel;
    private ILevel myLevel;
    private HUDConfiguration myHUDConfig;
    private Observable<GameInfo> myInfoObservable;

    public Game(IWorld world) {
        myPool = new ChampionPool();
        myWorld = world;
        controlBus = new ControlBus();
        myStats = new GameStats();
        mySerializer = new Serializer();
        myFolder = new File(System.getProperty("user.home"));
        myIO = new TextIO();
        currentLevel = maxLevel = 1;
        myLevel = new Level(myWorld);
        myHUDConfig = new HUDConfiguration();
        myInfoObservable = new ObservableBase<>();
    }
    
    @Override
    public HUDConfiguration getHUDConfig(){
    	return myHUDConfig;
    }
    
    @Override
    public WorldInfo getWorld() {
        return myWorld;
    } 
    
    @Override
    public LevelInfo getLevelInfo(){
    	return myLevel;
    }

    @Override
    public GameStats getStats() {
        return myStats;
    }
    
    @Override
    public int numLevels() {
        return maxLevel;
    }

    @Override
    public int currentLevel() {
        return currentLevel;
    }

    @Override
    public void toLevel(int level) {
        if(level > maxLevel) {
            throw new VoogaException("Level %d does not exist", level);
        } else {
            myLevel.copyFrom(loadObject(String.format(LEVEL_FILE, level), Level.class));
            refreshControl();
            currentLevel = level;
        }
        myInfoObservable.notifyObservers(this);
    }

    @Override
    public int connect(Template template) {
        return controlBus.addControl(myWorld.addChampion(template));
    }

    @Override
    public ObjectInfo getChampion(int id) {
        return controlBus.getChampion(id);
    }

    @Override
    public ChampionPool getChampionPool() {
        return myPool;
    }

    @Override
    public void addLevel() {
        saveLevel();
        myLevel.clear();
        maxLevel++;
        currentLevel = maxLevel;
        myInfoObservable.notifyObservers(this);
    }

    @Override
    public void saveRecord(File file, int controlID) {
        VoogaException.safeIO(() -> myIO.save(file, mySerializer.toXML(new GameRecord(myLevel, myStats, controlBus, currentLevel, controlID))));
    }

    @Override
    public int loadRecord(File file) {
        GameRecord record = VoogaException.safeIO(() -> mySerializer.fromXML(myIO.load(file), GameRecord.class));
        myLevel.copyFrom(record.getLevel());
        myStats.copyFrom(record.getStats());
        controlBus = record.getControlBus();
        refreshControl();
        currentLevel = record.currentLevel();
        myInfoObservable.notifyObservers(this);
        return record.getControlID();
    }

    @Override
    public void saveGame() {
        saveObject(myPool, CHAMPION_FILE);
        saveLevel();
        saveObject(new GameMeta(maxLevel), METADATA_FILE);
    }

    @Override
    public void saveGame(File file) {
        if(file.isDirectory()) {
//            for(int i = 1; i <= maxLevel; i++) {
//                String filename = String.format(LEVEL_FILE, i);
//                VoogaException.safeIO(() -> Files.copy(new File(myFolder, filename).toPath(), new File(file, filename).toPath(), StandardCopyOption.REPLACE_EXISTING));
//            }
            myFolder = file;
            saveGame();
        } else {
            throw new VoogaException("%s is not a folder", file.getAbsolutePath());
        }
    }

    @Override
    public void loadGame(File file) {
        if(file.isDirectory()) {
            myFolder = file;
            controlBus = new ControlBus();
            myPool = loadObject(CHAMPION_FILE, ChampionPool.class);
            GameMeta meta = loadObject(METADATA_FILE, GameMeta.class);
            maxLevel = meta.getMaxLevel();
            toLevel(1);
            myInfoObservable.notifyObservers(this);
        } else {
            throw new VoogaException("%s is not a folder", file.getAbsolutePath());
        }
    }

    @Override
    public void handleKeyPressed(int controlId, KeyCode code) {
        controlBus.handleKeyPressed(controlId, code);
    }

    @Override
    public void handleKeyReleased(int controlId, KeyCode code) {
        controlBus.handleKeyReleased(controlId, code);
    }

    @Override
    public void addChangeObserver(Observer<GameInfo> observer) {
        myInfoObservable.addObserver(observer);
    }

    @Override
    public void removeChangeObserver(Observer<GameInfo> observer) {
        myInfoObservable.removeObserver(observer);
    }
    
    private void saveLevel() {
        ILevel level = new Level(new World());
        level.copyFrom(myLevel);
        saveObject(level, String.format(LEVEL_FILE, currentLevel));
    }
    
    private void saveObject(Object toSave, String fileName) {
        File file = new File(myFolder, fileName);
        VoogaException.safeIO(() -> {
            file.createNewFile();
            myIO.save(file, mySerializer.toXML(toSave));
        });
    }
    
    private <T> T loadObject(String fileName, Class<T> clazz) {
        File file = new File(myFolder, fileName);
        return VoogaException.safeIO(() -> mySerializer.fromXML(myIO.load(file), clazz));
    }
    
    private void refreshControl() {
        for(Integer id: controlBus.getIDs()) {
            controlBus.changeControl(id, myWorld.addChampion(new Template(controlBus.getChampion(id))));
        }
    }

}
