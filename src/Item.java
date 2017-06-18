/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

public abstract class Item {

	private String name;
	private String level;
	private int weight;

	/**
	 *Calls the mutators methods to assign the attributes.
	 *
	 * @param name String of the item
	 * @param value int of the item's value
	 * @param weight int of item's weight
	 */
	public Item(String Name, int weight, String level) {
		setName(Name);
		setWeight(weight);
		setLevel(level);
	}
	
	/**
	 * @return the healthpoints or hit points
	 */
	public abstract int getPoints();
	
	/**
	 * Assigns the passed in parameter Name to the attribute Name
	 * 
	 * @param Name string of thier name
	 */
	private void setName (String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return  the attribute Name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Assigns the passed in parameter value to the attribute value
	 * 
	 * @param value int of items value
	 */
	private void setLevel(String level) {
		this.level = level;
	}
	
	/**
	 * 
	 * @return  the attribute value
	 */
	public String getLevel() {
		return this.level;
		}
	
	/**
	 * Assigns the passed in parameter weight to the attribute weight
	 * 
	 * @param weight int of items weight
	 */
	private void setWeight (int weight) {
		this.weight = weight;
	}
	
	/**
	 * 
	 * @return  the attribute weight
	 */
	public int getWeight() {
		return this.weight;
	}
	
	
}
