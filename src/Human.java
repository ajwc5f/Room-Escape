/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

public class Human extends GameCreature{
	
	/**
	 * Calls the setters setName using the passed parameter of name, createHealth using the passed parameter of hp, initBag with the passed parameter of emptyBag, and initCommandProcessor().
	 * 
	 * @param name of the human to be set
	 * @param hp of the human to be set
	 * @param emptyBag to be set
	 */
	public Human (String name, int hp, Bag emptyBag) {
		super(name, hp, emptyBag);
	}

	/**
	 * Adds the passed item to the bag using the addItem method from the Bag class.
	 * 
	 * @param item to be picked up
	 * @return the result of addItem.
	 */
	public boolean pickup (Item item) {
		return this.getBag().addItem(item);
	}
	
	/**
	 * Drops the passed item from the bag using the dropItem method from the Bag class
	 * 
	 * @param item to be dropped
	 * @return the result of dropItem
	 */
	public boolean drop (Item item) {
		return super.bag.dropItem(item);
	}
	
	/**
	 * drops all items in one's bag
	 */
	public void dropAll() {
		super.bag.dropItems();
	}
	
	/**
	 * Applies the hit points from the item to the creatures health
	 * 
	 * @param creature the GameCreature to be injured
	 * @param item the Item the creature is being hurt with
	 */
	public boolean attack (GameCreature creature, Item item) {
		if (item == null) {
			return false;
		}
		else {
			creature.injured(item.getPoints() * 2);
			return true;
		}
	}
	
	/**
	 * Applies healpoints to the human health
	 * 
	 * @param item the Item that is being used to heal
	 */
	public boolean heal (Item item) {
		if (item == null) {
			return false;
		}
		else {
			super.health.heal(item.getPoints());
			super.bag.dropItem(item);
			return true;
		}
	}
}
