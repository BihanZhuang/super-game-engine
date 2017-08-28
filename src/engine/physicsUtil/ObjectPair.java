package engine.physicsUtil;

import gameobject.IGameObject;
/**
 * For checking if collision handling for a particular object pair has been done
 *
 */
public class ObjectPair {
	

	private IGameObject g1, g2;
	public ObjectPair(IGameObject g1, IGameObject g2) {
		this.g1=g1;
		this.g2=g2;
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof ObjectPair){
			return (this.g1.getID()==((ObjectPair)other).g1.getID() && this.g2.getID()==((ObjectPair)other).g2.getID())
					||
					(this.g1.getID()==((ObjectPair)other).g2.getID() && this.g2.getID()==((ObjectPair)other).g1.getID());
		} else {
			return false;
		}
		
	}

}
