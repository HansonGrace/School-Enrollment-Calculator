package atm;

import java.util.HashMap;
import java.util.Map;

public class AtmImpl implements AtmInterface {

	public static final String RED = "\u001B[31m";
	public static final String RESET = "\u001B[0m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";

	Atm atm = new Atm();
	Map<Double, String> ministmt = new HashMap<>();

	@Override
	public void viewBalance() {
		System.out.println("Available Balance is : " + YELLOW + atm.getBalance() + RESET);
	}

	@Override
	public void withdrawAmount(double withdrawAmount) {
		if (withdrawAmount % 100 == 0) {
			if (withdrawAmount <= atm.getBalance()) {
				ministmt.put(withdrawAmount, YELLOW + " Amount Withdrawn" + RESET);
				System.out.println(YELLOW + "Collect the Cash " + withdrawAmount + RESET);
				atm.setBalance(atm.getBalance() - withdrawAmount);
				viewBalance();
			} else {
				System.out.println(RED + "Insufficient Balance !!" + RESET);
			}
		} else {
			System.out.println(YELLOW + "Please enter the amount in increments of 100" + RESET);
		}
	}

	@Override
	public void depositAmount(double depositAmount) {
		ministmt.put(depositAmount, GREEN + " Amount Deposited" + RESET);
		System.out.println(GREEN + depositAmount + " Deposited $$ Successfully !!" + RESET);
		atm.setBalance(atm.getBalance() + depositAmount);
		viewBalance();
	}

	@Override
	public void viewMiniStatement() {
		for (Map.Entry<Double, String> m : ministmt.entrySet()) {
			System.out.println(m.getKey() + "" + m.getValue());
		}
	}
}