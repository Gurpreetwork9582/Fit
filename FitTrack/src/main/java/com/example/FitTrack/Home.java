package com.example.FitTrack;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

@Route("")
public class Authentication extends VerticalLayout {

    @Autowired
    private ClientRepository clientRepository;

    // Fields to store user data across steps
    private String name;
    private String email;
    private String password;
    private int age;
    private float height;
    private float weight;
    private String sex;

    public Authentication() {
        showLoginPage();
    }

    private void showLoginPage() {
        Div heading = new Div();
        heading.setText("𝔉𝔦𝔱𝔗𝔯𝔞𝔠𝔨");
        heading.getStyle()
                .set("font-size", "24px")
                .set("font-weight", "bold");

        TextField emailField = new TextField();
        emailField.setPlaceholder("Email");

        PasswordField passwordField = new PasswordField();
        passwordField.setPlaceholder("Password");

        Button loginButton = new Button("Login");
        loginButton.addClickListener(event -> {
            // Authenticate user
            Client client = clientRepository.findByEmail(emailField.getValue());

            if (client != null && client.getPassword().equals(passwordField.getValue())) {
                Notification.show("Login successful!", 3000, Notification.Position.MIDDLE);

                // Store logged-in user details in the session
                UI.getCurrent().getSession().setAttribute(Client.class, client);

                if ("admin@fittrack.com".equals(client.getEmail())) {
                    // Navigate to Admin page if it's an admin
                    UI.getCurrent().navigate(Admin.class);
                } else {
                    // Navigate to UserPage if it's a normal user
                    UI.getCurrent().navigate("UserPage");
                }
            } else {
                Notification.show("Invalid email or password!", 3000, Notification.Position.MIDDLE);
            }
        });




        Button signUpButton = new Button("Sign Up");
        signUpButton.addClickListener(event -> {
            removeAll(); // Remove login screen
            showStep1(); // Navigate to Step 1
        });


        VerticalLayout loginLayout = new VerticalLayout(heading, emailField, passwordField, loginButton, signUpButton );
        loginLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        loginLayout.setWidthFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();

        add(loginLayout);
    }

    private void showStep1() {
        Div step1Heading = new Div();
        step1Heading.setText("Step 1: Create Your Account");
        step1Heading.getStyle()
                .set("font-size", "20px")
                .set("font-weight", "bold");

        TextField nameField = new TextField("Name");
        TextField emailField = new TextField("Email");
        PasswordField passwordField = new PasswordField("Password");
        PasswordField confirmPasswordField = new PasswordField("Confirm Password");

        Button nextButton = new Button("Next");
        nextButton.addClickListener(event -> {
            if (!passwordField.getValue().equals(confirmPasswordField.getValue())) {
                Notification.show("Passwords do not match!", 3000, Notification.Position.MIDDLE);
            } else {
                // Store the values from Step 1
                name = nameField.getValue();
                email = emailField.getValue();
                password = passwordField.getValue();

                removeAll(); // Remove Step 1 screen
                showStep2(); // Navigate to Step 2
            }
        });

        VerticalLayout step1Layout = new VerticalLayout(step1Heading, nameField, emailField, passwordField, confirmPasswordField, nextButton);
        step1Layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        step1Layout.setWidthFull();
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(step1Layout);
    }

    private void showStep2() {
        Div step2Heading = new Div();
        step2Heading.setText("Step 2: Enter Your Details");
        step2Heading.getStyle()
                .set("font-size", "20px")
                .set("font-weight", "bold");

        TextField heightField = new TextField("Height (cm)");
        TextField weightField = new TextField("Weight (kg)");
        TextField ageField = new TextField("Age");
        RadioButtonGroup<String> sexGroup = new RadioButtonGroup<>();
        sexGroup.setLabel("Sex");
        sexGroup.setItems("Male", "Female", "Other");

        Button nextButton = new Button("Next");
        nextButton.addClickListener(event -> {
            if (heightField.isEmpty() || weightField.isEmpty() || ageField.isEmpty() || sexGroup.isEmpty()) {
                Notification.show("Please fill out all fields!", 3000, Notification.Position.MIDDLE);
            } else {
                // Store the values from Step 2
                height = Float.parseFloat(heightField.getValue());
                weight = Float.parseFloat(weightField.getValue());
                age = Integer.parseInt(ageField.getValue());
                sex = sexGroup.getValue();

                removeAll(); // Remove Step 2 screen
                showStep3(); // Navigate to Step 3
            }
        });

        VerticalLayout step2Layout = new VerticalLayout(step2Heading, heightField, weightField, ageField, sexGroup, nextButton);
        step2Layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        step2Layout.setWidthFull();
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(step2Layout);
    }

    private void showStep3() {
        Div step3Heading = new Div();
        step3Heading.setText("Step 3: Select Your Goal");
        step3Heading.getStyle()
                .set("font-size", "20px")
                .set("font-weight", "bold");

        RadioButtonGroup<String> goalGroup = new RadioButtonGroup<>();
        goalGroup.setLabel("Goal");
        goalGroup.setItems("Weight Loss", "Fat Loss", "Muscle Gain", "Yoga", "Balance Maintenance");


        Button finishButton = new Button("Finish");
        finishButton.addClickListener(event -> {
            if (goalGroup.isEmpty()) {
                Notification.show("Please select a goal!", 3000, Notification.Position.MIDDLE);
            } else {
                // Save the client to the database
                Client client = new Client(name, email, password, age, height, weight, sex, goalGroup.getValue());
                clientRepository.save(client);

                Notification.show("Signup successful! Please login.", 3000, Notification.Position.MIDDLE);

                // Clear session-related signup data (optional)
                name = null;
                email = null;
                password = null;
                age = 0;
                height = Float.parseFloat(null);
                weight = Float.parseFloat(null);
                sex = null;

                // Navigate back to the login page
                removeAll();
                showLoginPage();
            }
        });

        VerticalLayout step3Layout = new VerticalLayout(step3Heading, goalGroup, finishButton);
        step3Layout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        step3Layout.setWidthFull();
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(step3Layout);


    }
}


