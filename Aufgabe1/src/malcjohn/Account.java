package malcjohn;

public class Account extends Exception {
	public static int accountNumber;
	public static double overdraft;
	public static double bankDeposit;
	public static int pin;

	// Constructor for this class
	public Account() {

	}

	// Methods for AccountNumber, BankDeposit, Overdraft and
	// Pin Always both, set and get methods.

	/**
	 * Speicherung der Kontonummer
	 * 
	 * @param value
	 *            accountNumber
	 */
	public void setAccountNumber(int value) {
		Account.accountNumber = value;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Dispokredit des Kontos
	 * 
	 * @param value2
	 *            overdraft
	 */
	public void setOverdraft(double value2) {
		Account.overdraft = value2;
	}

	public double getOverdraft() {
		return overdraft;
	}

	/**
	 * die Hohe des Kontoguthabens
	 * 
	 * @param value3
	 *            bankDeposit
	 */
	public void setBankDeposit(double value3) {
		Account.bankDeposit = value3;
	}

	public double getBankDeposit() {
		return bankDeposit;
	}

	/**
	 * Set pin for Account class
	 * 
	 * @param value4
	 *            PIN
	 */
	public void setPin(int value4) {
		int length = String.valueOf(value4).length();
		if (length == 4) {
			Account.pin = value4;
		} else {
			try {
				System.out.println("Sie mussen nur 4 stellige nummer eingeben");
			} finally {
				System.exit(0);
			}
		}
	}

	public int getPin() {
		return pin;
	}
}
