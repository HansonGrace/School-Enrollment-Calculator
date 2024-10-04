package atm;

import java.util.Scanner;

public class AtmDisplay {

	//color strings
	public static final String Magenta = "\u001B[35m";
	public static final String Cyan = "\u001B[36m";
	public static final String RED = "\u001B[31m";
	public static final String RESET = "\u001B[0m";
	public static final String YELLOW = "\u001B[33m";
	
	//main method
	public static void main(String[] args) {
		AtmInterface ai = new AtmImpl();
		int cardno = 12345;
		int pinno = 123;
		Scanner in = new Scanner(System.in);
		//console interface messages
		System.out.println(Cyan + " *** Welcome to the ATM Machine ***" + RESET);
		System.out.print("Enter Your Card Number : ");
		int atmNumber = in.nextInt();
		System.out.print("Enter Your PersonalPin: ");
		int pin = in.nextInt();

		//if entered card % pin number matches what is in the system, proceed
		if ((cardno == atmNumber) && (pinno == pin)) {
			while (true) {
				//interface
				System.out.println(Cyan + "1.View Available Balance");
				System.out.println("2.Deposit Amount");
				System.out.println("3.Withdraw Amount");
				System.out.println("4.View Ministatement");
				System.out.println("5.Exit");

				System.out.println("Enter Choice : " + RESET);
				int ch = in.nextInt();
				if (ch == 1) {
					ai.viewBalance();

				} else if (ch == 2) {
					System.out.println("Enter Amount to Deposit :");
					double depositAmount = in.nextDouble();
					ai.depositAmount(depositAmount);

				} else if (ch == 3) {
					System.out.println("Enter Amount to Deposit :");
					double withdrawAmount = in.nextDouble();
					ai.withdrawAmount(withdrawAmount);

				} else if (ch == 4) {
					ai.viewMiniStatement();

				} else if (ch == 5) {
					System.out.println(Magenta + "Collect your ATM Card\n Thank you for using ATM Machine !!!" + RESET);
					System.exit(0);
				} else {
					System.out.println(YELLOW + "Please enter valid choice !" + RESET);
				}
			}
		} else //entered numbers do not match
			System.out.println(RED + "Incorrect Atm Number or Pin !!!" + RESET);
			System.exit(0);
		}
	}
