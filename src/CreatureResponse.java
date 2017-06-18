/*
 *Name: Aren Wells
 *HW #: 3
 *Date: 11/9/14 
 */

package ajwc5f.cs3330.hw3;

public class CreatureResponse {
	
	private String response;
	private boolean validAction;
	
	/**
	 * Calls the setters setResponse using the passed response parameter and setValidAction using the passed validAction parameter.
	 * 
	 * @param response from the Creautre
	 * @param validAction an action that is valid
	 */
	public CreatureResponse (String response, boolean validAction) {
		setResponse(response);
		setValidAction(validAction);
	}
	
	/**
	 * Assigns validAction to the passed validAction parameter.
	 * 
	 * @param validAction a valid action to be set
	 */
	private void setValidAction (boolean validAction) {
		this.validAction = validAction;
	}
	
	/**
	 * Assigns response to the passed response parameter.
	 * 
	 * @param response given by creature
	 */
	private void setResponse (String response) {
		this.response = response;
	}
	
	/**
	 * @return the validAction attribute
	 */
	public boolean getValidAction() {
		return this.validAction;
	}
	
	/**
	 * @return the response attribute.
	 */
	public String getResponse() {
		return this.response;
	}

}
