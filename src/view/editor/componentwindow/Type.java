package view.editor.componentwindow;

public enum Type {
    HEALTH("Health"),
    ATTACK("Attack"),
    BONUSSCORE("BonusScore"),
    DIMENSION("Dimension"),
    MASS("Mass"),
    VELOCITY("Velocity"),
    COLLIDER("Collider"),
    GENERATOR("Generator"),
    ANIMATION("Animation"),
    AI("AI"),
    CHAMPION("Champion"),
    PHYSICSBEHAVIOR("Physics Behavior"),
    TEAM("Team"),
	ITEMSTRATEGY("Item Strategy"),
	ENEMYSTRATEGY("Enemy Strategy"),
	INVULNERABLESTRATEGY("Invulnerable Strategy"),
	TOUCHEXPIRESTRATEGY("Touch Expire Strategy"),
	TOUCHGENERATORSTRATEGY("Touch Generator Strategy"),
	MARIOSTRATEGY("Mario Strategy"),
	ONETIMESTRATEGY("Onetime Strategy"),
	CHARACTERSTRATEGY("Character Strategy"),
	NULLVELOCITYSTRATEGY("NullVelocityStrategy"),
	BRICKSTRATEGY("Brick Strategy")
    ;

    private final String text;

    /**
     * @param text
     */
    private Type(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
