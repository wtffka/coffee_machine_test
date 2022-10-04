package com.example.test_task.view;

import com.example.test_task.model.CoffeeMachine;
import com.example.test_task.service.impl.CoffeeMachineServiceImpl;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class StartPageView extends VerticalLayout {

    private static CoffeeMachine coffeeMachine = new CoffeeMachine(false);

    public StartPageView(CoffeeMachineServiceImpl coffeeMachineService) {

        add(new H3("Welcome to coffee machine start page!"));
        add (new HorizontalLayout() {{
            add(new Button("On/off") {{
                addClickListener(click -> {
                    Notification.show(coffeeMachineService.turnOnOffCoffeeMachine(coffeeMachine),
                            1000,
                            Notification.Position.TOP_CENTER);
                    UI.getCurrent().getPage().reload();
                });
            }});

            add(new Button("Menu") {{
                if (!coffeeMachine.isOn()) {
                    setEnabled(false);
                } else {
                    setEnabled(true);
                    addClickListener(click -> getUI().ifPresent(ui -> ui.navigate("menu")));
                }
            }});

            add(new Button("Log") {{
                addClickListener(click -> getUI().ifPresent(ui -> ui.navigate("log")));
            }});
        }});
    }
}
