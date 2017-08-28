package view.editor.componentwindow;

public enum Field {
	MAXHEALTH("MaxHealth"),
	CURHEALTH("CurHealth"),
	ATTACK("Attack"),
	RANGE("Range"),
	BONUSSCORE("Bonus Score"),
	WIDTH("Width"),
	HEIGHT("Height"),
	MASS("Mass"),
	VELOCITYX("VelocityX"),
	VELOCITYY("VelocityY"),
	COLLISIONSTRATEGY("Collision Strategy"),
	GENERATORSTRATEGY("Generator Strategy"),
	LEFTTERRITORY("Left Territory"),
	RIGHTTERRITORY("Right Territory"),
	HORIZONTALSPEED("Horizontal Speed"),
	VERTICALSPEED("Vertical Speed"),
	CHAMPIONHORIZONTALSPEED("Champion Horizontal Speed"),
	CHAMPIONVERTICALSPEED("Champion Vertical Speed"),
	TEAMNUMBER("Team Number"),
	ITEMIMAGENAME("Item Image Name"),
	ITEMHEALTHINCREASE("Item Health Increase"),
	POWERUPBIRCKIMAGENAME("Powerup Brick Image Name"),
	GENERATEDIFFX("Generate Difference X"),
	GENERATEDIFFY("Generate Difference Y"),
	GENERATECNT("Total Times of Generating"),
	GENERATEREBORNTIME("The time before it reborns"),
	GRAVITYON("Gravity On"),
	COLLISIONON("Collision On"),
	PHYSICSBEHAVIOR("Physics Behavior")
	;
    private final String text;

    /**
     * @param text
     */
    private Field(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
