/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

public class Beast extends GameCreature {
	
	private String type;
	
	/**
	 * Beast constructor calls the super setter, and a setter
	 * 
	 * @param name String of the name of the beast
	 * @param hp int of the health points the beast has
	 * @param bag Bag containing the beasts' weapon
	 * @param type String of the type of the beast
	 */
	public Beast (String name, int hp, Bag bag, String type) {
		super(name, hp, bag);
		setType(type);
	}
	
	/**
	 * Assigns the passed in parameter type to the attribute type
	 * 
	 * @param type to be set
	 */
	private void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the attribute type
	 */
	public String getType() {
		return this.type;
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
			creature.injured(item.getPoints());
			return true;
		}
	}
	
	/**
	 * Applies healpoints to the beast health
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
	
	/**
	 * adds the item to their bag
	 * 
	 *@param item the Item to be picked up
	 */
	public boolean pickup (Item item) {	
		return this.getBag().addItem(item);
	}
	
	/**
	 * drops the item from their bag
	 * 
	 *@param item the Item to be dropped
	 */
	public boolean drop (Item item) {
		return this.getBag().dropItem(item);
	}
	
	/**
	 * drops all items in one's bag
	 */
	public void dropAll() {
		this.getBag().dropItems();
	}
	
	
	
	


}
