package com.gmail.emmanuel.UI;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import java.awt.*;
import java.time.LocalDate;
import java.util.Calendar;

@Route(value = "Task", layout = MainLayout.class)
@PageTitle("Task | Menu")
public class TaskView extends VerticalLayout {


    TaskView() {
        createAddBar();
    }

    private void createAddBar() {
        HorizontalLayout addLayout = new HorizontalLayout();
        addLayout.setWidth("100%");
        TextField addFiled = new TextField("Create new Task");
        Button addTask = new Button("Add");
        addTask.setThemeName(ButtonVariant.LUMO_PRIMARY.getVariantName());
        addTask.addClickShortcut(Key.ENTER);
        addLayout.expand(addFiled);
        addLayout.add(addFiled, addTask);
        addLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        add(addLayout);

        ;

        addTask.addClickListener(click -> {
            taskContent = addFiled.getValue();
            createNewTask();
        });

    }

    private void createNewTask() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("50%");

        TextField messageField = new TextField();
        messageField.setValue(taskContent);
        Button Done = new Button("Done");
        Done.setThemeName(ButtonVariant.LUMO_PRIMARY.getVariantName());
        Done.addClickShortcut(Key.ENTER);
        layout.add(messageField, Done);
        layout.expand(messageField);

        add(layout);
        Done.addClickListener(click -> {
        });


    }

    private static String taskContent;
    private String username;
}
