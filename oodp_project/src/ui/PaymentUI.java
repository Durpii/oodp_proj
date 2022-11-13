package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import cinema.Movie;
import cinema.Ticket;
import controllers.PaymentController;

/**
Payment UI for displaying form for changing prices for movies, and displaying total price for ticket. 
@author Liu Woon Kit
@version 1.0
@since 2022-11-11
*/

public class PaymentUI {
	/**
	 * Initialized Payment Controller
	 */
	PaymentController pc = new PaymentController();
	
	/** 
	* Class constructor.
	*/
	public PaymentUI() {
		
	}
	
	/**
	 * Calculates and displays price and ticket type
	 * @param ticket The ticket to be booked
	 * @return the calculated total price
	 */
	public double displayPaymentForm(Ticket ticket) {
		double price = pc.calculateFee(ticket);
		double total = price/100*107;
		if(ticket.isHoliday(ticket.getDateTime())) {
			System.out.println("Holiday Ticket");
		} else if(ticket.isWeekend(ticket.getDateTime())) {
			System.out.println("Weekend Ticket");
		} else {
			System.out.println("Weekday Ticket");
		}
		System.out.println("\tSUBTOTAL: $" + price);
		System.out.println("\tINCLUSIVE 7% GST: $" + price/100*7);
		System.out.println("Total Price Payable include GST: $" + total);
		System.out.println("Enter credit card number: XXXX-XXXX-XXXX-XXXX");
		System.out.println("Payment successful!");
		return total;
	}
	
	
	/**
	 * Displays form for adjusting the movie's price for weekday, weekend and holiday.
	 * Gives result to PaymentController to manipulate the text file database.
	 * @movieId movieId The movie that will have its price edited.
	 */
	public void displayMoviePriceAdjustmentForm(int movieId) {
		Scanner sc = new Scanner(System.in);
		
		HashMap<String, Double> prices = new HashMap<>();
		prices.put("Weekday", null);
		prices.put("Weekend", null);
		prices.put("Holiday", null);
		for(Entry<String, Double> entry : prices.entrySet()) {
			while(true) {
				System.out.printf("Enter price for %s: ", entry.getKey());
				if(sc.hasNextDouble()) {
					prices.put(entry.getKey(), sc.nextDouble());
					break;
				}
			}
		}
		sc.close();
		PaymentController pc = new PaymentController();
		pc.adjustMoviePrices(movieId, prices.get("Weekday"), prices.get("Weekend"), prices.get("Holiday"));
	}
}