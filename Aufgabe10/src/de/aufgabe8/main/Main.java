package de.aufgabe8.main;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author Anastasia Baron
 * @author Dmitry Petrov
 */

public class Main {
	public static void main(String[] args) {

		HashMap<Integer, Account> ac = new HashMap<Integer, Account>();
		/*
		 * Prufen Sie vor und nach dem Hinzufugen der Elemente den Ruckgabewert
		 * der Methode isEmpty(). Gibt true wenn leer - false wenn besetzt
		 */
		System.out.println("Ist die Set leer : " + ac.isEmpty());

		// erstellung account objekten, die danach in map kommen
		Account das = new Account(23456789, 465, 06541, 5465);
		Account das2 = new Account(32846519, 4965, 8576, 3122);
		Account das3 = new Account(123456, 465, 06541, 2135);

		// Element der Klasse Account in die Map einfuegen
		ac.put(das.getAccountNumber(), das);
		ac.put(das2.getAccountNumber(), das2);
		ac.put(das3.getAccountNumber(), das3);

		System.out.println("\n" + "----------- Ausgabe der Map: -----------");
		System.out.println(ac.toString());

		// Gibt false zurueck, wenn die Map nicht leer ist
		System.out.println("Ist die Map leer : " + ac.isEmpty());

		// Gibt die Anzahl der Elementen in die Map zurueck
		System.out.println("Grosse der Maps : " + ac.size());

		/*
		 * Ausgabe mit der Hilfe des Interfaces Entry
		 */
		System.out.println("\n"
				+ "-----Ausgabe der Schlussel-Wert-Paar (Iterator): ----");
		for (Entry<Integer, Account> entry : ac.entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}
}
