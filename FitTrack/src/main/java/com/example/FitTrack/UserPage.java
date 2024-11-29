package com.example.FitTrack;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("UserPage")
public class UserPage extends VerticalLayout {
    private final VerticalLayout mainContent;

    private final List<Workout> trainerWorkouts = new ArrayList<>(); // Sync workouts with Trainer 1

    public UserPage() {
        // Header Section
        HorizontalLayout header = createHeader();

        // Sidebar Navigation
        VerticalLayout sidebar = createSidebar();

        // Main Content
        mainContent = new VerticalLayout();
        mainContent.setWidth("80%");
        mainContent.getStyle().set("padding", "20px");

        // Layout Structure
        HorizontalLayout layout = new HorizontalLayout(sidebar, mainContent);
        layout.setSizeFull();
        layout.setFlexGrow(1, mainContent);

        add(header, layout);
        setSizeFull();
        getStyle().set("background-color", "#f9f9f9");

        // Load Home content by default
        showHomeContent();
        addLogoutButton();
    }

    private HorizontalLayout createHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.getStyle()
                .set("background-color", "#f8f9fa")
                .set("padding", "10px 20px")
                .set("border-bottom", "1px solid #ddd");

        Span logo = new Span("Fittrack");
        logo.getStyle()
                .set("font-size", "20px")
                .set("font-weight", "bold")
                .set("color", "#333");

        Image profileImage = new Image("https://via.placeholder.com/40", "Profile");
        profileImage.getStyle()
                .set("border-radius", "50%")
                .set("cursor", "pointer")
                .set("width", "40px")
                .set("height", "40px");

        header.add(logo, profileImage);
        header.setJustifyContentMode(JustifyContentMode.BETWEEN);
        header.setAlignItems(Alignment.CENTER);

        return header;
    }

    private VerticalLayout createSidebar() {
        VerticalLayout sidebar = new VerticalLayout();
        sidebar.setWidth("20%");
        sidebar.getStyle()
                .set("background-color", "#f4f4f4")
                .set("padding", "20px")
                .set("height", "100vh");

        sidebar.add(createSidebarButton("Home", VaadinIcon.HOME, this::showHomeContent));
        sidebar.add(createSidebarButton("Trainer", VaadinIcon.USER_STAR, this::showTrainerContent));
        sidebar.add(createSidebarButton("Workout", VaadinIcon.BOLT, this::showWorkoutContent));
        sidebar.add(createSidebarButton("Plan", VaadinIcon.BAR_CHART_H, this::showPlanContent));

        return sidebar;
    }

    private Button createSidebarButton(String text, VaadinIcon icon, Runnable action) {
        Button button = new Button(text, new Icon(icon));
        button.setWidthFull();
        button.addClickListener(e -> action.run());
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

    private void showHomeContent() {
        mainContent.removeAll();

        // Title
        Span title = new Span("Welcome to Fittrack");
        title.getStyle()
                .set("font-size", "28px")
                .set("font-weight", "bold")
                .set("color", "#333");

        // User Info Section
        VerticalLayout userInfoLayout = new VerticalLayout();
        userInfoLayout.getStyle()
                .set("background-color", "#f9f9f9")
                .set("padding", "20px")
                .set("border-radius", "10px")
                .set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");

        Span userName = new Span("Name: User1");
        userName.getStyle()
                .set("font-size", "18px")
                .set("font-weight", "bold")
                .set("color", "#444");

        Span userEmail = new Span("Email: user1@gmail.com");
        userEmail.getStyle()
                .set("font-size", "16px")
                .set("color", "#666");

        Span userAge = new Span("Age: 27");
        userAge.getStyle()
                .set("font-size", "16px")
                .set("color", "#666");

        Span userHeight = new Span("Height: 160 cm");
        userHeight.getStyle()
                .set("font-size", "16px")
                .set("color", "#666");

        Span userWeight = new Span("Weight: 70 kg");
        userWeight.getStyle()
                .set("font-size", "16px")
                .set("color", "#666");

        Span userGender = new Span("Gender: Female");
        userGender.getStyle()
                .set("font-size", "16px")
                .set("color", "#666");

        Span userGoal = new Span("Goal: Fat Loss");
        userGoal.getStyle()
                .set("font-size", "16px")
                .set("color", "#666");

        userInfoLayout.add(userName, userEmail, userAge, userHeight, userWeight, userGender, userGoal);

        // Add components to the main layout
        mainContent.add(title, userInfoLayout);
    }



    private void showTrainerContent() {
        mainContent.removeAll();

        // Title Section
        Span title = new Span("Trainer Profile");
        title.getStyle()
                .set("font-size", "28px")
                .set("font-weight", "bold")
                .set("color", "#333")
                .set("margin-bottom", "20px");

        // Trainer Details Section
        VerticalLayout trainerDetails = new VerticalLayout();
        trainerDetails.getStyle()
                .set("background-color", "#f9f9f9")
                .set("border", "1px solid #ddd")
                .set("border-radius", "10px")
                .set("padding", "20px")
                .set("margin-bottom", "20px");

        Span trainerName = new Span("Trainer 1: John Doe");
        trainerName.getStyle()
                .set("font-size", "20px")
                .set("font-weight", "bold")
                .set("color", "#555");

        Span expertise = new Span("Expertise: Strength Training");
        expertise.getStyle()
                .set("font-size", "16px")
                .set("color", "#666");

        Span experience = new Span("Experience: 5 years");
        experience.getStyle()
                .set("font-size", "16px")
                .set("color", "#666");

        trainerDetails.add(trainerName, expertise, experience);

        // Assigned Workouts Section
        Span workoutTitle = new Span("Assigned Workouts");
        workoutTitle.getStyle()
                .set("font-size", "22px")
                .set("font-weight", "bold")
                .set("margin-top", "20px")
                .set("margin-bottom", "10px");

        // Workout Grid
        Grid<Workout> workoutGrid = new Grid<>(Workout.class, false);
        workoutGrid.addColumn(Workout::getExercise).setHeader("Exercise").setAutoWidth(true);
        workoutGrid.setItems(trainerWorkouts);
        workoutGrid.getStyle()
                .set("border", "1px solid #ddd")
                .set("border-radius", "5px")
                .set("box-shadow", "0 2px 5px rgba(0, 0, 0, 0.1)");

        // Sync Trainer's Workouts
        trainerWorkouts.clear();
        trainerWorkouts.add(new Workout("Push-ups", "4 sets"));
        trainerWorkouts.add(new Workout("Squats", "3 sets"));
        trainerWorkouts.add(new Workout("Plank", "2 sets"));
        workoutGrid.setItems(trainerWorkouts);

        // Add Components to Main Content
        mainContent.add(title, trainerDetails, workoutTitle, workoutGrid);
    }


    private void showWorkoutContent() {
        mainContent.removeAll();

        // Title
        Span title = new Span("Assigned Workouts");
        title.getStyle()
                .set("font-size", "28px")
                .set("font-weight", "bold")
                .set("color", "#333")
                .set("margin-bottom", "20px");

        // Description
        Span description = new Span("Here are the workouts assigned by Trainer 1. Follow these regularly to achieve your fitness goals.");
        description.getStyle()
                .set("font-size", "16px")
                .set("color", "#555")
                .set("line-height", "1.6")
                .set("margin-bottom", "20px");

        // Grid for workouts
        Grid<Workout> workoutGrid = new Grid<>(Workout.class, false);
        workoutGrid.addColumn(Workout::getExercise)
                .setHeader("Exercise")
                .setAutoWidth(true)
                .setTextAlign(ColumnTextAlign.START);
        workoutGrid.addColumn(Workout::getRepetitions)
                .setHeader("Repetitions")
                .setAutoWidth(true)
                .setTextAlign(ColumnTextAlign.END);

        workoutGrid.setItems(trainerWorkouts); // Assuming `trainerWorkouts` is populated with data

        // Styling for the grid
        workoutGrid.getStyle()
                .set("border", "1px solid #ddd")
                .set("border-radius", "8px")
                .set("background-color", "#fff")
                .set("box-shadow", "0 4px 8px rgba(0, 0, 0, 0.1)");

        // Create a "Back to Trainer" Button
        Button backButton = new Button("Back to Trainer");
        backButton.getStyle()
                .set("background-color", "#007bff")
                .set("color", "#fff")
                .set("border", "none")
                .set("padding", "10px 20px")
                .set("border-radius", "5px")
                .set("cursor", "pointer");
        backButton.addClickListener(e -> showTrainerContent()); // Navigate back to Trainer content

        // Add all components to the main content
        mainContent.add(title, description, workoutGrid, backButton);
        mainContent.setSpacing(true);
        mainContent.setPadding(true);
    }


    private void showPlanContent() {
        mainContent.removeAll();

        // Title
        Span title = new Span("Weight Loss Plan");
        title.getStyle()
                .set("font-size", "28px")
                .set("font-weight", "bold")
                .set("color", "#333")
                .set("margin-bottom", "20px");

        // Description
        Span description = new Span(
                "This plan is designed to help you lose 5kg in 30 days with a combination of tailored workouts, balanced diet plans, and progress tracking.");
        description.getStyle()
                .set("font-size", "18px")
                .set("color", "#666")
                .set("line-height", "1.6")
                .set("margin-bottom", "30px");

        // Sub-section: Workouts
        Span workoutsTitle = new Span("Workouts Included:");
        workoutsTitle.getStyle()
                .set("font-size", "20px")
                .set("font-weight", "bold")
                .set("margin-top", "20px")
                .set("color", "#444");

        VerticalLayout workoutsList = new VerticalLayout();
        workoutsList.getStyle()
                .set("background-color", "#f9f9f9")
                .set("padding", "15px")
                .set("border-radius", "8px")
                .set("margin-top", "10px");
        workoutsList.add(new Span("1. Cardio - 30 minutes of brisk walking or running daily."),
                new Span("2. Strength Training - Full-body resistance exercises twice a week."),
                new Span("3. Yoga - Focused on flexibility and core strengthening."));

        // Sub-section: Diet Plan
        Span dietPlanTitle = new Span("Diet Recommendations:");
        dietPlanTitle.getStyle()
                .set("font-size", "20px")
                .set("font-weight", "bold")
                .set("margin-top", "30px")
                .set("color", "#444");

        VerticalLayout dietPlanList = new VerticalLayout();
        dietPlanList.getStyle()
                .set("background-color", "#f9f9f9")
                .set("padding", "15px")
                .set("border-radius", "8px")
                .set("margin-top", "10px");
        dietPlanList.add(new Span("1. High protein breakfast: Eggs, oats, and fresh fruits."),
                new Span("2. Balanced lunch: Grilled chicken or fish with vegetables."),
                new Span("3. Light dinner: Soups, salads, or a healthy smoothie."));

        // Add Components to Main Content
        mainContent.add(title, description, workoutsTitle, workoutsList, dietPlanTitle, dietPlanList);
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



    // Dummy classes for demonstration
    public static class User {
        private final String name;
        private final String email;
        private final String goal;

        public User(String name, String email, String goal) {
            this.name = name;
            this.email = email;
            this.goal = goal;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getGoal() {
            return goal;
        }
    }




    public static class Workout {
        private final String exercise;
        private final String repetitions;

        public Workout(String exercise, String repetitions) {
            this.exercise = exercise;
            this.repetitions = repetitions;
        }

        public String getExercise() {
            return exercise;
        }

        public String getRepetitions() {
            return repetitions;
        }

    }
}
