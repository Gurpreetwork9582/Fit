package com.example.FitTrack;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

import java.util.ArrayList;
import java.util.List;

@Route("home2")
public class home2 extends VerticalLayout {

    private final VerticalLayout mainContent;

    public home2() {
        // Header Section with Styling
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setPadding(true);
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.getStyle().set("background-color", "#f8f9fa").set("padding", "10px 20px").set("border-bottom", "1px solid #ddd");

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

        Span dashboardTitle = new Span("Management Dashboard");
        dashboardTitle.getStyle().set("font-size", "18px").set("font-weight", "bold").set("color", "#444");

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

        Grid<Trainer> trainerGrid = new Grid<>(Trainer.class, false);
        trainerGrid.addColumn(Trainer::getName).setHeader("Name").setAutoWidth(true);
        trainerGrid.addColumn(Trainer::getSpecialization).setHeader("Specialization").setAutoWidth(true);
        trainerGrid.addColumn(Trainer::getExperience).setHeader("Experience").setAutoWidth(true);

        trainerGrid.setItems(getTrainerList());
        mainContent.add(title, trainerGrid);
    }

    private void showClients() {
        mainContent.removeAll();
        Span title = new Span("Clients");
        title.getStyle().set("font-size", "22px").set("font-weight", "bold").set("color", "#333");

        Grid<Client> clientGrid = new Grid<>(Client.class, false);
        clientGrid.addColumn(Client::getName).setHeader("Name").setAutoWidth(true);
        clientGrid.addColumn(Client::getGoal).setHeader("Goal").setAutoWidth(true);

        clientGrid.setItems(getClientList());
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
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(new Trainer("John Smith", "Strength Training", "5 years"));
        trainers.add(new Trainer("Jane Doe", "Cardio & Endurance", "3 years"));
        trainers.add(new Trainer("Mike Johnson", "Flexibility & Yoga", "8 years"));
        return trainers;
    }

    private List<Client> getClientList() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Alice", "Lose 10kg in 2 months"));
        clients.add(new Client("Bob", "Build muscle mass"));
        clients.add(new Client("Carol", "Improve stamina"));
        return clients;
    }

    private List<Plan> getPlanList() {
        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan("Strength and Conditioning", "3-day a week strength and conditioning plan.", "10 users"));
        plans.add(new Plan("Fat Loss", "A 30-day fat loss plan for beginners.", "5 users"));
        plans.add(new Plan("Muscle Gain", "A 60-day muscle gain plan for intermediate users.", "2 users"));
        plans.add(new Plan("Endurance Training", "An endurance training plan for advanced users.", "8 users"));
        return plans;
    }

    public static class Trainer {
        private String name;
        private String specialization;
        private String experience;

        public Trainer(String name, String specialization, String experience) {
            this.name = name;
            this.specialization = specialization;
            this.experience = experience;
        }

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

    public static class Client {
        private String name;
        private String goal;

        public Client(String name, String goal) {
            this.name = name;
            this.goal = goal;
        }

        public String getName() {
            return name;
        }

        public String getGoal() {
            return goal;
        }
    }

    public static class Plan {
        private String planName;
        private String description;
        private String assignments;

        public Plan(String planName, String description, String assignments) {
            this.planName = planName;
            this.description = description;
            this.assignments = assignments;
        }

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
}
