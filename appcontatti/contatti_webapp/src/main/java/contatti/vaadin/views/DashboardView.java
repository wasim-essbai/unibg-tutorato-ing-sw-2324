package contatti.vaadin.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import contatti.db_model.generated.tables.records.IndirizziRecord;
import db_model.DataService;

import contatti.vaadin.layouts.MainLayout;

/**
 * @author Wasim Essbai
 */
@Route(value = "", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3791610571524034067L;
	
	private DataService dataService;
	private HorizontalLayout toolbar;
	private TextField filterText;
	private Grid<IndirizziRecord> grid;
	
	public DashboardView() {
		dataService = new DataService();
		initView();
    }
	
	private void initView() {
		add(new H2("Lista degli indirizzi"));
		
		// imposto un testo e un bottone per cercare
		
    	filterText = new TextField();
    	filterText.setPlaceholder("Cerca per nome...");
    	
    	filterText.addThemeName("bordered");
    	
    	Button searchContactButton = new Button("Cerca", e -> {
    		if(filterText.getValue() != null && filterText.getValue() != "") {
    			cercaIndirizzoPerNome(filterText.getValue());
    		} else {
    			cercaTuttiIndirizzi();
    		}
    	});
    	
    	searchContactButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    	searchContactButton.addClickShortcut(Key.ENTER);
    	
    	// imposto pop up per inserimento
    	Button inserisciButton = setInsertPopUpView();
    	inserisciButton.getElement().getStyle().set("margin-left", "auto");
    	
    	toolbar = new HorizontalLayout(filterText, searchContactButton, inserisciButton);
    	toolbar.setWidth("100%");
    	
    	// Imposto la tabella che conterrà gli indirizzi
    	grid = new Grid<>(IndirizziRecord.class, false);
    	grid.addColumn(IndirizziRecord::getNome).setHeader("Nome");
    	grid.addColumn(IndirizziRecord::getNumtelefono).setHeader("Telefono");
    	grid.addComponentColumn(item -> new Button("Delete", click -> {
    		cancellaIndirizzo(item.get(0).toString());
    	}));
    	
    	cercaTuttiIndirizzi();
    	
    	add(toolbar, grid);
	}
	
	// Funzione per ottenere il pulsante che apre il pop-up di inserimento
	private Button setInsertPopUpView() {
		Dialog dialog = new Dialog();
		
		dialog.setHeaderTitle("Inserisci nuovo indirizzo");
		
		VerticalLayout dialogLayout = new VerticalLayout();
		TextField nome = new TextField("Nome");
		TextField numTel = new TextField("Numero di telefono");
		dialogLayout.add(nome);
		dialogLayout.add(numTel);
		
		Button saveButton = new Button("Salva", e -> {
			salvaIndirizzo(nome.getValue(), numTel.getValue());
			dialog.close();
		});
		
		Button cancelButton = new Button("Cancella", e -> dialog.close());
		
		dialog.getFooter().add(cancelButton);
		dialog.getFooter().add(saveButton);
		
		dialog.add(dialogLayout);

		Button button = new Button("Inserisci indirizzo", e -> dialog.open());
		button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		
		add(dialog);
		
		return button;
	}
	
	private void cercaTuttiIndirizzi() {
		grid.setItems(dataService.getIndirizziRecord());
		aggiornaLista();
	}
	
	private void cercaIndirizzoPerNome(String nome) {
		List<IndirizziRecord> people = new ArrayList<IndirizziRecord>();
		
		IndirizziRecord indirizzo = dataService.getIndirizzoPerNome(nome);
		if(indirizzo != null) {
			people.add(indirizzo); 		
		}
		
		grid.setItems(people);
		aggiornaLista();
	}
	
	private void salvaIndirizzo(String nome, String numTel) {
		dataService.inserisciIndirizzo(nome, numTel);
		cercaTuttiIndirizzi();
	}
	
	private void cancellaIndirizzo(String nome) {
		dataService.cancellaIndirizzo(nome);
		cercaTuttiIndirizzi();
	}
	
	private void aggiornaLista() {
		grid.getDataProvider().refreshAll();
	}
}
