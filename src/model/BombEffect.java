package model;

public class BombEffect implements ArmorState {
	ArmorState hasArmor;
	ArmorState noArmor;
	ArmorState armorState;
	private static BombEffect myInstance = new BombEffect();

	public static BombEffect getInstance() {
		return myInstance;
	}

	private BombEffect() {
		hasArmor = new HasArmor();
		noArmor = new NoArmor();
		armorState = noArmor;
	}
	public void setArmedState() {
		armorState=hasArmor;
	}
	public void setNoArmedState() {
		armorState=noArmor;
	}

	@Override
	public void bombExplosion() {
		armorState.bombExplosion();
	}

	@Override
	public void removeArmor() {
		armorState.removeArmor();
	}

}
