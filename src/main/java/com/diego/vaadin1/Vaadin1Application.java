package com.diego.vaadin1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@SpringBootApplication
@Theme(themeClass = Lumo.class)
public class Vaadin1Application implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(Vaadin1Application.class, args);
	}


}
