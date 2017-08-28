package goalstrategies;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import gameobject.ObjectInfo;
import gameobject.component.type.ComponentTypes;

/**
 * Destination Strategy
 * @author Yanbo Fang
 *
 */
public class DestinationStrategy extends AbstractGoalStrategy {

	private boolean destinationReached;
	
	public DestinationStrategy() {
		super(DEFAULT_WEIGHT);
	}
	
	public DestinationStrategy(double weight) {
		super(weight);
	}
	
	@Override
	protected void initializeFunction() {
		this.setFunction((info, engine, currentWeight) -> {

			ObjectInfo champion = StreamSupport
					.stream(Spliterators.spliteratorUnknownSize(info.getWorld().iterator(), Spliterator.ORDERED), false)
					.filter(obj -> obj.has(ComponentTypes.CHAMPION)).findFirst().get();
			
			ObjectInfo destination = StreamSupport
					.stream(Spliterators.spliteratorUnknownSize(info.getWorld().iterator(), Spliterator.ORDERED), false)
					.filter(obj -> obj.has(ComponentTypes.DESTINATION)).findFirst().get();

			if (engine.hasCollision(champion, destination)) {
				destinationReached = true;
				currentWeight += this.getWeight();
				this.setFinished();
			}
			
			this.notifyObservers(this);

			return currentWeight;
		});
	}

	@Override
	public String getStatus() {
		String status = "Destination Goal: ";
		status += (destinationReached) ? "Reached" : "Not Reached";
		return status;
	}

}
