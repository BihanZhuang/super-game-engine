package util;

import java.io.Serializable;
import java.util.PriorityQueue;

/**
 * Provides ID generation and recycle service.
 * Generated ID is the smallest integer available.
 * @author Mike Liu
 *
 */
@SuppressWarnings("serial")
public class IDControl implements Serializable {
    
    private int next;
    private PriorityQueue<Integer> recycled;
    
    public IDControl() {
    	this.recycled = new PriorityQueue<Integer>();
        next = 0;
        recycled = new PriorityQueue<>();
    }
    
    /**
     * Returns the smallest integer available.
     * @return
     */
    public int nextId() {
    	if(recycled.isEmpty()) {
    	    return next++;
    	} else {
    	    return recycled.poll();
    	}
    }
    
    /**
     * Recycles <code>id</code> if it is occupied.
     * @param id
     */
    public void recycle(int id) {
        if(id < next && !recycled.contains(id)) {
            recycled.add(id);
        }
    }
    
}