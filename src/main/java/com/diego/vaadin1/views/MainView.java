package com.diego.vaadin1.views;

import java.text.MessageFormat;

import com.diego.vaadin1.moodle.Student;
import com.diego.vaadin1.services.StudentService;
import com.diego.vaadin1.services.UserServiceImpl;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

import jakarta.annotation.security.PermitAll;

/**
 * The main view of the application.
 */
@Route(value = "/")
@PageTitle(value = "Home")
@PermitAll
public class MainView extends VerticalLayout {
    
    /**
     * The student service for performing student operations.
     */
    private final StudentService studentS;
    
    /**
     * The grid for displaying students.
     */
    private Grid<Student> grid;
    
    /**
     * The logo layout for displaying the application logo.
     */
    private LogoLayout logoLayout;
    
    /**
     * The text field for filtering students by name.
     */
    private TextField filterField;
    
    /**
     * The checkbox for toggling the theme.
     */
    private Checkbox themeToggle;
    
    /**
     * A flag for tracking the theme toggle state.
     */
    private static boolean isChecked; 
    
    /**
     * The user service for performing user operations.
     */
    private UserServiceImpl userService; 
    
    /**
     * Constructor for the main view.
     * 
     * @param studentS the student service.
     */
    public MainView(StudentService studentS) {
        this.studentS = studentS;
        this.userService = new UserServiceImpl();
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        createFieldsVariables();
        configurateGrid();

        add(logoLayout, createToolbar(), grid);
        loadStudents();
    }

    /**
     * Creates a checkbox for toggling the theme.
     * 
     * @return the theme toggle checkbox.
     */
    private Checkbox createToogle() {
        this.themeToggle = new Checkbox("Dark Box");
        this.themeToggle.setValue(isChecked );
        this.themeToggle.addValueChangeListener(e->{
            MainView.isChecked =!isChecked;
            setTheme(isChecked);
        });
        return this.themeToggle;
    }

    /**
     * Sets the theme of the application based on the toggle state.
     * 
     * @param dark whether to set the dark theme.
     */
    private void setTheme(boolean dark) {
        var js = MessageFormat.format("""
                document.documentElement.setAttribute("theme", "{0}")
            """, dark ? Lumo.DARK : Lumo.LIGHT);
        // execute java script
        getElement().executeJs(js);
    }

    /**
     * Creates the toolbar with filtering, adding, deleting, and logging out functionality.
     * 
     * @return the toolbar.
     */
    private Component createToolbar() {
        filterField.setPlaceholder("Filter by name...");
        filterField.setClearButtonVisible(true);
        filterField.setValueChangeMode(ValueChangeMode.LAZY);
        filterField.addValueChangeListener(e -> updateStudents());

        Button addStudentButton = new Button("Add Student");
        addStudentButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("add-student")));
        addStudentButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY ,
                ButtonVariant.LUMO_SUCCESS);
        
        Button deleteStudentButton = new Button("Delete Student");
        deleteStudentButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate("delete-student")));
        deleteStudentButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
                ButtonVariant.LUMO_ERROR);
        
        Button logout = new Button("Logout"); 
        logout.addClickListener(e->userService.logout()); 		
        logout.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        return new HorizontalLayout(filterField, addStudentButton, deleteStudentButton
                ,logout,createToogle());
    }

    /**
     * Updates the students in the grid based on the filter value.
     */
    private void updateStudents() {
        grid.setItems(studentS.find(filterField.getValue()));

    }

    /**
     * Configures the grid for displaying students.
     */
    private void configurateGrid() {
        grid.setSizeFull();
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES,
                GridVariant.LUMO_COLUMN_BORDERS);
        grid.setColumns("country", "zipcode");
        grid.addColumn(s -> s.getName()).setHeader("Name");
        grid.addColumn(s -> s.getAge()).setHeader("Age");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.addComponentColumn(s -> {
            Icon icon;
            if (s.getStatus().getName().equals("ACTIVE")) {
                icon = VaadinIcon.CIRCLE.create();
                icon.setColor("green");
            } else if (s.getStatus().getName().equals("PASSIVE")) {
                icon = VaadinIcon.CLOSE_CIRCLE.create();
                icon.setColor("red");
            } else {
                icon = VaadinIcon.CHECK_CIRCLE.create();
                icon.setColor("orange");
            }
            return icon;
        }).setHeader("Status");
    }

    /**
     * Loads all students into the grid.
     */
    private void loadStudents() {
        grid.setItems(studentS.findAll());
    }

    /**
     * Creates the field variables for the view.
     */
    private void createFieldsVariables() {
       this.logoLayout = new LogoLayout();
        this.grid = new Grid<>(Student.class);
        this.filterField = new TextField();
    }
}