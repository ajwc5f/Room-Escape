/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

public class Weapon extends Item {
	
	private int damagePoints;
	
	/**
	 * Weapon constructor class
	 * 
	 * @param level String of the level of the weapon
	 * @param name String of the name of the weapon
	 * @param weight int of the weapon's weight
	 * @param damagePoints int of the healer's damagepoints
	 */
	public Weapon (String level, String name, int weight, int damagePoints) {
		super(name, weight, level);
		setDamagePoints(damagePoints);
	}
	
	/**
	 * assigns the passed in parameter damagepoints to the attribute damagepoints
	 * 
	 * @param healPoints the attribute to be set
	 */
	private void setDamagePoints (int damagePoints) {
		this.damagePoints = damagePoints;
	}
	
	/**
	 * return the int of the healpoints
	 */
	public int getPoints() {
		return this.damagePoints;
	}

}
