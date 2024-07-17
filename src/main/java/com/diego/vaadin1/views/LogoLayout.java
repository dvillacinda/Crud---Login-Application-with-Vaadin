package com.diego.vaadin1.views;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class LogoLayout extends HorizontalLayout{
	
	private Image image;

	public LogoLayout() {
		image = new Image("images/techno-logo.jpg","My Logo"); 
		setJustifyContentMode(JustifyContentMode.CENTER); 
		image.setHeight("200px"); 
		image.setWidth("100%"); 
		add(image); 
	} 
	

}
