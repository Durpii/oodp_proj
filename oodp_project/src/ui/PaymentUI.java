package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import cinema.Movie;
import cinema.Ticket;
import controllers.PaymentController;

public class PaymentUI {
	PaymentController pc = new PaymentController();
	
	public PaymentUI() {
		
	}
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
	
	public void displayMoviePriceAdjustmentForm(Movie movie) {
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
		pc.adjustMoviePrices(movie.getId(), prices.get("Weekday"), prices.get("Weekend"), prices.get("Holiday"));
	}
}