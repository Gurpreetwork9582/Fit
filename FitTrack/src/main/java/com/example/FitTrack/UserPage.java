package com.example.FitTrack;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route("userpage")
public class UserPage extends VerticalLayout {

    private TextField firstNameField;
    private TextField lastNameField;

    public UserPage() {
        // Get the logged-in user from the session
        Client client = VaadinSession.getCurrent().getAttribute(Client.class);

        if (client != null) {
            firstNameField = new TextField("First Name", client.getFirstName());
            lastNameField = new TextField("Last Name", client.getLastName());
            add(firstNameField, lastNameField);
        } else {
            // If no client is found, redirect to login
            UI.getCurrent().navigate(Authentication2.class);
        }
    }
}
