package ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import cinema.Movie;
import controllers.PaymentController;

public class PaymentUI {
	public PaymentUI() {
		
	}
	public void displayPaymentForm() {
		
	}
	
	public void displayMoviePriceAdjustmentForm(Movie movie) {
		Scanner sc = new Scanner(System.in);
		
		HashMap<String, Double> prices = new HashMap<>();
		prices.put("weekday", null);
		prices.put("weekend", null);
		prices.put("holiday", null);
		
		while(true) {
			
		}
		
		for(Entry<String, Double> entry : prices.entrySet()) {
			while(true) {
				System.out.printf("Enter price for %s: ", entry.getKey());
				if(sc.hasNextDouble()) {
					prices.put(entry.getKey(), sc.nextDouble());
					break;
				}
			}
		}
		System.out.println()
		sc.close();
		PaymentController pc = new PaymentController();
		pc.adjustMoviePrices(movie.getId(), prices.get("weekday"), prices.get("weekend"), prices.get("holiday"));
	}
}