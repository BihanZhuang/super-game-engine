package gameobject.component;

import gameobject.component.type.ComponentType;
import gameobject.component.type.ComponentTypes;

public class Team implements Component {
    private static final long serialVersionUID = 1L;
    
    private int teamnumber =1;
    public Team() {
    	this.teamnumber=0;
    }
    public Team(int num) {
    	this.teamnumber=num;
    }
	public Team(Team team) {
		// TODO Auto-generated constructor stub
		this.teamnumber=team.teamnumber;
	}

	@Override
	public Component copy() {
		// TODO Auto-generated method stub
		return new Team(this);
	}

	@Override
	public ComponentType<? extends Component> getType() {
		// TODO Auto-generated method stub
		return ComponentTypes.TEAM;
	}

	public int getTeamnumber() {
		return teamnumber;
	}

	public void setTeamnumber(int teamnumber) {
		this.teamnumber = teamnumber;
	}
    
}
