package com.diego.vaadin1.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.login.LoginForm;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

/**
 * The login view component.
 * 
 * This view is responsible for displaying the login form and handling the login process.
 * 
 */
@Route(value="login")
@PageTitle("Login")
@AnonymousAllowed
public class LoginView extends VerticalLayout {
    /**
     * The sign up button.
     */
    private Button signup;
    
    /**
     * The login form component.
     */
    private LoginForm login; 
    
    /**
     * Constructor for the login view.
     */
    public LoginView() { 
        /**
         * Create a new sign up button with the label "Sign Up" and add the LUMO_TERTIARY theme variant.
         */
        this.signup = new Button("Sign Up");
        this.signup.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        
        /**
         * Set the layout to full size and center the content.
         */
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        
        /**
         * Create a new login form component.
         */
        this.login = new LoginForm();
        
        /**
         * Add the buttons to the layout.
         */
        addButtons() ;
        
        /**
         * Add the login form and sign up button to the layout.
         */
        add(login, signup);
    }
    
    /**
     * Method to add the buttons to the layout.
     */
    private void addButtons() {
        /**
         * Hide the forgot password button on the login form.
         */
        login.setForgotPasswordButtonVisible(false);
        
        /**
         * Set the action for the login form to "login".
         */
        login.setAction("login");
        
        /**
         * Add a click listener to the sign up button to navigate to the sign up page.
         */
        signup.addClickListener(e->signup()); 
    }
    
    /**
     * Method to handle the sign up button click event.
     */
    private void signup() {
        /**
         * Navigate to the sign up page.
         */
        getUI().ifPresent(ui -> ui.navigate("/signup"));
    }
}

