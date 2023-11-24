package db_model;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		DataService dataService = new DataService();
		
		dataService.cancellaTuttiIndirizzi();
		System.out.println("cancello tutto");
		stampaLista(dataService.getIndirizziRecord());
		
		dataService.inserisciIndirizzo("Pino Pasticcino", "0123456789");
		System.out.println("inserisco indirizzo");
		
		dataService.inserisciIndirizzo("Pinco Pallino", "0123456789");
		System.out.println("inserisco indirizzo");
		
		stampaLista(dataService.getIndirizziRecord());
		
		dataService.cancellaIndirizzo("Pino Pasticcino");
		System.out.println("cancello indirizzo");
		
		stampaLista(dataService.getIndirizziRecord());
	}
	
	public static <T> void stampaLista(List<T> lista) {
		for (T o : lista) {
		    System.out.println(o);
		}
	}
}