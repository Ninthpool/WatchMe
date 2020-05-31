package com.gmail.emmanuel.UI;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.awt.*;
//import java.util.concurrent.SubmissionPublisher;

@Route(value = "Chat", layout = MainLayout.class) 
@PageTitle("Chat | Menu")
public class ChatView extends VerticalLayout {

    private final UnicastProcessor<ChatMessage> publisher;
    private final Flux<ChatMessage> messages;
    private String username = MainView.getUser();

    public ChatView(UnicastProcessor<ChatMessage> publisher,
                    Flux<ChatMessage> messages) {
        this.publisher = publisher;
        this.messages = messages;
        
        startChat();
        createInputLayout();
    }

    private void startChat() {
        MessageList messageList = new MessageList();
        VerticalLayout v = new VerticalLayout();
        v.add(messageList, createInputLayout());
        expand(messageList);
        add(v);


        messages.subscribe(m -> {
            getUI().ifPresent(ui -> ui.access(() -> messageList.add(new Paragraph(m.getFrom() + ": " + m.getMessage()))));
        });


    }

    private Component createInputLayout() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");

        TextField messageField = new TextField();
        Button send = new Button("Send");
        send.setThemeName(ButtonVariant.LUMO_PRIMARY.getVariantName());
        send.addClickShortcut(Key.ENTER);

        send.addClickListener(click -> {
            publisher.onNext(new ChatMessage(username, messageField.getValue()));

            messageField.clear();
            messageField.focus();
        });
        messageField.focus();

        layout.add(messageField, send);
        layout.expand(messageField);
        return layout;
    }
}
