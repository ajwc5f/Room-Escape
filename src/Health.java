/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

public class Health {

	private int healthPoints;
	private boolean alive;
	
	/**
	 * Health constructor with no parameter
	 */
	public Health() {
		setHealth(100);
		setAlive(true);
	}
	
	/**
	 * Calls the setters setHealth using the passed parameter hp and setAlive using true.
	 */
	public Health (int hp) {
		setHealth(hp);
		setAlive(true);
	}
	
	/**
	 * Checks if the passed parameter hp is greater than or equal to 1 if so, assigns the attribute healthPoints to the passed parameter hp, else call setAlive using false.
	 * 
	 * @param healthPoints to be set
	 */
	private void setHealth (int hp) {
		if (hp >= 1) {
			this.healthPoints = hp;
		}
		else {
			setAlive(false);
		}
	}
	
	/**
	 * Assigns the attribute alive to the passed parameter alive
	 * 
	 * @param alive attribute to be set
	 */
	private void setAlive (boolean alive) {
		this.alive = alive;
	}
	
	/**
	 * @return the attribute healthPoints.
	 */
	public int getHealthPoints() {
		return this.healthPoints;
	}
	
	/**
	 * @return the attribute alive
	 */
	public boolean getAlive() {
		return this.alive;
	}
	
	/**
	 * Calls the setHealth method using the current healthPoints attribute minus the passed parameter hitPoints
	 * 
	 * @param hitsPoints number of hit points
	 */
	public void hit (int hitPoints) {
		setHealth(this.healthPoints - hitPoints);
	}
	
	/**
	 * Calls the setHealth method using the current healthPoints attribute plus the passed parameter healPoints
	 * 
	 * @param healPoints number of heal points
	 */
	public void heal (int healPoints) {
		setHealth(this.healthPoints + healPoints);
	}
	
}
