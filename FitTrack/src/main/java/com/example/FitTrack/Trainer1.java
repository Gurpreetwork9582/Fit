package com.example.FitTrack;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("Trainer1")
public class Trainer1 extends HorizontalLayout {

    public Trainer1() {
        // Sidebar
        VerticalLayout sidebar = createSidebar();

        // Main Content
        VerticalLayout mainContent = new VerticalLayout();
        mainContent.setWidth("80%");

        // Initialize Home content (Welcome message)
        VerticalLayout homeContent = createHomeContent();
        mainContent.add(homeContent);

        // Add everything to the layout
        add(sidebar, mainContent);
        setSizeFull();

        addLogoutButton();
    }

    private VerticalLayout createSidebar() {
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setWidth("20%");
        sidebar.getStyle()
                .set("background-color", "#f4f4f4")
                .set("padding", "20px")
                .set("height", "100vh");

        Span title = new Span("FitTrack");
        title.getStyle()
                .set("font-size", "24px")
                .set("font-weight", "bold");

        // Sidebar buttons
        Button home = new Button("Home");
        Button workouts = new Button("Workouts");
        Button calendar = new Button("Calendar");

        Button clients = new Button("Clients");

        // Style buttons
        for (Button button : new Button[]{home,  workouts, calendar, clients}) {
            button.setWidthFull();
            button.getStyle()
                    .set("text-align", "left")
                    .set("background", "none")
                    .set("border", "none")
                    .set("padding", "10px")
                    .set("font-size", "16px")
                    .set("color", "#555")
                    .set("cursor", "pointer");
        }

        // Button Click Listeners
        home.addClickListener(event -> showHomeContent());
        calendar.addClickListener(event -> showCalendarContent());
        workouts.addClickListener(event -> showExercisesContent());
        clients.addClickListener(event -> showClientsContent());

        sidebar.add(title, home, calendar, workouts, clients);
        return sidebar;
    }

    private VerticalLayout createHomeContent() {
        // Create the home layout container
        VerticalLayout homeContent = new VerticalLayout();

        // Section for Trainer's Profile Picture and Greeting
        HorizontalLayout profileSection = new HorizontalLayout();
        Image profileImage = new Image("https://via.placeholder.com/80", "Trainer Profile");
        profileImage.getStyle().set("border-radius", "50%").set("width", "80px").set("height", "80px");

        Span welcomeMessage = new Span("Welcome, Trainer 1!");
        welcomeMessage.getStyle()
                .set("font-size", "24px")
                .set("font-weight", "bold")
                .set("color", "#333");

        profileSection.add(profileImage, welcomeMessage);
        profileSection.setAlignItems(Alignment.CENTER);
        profileSection.getStyle().set("margin-bottom", "20px");

        // Section for Trainer's Detailed Information
        VerticalLayout detailsSection = new VerticalLayout();

        Span heightLabel = new Span("Height: 175 cm");
        heightLabel.getStyle().set("font-size", "18px").set("color", "#666");

        Span weightLabel = new Span("Weight: 70 kg");
        weightLabel.getStyle().set("font-size", "18px").set("color", "#666");

        Span qualificationLabel = new Span("Qualification: Certified Personal Trainer");
        qualificationLabel.getStyle().set("font-size", "18px").set("color", "#666");

        // Add these details to the details section
        detailsSection.add(heightLabel, weightLabel, qualificationLabel);
        detailsSection.getStyle().set("margin-top", "20px");

        // Section for Trainer's Bio or Introduction (Optional)
        Span bioTitle = new Span("Trainer Bio");
        bioTitle.getStyle().set("font-size", "22px").set("font-weight", "bold");

        Span bioText = new Span("I am a certified personal trainer with 5+ years of experience in fitness and weight management. I help clients achieve their fitness goals through customized workout plans and nutrition guidance.");
        bioText.getStyle().set("font-size", "16px").set("color", "#666").set("line-height", "1.6");

        // Add bio to the content
        VerticalLayout bioSection = new VerticalLayout();
        bioSection.add(bioTitle, bioText);
        bioSection.getStyle().set("margin-top", "20px");

        // Add everything to the home content
        homeContent.add(profileSection, detailsSection, bioSection);

        return homeContent;
    }

    private VerticalLayout createCalendarContent() {
        // Create the main container for the calendar
        VerticalLayout calendarContent = new VerticalLayout();

        // Calendar Title
        Span calendarTitle = new Span("Trainer's Calendar");
        calendarTitle.getStyle()
                .set("font-size", "24px")
                .set("font-weight", "bold")
                .set("color", "#333")
                .set("margin-bottom", "20px");

        // Add Title
        calendarContent.add(calendarTitle);

        // Create the calendar header with days of the week
        HorizontalLayout calendarHeader = new HorizontalLayout();
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        for (String day : daysOfWeek) {
            Span dayLabel = new Span(day);
            dayLabel.getStyle().set("font-size", "16px").set("font-weight", "bold").set("text-align", "center")
                    .set("width", "50px").set("margin", "0 5px");
            calendarHeader.add(dayLabel);
        }

        // Create a grid for the calendar dates (7 columns for each day)
        HorizontalLayout calendarGrid = new HorizontalLayout();
        calendarGrid.setSpacing(true); // Add spacing between rows
        calendarGrid.setPadding(false); // Remove padding between rows
        calendarGrid.setWidthFull(); // Make sure the calendar is 100% width

        // Create empty cells for the calendar grid (7 columns for each day of the week)
        for (int i = 0; i < 7; i++) {
            TextField dayCell = new TextField();
            dayCell.setWidth("60px"); // Set width for text fields
            dayCell.setHeight("60px"); // Set height for text fields
            dayCell.setPlaceholder("Day");
            dayCell.getStyle().set("border", "1px solid #ddd")
                    .set("border-radius", "8px")
                    .set("font-size", "16px")
                    .set("text-align", "center")
                    .set("margin", "5px")
                    .set("padding", "10px")
                    .set("background-color", "#f9f9f9")
                    .set("cursor", "pointer")
                    .set("outline", "none");

            // Add click listener (either focus or mouse click listener)
            dayCell.addFocusListener(event -> {
                // Handle focus (click) event
                System.out.println("Day clicked: " + dayCell.getPlaceholder());
            });

            calendarGrid.add(dayCell);
        }

        // Add header and grid to the calendar content
        calendarContent.add(calendarHeader, calendarGrid);

        // Style the whole calendar content
        calendarContent.getStyle().set("padding", "20px")
                .set("background-color", "#f9f9f9")
                .set("border-radius", "8px")
                .set("box-shadow", "0 2px 5px rgba(0, 0, 0, 0.1)");

        return calendarContent;
    }


//

    private VerticalLayout createExercisesContent() {
        // Exercises Section (like before)
        VerticalLayout exercisesSection = new VerticalLayout();
        Span title = new Span("Exercises");
        title.getStyle()
                .set("font-size", "22px")
                .set("font-weight", "bold");

        // Example exercises
        exercisesSection.add(title);
        exercisesSection.add(createExerciseItem("Squat", "3 sets, 10 reps"));
        exercisesSection.add(createExerciseItem("Pushup", "3 sets, 10 reps"));
        exercisesSection.add(createExerciseItem("Plank", "3 sets, 10 reps"));

        // Create a Panel for inputting new exercise
        VerticalLayout addExercisePanel = new VerticalLayout();

        // Horizontal layout for text fields and buttons
        HorizontalLayout inputLayout = new HorizontalLayout();
        inputLayout.setSpacing(true);

        // Text fields for Exercise, Sets, and Reps
        TextField exerciseField = new TextField("Exercise Name");
        TextField setsField = new TextField("Sets");
        TextField repsField = new TextField("Reps/Details");

        // Set widths for fields to align them horizontally
        exerciseField.setWidth("200px");
        setsField.setWidth("200px");
        repsField.setWidth("200px");

        // Button to Add Exercise
        Button addButton = new Button("Add Exercise");
        addButton.addClickListener(event -> {
            String exercise = exerciseField.getValue();
            String sets = setsField.getValue();
            String reps = repsField.getValue();
            if (!exercise.isEmpty() && !sets.isEmpty() && !reps.isEmpty()) {
                exercisesSection.add(createExerciseItem(exercise, sets + " sets, " + reps + " reps"));
                exerciseField.clear();
                setsField.clear();
                repsField.clear();
            }
        });

        // Button to Clear All Exercises (keep fields and buttons intact)
        Button clearAllButton = new Button("Clear All Exercises");
        clearAllButton.addClickListener(event -> {
            // Only clear exercise items, not the fields and buttons
            exercisesSection.getChildren().filter(child -> child instanceof Div).forEach(exercise -> {
                exercisesSection.remove(exercise); // Remove only the exercises (Div elements)
            });
        });

        // Style buttons
        addButton.getStyle()
                .set("background-color", "#28a745")
                .set("color", "white")
                .set("border", "none")
                .set("cursor", "pointer")
                .set("border-radius", "4px")
                .set("padding", "10px 20px");
        clearAllButton.getStyle()
                .set("background-color", "#dc3545")
                .set("color", "white")
                .set("border", "none")
                .set("cursor", "pointer")
                .set("border-radius", "4px")
                .set("padding", "10px 20px");

        // Add text fields and buttons to the horizontal layout
        inputLayout.add(exerciseField, setsField, repsField, addButton, clearAllButton);

        // Add Panel for input fields and buttons
        addExercisePanel.add(inputLayout);

        // Add all components to the exercisesSection
        exercisesSection.add(addExercisePanel);

        return exercisesSection;
    }

    ///
    private Div createExerciseItem(String name, String details) {
        Div exerciseItem = new Div();

        // Create individual components for name, sets, and reps
        Span exerciseName = new Span(name);
        exerciseName.getStyle()
                .set("font-size", "18px")
                .set("font-weight", "bold");

        Span exerciseDetails = new Span(details);
        exerciseDetails.getStyle()
                .set("font-size", "14px")
                .set("color", "#666");

        // Add the exercise name and details to the item
        exerciseItem.add(exerciseName, new Span(" - "), exerciseDetails);

        // Apply flexbox styling to space items out horizontally
        exerciseItem.getStyle()
                .set("display", "flex")
                .set("align-items", "center")  // Align items vertically centered
                .set("justify-content", "space-between")  // Spread items across the available width
                .set("padding", "12px 20px")  // Increase padding for more space
                .set("border", "1px solid #ddd")
                .set("border-radius", "8px")  // Round corners a bit more for aesthetics
                .set("margin-bottom", "15px")  // Add more margin between items
                .set("background-color", "#f9f9f9");  // Light background for better contrast

        // Optionally, you can add spacing between the name and details
        exerciseItem.getStyle().set("gap", "20px");

        return exerciseItem;
    }






    ///
    private VerticalLayout createClientsContent() {
        // Placeholder for clients' list (replace with actual client data retrieval)
        VerticalLayout clientsContent = new VerticalLayout();
        Span title = new Span("Clients");
        title.getStyle()
                .set("font-size", "22px")
                .set("font-weight", "bold");

        // Sample clients (You can replace this with actual client data from a database)
        clientsContent.add(title, createClientItem("John Doe", " Fat Loss", " john.doe@example.com"));
        clientsContent.add(createClientItem("Jane Smith", " Muscle Gain", " jane.smith@example.com"));

        return clientsContent;
    }

    private Div createClientItem(String name, String goal, String email) {
        Div clientItem = new Div();
        Span clientName = new Span(name);
        clientName.getStyle()
                .set("font-size", "18px")
                .set("font-weight", "bold");
        Span clientGoal = new Span(goal);
        clientGoal.getStyle()
                .set("font-size", "14px")
                .set("color", "#666");
        Span clientEmail = new Span(email);
        clientEmail.getStyle()
                .set("font-size", "14px")
                .set("color", "#666");

        clientItem.add(clientName, new Span(" - "), clientGoal, new Span(" - "), clientEmail);
        clientItem.getStyle()
                .set("display", "flex")
                .set("justify-content", "space-between")
                .set("padding", "10px")
                .set("border", "1px solid #ddd")
                .set("border-radius", "4px")
                .set("margin-bottom", "10px");

        return clientItem;
    }

    private void addLogoutButton() {
        // Logout Button (located at the bottom-left of the screen)
        Button logoutButton = new Button("Logout");
        logoutButton.getStyle()
                .set("position", "absolute")
                .set("bottom", "20px")
                .set("left", "20px")
                .set("padding", "10px 20px")
                .set("background-color", "#dc3545")
                .set("color", "white")
                .set("border", "none")
                .set("cursor", "pointer")
                .set("border-radius", "4px");

        logoutButton.addClickListener(event -> {
            // Clear session or any authentication state
            UI.getCurrent().getSession().getSession().invalidate();
            // Navigate to login page
            UI.getCurrent().navigate("");
        });

        add(logoutButton);
    }

    private void showHomeContent() {
        // Display Home Content
        VerticalLayout homeContent = createHomeContent();
        // Update main content with Home Content
        ((VerticalLayout) getChildren().skip(1).findFirst().get()).removeAll();
        ((VerticalLayout) getChildren().skip(1).findFirst().get()).add(homeContent);
    }

    private void showCalendarContent() {
        // Display Calendar Content
        VerticalLayout calendarContent = createCalendarContent();
        // Update main content with Calendar Content
        ((VerticalLayout) getChildren().skip(1).findFirst().get()).removeAll();
        ((VerticalLayout) getChildren().skip(1).findFirst().get()).add(calendarContent);
    }

    private void showExercisesContent() {
        // Display Exercises Content
        VerticalLayout exercisesContent = createExercisesContent();
        // Update main content with Exercises Content
        ((VerticalLayout) getChildren().skip(1).findFirst().get()).removeAll();
        ((VerticalLayout) getChildren().skip(1).findFirst().get()).add(exercisesContent);
    }

    private void showClientsContent() {
        // Display Clients Content
        VerticalLayout clientsContent = createClientsContent();
        // Update main content with Clients Content
        ((VerticalLayout) getChildren().skip(1).findFirst().get()).removeAll();
        ((VerticalLayout) getChildren().skip(1).findFirst().get()).add(clientsContent);
    }
}
