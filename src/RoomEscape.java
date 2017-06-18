/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RoomEscape {
	
	private static Scanner userInput;
	private static ArrayList<Item> gameItems;
	private static ArrayList<Beast> gameBeasts;
	private static ArrayList<Beast> currentRoomBeasts;
	private static Human currentPlayer;
	private static Random randomGenerator;
	public static boolean runawaySignal = false;//signals the use of runaway
	private static int runawayCount = 0;//counts # of times runaway has been used
	
	/**
     * The user enters name and number of levels to play. This instantiates a new human as the player.
     * The user will then enter the first room and begin. The user then randomly encounters items and
     * beasts inside that room until they have defeated two beasts, and then will move onto the next
     * room or level. If a user encounters an item they can either pick it up or continue. If the item is a weapon
     * they can attack with it in battle. If it is a healer, they can use it to heal their health. However, the
     * user can only hold a total weight of 20 in their bag. If the user encounters a beast, they will enter battle. In battle, the user has two options: to attack
     * with a weapon in their bag, or runaway. Running away will cause the user to drop all their items,
     * and it can only be used five times. If the user attacks the beast, the damage done will be shown,
     * and then the beasts' attack damage on the user will be displayed. This will continue until either
     * a) the user has defeated the beast, b) the beast kills the user, or c) the user runs away. The user
     * continues to encounter random items and beasts in each room until they have reached the number of
     * rooms(levels) they entered.  In this case, the user has defeated Room Escape.
     */
    public static void main(String[] args) {
            
            initGameData();
            System.out.print("Enter Character Name: ");
            String name = userInput.nextLine();
            
            System.out.print("Enter number of levels: ");
            int maxRooms = Integer.parseInt(userInput.nextLine());
            
            currentPlayer = new Human(name,100, new Bag());
            int roomsBeat = 0;
            
            while (roomsBeat < maxRooms) {
                    /*
                     * Create room bad guys and items
                     */
                    System.out.println("Entering Room " + (roomsBeat + 1));
                    System.out.println(" ");
                    initCurrentRoomBeasts();
                    int creaturesDestroyed = 0;
                    
                    while (currentPlayer.isLiving() && creaturesDestroyed < 2) {
                            int encounterProb = randomGenerator.nextInt(100);
                            Item foundItem = null;
                            Beast foundEnemy = null;
                            /*
                             * 60 percent chance of being a Beast.
                             */
                            if (encounterProb <= 40) {
                                    foundItem = findItem();
                                    System.out.println("You have discovered the item "  + foundItem.getLevel() + " " + foundItem.getName());
                                    System.out.println();
                                    while (noBattle(foundItem)); 
                                    
                            }
                            else {
                                    foundEnemy = findEnemy();
                                    System.out.println("You have encountered a(n) " + foundEnemy.getType() + " " + foundEnemy.getName());
                                    System.out.println();
                                    if (battle (foundEnemy)) {
                                            creaturesDestroyed++;
                                    }
                                    else if (runawaySignal == true) {
                                    	runawaySignal = false;//reset the signal to false
                                    	runawayCount++;//bump usage count
                                    	if (runawayCount > 5) {//if usage > 5, user dies
                                    		System.out.println("You are dead bro!");
                                    		return;
                                    	}
                                    }
                                    else {
                                            System.out.println("You are dead bro!");
                                            return;
                                    }
                            }
                    }
                    roomsBeat++;
            }
            System.out.println("You have beat RoomEscape bro! Congratulations " + name +"!");
    }
    
    /**
     * Allows the current player to engage in combat with the incoming beast. The player uses 
     * items from his bag to kill the enemy. The player also recieves damage done by attacks from
     * the beast.
     * 
     * @param battlingBeast The found beast the player has encountered
     * @return Whether the currentPlayer is dead being false or alive being true.
     */
    private static boolean battle (Beast battlingBeast) {
            
            while (currentPlayer.isLiving()) {
                    displayBagContents();
                   System.out.print("Command: ");
                    String input = userInput.nextLine();
                    System.out.println(" ");
                    
                    CreatureResponse response = currentPlayer.processCommand(input, battlingBeast,null);
                    
                    if (runawaySignal == true) {//if the user runs away, print response and return
                    	System.out.println(response.getResponse());
                    	System.out.println();
                    	System.out.println();
                    	return false;
                    }
                    
                    while (!response.getValidAction()) {
                    	System.out.print(response.getResponse() + ", Try again: ");
                        input = userInput.nextLine();
                        response = currentPlayer.processCommand(input, battlingBeast, null);
                    }
                    
                    if (currentPlayer.isLiving() && !battlingBeast.isLiving()) {
                    	System.out.println(response.getResponse());
                    	System.out.println();
                    	System.out.println(battlingBeast.getName() + " is killed");
                        System.out.println(" ");
                        System.out.println();
                        return true;
                    }
                    else {
                    	System.out.println(response.getResponse());
                    	System.out.println();
                    	//display the beasts' health points
                        System.out.println(battlingBeast.getName() + " HP: " + battlingBeast.currentHealthPoints());
                        //display the beasts' attack on the user
                        System.out.println(battlingBeast.getType() + " " + battlingBeast.getName() + " attacked you with " + battlingBeast.getBag().getItem(0).getLevel() + " " + battlingBeast.getBag().getItem(0).getName() + " bro.");
                        System.out.println();
                        battlingBeast.attack(currentPlayer, battlingBeast.getBag().getItem(0));
                    }
            }
            return false;
    }


    /**
     * Displays the contents of the bag and health to player
     *
     */
    private static void displayBagContents () {
    		System.out.println("Player Health: " + currentPlayer.currentHealthPoints());
    		System.out.println("Contents of Bag:");
            System.out.println("# Type    Name             Points Weight");
            Bag currentPlayerBag = currentPlayer.getBag();
            
            for(int i = 0; i < currentPlayerBag.getSize(); i++){//loops through all items in bag
            	Item currentItem = currentPlayerBag.getItems().get(i);
            	String currentName = currentItem.getLevel()+ " " + currentItem.getName();//item name
            	
            	if(currentItem instanceof Weapon){//if the item is a weapon, display weapon
            		System.out.println(String.format("%d WEAPON %-16s  -%-5d %d", (i+1), currentName, currentItem.getPoints(), currentItem.getWeight()) );
            	}
            	else if(currentItem instanceof Healer){//if the item is a healer, display healer
            		System.out.println(String.format("%d HEALER %-16s   %-6d%d", (i+1), currentName, currentItem.getPoints(), currentItem.getWeight()) );
            	}
            }
            System.out.println(" ");
    }
    
    /**
     * Allows the player to run the following game commands "pickup" on the found item, “view” the player bag contents,
     * "drop" an item from their bag, show the game commands using "help", current item, "runaway" from a battle, "heal"
     * with a healer item, and the enter key allow you to skip past an item (the string is empty).
     *  
     * 
     * @param foundItem An instantiated Item that the player has found
     */
    
    private static boolean noBattle (Item foundItem) {
            displayBagContents();
            System.out.print("Command: ");
            String input = userInput.nextLine();
            if (input.isEmpty()) {
                    return false;
            }
            CreatureResponse response = currentPlayer.processCommand(input, null,foundItem);
            while (!response.getValidAction()) {
                    System.out.print(response.getResponse() + ", Try Again: ");
                    input = userInput.nextLine();
                    response = currentPlayer.processCommand(input, null,foundItem);                
                    if (input.isEmpty()) {
                            return false;
                    }
            }        
            System.out.println(response.getResponse());
            return true;
    
    }
    
    /**
     * Simulates the player is actually searching the array list for an item and grabs the item
     * 
     * @return a randomly picked Item from gameItems
     */
    private static Item findItem() {
    	int index = randomGenerator.nextInt(gameItems.size());
    	int rand_loop= randomGenerator.nextInt(3)+1;
    	
    	System.out.println("Searching area...");
    	
    	for (int i = 0; i < rand_loop; i++) {
    		System.out.println("Searching area...");
    	}
    	return gameItems.get(index);
    }
    
    /**
     * Simulates the player is actually searching the array list for an beast and grabs the enemy
     * 
     * @return a randomly picked Beast from gameBeasts
     */
    private static Beast findEnemy() {
    	int index = randomGenerator.nextInt(currentRoomBeasts.size());
    	int rand_loop= randomGenerator.nextInt(3)+1;
    	
    	System.out.println("Searching area...");
    	
    	for (int i = 0; i < rand_loop; i++) {
    		System.out.println("Searching area...");
    	}
    	return currentRoomBeasts.get(index);
    }
    
    /**
     * Instantiates a new ArrayList of type Beast, assigning that to the currentRoomBeasts attribute and copies all the Beasts stored in the ArrayList gameBeasts attribute into the currentRoomBeasts ArrayList attribute.
     */
    private static void initCurrentRoomBeasts() {
    	ArrayList<Beast> roomBeasts = new ArrayList<Beast>();
    	currentRoomBeasts = roomBeasts;
    	
    	for (Beast i : gameBeasts) {
    		currentRoomBeasts.add(i);
    	}
    	
    }
    
    /**
     * Instantiates all gameData for Room Escape
     */
    private static void initGameData() {
    	userInput = new Scanner(System.in);
    	randomGenerator = new Random(1337);
    	GameDataReader gameDataReader = new GameDataReader();
    	gameItems = gameDataReader.getGameItems("GameData/GameItems.csv");
    	gameBeasts = gameDataReader.getGameBeasts("GameData/GameCreatures.csv");
    }

}
