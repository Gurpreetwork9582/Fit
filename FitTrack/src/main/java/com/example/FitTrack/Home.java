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

@Route("Home")
public class Home extends VerticalLayout {

    public Home() {
        // Header Section with Styling
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.setPadding(true);
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.getStyle().set("background-color", "#f8f9fa").set("padding", "10px 20px").set("border-bottom", "1px solid #ddd");

        Span logo = new Span("Fittrack");
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

        Button overviewButton = createSidebarButton("Overview");
        Button trainersButton = createSidebarButton("Trainers");
        Button clientsButton = createSidebarButton("Clients");
        Button groupsButton = createSidebarButton("Groups");
        Button plansButton = createSidebarButton("Plans");

        sidebar.add(dashboardTitle, overviewButton, trainersButton, clientsButton, groupsButton, plansButton);

        // Main Content Area with Styling
        VerticalLayout mainContent = new VerticalLayout();
        mainContent.setWidth("80%");
        mainContent.getStyle().set("padding", "20px");

        Span title = new Span("Plans");
        title.getStyle().set("font-size", "22px").set("font-weight", "bold").set("color", "#333");

        Grid<Plan> grid = new Grid<>(Plan.class, false);
        grid.addColumn(Plan::getPlanName).setHeader("Plan").setAutoWidth(true);
        grid.addColumn(Plan::getDescription).setHeader("Description").setAutoWidth(true);
        grid.addColumn(Plan::getAssignments).setHeader("Assignments").setAutoWidth(true);

        grid.getStyle().set("border", "1px solid #ddd").set("border-radius", "5px").set("overflow", "hidden");

        List<Plan> plans = getPlanList();
        grid.setItems(plans);

        mainContent.add(title, grid);

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

    private List<Plan> getPlanList() {
        List<Plan> plans = new ArrayList<>();
        plans.add(new Plan("Strength and Conditioning", "3-day a week strength and conditioning plan.", "10 users"));
        plans.add(new Plan("Fat Loss", "A 30-day fat loss plan for beginners.", "5 users"));
        plans.add(new Plan("Muscle Gain", "A 60-day muscle gain plan for intermediate users.", "2 users"));
        plans.add(new Plan("Endurance Training", "An endurance training plan for advanced users.", "8 users"));
        return plans;
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
