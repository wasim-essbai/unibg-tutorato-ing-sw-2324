package contatti.vaadin.views;


import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

import contatti.vaadin.layouts.MainLayout;

/**
 * @author Wasim Essbai
 */
@Route(value = "empty", layout = MainLayout.class)
public class EmpyPageView extends VerticalLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4114249943996892130L;

	public EmpyPageView() {
		
		Span helloWorld = new Span("Hello world"); 
		helloWorld.addClassNames(
            LumoUtility.FontSize.XLARGE,
            LumoUtility.Margin.Top.MEDIUM);
        
		add(helloWorld);
    }

}
