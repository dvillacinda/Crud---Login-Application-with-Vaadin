package com.diego.vaadin1.views;

import java.util.List;

import com.diego.vaadin1.moodle.Status;
import com.diego.vaadin1.moodle.Student;
import com.diego.vaadin1.services.StatusService;
import com.diego.vaadin1.services.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;


/**
 * View for adding a new student.
 */
@PageTitle("Add Students")
@Route(value = "/add-student")
@PermitAll
public class AddStudentView extends VerticalLayout {
    
    /**
     * Text field for student's age.
     */
    private TextField age;
    
    /**
     * Text field for student's name.
     */
    private TextField name;
    
    /**
     * Text field for student's country.
     */
    private TextField country;
    
    /**
     * Text field for student's zipcode.
     */
    private TextField zipcode;
    
    /**
     * Combo box for student's status.
     */
    private ComboBox<Status> status;
    
    /**
     * Logo layout for the view.
     */
    private LogoLayout image;

    /**
     * Button for saving the student.
     */
    private Button save;
    
    /**
     * Button for closing the view.
     */
    private Button close;

    /**
     * Service for status operations.
     */
    private final StatusService statusS;
    
    /**
     * Service for student operations.
     */
    private final StudentService studentS;

    /**
     * The student being added.
     */
    private Student student;
    
    /**
     * Binder for validating and binding the student fields.
     */
    private Binder<Student> binder;

    /**
     * Constructor for the view.
     * 
     * @param statusS the status service.
     * @param studentS the student service.
     */
    public AddStudentView(StatusService statusS, StudentService studentS) {
        this.statusS = statusS;
        this.studentS = studentS;

        setAlignItems(Alignment.CENTER);
        createVariables();
        createStatus();
        createBinder();
        add(image);	
        add(createFormLayout());
    }

    /**
     * Creates the binder for the student fields.
     */
    private void createBinder() {
        this.student = new Student();
        binder = new BeanValidationBinder<>(Student.class);
        binder.bindInstanceFields(this);
    }

    /**
     * Creates the combo box for the student's status.
     */
    private void createStatus() {
        List<Status> statusItems = statusS.findAll();
        status.setItems(statusItems); 
        status.setValue(statusItems.get(0));
        status.setItemLabelGenerator(Status::getName);
    }

    /**
     * Creates the form layout for the student fields.
     * 
     * @return the form layout.
     */
    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(this.name, this.age, this.country, this.zipcode, this.status, createButton());
        formLayout.setColspan(image, 2);
        formLayout.setColspan(name, 2);
        return formLayout;
    }

    /**
     * Creates the buttons for saving and closing.
     * 
     * @return the button layout.
     */
    private Component createButton() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        close.addClickListener(e -> closeView());
        save.addClickListener(e -> saveStudent());

        return new HorizontalLayout(save, close);
    }

    /**
     * Saves the student to the database.
     */
    private void saveStudent() {
        try {
            binder.writeBean(student);
            studentS.save(student);
            clearFields();
            Notification notification = Notification.show("Student saved successfully");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setPosition(Position.TOP_CENTER);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the fields after saving.
     */
    private void clearFields() {
        student = new Student();
        status.setValue(statusS.findAll().get(0));
        binder.getFields().forEach(HasValue::clear);
    }

    /**
     * Closes the view.
     */
    private void closeView() {
        getUI().ifPresent(ui -> ui.navigate(""));
    }

    /**
     * Creates the variables for the view.
     */
    private void createVariables() {
        this.age = new TextField("Age");
        this.name = new TextField("Name");
        this.country = new TextField("Country");
        this.zipcode = new TextField("Zipcode");
        this.status = new ComboBox<Status>("Status");
        this.image = new LogoLayout();

        this.save = new Button("Save");
        this.close = new Button("Close");
    }
}