package view;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Sound {
	
	private Media mySound;
	private MediaPlayer myPlayer;
	
	public Sound(String musicfile){
		setMusic(musicfile);
	}
	
	public void play(){	
		myPlayer.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         myPlayer.seek(Duration.ZERO);
		       }
		   });
		myPlayer.play();	
	}
	
	public void pause(){
		myPlayer.pause();
	}
	
	public void setMusic(String musicfile){
		mySound = new Media(new File(musicfile).toURI().toString());
		myPlayer = new MediaPlayer(mySound);
	}
}
