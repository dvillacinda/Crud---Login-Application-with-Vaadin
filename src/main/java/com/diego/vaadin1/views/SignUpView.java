package com.diego.vaadin1.views;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.diego.vaadin1.moodle.Role;
import com.diego.vaadin1.moodle.User;
import com.diego.vaadin1.services.RoleService;
import com.diego.vaadin1.services.UserServiceImpl;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

/**
 * The sign up view for registering new users.
 */
@Route(value = "signup")
@PageTitle("Register")
@AnonymousAllowed
public class SignUpView extends VerticalLayout {
	/**
	 * The H1 field for the title of the page 
	 */
    private H1 titH1; 
    /**
     * The text field for entering the username.
     */
    private TextField username;
    
    /**
     * The password field for entering the password.
     */
    private PasswordField password;
    
    /**
     * The password field for confirming the password.
     */
    private PasswordField password2;
    
    /**
     * The button for saving the user.
     */
    private Button save;
    
    /**
     * The button for closing the view.
     */
    private Button close;
    
    /**
     * The combo box for selecting the user role.
     */
    private ComboBox<Role> role;

    /**
     * The user service for performing user operations.
     */
    private final UserServiceImpl userService;
    
    /**
     * The role service for performing role operations.
     */
    private final RoleService roleService;

    /**
     * The binder for binding the user fields.
     */
    private Binder<User> binder;
    
    /**
     * The user object being created.
     */
    private User user;
    
    /**
     * The password encoder for encoding passwords.
     */
    private PasswordEncoder passwordEncoder;  

    /**
     * Constructor for the sign up view.
     * 
     * @param userService the user service.
     * @param roleService the role service.
     */
    public SignUpView(UserServiceImpl userService, RoleService roleService) {
        super();
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = new BCryptPasswordEncoder();
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        createFields();
        createRole();
        createBinder();
        add(createFormLayout());
    }

    /**
     * Creates the fields for the sign up form.
     */
    private void createFields() {
        this.username = new TextField("Username");
        this.password = new PasswordField("Password");
        this.password2 = new PasswordField("Confirm Password");

        this.close = new Button("Close");
        this.save = new Button("Save");

        this.role = new ComboBox<Role>("Role");
		this.titH1 = new H1("Sign Up"); 
    }

    /**
     * Creates the role combo box.
     */
    private void createRole() {
        List<Role> roleItems = roleService.findAll();
        role.setItems(roleItems);
        role.setValue(roleItems.get(0));
        role.setItemLabelGenerator(Role::getName);
    }

    /**
     * Creates the binder for binding the user fields.
     */
    private void createBinder() {
        this.user = new User();
        binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);
    }

    /**
     * Creates the form layout for the sign up form.
     * 
     * @return the form layout.
     */
    private Component createFormLayout() {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.add(this.titH1,this.username, this.password, this.password2,this.role,createButtons());
        formLayout.setAlignItems(Alignment.CENTER);
        formLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        return formLayout;
    }

    /**
     * Creates the buttons for the sign up form.
     * 
     * @return the buttons.
     */
    private Component createButtons() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        close.addClickListener(e -> closeView());
        save.addClickListener(e-> saveUser()); 
        
        return new HorizontalLayout(save, close); 
    }
    
    /**
     * Saves the user.
     */
    private void saveUser() {
        if(password.getValue().equals(password2.getValue())) {
            try {
                password.setValue(passwordEncoder.encode(password.getValue()));
                binder.writeBean(user);
                userService.save(user);
                clearFields();
                Notification notification = Notification.show("Student save succefully");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.setPosition(Position.TOP_CENTER);
            }catch(ValidationException e) {
                e.printStackTrace();
                Notification notification = Notification.show("Passwords do not match");
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Position.TOP_CENTER);
            }
        }else {
            Notification notification = Notification.show("Passwords do not match");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setPosition(Position.TOP_CENTER);
        }
    }
    
    /**
     * Clears the fields after saving the user.
     */
    private void clearFields() {
        user = new User();
        role.setValue(roleService.findAll().get(0));
        binder.getFields().forEach(HasValue::clear);
        password2.clear();
    }

    /**
     * Closes the view and navigates to the login view.
*/
    private void closeView() {
        getUI().ifPresent(ui -> ui.navigate("login"));
    }
}