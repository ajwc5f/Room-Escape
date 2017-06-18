/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

import java.util.ArrayList;

public class CommandProcessor {
	
protected ArrayList<String> commands;
	
	/**
	 * Calls the setter setGameCommands
	 */
	public CommandProcessor() {
		setGameCommands();
	}
	
	/**
	 * Instantiates a new GameDataReader object and calls the getGameCommands method on the newly instantiated GameDataReader object using the following String “GameData/Commands.txt”, and assigns the returning ArrayList of type String to the attribute gameCommands.
	 */
	private void setGameCommands() {
		GameDataReader gameDataReader = new GameDataReader();
		this.commands = gameDataReader.getGameCommands("GameData/Commands.txt");
	}
	
	/**
	 * Checks if the passed­in command is in gameCommands
	 * 
	 * @param command to be checked
	 * @return true if command is found, else false
	 */
	public boolean validateUserCommand (String command) {
		for (String i : commands) {//loop through game commands, and see if the entered command is valid
			if (i.equals(command)) {
				return true;
			}
		}
		return false;
	} 
	
	/**
	 * @return the entire gameCommands ArrayList.
	 */
	public ArrayList<String> getCommands() {
		return this.commands;
	}
}
