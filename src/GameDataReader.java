/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameDataReader {
	
	/**
	 * Empty constructor class
	 */
	public GameDataReader() {
		
	}
	
	/**
	 * reads game commands from file and stores them in game command array
	 * 
	 * @param filePath the path of the file were the commands are
	 * @return an ArrayList containing the game's commands
	 */
	public ArrayList<String> getGameCommands (String filePath) {
		
		File file = new File(filePath);
		
		ArrayList<String> commandArray = new ArrayList<String>();
		
		try {
			
			Scanner sc = new Scanner(file);//creates new scanner to scan in the file
			
			while (sc.hasNextLine()) {
					String line = sc.nextLine();//set the line read in from the file to the String "line"
					commandArray.add(line);//creates new with the given values read in from the file
			}
			sc.close();
			return commandArray;
		}
		
		catch (FileNotFoundException e) {//if file is not found
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * reads game item from file and stores them in game item array
	 * 
	 * @param filePath the path of the file were the items are
	 * @return an ArrayList containing the game's items
	 */
	public ArrayList<Item> getGameItems (String filePath) {
		
		File file = new File(filePath);
		
		ArrayList<Item> itemArray = new ArrayList<Item>();
		
		try {
			
			Scanner sc = new Scanner(file);//creates new scanner to scan in the file
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();//set the line read in from the file to the String "line"
				String[] strArray = line.split(",");//splits the string stored in "line" and stores the split parts in a String Array
					if (strArray[0].equalsIgnoreCase("Weapon")) {//if it's a weapon, add a new weapon item
						String namlevArray[] = strArray[1].split(" ");//splits name and level
						itemArray.add(new Weapon(namlevArray[0], namlevArray[1], Integer.parseInt(strArray[2]), Integer.parseInt(strArray[3])));//creates new with the given values read in from the file
					}
					else if (strArray[0].equalsIgnoreCase("Healer")) {//if its a healer, add a new healer item
						String namlevArray[] = strArray[1].split(" ");//splits name and level
						itemArray.add(new Healer(namlevArray[0], namlevArray[1], Integer.parseInt(strArray[2]), Integer.parseInt(strArray[3])));//creates new with the given values read in from the file
					}
			}		
			sc.close();
			return itemArray;
		}
		
		catch (FileNotFoundException e) {//if file is not found
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * reads game beasts from file and stores them in game beasts array
	 * 
	 * @param filePath the path of the file were the beasts are
	 * @return an ArrayList containing the game's beasts
	 */
	public ArrayList<Beast> getGameBeasts (String filePath) {
		
		File file = new File(filePath);
		
		ArrayList<Beast> beastArray = new ArrayList<Beast>();
		
		try {
			
			Scanner sc = new Scanner(file);//creates new scanner to scan in the file
			
			while (sc.hasNextLine()) {
				String line = sc.nextLine();//set the line read in from the file to the String "line"
				String[] strArray = line.split(",");//splits the string stored in "line" and stores the split parts in a String Array
				String[] typeNameArray = strArray[0].split(" ");//splits the type and name of the beast
				String[] levelNameArray = strArray[2].split(" ");//splits the level and name of the weapon
				Item weapon = new Weapon(levelNameArray[0], levelNameArray[1], Integer.parseInt(strArray[3]), Integer.parseInt(strArray[4]));//new item of the item the creature has
				Bag beastBag = new Bag();//new bag to store the item
				beastBag.addItem(weapon);
				beastArray.add(new Beast(typeNameArray[1], Integer.parseInt(strArray[1]), beastBag, typeNameArray[0]));//creates new with the given values read in from the file
			}
			sc.close();
			return beastArray;
		}
		
		catch (FileNotFoundException e) {//if file is not found
			e.printStackTrace();
			return null;
		}
	}

}
