package com.example.FitTrack;

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

    private void showClients() {
        mainContent.removeAll(); // Clear existing content

        Span title = new Span("Clients");
        title.getStyle().set("font-size", "22px").set("font-weight", "bold").set("color", "#333");

        Grid<Client> clientGrid = new Grid<>(Client.class, false);
        clientGrid.addColumn(Client::getName).setHeader("Name").setAutoWidth(true);
        clientGrid.addColumn(Client::getEmail).setHeader("Email").setAutoWidth(true);
        clientGrid.addColumn(Client::getGoal).setHeader("Goal").setAutoWidth(true);
        clientGrid.addColumn(Client::getHeight).setHeader("Height (cm)").setAutoWidth(true);
        clientGrid.addColumn(Client::getWeight).setHeader("Weight (kg)").setAutoWidth(true);

        // Fetch clients from the database
        List<Client> clients = clientRepository.findAll();
        clientGrid.setItems(clients);

        mainContent.add(title, clientGrid);
    }

    private void showPlans() {
        mainContent.removeAll();
        Span title = new Span("Plans");
        title.getStyle().set("font-size", "22px").set("font-weight", "bold").set("color", "#333");

        Grid<Plan> planGrid = new Grid<>(Plan.class, false);
        planGrid.addColumn(Plan::getPlanName).setHeader("Plan").setAutoWidth(true);
        planGrid.addColumn(Plan::getDescription).setHeader("Description").setAutoWidth(true);
        planGrid.addColumn(Plan::getAssignments).setHeader("Assignments").setAutoWidth(true);

        planGrid.setItems(getPlanList());
        mainContent.add(title, planGrid);
    }

    private List<Trainer> getTrainerList() {
        return List.of(
                new Trainer("John Smith", "Strength Training", "5 years"),
                new Trainer("Jane Doe", "Cardio & Endurance", "3 years"),
                new Trainer("Mike Johnson", "Flexibility & Yoga", "8 years")
        );
    }

    private List<Plan> getPlanList() {
        return List.of(
                new Plan("Strength and Conditioning", "3-day a week plan.", "10 users"),
                new Plan("Fat Loss", "A 30-day fat loss plan.", "5 users"),
                new Plan("Muscle Gain", "A 60-day muscle gain plan.", "2 users"),
                new Plan("Endurance Training", "An endurance training plan.", "8 users")
        );
    }
}

