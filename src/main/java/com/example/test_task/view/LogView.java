package com.example.test_task.view;

import com.example.test_task.model.CoffeeMachineOperation;
import com.example.test_task.service.impl.CoffeeMachineServiceImpl;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("log")
public class LogView extends VerticalLayout {

    private Grid<CoffeeMachineOperation> grid = new Grid<>(CoffeeMachineOperation.class);

    public LogView(CoffeeMachineServiceImpl coffeeMachineService) {

        TextField textField1 = new TextField();
        textField1.setLabel("Input coffee type");

        TextField textField2 = new TextField();
        textField2.setLabel("Input operation id");

        TextField textField3 = new TextField();
        textField3.setLabel("Input operation id to delete");

        TextField textField4 = new TextField();
        textField4.setLabel("Input coffee type to delete");

        add(new HorizontalLayout() {{
            add(new VerticalLayout() {{
                add(textField1, new Button("Search") {{
                    addClickListener(click -> grid.setItems(coffeeMachineService
                            .getOperationsByCoffeeType(textField1.getValue().toUpperCase())));
                    add(grid);
                }});
            }});

            add(new VerticalLayout() {{
                add(textField2, new Button("Search") {{
                    addClickListener(click -> grid.setItems(coffeeMachineService
                            .getOperationById(Long.valueOf(textField2.getValue()))));
                    add(grid);
                }});
            }});

            add(new VerticalLayout() {{
                add(textField3, new Button("Execute") {{
                    addClickListener(click -> {
                        addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
                        coffeeMachineService.deleteById(Long.valueOf(textField3.getValue()));
                        grid.setItems(coffeeMachineService.getAll());
                    });
                    add(grid);
                }});
            }});

            add(new VerticalLayout() {{
                add(textField4, new Button("Execute") {{
                    addClickListener(click -> {
                        addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
                        coffeeMachineService.deleteByCoffeeType(textField4.getValue().toUpperCase());
                        grid.setItems(coffeeMachineService.getAll());
                    });
                    add(grid);
                }});
            }});
        }});

        grid.setItems(coffeeMachineService.getAll());
        add(grid);

        HorizontalLayout layout = new HorizontalLayout();
        layout.add(new Button("All operations") {{
            addClickListener(click -> grid.setItems(coffeeMachineService.getAll()));
            add(grid);
        }}, new Button("Start page") {{

            addClickListener(click -> getUI().ifPresent(ui -> ui.navigate("")));
        }}, new Button("Delete all") {{
            addClickListener(click -> {
                addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);
                coffeeMachineService.deleteAll();
                grid.setItems(coffeeMachineService.getAll());
            });
        }});
        add(layout);

    }
}
