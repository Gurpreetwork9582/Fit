package com.example.FitTrack;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.example.FitTrack.Client;
import com.example.FitTrack.Plan;
import com.example.FitTrack.Trainer;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("Admin")
public class Admin extends VerticalLayout {

    private final VerticalLayout mainContent;

    @Autowired
    private ClientRepository clientRepository;

    public Admin() {
        // Header Section with Styling
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setPadding(true);
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.getStyle()
                .set("background-color", "#f8f9fa")
                .set("padding", "10px 20px")
                .set("border-bottom", "1px solid #ddd");

        Span logo = new Span("FitTrack");
        logo.getStyle()
                .set("font-size", "24px")
                .set("font-weight", "bold")
                .set("color", "#333");

        Image profileImage = new Image("https://via.placeholder.com/40", "Profile");
        profileImage.getStyle()
                .set("border-radius", "50%")
                .set("cursor", "pointer")
                .set("width", "40px")
                .set("height", "40px");

        header.add(logo, profileImage);

        // Sidebar Navigation with Styling
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setWidth("20%");
        sidebar.getStyle()
                .set("background-color", "#f4f4f4")
                .set("padding", "20px")
                .set("height", "100vh")
                .set("box-shadow", "1px 0px 5px rgba(0,0,0,0.1)");

        Span dashboardTitle = new Span("Admin Dashboard");
        dashboardTitle.getStyle()
                .set("font-size", "18px")
                .set("font-weight", "bold")
                .set("color", "#444");

        // Sidebar Buttons
        Button overviewButton = createSidebarButton("Overview");
        Button trainersButton = createSidebarButton("Trainers");
        Button clientsButton = createSidebarButton("Clients");
        Button plansButton = createSidebarButton("Plans");

        // Add click listeners to buttons
        overviewButton.addClickListener(event -> showOverview());
        trainersButton.addClickListener(event -> showTrainers());
        clientsButton.addClickListener(event -> showClients());
        plansButton.addClickListener(event -> showPlans());

        sidebar.add(dashboardTitle, overviewButton, trainersButton, clientsButton, plansButton);

        // Main Content Area with Styling
        mainContent = new VerticalLayout();
        mainContent.setWidth("80%");
        mainContent.getStyle().set("padding", "20px");
        mainContent.getStyle().set("background-color", "#fff");
        mainContent.setMargin(false);
        mainContent.setPadding(false);
        mainContent.setSpacing(false);

        // Initially show the Overview content
        showOverview();

        // Combine Sidebar and Main Content
        HorizontalLayout contentLayout = new HorizontalLayout(sidebar, mainContent);
        contentLayout.setSizeFull();
        contentLayout.setFlexGrow(1, mainContent);

        // Add Components to Main Layout
        add(header, contentLayout);
        setSizeFull(); // Ensure the content is full screen
        getStyle().set("background-color", "#f9f9f9");

        addLogoutButton();
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

    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setWidthFull();
        button.getStyle()
                .set("text-align", "left")
                .set("background", "none")
                .set("border", "none")
                .set("padding", "10px")
                .set("font-size", "16px")
                .set("color", "#555")
                .set("cursor", "pointer");
        return button;
    }

    private void showOverview() {
        mainContent.removeAll();
        Span welcomeMessage = new Span("Welcome to FitTrack!");
        welcomeMessage.getStyle()
                .set("font-size", "22px")
                .set("font-weight", "bold")
                .set("color", "#333");
        mainContent.add(welcomeMessage);
    }
/*
    private void showTrainers() {
        mainContent.removeAll();
        Span title = new Span("Trainers");
        title.getStyle().set("font-size", "22px").set("font-weight", "bold").set("color", "#333");

        // Example Trainers Grid
        Grid<Trainer> trainerGrid = new Grid<>(Trainer.class, false);
        trainerGrid.addColumn(Trainer::getName).setHeader("Name").setAutoWidth(true);
        trainerGrid.addColumn(Trainer::getSpecialization).setHeader("Specialization").setAutoWidth(true);
        trainerGrid.addColumn(Trainer::getExperience).setHeader("Experience").setAutoWidth(true);

        trainerGrid.setItems(getTrainerList());
        mainContent.add(title, trainerGrid);
    }

 */

    private void showClients() {
        mainContent.removeAll(); // Clear existing content

        // Title
        Span title = new Span("Clients");
        title.getStyle()
                .set("font-size", "28px")
                .set("font-weight", "bold")
                .set("color", "#333")
                .set("margin-bottom", "20px");

        // Grid Layout for Clients
        Grid<Client> clientGrid = new Grid<>(Client.class, false);

        // Configure columns with better styling
        clientGrid.addColumn(Client::getName).setHeader("Name").setAutoWidth(true);

        clientGrid.addColumn(Client::getEmail).setHeader("Email").setAutoWidth(true);


        clientGrid.addColumn(Client::getGoal).setHeader("Goal").setAutoWidth(true);

        clientGrid.addColumn(Client::getHeight).setHeader("Height (cm)").setAutoWidth(true);

        clientGrid.addColumn(Client::getWeight).setHeader("Weight (kg)").setAutoWidth(true);


        // Apply custom styling to the grid itself
        clientGrid.getStyle().set("border-collapse", "collapse")
                .set("width", "100%")
                .set("background-color", "#fff")
                .set("box-shadow", "0 4px 6px rgba(0,0,0,0.1)");

        // Fetch clients from the database
        List<Client> clients = clientRepository.findAll();
        clientGrid.setItems(clients);

        // Add a "Create New Client" button (optional for further interaction)
        Button createNewClientButton = new Button("Add New Client");
        createNewClientButton.getStyle()
                .set("background-color", "#28a745")
                .set("color", "#fff")
                .set("border", "none")
                .set("padding", "10px 20px")
                .set("border-radius", "5px")
                .set("cursor", "pointer");

        // Add all components to the main content area
        VerticalLayout layout = new VerticalLayout(title, clientGrid, createNewClientButton);
        layout.setSpacing(true);
        layout.setPadding(true);
        layout.getStyle().set("padding", "20px");

        mainContent.add(layout);
    }


    // Trainer class to represent each trainer's details
    public class Trainer {
        private String name;
        private String specialization;
        private String experience;

        // Constructor for initializing Trainer object
        public Trainer(String name, String specialization, String experience) {
            this.name = name;
            this.specialization = specialization;
            this.experience = experience;
        }

        // Getter methods for trainer details
        public String getName() {
            return name;
        }

        public String getSpecialization() {
            return specialization;
        }

        public String getExperience() {
            return experience;
        }
    }

    // Plan class to represent the details of a workout plan
    public class Plan {
        private String planName;
        private String description;
        private String assignments;

        // Constructor for initializing Plan object
        public Plan(String planName, String description, String assignments) {
            this.planName = planName;
            this.description = description;
            this.assignments = assignments;
        }

        // Getter methods for plan details
        public String getPlanName() {
            return planName;
        }

        public String getDescription() {
            return description;
        }

        public String getAssignments() {
            return assignments;
        }
    }

    // Method to display the list of trainers in the UI
    private List<Trainer> getTrainerList() {
        return List.of(
                new Trainer("John Smith", "Strength Training", "5 years"),
                new Trainer("Jane Doe", "Cardio & Endurance", "3 years"),
                new Trainer("Mike Johnson", "Flexibility & Yoga", "8 years")
        );
    }

    // Method to display the list of plans in the UI
    private List<Plan> getPlanList() {
        return List.of(
                new Plan("Strength and Conditioning", "3-day a week strength and conditioning plan.", "10 users"),
                new Plan("Fat Loss", "A 30-day fat loss plan for beginners.", "5 users"),
                new Plan("Muscle Gain", "A 60-day muscle gain plan for intermediate users.", "2 users"),
                new Plan("Endurance Training", "An endurance training plan for advanced users.", "8 users")
        );
    }

    // Method to show plans in the main content area
    private void showPlans() {
        mainContent.removeAll(); // Clear any existing content

        // Title for Plans section
        Span title = new Span("Plans");
        title.getStyle().set("font-size", "22px").set("font-weight", "bold").set("color", "#333");

        // Creating a Grid to display the Plan list
        Grid<Plan> planGrid = new Grid<>(Plan.class, false);
        planGrid.addColumn(Plan::getPlanName).setHeader("Plan").setAutoWidth(true);
        planGrid.addColumn(Plan::getDescription).setHeader("Description").setAutoWidth(true);
        planGrid.addColumn(Plan::getAssignments).setHeader("Assignments").setAutoWidth(true);

        // Fetching and setting items for the plan grid
        planGrid.setItems(getPlanList());

        // Add the title and the grid to the main content
        mainContent.add(title, planGrid);
    }

    // Method to show trainers in the main content area
    private void showTrainers() {
        mainContent.removeAll(); // Clear any existing content

        // Title for Trainers section
        Span title = new Span("Trainers");
        title.getStyle().set("font-size", "22px").set("font-weight", "bold").set("color", "#333");

        // Creating a Grid to display the Trainer list
        Grid<Trainer> trainerGrid = new Grid<>(Trainer.class, false);
        trainerGrid.addColumn(Trainer::getName).setHeader("Name").setAutoWidth(true);
        trainerGrid.addColumn(Trainer::getSpecialization).setHeader("Specialization").setAutoWidth(true);
        trainerGrid.addColumn(Trainer::getExperience).setHeader("Experience").setAutoWidth(true);

        // Fetching and setting items for the trainer grid
        trainerGrid.setItems(getTrainerList());

        // Add the title and the grid to the main content
        mainContent.add(title, trainerGrid);
    }
}