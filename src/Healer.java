/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

public class Healer extends Item {

	private int healPoints;
	
	/**
	 * Healer constructor class
	 * 
	 * @param level String of the level of the healer
	 * @param name String of the name of the healer
	 * @param weight int of the healer's weight
	 * @param healPoints int of the healer's healpoints
	 */
	public Healer (String level, String name, int weight, int healPoints) {
		super(name, weight, level);
		setHealPoints(healPoints);
	}
	
	/**
	 * assigns the passed in parameter healpoints to the attribute healpoints
	 * 
	 * @param healPoints the attribute to be set
	 */
	private void setHealPoints (int healPoints) {
		this.healPoints = healPoints;
	}
	
	/**
	 * return the int of the healpoints
	 */
	public int getPoints() {
		return this.healPoints;
	}	
}
