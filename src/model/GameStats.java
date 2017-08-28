package model;

import util.QuickNotifyObservable;
import util.Replicate;

public class GameStats extends QuickNotifyObservable<GameStats> implements Replicate<GameStats> {

    private int myScore;
    
    public GameStats() {
        myScore = 0;
    }
    
    public int getScore() {
        return myScore;
    }
    
    public void addScore(int score) {
        myScore += score;
		notifyObservers(this);
    }

    @Override
    public void copyFrom(GameStats other) {
        myScore = other.getScore();
    }

	@Override
	protected GameStats notification() {
		return this;
	}
    
}
