package contatti.vaadin.layouts;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

import contatti.vaadin.views.DashboardView;
import contatti.vaadin.views.EmpyPageView;

/**
 * @author Wasim Essbai
 */
public class MainLayout extends AppLayout { 

    /**
	 * 
	 */
	private static final long serialVersionUID = 8891531520692894495L;

	public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Gestore indirizzi");
        logo.addClassNames(
            LumoUtility.FontSize.LARGE, 
            LumoUtility.Margin.MEDIUM);

        var header = new HorizontalLayout(new DrawerToggle(), logo ); 

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER); 
        header.setWidthFull();
        header.addClassNames(
            LumoUtility.Padding.Vertical.NONE,
            LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header); 

    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout( 
                new RouterLink("Dashboard", DashboardView.class),
                new RouterLink("Empty Page", EmpyPageView.class)
        ));
    }
}