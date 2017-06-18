/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

import java.util.ArrayList;

public abstract class GameCreature implements Creature {
	
	private String name;
	protected Bag bag;
	protected Health health;
	private CommandProcessor commandProcessor;
	
	/**
	 * Constructor for a gameCreature
	 * 
	 * @param name String of creature name to be set
	 * @param hp int of Healthpoints to be set
	 * @param bag Contents of creature bag to be set
	 */
	public GameCreature (String name, int hp, Bag bag) {
		setName(name);
		createHealth(hp);
		initBag(bag);
		initCommandProcessor();
	}
	
	/**
	 * initiates the new command processor
	 */
	private void initCommandProcessor() {
		CommandProcessor new_processor = new CommandProcessor();
		this.commandProcessor = new_processor;
	}
	
	/**
	 * assigns passed paramter hp to the attribute health
	 * 
	 * @param hp int of the creature's healthpoints
	 */
	private void createHealth(int hp) {
		Health health = new Health(hp);
		this.health = health;
	}
	
	/**
	 * Assigns the passed in parameter name to the attribute name
	 * 
	 * @param name to be set
	 */
	private void setName (String name) {
		this.name = name;
	}
	
	/**
	 * @return the attribute name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * initializes the contents of the creatures bag
	 * 
	 * @param bag the Bag of the creature's to be initialized
	 */
	private void initBag (Bag bag) {
		this.bag = bag;
		if (this.bag.getSize() == 0) {//if size is still 0, creature is a human, so they get a crowbar
			this.bag.addItem(new Weapon("Standard","Crowbar",5,30));
		}
	}
	
	/**
	 * Processes the command entered by the user
	 * 
	 * @param string a list of all commands
	 * @param creature the creature to be used
	 * @param item the item to be used
	 * @return the response given by the particular command
	 */
	public CreatureResponse processCommand (String commands, GameCreature creature, Item item) {
		String response = "";
		boolean validAction = true;
		
		String[] splitCommands = commands.split(" ");//splits the string stored in "Commands" and stores the split parts in a String Array
		
		if(commandProcessor.validateUserCommand(splitCommands[0])) {
			
			if (splitCommands[0].equalsIgnoreCase("attack") && splitCommands.length >= 3 && splitCommands[1].equalsIgnoreCase("with")) {
				if (creature.isLiving()) {
					if(bag.getItem(splitCommands[2]) instanceof Weapon) {//if the item is a weapon
						if(!attack(creature, bag.getItem(splitCommands[2]))) {//if attack on beast with the item was unsuccessful
							response = "Not a valid weapon bro";
							validAction = false;
						}
						else {
							response = "Attack Successful bro.";
						}
					}
					else {
						response = "Item not used successfully for attacking bro";
						validAction = false;
					}
				}
			}
			
			else if (splitCommands[0].equalsIgnoreCase("pickup")) {
				if(pickup(item)) {
					response = "Item added successfully bro.";
				}
				else {
					response = "Item not added successfully bro";
					validAction = false;
				}
				
			}
			
			else if (splitCommands[0].equalsIgnoreCase("drop") && splitCommands.length >= 2) {
				if(splitCommands.length == 3){//if commans is length 3, drop the item at the index
					if(drop(bag.getItem(Integer.parseInt(splitCommands[2])-1))){
						response = "Item successfully dropped bro.";
					}	
					else{
						response = "Item not successfully dropped bro";
						validAction = false;
					}
				}
				else {
					if(drop(bag.getItem(splitCommands[1]))){//otherwise drop item by name
						response = "Item successfully dropped bro.";
					}	
					else{
						response = "Item not successfully dropped bro";
						validAction = false;
					}
				}
			}
			
			else if (splitCommands[0].equalsIgnoreCase("help")) {
				ArrayList<String> helpArray = new ArrayList<String>();
				
				helpArray = commandProcessor.getCommands();
				response = "\r";
				
				for (String i : helpArray) {//loop through help array to show the commands
					response +="\n"+ i;
				}
			}
			
			else if (splitCommands[0].equalsIgnoreCase("runaway")) {
				RoomEscape.runawaySignal = true;//set signal to true
				dropAll();
				response = "Fled and dropped all items bro.";
			}
				
			else if (splitCommands[0].equalsIgnoreCase("heal") && splitCommands.length >=3  && splitCommands[1].equalsIgnoreCase("with")) {
					if(bag.getItem(splitCommands[2]) instanceof Healer) {//if the item is a healer item
						if(!heal(bag.getItem(splitCommands[2]))){
							response = "Item not successfully used for healing bro";
							validAction = false;
						}
						else{
							response = "Item successfully used for healing bro.";
						}
					}
					else {
						response = "Item not used successfully for healing bro";
						validAction = false;
					}
			}
		}
		
		else {
			response = "Invalid game command bro";
			validAction = false;
		}
		
		return new CreatureResponse(response,validAction);
	}
	
	/**
	 * @return the attribute bag
	 */
	public Bag getBag() {
		return this.bag;
	}
	
	/**
	 * causes healthpoints to decrease
	 * 
	 * @param index amount to decrease the healthpoints by
	 */
	public void injured (int hp) {
			this.health.hit(hp);
	}
	
	/**
	 * @return the current creature's health points
	 */
	public int currentHealthPoints () {
		return this.health.getHealthPoints();
	}
	
	/**
	 * @return true if creaure is alive, false otherwise
	 */
	public boolean isLiving() {
		return this.health.getAlive();
	}
	
	/*public abstract boolean pickup(Item item);
	public abstract boolean drop (Item item);
	public abstract void dropAll();
	public abstract boolean attack(GameCreature creature, Item item);
	public abstract boolean heal (Item item);
	*/
}
