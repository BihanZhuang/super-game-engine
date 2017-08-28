package gameobject.component;

@SuppressWarnings("serial")
public abstract class MaxAndCurrComponent implements Component {
	public abstract double getMaxValue();
	public abstract double getCurrValue();
}
