package com.example.FitTrack;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class SignUp extends VerticalLayout {

    public SignUp () {
        var heading = new Text("\uD835\uDD3D\uD835\uDD5A\uD835\uDD65\uD835\uDD4B" +
                "\uD835\uDD63\uD835\uDD52\uD835\uDD54\uD835\uDD5C");

        // Email and Password Fields
        TextField email = new TextField();
        email.setPlaceholder("Email");
        email.addClassName("auth-input");

        TextField password = new TextField();
        password.setPlaceholder("Password");
        password.addClassName("auth-input");

        // Buttons
        Button loginButton = new Button("Login");
        loginButton.addClickListener(event -> {
            // Navigate to the Home page after Login
            UI.getCurrent().navigate("Home");
        });

        Button signInButton = new Button("Sign lol");
        signInButton.addClickListener(event -> {
            // Navigate to the SignUp page after SignIn
            UI.getCurrent().navigate("home2");
        });

        // Add Components


        // Add styling to align everything centrally
        addClassName("auth-container");
        var Setup = new VerticalLayout(heading ,email , password , loginButton , signInButton);
        setSizeFull();
        Setup.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);


        add(Setup);

    }
}
