package com.gmail.emmanuel.UI;

import com.gmail.emmanuel.Application;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.ListView;

@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("frontend://styles/styles.css")
public class MainView extends VerticalLayout {

    public MainView() {
//        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        addClassName("main-view");
        H1 header = new H1("WatchMe");
        header.getElement().getThemeList().add("dark");
        add(header);

        askUserInfo();




    }

    private void askUserInfo() {
        VerticalLayout startView = new VerticalLayout();
        TextField nameField = new TextField("Your name");
        TextField roomNumFiled = new TextField("Room Number");
        Button startButton = new Button("Enter the Room");
        startButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        startButton.addClickShortcut(Key.ENTER);
        startView.add(nameField, roomNumFiled, startButton);
        startView.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(startView);

        startButton.addClickListener(buttonClickEvent -> {
            user = nameField.getValue();
            roomNum = roomNumFiled.getValue();
            removeAll();
            MainLayout a = new MainLayout();
            add(a);
        });
    }

    public static String getUser() {
        return user;
    }

    static String user;
    static String roomNum;

}
