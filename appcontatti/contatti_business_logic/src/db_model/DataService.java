package db_model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import contatti.db_model.generated.tables.Indirizzi;
import contatti.db_model.generated.tables.records.IndirizziRecord;
import contatti.db_sqlite.CreateDB;

/**
 * 
 * Questa classe fornisce dei servizi interfacciarsi con il db_sqlite usando il linguaggio Java.
 * Per usare questa classe bisogna creare un oggetto con new DataService() e poi chiamare i metodi di interesse.
 * 
 * @author Wasim Essbai
 * @version 1.0
 * 
 */

public class DataService {
	
	private Connection conn;
	
	private DSLContext create;
	
	
	public DataService() {
		try {
			conn = DriverManager.getConnection(CreateDB.DB_URL);
			create = DSL.using(conn, SQLDialect.SQLITE);
		}
		catch (SQLException e) {
		   System.out.println("ERROR: failed to connect!");
		   System.out.println("ERROR: " + e.getErrorCode());
		   e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Questo metodo interroga il db ed estrae tutti gli indirizzi
	 * 
	 * @return lista degli indirizzi se tutto va bene; altrimenti null.
	 */
	public List<IndirizziRecord> getIndirizziRecord() {
		return create.selectFrom(Indirizzi.INDIRIZZI).fetchInto(IndirizziRecord.class);

	}
	
	/**
	 * 
	 * Questo metodo interroga il db ed estrae l'indirizzo con nome uguale
	 * a quello passato come parametro
	 * 
	 * @param nome il nome dell'indirizzo che vogliamo richiedere
	 * 
	 * @return lista degli indirizzi se tutto va bene; altrimenti null.
	 */
	public IndirizziRecord getIndirizzoPerNome(String nome) {
		return create.selectFrom(Indirizzi.INDIRIZZI)
				.where(Indirizzi.INDIRIZZI.NOME.eq(nome))
				.fetchSingle();
	}
	
	/**
	 * 
	 * Questo metodo inserisce nel db un nuovo indirizzo con i dati passati
	 * 
	 * @param nome il nome dell'indirizzo che vogliamo inserire
	 * @param numTelefono il numero di telefono dell'indirizzo che vogliamo inserire
	 * 
	 */
	public void inserisciIndirizzo(String nome, String numTelefono){
		IndirizziRecord giovanni = new IndirizziRecord(nome, numTelefono);
		int result = create.insertInto(Indirizzi.INDIRIZZI).set(giovanni).execute();
		System.out.println(result); // stampa 1 se tutto andato bene
	}
	
	/**
	 * 
	 * Questo metodo cancella dal db l'indirizzo con il nome passato come parametro
	 * 
	 * @param nome il nome dell'indirizzo che vogliamo cancellare
	 * 
	 */
	public void cancellaIndirizzo(String nome){
		int result = create.deleteFrom(Indirizzi.INDIRIZZI)
				.where(Indirizzi.INDIRIZZI.NOME.eq(nome))
				.execute();
		System.out.println(result); // stampa 1 se tutto andato bene
	}
	
	/**
	 * 
	 * Questo metodo cancella tutti gli indirizzi dal db 
	 * 
	 */
	public void cancellaTuttiIndirizzi(){
		int result = create.deleteFrom(Indirizzi.INDIRIZZI)
				.execute();
		System.out.println(result); // stampa 1 se tutto andato bene
	}
}
