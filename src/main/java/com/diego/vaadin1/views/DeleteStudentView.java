package com.diego.vaadin1.views;

import java.util.HashSet;
import java.util.Set;

import com.diego.vaadin1.moodle.Student;
import com.diego.vaadin1.services.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.data.selection.SelectionListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

/**
 * View for deleting students.
 */
@PageTitle("Delete Students")
@Route(value = "/delete-student")
//@PermitAll
@RolesAllowed({"ADMIN"})
public class DeleteStudentView extends VerticalLayout 
						implements SelectionListener<Grid<Student>, Student>{
    
    /**
     * Grid for displaying students.
     */
    private Grid<Student> grid;
    
    /**
     * Service for student operations.
     */
    private final StudentService studentS;
    
    /**
     * Button for deleting selected students.
     */
    private Button delete;
    
    /**
     * Button for canceling the operation.
     */
    private Button cancel;
    
    /**
     * Set of selected students.
     */
    private Set<Student> selected; 
    
    /**
     * Constructor for the view.
     * 
     * @param studentS the student service.
     */
    public DeleteStudentView(StudentService studentS) {
        this.selected = new HashSet<Student>();
        this.studentS = studentS;
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        createFieldVariables();
        configurateGrid();
        
        add(grid, createButtonLayout());
        loadStudents(); 
    }

    /**
     * Loads all students into the grid.
     */
    private void loadStudents() {
        grid.setItems(studentS.findAll());
        grid.setSelectionMode(SelectionMode.MULTI); 
        grid.addSelectionListener(this);
    }

    /**
     * Creates the button layout for deleting and canceling.
     * 
     * @return the button layout.
     */
    private Component createButtonLayout() {
        cancel.addClickListener(e->closeView()); 
        delete.addClickListener(e->deleteSelectedItemes()); 
        return new HorizontalLayout(delete, cancel);
    }

    /**
     * Deletes the selected students.
     */
    private void deleteSelectedItemes() {
        selected.forEach(studentS :: remove);
        Notification notification = Notification.show(
				  "Student(s) deleted successfully"); 
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        loadStudents();
    }

    /**
     * Closes the view.
     */
    private void closeView() {
        getUI().ifPresent(ui->ui.navigate(""));
    }

    /**
     * Creates the field variables for the view.
     */
    private void createFieldVariables() {
        this.grid = new Grid<Student>(Student.class);
        this.cancel = new Button("Cancel");
        this.delete = new Button("Delete");
        this.delete.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }

    /**
     * Configures the grid for displaying students.
     */
    private void configurateGrid() {
        grid.setSizeFull();
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

    @Override
    public void selectionChange(SelectionEvent<Grid<Student>, Student> event) {
        selected = event.getAllSelectedItems(); 
    }
}