package de.university.neueVersion.exceptions;

import de.university.neueVersion.exceptions.MeineException;

/** 
 * @author Anastasia Baron
 * @author Dmitry Petrov
 */

public class NotEnoughMoneyException extends MeineException {

	public NotEnoughMoneyException() {
		System.out.println("");
		System.out.println("Sie haben nicht genug Geld zum abheben");

	}

}
