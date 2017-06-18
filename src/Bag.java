/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

import java.util.ArrayList;

public class Bag {
	
	private ArrayList<Item> items;
	private int weight;
	
	/**
	 * Calls the initBag method.
	 */
	public Bag() {
		initBag();
	}
	
	/**
	 * Assigns the items attribute to a new ArrayList of type Item and assigns the weight attribute to zero
	 */
	private void initBag() {
		this.items = new ArrayList<Item>();
		this.weight = 0;
	}
	
	/**
	 * If current weight is less than 20, add the passed item, and add the item's weight.
	 * 
	 * @param item to be added
	 * @return true if item is added, else false
	 */
	public boolean addItem (Item item) {
		
		if (this.weight + item.getWeight() <= 20 ){
			this.items.add(item);
			this.weight += item.getWeight();
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Attempts to remove a the passed­in item from the items ArrayList, and decrease weight.
	 * 
	 * @param item to be dropped
	 * @return true if item id dropped, else false
	 */
	public boolean dropItem (Item item) {
		if (items.remove(item)) {
			this.weight -= item.getWeight();
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @return  the items ArrayList.
	 */
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	/**
	 * @return the current size of the items ArrayList.
	 */
	public int getSize() {
		return this.items.size();
	}
	
	/**
	 *Checks if the an item in the items ArrayList has the same name as the passed parameter name
	 * 
	 * @param name of the Item.
	 * @return if names are same, return true, else null 
	 */
	public Item getItem (String name) {
		
		for (Item i : items) {
			if (i.getName().equalsIgnoreCase(name)) {//if the item is in the items arraylist
				return i;
			}
		}
		return null;
	}
	
	/**
	 * drop however many items from the bag
	 */
	public void dropItems() {
		while(items.size() >= 1){//loops through while items still has items in it
			int temp = getItem(0).getWeight();
			if(items.remove(getItem(0))){//remove the item and decrease bag weight
				this.weight -= temp;
				
			}
		}
	}
	
	/**
	 * gets an item at the given index
	 * 
	 * @param itemIdx int of the index the item is at
	 * @return the item at given index
	 */
	public Item getItem (int itemIdx) {
		if (itemIdx < items.size()) {//if the index is in the arraylist
			return items.get(itemIdx);
		}
		else {
			return null;
		}
	}
}
