package malcjohn;

public class CashMachine extends Account {
	Account account;
	Karte status;
	int accountZumUberprufen;
	int r = 0;

	public CashMachine(Account accountX) {
		/**
		 * hier wenn man auf Card_inserted macht, dann keine Informationen uber
		 * Account ?tested? Ja, Nur im Moment Karte.READY; card inse nicht
		 * tested
		 */
		status = Karte.READY;
		// status = Karte.CARD_INSERTED;
		this.account = accountX;
	}

	public CashMachine() {

	}

	CashCard cashCard;
	Account[] accounts = new Account[5];

	/**
	 * Enumeration
	 * http://javarevisited.blogspot.cz/2011/08/enum-in-java-example-
	 * tutorial.html
	 */
	public enum Karte {
		READY, CARD_INSERTED, PIN_CORRECT, PIN_WRONG;
	}

	public Karte getStatus() {
		return status;
	}

	/**
	 * <h2>Setzt den Status des Emums</h2 Kopiert aus dem
	 * http://www.java-forum.org/java-basics-anfaenger-themen/87727-enum-dann-
	 * setter -setzen.html
	 */
	public void setStatus(Karte status) {
		if (status != null) {
			this.status = status;
		} else {
			throw new NullPointerException("status darf nicht null sein");
		}
	}

	@SuppressWarnings("unused")
	private Karte statusReady = Karte.READY;
	private Karte statusCardInserted = Karte.CARD_INSERTED;

	private Karte statusPinCorrect = Karte.PIN_CORRECT;
	private Karte statusPinWrong = Karte.PIN_WRONG;

	/**
	 * <h2>Karteneingabe</h2> Nur im Zustand READY konnen die Informationen der
	 * eingegebenen Cashcard im Attribute cashCard abgespeichert werden. Bei
	 * erfolgreicher Eingabe wechselt der Zustand von READY auf CARD_INSERTED.
	 * Der Status des Automaten soll auf der Konsole protokolliert werden.
	 */
	public void insertCashCard(CashCard cashCard) {
		if (cashCard != null) {
			if (status.equals(Karte.READY)) {
				setStatus(Karte.CARD_INSERTED);
				System.out
						.print("Sie haben jetzt ihre Karte im Automat. Status ist auf ");
				System.out.print(getStatus());
				System.out.println(" gesetzt");
				int accountZumUberprufen = cashCard.getAccountNumber();

				int pinZumUberprufen = cashCard.getPin();

				// fur testing purposes
				System.out.println("Ihren Pin von Karte ist "
						+ pinZumUberprufen);
				System.out.println("Ihren Account von Karte ist "
						+ accountZumUberprufen);

				/**
				 * Hier geht es falsch muss bearbeitet werden.
				 */
				if (accountZumUberprufen != cashCard.getAccountNumber()) {
					System.out
							.println("Ihre account Nummer mit dem Karte nummer stimmt nicht uberein");
					System.out
							.println("Keine weitere methoden ausfuhrbar > Karte wird ausgeworfen");
					ejectCashCard();
				} else {
					System.out
							.println("Ihre account Nummer mit dem Karte nummer STIMMT uberein");
					if (pinZumUberprufen != cashCard.getPin()) {
						setStatus(statusPinWrong);
						System.out
								.println("Ihre Pin Nummer mit dem Pin der Karte stimmt nicht uberein");
						System.out
								.println("Keine weitere methoden ausfuhrbar (wegen"
										+ statusPinWrong
										+ "> Karte wird ausgeworfen");
						ejectCashCard();
					}
				}

				// int zea = accounts[0].getAccountNumber();
				// oder accounts.lenght
				// for (int b = 0; b < accounts.length; b++) {
				// if (accountZumUberprufen == account.getAccountNumber()
				// && account.getPin() == pinZumUberprufen) {
				// // Zum uberprufung welches nummer man zeigt
				// // System.out.println(accounts[0].getAccountNumber());
				// System.out.println("Account nummer und Pin SIND gleich ");
				// } else {
				// System.out.println("Account nummer sind und pin NICHT gleich ");
				// }
				// }

			} else {
				setStatus(statusCardInserted);
				System.out
						.print("Ihre karte ist schon im Automat. Status ist auf ");
				System.out.print(getStatus());
				System.out.print(" gesetzt");
				System.out.println("");
			}
		} else {
			System.out.println("Sie haben ihre karte ausgemacht");
		}
	}

	/**
	 * <h2>Abheben</h2> Das Abheben kann nur erfolgen wenn der Geldautomat im
	 * Zustand CARD_INSERTED ist. Der angegebene Betrag kann vom Konto abgehoben
	 * werden, solange es den Dispokredit nicht überschreitet.Ausserdem muss das
	 * Kontoguthaben neu berechnet und auf der Konsole ausgegeben werden.
	 * 
	 * Beachten Sie, dass Geld nur noch abgehoben werden kann, wenn der Pin
	 * korrekt ist.
	 * 
	 */
	public void withdraw(double amount) {
		if (status.equals(statusPinCorrect)) {
			setStatus(statusCardInserted);
		}
		if (status.equals(statusCardInserted)) {
			if (amount > overdraft) {
				System.err.println("Sie konnen das nicht machen");
			} else {
				double neues = bankDeposit - amount;
				System.out.println("Ihres neuen zustand ist " + neues);
			}
		} else {
			System.out.println("Sie konnen abheben nur im Zustand "
					+ statusCardInserted);
		}
	}

	/**
	 * <h2>Kontoinformationen</h2> Ausgabe der aktuellen Kontoinformationen auf
	 * der Konsole, nur moglich im Zustand CARD_INSERTED.
	 * 
	 * @param FINISHED
	 */

	public void accountStatement() {
		if (getStatus() != statusPinWrong) {

			if (status == getStatus()) {
				System.out.println("Your account number is " + accountNumber
						+ ".");
				System.out.println("Your pin is " + pin + ".");
				System.out.println("Your bank deposit is " + bankDeposit + ".");
				System.out.println("Your credit is " + overdraft + ".");
			} else {
				try {
					System.out.println("");
				} finally { // finally = always
					System.out
							.println("Sie konnen KEINE Informationnen kriegen, weil sie keine Karte im Automat haben");
				}
			}
		} else {
			System.out
					.println("Sie konnen die Informationen nicht kriegen, weil der Pin falsch ist");
		}
	}

	/**
	 * <h2>Kartenauswurf</h2> Der Geldautomat wird auf den Status READY gesetzt,
	 * das Attribute cashCard wird zur Nullreferenz. Das ist nur moglich im
	 * Zustand CARD_INSERTED. Der Status des Automaten soll auf der Konsole
	 * protokolliert werden.
	 */
	public void ejectCashCard() {

		System.out.println(getStatus()); // pin wrong

		if (getStatus() != statusCardInserted) {
			// nur statuse wurde jetzt geandert
			// setStatus(statusReady);
			// System.out.print("Sie haben den status auf");
			// System.out.print(getStatus());
			// System.out.println(" gesetzt");
			// insertCashCard(null);
			// System.out.println("Sie haben ihre karte ausgemacht!");
			// System.out.println("Ihre karte ist auf anderen Status gesetzt");

		} else {
			// nur statuse wurde jetzt geandert
			setStatus(statusReady);
			System.out.print("Sie haben d status auf ");
			System.out.print(getStatus());
			System.out.println(" gesetzt");
			System.out.println("Sie haben ihre karte ausgemacht!");

			insertCashCard(null);

		}

	}

	/**
	 * <h2>Setzung des Pins (Abgleichung + status anderung)</h2> Die Methode
	 * gleicht die Eingabe des Pins mit dem Pin des gerade verwendeten Accounts
	 * ab. Bei korrekter Eingabe wechselt der Zustand des Geldautomaten in
	 * PIN_CORRECT. Bei Falscheingabe in PIN_WRONG. Der Status des Automaten
	 * soll auf der Konsole protokolliert werden.
	 * 
	 * @param pin
	 * @param FINISHED
	 */
	public void enterPin(int pin) {
		if (account.getPin() == pin) {
			setStatus(statusPinCorrect);
			System.out
					.println("Sie haben folgenden Pin fur ihre karte eingegeben:"
							+ account.getPin());
			System.out
					.print("Sie haben RICHTIGEN pin eingegen. Status wird auf ");
			System.out.print(getStatus());
			System.out.println(" gesetzt");
		} else {
			setStatus(statusPinWrong);
			System.out.println("Der Richtige pin lautet:" + account.getPin());
			System.out
					.print("Sie haben Falschen pin eingegen. Status wird auf ");
			System.out.print(getStatus());
			System.out.println(" gesetzt");
			if (getStatus() == statusPinWrong) {

				System.out
						.println("sie konnen keine weitere methodone ausfuhren");
				System.out.println("Ihre karte word ausgeworfen");
				ejectCashCard();

			}

		}
	}
}
