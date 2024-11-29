package com.example.FitTrack;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("Trainer1")
public class Trainer1 extends HorizontalLayout {

    public Trainer1() {
        // Sidebar
        VerticalLayout sidebar = createSidebar();

        // Main Content
        VerticalLayout mainContent = new VerticalLayout();
        mainContent.setWidth("80%");
        mainContent.add(createExercisesSection(), createLogsSection());

        // Add everything to the layout
        add(sidebar, mainContent);
        setSizeFull();
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

        Button home = new Button("Home");
        Button calendar = new Button("Calendar");
        Button workouts = new Button("Workouts");
        Button groups = new Button("Groups");
        Button subscription = new Button("Subscription");

        // Style buttons
        for (Button button : new Button[]{home, calendar, workouts, groups, subscription}) {
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

        sidebar.add(title, home, calendar, workouts, groups, subscription);
        return sidebar;
    }

    private VerticalLayout createExercisesSection() {
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

        return exercisesSection;
    }

    private Div createExerciseItem(String name, String details) {
        Div exerciseItem = new Div();
        Span exerciseName = new Span(name);
        exerciseName.getStyle()
                .set("font-size", "18px")
                .set("font-weight", "bold");
        Span exerciseDetails = new Span(details);
        exerciseDetails.getStyle()
                .set("font-size", "14px")
                .set("color", "#666");

        exerciseItem.add(exerciseName, new Span(" - "), exerciseDetails);
        exerciseItem.getStyle()
                .set("display", "flex")
                .set("justify-content", "space-between")
                .set("padding", "10px")
                .set("border", "1px solid #ddd")
                .set("border-radius", "4px")
                .set("margin-bottom", "10px");

        return exerciseItem;
    }

    private VerticalLayout createLogsSection() {
        VerticalLayout logsSection = new VerticalLayout();
        Span title = new Span("Logs");
        title.getStyle()
                .set("font-size", "22px")
                .set("font-weight", "bold");

        // Example logs
        logsSection.add(title);
        logsSection.add(createLogItem("Squat", "10 reps"));
        logsSection.add(createLogItem("Pushup", "10 reps"));
        logsSection.add(createLogItem("Plank", "10 reps"));

        // Add buttons
        Button newWorkout = new Button("New workout");
        newWorkout.getStyle()
                .set("background-color", "#28a745")
                .set("color", "white")
                .set("border", "none")
                .set("border-radius", "4px")
                .set("padding", "10px 20px");

        Button logSet = new Button("Log your set");
        logSet.getStyle()
                .set("background-color", "#f8f9fa")
                .set("color", "#555")
                .set("border", "1px solid #ddd")
                .set("border-radius", "4px")
                .set("padding", "10px 20px");

        logsSection.add(newWorkout, logSet);
        return logsSection;
    }

    private Div createLogItem(String name, String details) {
        Div logItem = new Div();
        Span logName = new Span(name);
        logName.getStyle()
                .set("font-size", "18px")
                .set("font-weight", "bold");
        Span logDetails = new Span(details);
        logDetails.getStyle()
                .set("font-size", "14px")
                .set("color", "#666");

        logItem.add(logName, new Span(" - "), logDetails);
        logItem.getStyle()
                .set("display", "flex")
                .set("justify-content", "space-between")
                .set("padding", "10px")
                .set("border", "1px solid #ddd")
                .set("border-radius", "4px")
                .set("margin-bottom", "10px");

        return logItem;
    }
}