package gameobject.component.type;

import gameobject.component.AI;
import gameobject.component.Actor;
import gameobject.component.Animation;
import gameobject.component.Attack;
import gameobject.component.Belongings;
import gameobject.component.BonusScore;
import gameobject.component.Boss;
import gameobject.component.Collider;
import gameobject.component.Destination;
import gameobject.component.Generator;
import gameobject.component.Health;
import gameobject.component.PhysicsBehavior;
import gameobject.component.Scalar;
import gameobject.component.Team;
import gameobject.component.Vector2D;

/**
 * A library of all the <code>ComponentTypes</code>.
 *
 */
@SuppressWarnings("serial")
public class ComponentTypes {
    
	/*
	 * Physics related
	 */
	public static final ComponentType<PhysicsBehavior> PHYSICS_BEHAVIOR = new ComponentType<PhysicsBehavior>() {};
    public static final CommonType<Vector2D> VELOCITY = new CommonType<Vector2D>("Velocity") {};
    public static final CommonType<Vector2D> ACCELERATION = new CommonType<Vector2D>("Acceleration") {};
    public static final CommonType<Scalar> MASS = new CommonType<Scalar>("Mass") {};
    
    
    /*
     * Gameplay related
     */
    public static final ComponentType<Health> HEALTH = new ComponentType<Health>() {};
    public static final ComponentType<Collider> COLLIDE = new ComponentType<Collider>() {};
    public static final ComponentType<Attack> ATTACK = new ComponentType<Attack>() {};
    public static final ComponentType<Animation> ANIMATION = new ComponentType<Animation>() {};
    public static final ComponentType<Generator> GENERATOR = new ComponentType<Generator>() {};
    public static final ComponentType<Belongings> BELONGINGS = new ComponentType<Belongings>() {};
	public static final CommonType<AI> AI = new CommonType<AI>("AI") {};
    public static final CommonType<Actor> CHAMPION = new CommonType<Actor>("Champion") {};
    public static final ComponentType<BonusScore> BONUSSCORE = new ComponentType<BonusScore>() {};
    public static final ComponentType<Boss> BOSS = new ComponentType<Boss>() {};
    public static final ComponentType<Destination> DESTINATION = new ComponentType<Destination>() {};
    public static final ComponentType<Team> TEAM = new ComponentType<Team>() {};
    
}
