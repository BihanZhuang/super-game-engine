package view.gameplay;

import javafx.scene.Parent;

public abstract class AbstractView<T extends Parent>{
	private T mRoot;

	public AbstractView(T root){
		mRoot = root;
	}
	
	public T getRoot(){
		return mRoot;
	}

}
