package com.example.FitTrack;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class Authentication2 extends VerticalLayout {

    private final ClientRepository clientRepository;

    @Autowired
    public Authentication2(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

        // Create form fields
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");
        Button loginButton = new Button("Login");

        // Add components to layout
        add(usernameField, passwordField, loginButton);

        loginButton.addClickListener(e -> {
            // Check if user exists
            Client client = clientRepository.findByUsernameAndPassword(usernameField.getValue(), passwordField.getValue());
            if (client != null) {
                // Save client to session
                UI.getCurrent().getSession().setAttribute(Client.class, client);
                Notification.show("Login successful!");
                UI.getCurrent().navigate(UserPage.class);
            } else {
                Notification.show("Invalid username or password", 3000, Notification.Position.MIDDLE);
            }
        });
    }
}
