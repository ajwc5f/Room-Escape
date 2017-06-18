/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

public interface Creature {
	
	/**
	 * adds the item to their bag
	 * 
	 *@param item the Item to be picked up
	 */
	public boolean pickup (Item item);
	
	/**
	 * drops the item from their bag
	 * 
	 *@param item the Item to be dropped
	 */
	public boolean drop (Item item);
	
	/**
	 * drops all items in one's bag
	 */
	public void dropAll();
	
	/**
	 * Processes the command entered by the user
	 * 
	 * @param string a list of all commands
	 * @param creature the creature to be used
	 * @param item the item to be used
	 * @return the response given by the particular command
	 */
	public CreatureResponse processCommand(String string, GameCreature creature, Item item);
	
	/**
	 * causes healthpoints to decrease
	 * 
	 * @param index amount to decrease the healthpoints by
	 */
	public void injured(int index);

	/**
	 * Applies the hit points from the item to the creatures health
	 * 
	 * @param creature the GameCreature to be injured
	 * @param item the Item the creature is being hurt with
	 */
	public boolean attack(GameCreature creature, Item item);
	
	/**
	 * Applies healpoints to the creature health
	 * 
	 * @param item the Item that is being used to heal
	 */
	public boolean heal (Item item);
}
