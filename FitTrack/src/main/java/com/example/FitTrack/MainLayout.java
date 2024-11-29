package com.example.FitTrack;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;


@Route("Nav")
public class MainLayout extends HorizontalLayout {
    public MainLayout() {
        // Use RouterLink for navigation
        RouterLink homeLink = new RouterLink("Home", Home.class);
        RouterLink aboutLink = new RouterLink("About", Home.class);

        add(homeLink, aboutLink);
    }
}