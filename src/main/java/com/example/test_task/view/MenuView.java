package com.example.test_task.view;

import com.example.test_task.dto.CoffeeMachineOperationDto;
import com.example.test_task.service.impl.CoffeeMachineServiceImpl;
import com.example.test_task.utils.CoffeeTypes;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route("menu")
public class MenuView extends VerticalLayout {

    public MenuView(CoffeeMachineServiceImpl coffeeMachineService) {
        add(new H3("Coffee machine menu"));
        for(CoffeeTypes type : CoffeeTypes.values()) {
            add (new HorizontalLayout() {{
                add(new H6(type.name()));
                add(new Button("Brew") {{
                    addClickListener(click ->  {
                        Notification.show(coffeeMachineService
                                        .createNewOperation(new CoffeeMachineOperationDto(type.name()))
                                , 1000
                                , Notification.Position.TOP_CENTER);
                        getUI().ifPresent(ui -> ui.navigate(""));
                    });
                }});
            }});
        }

        add(new Button("Start page") {{
            addClickListener(click -> getUI().ifPresent(ui -> ui.navigate("")));
        }});
    }
}
